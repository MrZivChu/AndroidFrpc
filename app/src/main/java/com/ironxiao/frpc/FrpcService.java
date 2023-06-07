package com.ironxiao.frpc;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

import frpclib.Frpclib;

public class FrpcService extends Service {
    private static final String TAG = "--zwh-- FrpcService";
    private Notification notification;
    private boolean running = false;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel mChannel = new NotificationChannel(getString(R.string.frpc_status_running), getString(R.string.app_name),
                    NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(mChannel);
            notification = new Notification.Builder(getApplicationContext(), getString(R.string.frpc_status_running)).build();
            startForeground(1, notification);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            startForeground(1, notification);
        }
        if (!running) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    running = true;
                    File file = new File(getFilesDir(), FrpcConfig.KEY_FRP_INI_NAME);
                    if(file.exists()) {
                        String savePath = file.getAbsolutePath();
                        Frpclib.run(savePath);
                    }
                }
            }).start();
            Log.d(TAG, "frpc运行成功");
        } else {
            Log.d(TAG, "frpc已经运行");
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Frpclib.stop();
        Log.d(TAG, "frpc已经停止");
    }
}