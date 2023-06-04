package com.ironxiao.frpc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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

        SharedPreferences ref = getContext().getSharedPreferences("cameraInfo", Context.MODE_PRIVATE);
        ipEdit.setText(ref.getString("ip", "192.168.0.103"));
        pwdEdit.setText(ref.getString("pwd", "HikCDJDNF"));

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide();
            }
        });
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getContext().getSharedPreferences("cameraInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("ip", ipEdit.getText().toString());
                editor.putString("pwd", pwdEdit.getText().toString());
                editor.putInt("port", 8000);
                editor.commit();
                hide();
                EventManager.Instance().DisPatch(NotifyType.CameraInfoSetComplete, null);

                saveConfig(ipEdit.getText().toString());
                QuitFrpc();
                StartFrpc();
            }
        });
    }

    public void saveConfig(String localIP) {
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

    private void StartFrpc() {
        Intent intent = new Intent(getContext(), FrpcService.class);
        getContext().startService(intent);
    }

    private void QuitFrpc() {
        Intent intent = new Intent(getContext(), FrpcService.class);
        getContext().stopService(intent);
    }

}