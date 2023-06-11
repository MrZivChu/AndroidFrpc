package com.ironxiao.frpc;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
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

    private Socket client_ = null;
    private Thread thread_ = null;
    private int overTime = 5; // 秒
    private int checkResponseSeconds_;
    private String serverIp_;
    private int serverPort_;
    private String cmd_;

    public void ReStart(Context context) {
        if (LocalBaseDataHelper.Instance().GetSocketIp(context) != null && LocalBaseDataHelper.Instance().GetSocketPort(context) != null && LocalBaseDataHelper.Instance().GetCommand(context) != null) {
            serverIp_ = LocalBaseDataHelper.Instance().GetSocketIp(context);
            serverPort_ = Integer.parseInt(LocalBaseDataHelper.Instance().GetSocketPort(context));
            cmd_ = LocalBaseDataHelper.Instance().GetCommand(context);
            CheckAbnormalCase();
        }
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
                        while ((inputLine = stdIn.readLine()) != null) {
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
                for (int i = 0; i < count; i++) {
                    int value = Integer.parseInt(data.substring(2 + 2 * i, 2 + 2 * i + 2), 16);
                }
            }
        }
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

    private boolean isRunCheck = false;

    private void CheckAbnormalCase() {
        if (isRunCheck) {
            return;
        }
        isRunCheck = true;
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        Runnable command = new Runnable() {
            @Override
            public void run() {
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
        };
        executorService.scheduleAtFixedRate(command, 0, 1, TimeUnit.SECONDS);
    }
}
