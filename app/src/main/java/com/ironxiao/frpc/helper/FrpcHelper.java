package com.ironxiao.frpc.helper;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ironxiao.frpc.FrpcConfig;
import com.ironxiao.frpc.FrpcService;

import java.io.File;

public class FrpcHelper {
    private static final String TAG = "--zwh-- FrpcHelper";
    private static FrpcHelper instance;

    public static FrpcHelper Instance() {
        if (instance == null) {
            instance = new FrpcHelper();
        }
        return instance;
    }

    public void SaveData(Context context, String localIP) {
        String[] splits = localIP.split("\\.");
        if (splits.length >= 4) {
            FrpcConfig config = new FrpcConfig();

            FrpcConfig.Node nodeCommon = new FrpcConfig.Node("common");
            nodeCommon.add(FrpcConfig.KEY_SERVER_ADDR, "106.14.213.225");
            nodeCommon.add(FrpcConfig.KEY_SERVER_PORT, "7000");
            config.addNode(nodeCommon);

            String port = splits[3] + splits[2].substring(0, 1);
            String name = "camera" + port;
            FrpcConfig.Node nodeLocal = new FrpcConfig.Node(name);
            nodeLocal.add(FrpcConfig.KEY_LOCAL_TYPE, "tcp");
            nodeLocal.add(FrpcConfig.KEY_LOCAL_IP, localIP);
            nodeLocal.add(FrpcConfig.KEY_LOCAL_PORT, String.valueOf(CameraDataHelper.Instance().GetCameraPort()));

            CameraDataHelper.Instance().SaveFrpPort(context, port);
            nodeLocal.add(FrpcConfig.KEY_REMOTE_PORT, port);
            config.addNode(nodeLocal);

            config.saveTo(context);
        }
    }

    public void StartFrpc(Context context) {
        File file = new File(context.getFilesDir(), FrpcConfig.KEY_FRP_INI_NAME);
        if (file.exists()) {
            Log.d(TAG, "启动Frpc：" + file.getAbsolutePath());
            Intent intent = new Intent(context, FrpcService.class);
            context.startService(intent);
        }
    }

    public void QuitFrpc(Context context) {
        Log.d(TAG, "Frpc退出");
        Intent intent = new Intent(context, FrpcService.class);
        context.stopService(intent);
    }
}
