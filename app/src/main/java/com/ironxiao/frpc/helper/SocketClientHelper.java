package com.ironxiao.frpc.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.ironxiao.frpc.sql.HistoryDBManager;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SocketClientHelper {
    private static final String TAG = "--zwh-- SocketClientHelper";
    private static SocketClientHelper instance;

    public static SocketClientHelper Instance() {
        if (instance == null) {
            instance = new SocketClientHelper();
        }
        return instance;
    }

    private final String SharedPreferencesKey = "socketInfo";
    private final String SocketIpKey = "socketIP";
    private final String SocketPortKey = "socketPort";

    private Socket client_ = null;
    private Thread thread_ = null;
    private int overTime = 5; // 秒
    private int checkResponseSeconds_;
    private String serverIp_;
    private int serverPort_;
    private String cmd_;

    public void ReStart(Context context) {
        if (!TextUtils.isEmpty(GetSocketIp(context)) && !TextUtils.isEmpty(GetSocketPort(context))
                && !TextUtils.isEmpty(GasInfoHelper.Instance().GetCommand(context))) {
            serverIp_ = GetSocketIp(context);
            serverPort_ = Integer.parseInt(GetSocketPort(context));
            cmd_ = GasInfoHelper.Instance().GetCommand(context);
            RunTick();
        }
        ProjectUtils.AnalysisGasBaseInfoList(context);
    }

    private void StartSocket() throws IOException {
        checkResponseSeconds_ = 0;
        thread_ = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.i(TAG, "开始连接");
                    client_ = new Socket();
                    client_.connect(new InetSocketAddress(serverIp_, serverPort_), overTime * 1000);
                    if (client_.isConnected()) {
                        Log.i(TAG, "连接成功");
                        checkResponseSeconds_ = 0;
                        PrintWriter out = new PrintWriter(client_.getOutputStream(), true);
                        out.println(cmd_);
                        Log.i(TAG, "向服务端发送命令：" + cmd_);
                        //从服务端收消息
                        BufferedReader stdIn = new BufferedReader(new InputStreamReader(client_.getInputStream()));
                        String inputLine;
                        while (!TextUtils.isEmpty(inputLine = stdIn.readLine())) {
                            Log.d(TAG, "收到服务端消息: " + inputLine);
                            AnalysisData(inputLine);
                            checkResponseSeconds_ = 0;
                            Log.i(TAG, "向服务端发送命令：" + cmd_);
                            out.println(cmd_);
                        }
                    }
                } catch (Exception e) {
                    Log.d(TAG, "发生异常，等待重新进行连接:" + e.getMessage());
                }
            }
        });
        thread_.start();
    }

    void AnalysisData(String data) {
        if (data.length() >= 2) {
            int count = Integer.parseInt(data.substring(0, 2), 16);
            if (data.length() >= count * 2 + 2) {
                String gasValues = "";
                for (int i = 0; i < count; i++) {
                    int value = Integer.parseInt(data.substring(2 + 2 * i, 2 + 2 * i + 2), 16);
                    gasValues += value + ",";
                }
                SyncRealtimeDataToServer(gasValues);
                SyncSingleHistoryDataToServer(gasValues);

                HistoryDBManager.AddInfo(AndroidUtils.GetNowTimeStampUnix(), gasValues);
                EventManager.Instance().DisPatch(DataDefines.NotifyType.UpdateRealtimeGasData, ProjectUtils.GasValuesToSGasInfoList(gasValues, 0));
            }
        }
    }

    public void SyncRealtimeDataToServer(String gasValues) {
        RequestParams params = new RequestParams("http://www.huaiantegang.com/Handler/Camera.ashx");
        params.addBodyParameter("requestType", "UpdateRealtimeGasData");
        params.addBodyParameter("androidID", AndroidUtils.GetAndroidID(params.getContext()));
        params.addBodyParameter("gasValues", gasValues);
        x.http().post(params, new org.xutils.common.Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d(TAG, "同步实时数据到服务器成功");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d(TAG, "同步实时数据到服务器出错:" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    public void SyncSingleHistoryDataToServer(String gasValues) {
        RequestParams params = new RequestParams("http://www.huaiantegang.com/Handler/CameraHistory.ashx");
        params.addBodyParameter("requestType", "InsertSingleData");
        params.addBodyParameter("androidID", AndroidUtils.GetAndroidID(params.getContext()));
        params.addBodyParameter("timeStamp", String.valueOf(AndroidUtils.GetNowTimeStampUnix()));
        params.addBodyParameter("gasValues", gasValues);
        x.http().post(params, new org.xutils.common.Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d(TAG, "同步单个历史数据到服务器成功");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d(TAG, "同步单个历史数据到服务器出错:" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private void CloseSocket() throws IOException {
        if (client_ != null) {
            Log.d(TAG, "关闭Socket");
            client_.close();
        }
        if (thread_ != null) {
            Log.d(TAG, "关闭线程");
            thread_.interrupt();
        }
    }

    private boolean isRunTick = false;

    void RunTick() {
        if (isRunTick) {
            return;
        }
        isRunTick = true;
        Runnable command = new Runnable() {
            @Override
            public void run() {
                CheckAbnormalCaseTick();
                CheckExpireInfoTick();
            }
        };
        AndroidUtils.GethreadPool().scheduleAtFixedRate(command, 0, 1, TimeUnit.SECONDS);
    }

    int tempExpireInfoTime = 0;
    int maxExpireInfoTime = 5; // 5天
    int checkOneRoundTime = 3600; // 1小时

    void CheckExpireInfoTick() {
        tempExpireInfoTime++;
        if (tempExpireInfoTime > checkOneRoundTime) {
            Log.d(TAG, checkOneRoundTime + "秒检测删除一次" + maxExpireInfoTime + "天前的数据");
            tempExpireInfoTime = 0;
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -maxExpireInfoTime);
            HistoryDBManager.DeleteExpireInfo((int) AndroidUtils.GetStampUnix(calendar));
            DeleteServerExpireInfo((int) AndroidUtils.GetStampUnix(calendar));
        }
    }

    void DeleteServerExpireInfo(int timeStamp) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams params = new RequestParams("http://www.huaiantegang.com/Handler/CameraHistory.ashx");
                params.addBodyParameter("requestType", "DeleteHistoryData");
                params.addBodyParameter("timeStamp", String.valueOf(timeStamp));
                x.http().post(params, new org.xutils.common.Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d(TAG, "删除服务器过期历史数据成功");
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.d(TAG, "删除服务器过期历史数据出错:" + ex.getMessage());
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                    }

                    @Override
                    public void onFinished() {
                    }
                });
            }
        }).start();
    }

    private void CheckAbnormalCaseTick() {
        checkResponseSeconds_++;
        Log.d(TAG, "检测响应时间: " + checkResponseSeconds_);
        if (checkResponseSeconds_ > overTime) {
            Log.d(TAG, "服务端长时间没有响应，socket重新连接");
            checkResponseSeconds_ = 0;
            try {
                CloseSocket();
                StartSocket();
            } catch (IOException e) {
                Log.d(TAG, "重新启动socket失败：" + e.getMessage());
            }
        }
    }

    public void SaveData(Context context, String socketIP, String socketPort) {
        SharedPreferences pref = context.getSharedPreferences(SharedPreferencesKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(SocketIpKey, socketIP);
        editor.putString(SocketPortKey, socketPort);
        editor.commit();
    }

    public String GetSocketIp(Context context) {
        SharedPreferences ref = context.getSharedPreferences(SharedPreferencesKey, Context.MODE_PRIVATE);
        return ref.getString(SocketIpKey, null);
    }

    public String GetSocketPort(Context context) {
        SharedPreferences ref = context.getSharedPreferences(SharedPreferencesKey, Context.MODE_PRIVATE);
        return ref.getString(SocketPortKey, null);
    }
}
