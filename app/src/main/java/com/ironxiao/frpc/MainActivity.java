package com.ironxiao.frpc;

import android.os.Bundle;


import android.provider.Settings;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.gson.internal.bind.JsonTreeReader;
import com.hcnetsdk.jna.CameraHelper;
import com.ironxiao.frpc.helper.AndroidUtils;
import com.ironxiao.frpc.helper.CameraDataHelper;
import com.ironxiao.frpc.helper.FrpcHelper;
import com.ironxiao.frpc.helper.SocketClientHelper;
import com.ironxiao.frpc.sql.CameraHistoryModel;
import com.ironxiao.frpc.sql.HistoryDBManager;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "--zwh-- MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");

        AndroidUtils.CreateThreadPool(3);
        CameraHelper.OnInit();
        x.Ext.init(getApplication());
        HistoryDBManager.initDB(getApplication());

        HandleServerLocalHistoryData();
        FrpcHelper.Instance().StartFrpc(getApplicationContext());
        CameraDataHelper.Instance().SyncDataToServer(getApplicationContext());
        SocketClientHelper.Instance().ReStart(getApplicationContext());

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        List<Fragment> fragmentLis = new ArrayList<>();
        fragmentLis.add(new PreviewActivity());
        fragmentLis.add(new PlaybackActivity());
        fragmentLis.add(new HistoryActivity());
        viewPager.setAdapter(new ViewPageAdapter(this, fragmentLis));
        viewPager.setCurrentItem(0);

    }

    void HandleServerLocalHistoryData() {
        RequestParams params = new RequestParams("http://www.huaiantegang.com/Handler/CameraHistory.ashx");
        params.addBodyParameter("requestType", "SelectOneNeweastData");
        x.http().post(params, new org.xutils.common.Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d(TAG, "服务器最新的历史记录时间戳" + result);
                AnalysisHistoryData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d(TAG, "查询历史记录时间戳失败" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    void AnalysisHistoryData(String timeStamp) {
        int pageSize = 1000;
        int ts = timeStamp == null ? 0 : Integer.parseInt(timeStamp);
        List<CameraHistoryModel> list = HistoryDBManager.GetDataByTimestamp(ts);
        if (list != null && list.size() > 0) {
            int listCount = list.size();
            int pageCount = listCount / pageSize + ((listCount % pageSize) > 0 ? 1 : 0);
            Log.d(TAG, "需要同步的历史数据数量: " + listCount + ",需要分" + pageCount + "次分发");
            for (int j = 0; j < pageCount; j++) {
                StringBuilder sql = new StringBuilder("insert into CameraHistory (AndroidID,TimeStamp,GasValues)values");
                int startIndex = pageSize * j;
                int endIndex = (pageSize * (j + 1)) > listCount ? listCount : (pageSize * (j + 1));
                for (int i = startIndex; i < endIndex; i++) {
                    sql.append("('");
                    sql.append(list.get(i).AndroidID);
                    sql.append("',");
                    sql.append(list.get(i).TimeStamp);
                    sql.append(",'");
                    sql.append(list.get(i).GasValues);
                    sql.append("')");
                    if (i < endIndex - 1) {
                        sql.append(",");
                    }
                }
                SyncHistoryToServer(sql.toString());
            }
        }
    }

    void SyncHistoryToServer(String sql) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams params = new RequestParams("http://www.huaiantegang.com/Handler/CameraHistory.ashx");
                params.addBodyParameter("requestType", "Insert");
                params.addBodyParameter("sql", sql);
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.d(TAG, "同步历史数据失败: " + ex.getMessage());
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

    @Override
    protected void onResume() {
        super.onResume();
        NotificationManagerCompat notification = NotificationManagerCompat.from(getApplicationContext());
        boolean isEnabled = notification.areNotificationsEnabled();
        if(!isEnabled){
            AndroidUtils.OpenNotificationSettingsForApp(getApplicationContext());
        }
    }
}