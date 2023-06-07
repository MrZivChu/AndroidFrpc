package com.ironxiao.frpc;

import androidx.annotation.NonNull;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;

public class CameraInfoSetting extends Dialog {

    private static final String TAG = "--zwh-- CameraInfoSetting";

    public CameraInfoSetting(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camerainfo_setting);

        Button okBtn = findViewById(R.id.okBtn);
        Button cancelBtn = findViewById(R.id.cancelBtn);
        EditText ipEdit = findViewById(R.id.editIP3);
        EditText pwdEdit = findViewById(R.id.editPwd3);
        EditText serverIpEdit = findViewById(R.id.serverIP);
        EditText serverPortEdit = findViewById(R.id.serverPort);

        SharedPreferences ref = getContext().getSharedPreferences("deviceInfo", Context.MODE_PRIVATE);
        ipEdit.setText(ref.getString("cameraIp", null));
        pwdEdit.setText(ref.getString("cameraPwd", null));
        serverIpEdit.setText(ref.getString("serverIP", null));
        serverPortEdit.setText(ref.getString("serverPort", null));

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide();
            }
        });
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveCameraInfo(ipEdit.getText().toString(), pwdEdit.getText().toString(), serverIpEdit.getText().toString(), serverPortEdit.getText().toString());
                SaveFrpConfig(ipEdit.getText().toString());
                EventManager.Instance().DisPatch(NotifyType.CameraInfoSetComplete, null);
                hide();
            }
        });
    }

    void SaveCameraInfo(String ip, String pwd, String serverIp, String serverPort) {
        SharedPreferences pref = getContext().getSharedPreferences("deviceInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("cameraIp", ip);
        editor.putString("cameraPwd", pwd);
        editor.putString("serverIP", serverIp);
        editor.putString("serverPort", serverPort);
        editor.commit();
    }

    void SaveFrpConfig(String localIP) {
        File file = new File(getContext().getFilesDir(), FrpcConfig.KEY_FRP_INI_NAME);
        FrpcConfig config = new FrpcConfig();
        FrpcConfig.Node nodeCommon = new FrpcConfig.Node("common");
        nodeCommon.add(FrpcConfig.KEY_SERVER_ADDR, "106.14.213.225");
        nodeCommon.add(FrpcConfig.KEY_SERVER_PORT, "7000");
        config.addNode(nodeCommon);

        FrpcConfig.Node nodeLocal = new FrpcConfig.Node("camera");
        nodeLocal.add(FrpcConfig.KEY_LOCAL_TYPE, "tcp");
        nodeLocal.add(FrpcConfig.KEY_LOCAL_IP, localIP);
        nodeLocal.add(FrpcConfig.KEY_LOCAL_PORT, "8000");
        String[] splits = localIP.split("\\.");
        nodeLocal.add(FrpcConfig.KEY_REMOTE_PORT, splits[3]);
        config.addNode(nodeLocal);

        config.saveTo(file.getAbsolutePath());
    }

}