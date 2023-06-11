package com.ironxiao.frpc;

import androidx.annotation.NonNull;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        ipEdit.setText(LocalBaseDataHelper.Instance().GetCameraIP(getContext()));
        pwdEdit.setText(LocalBaseDataHelper.Instance().GetCameraPwd(getContext()));
        serverIpEdit.setText(LocalBaseDataHelper.Instance().GetSocketIp(getContext()));
        serverPortEdit.setText(LocalBaseDataHelper.Instance().GetSocketPort(getContext()));

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide();
            }
        });
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrpcHelper.Instance().SaveData(getContext(), ipEdit.getText().toString());
                FrpcHelper.Instance().QuitFrpc(getContext());
                FrpcHelper.Instance().StartFrpc(getContext());
                SocketClientHelper.Instance().ReStart(getContext());
                LocalBaseDataHelper.Instance().SaveBaseData(getContext(), ipEdit.getText().toString(), pwdEdit.getText().toString(), serverIpEdit.getText().toString(), serverPortEdit.getText().toString());
                LocalBaseDataHelper.Instance().SyncDataToServer(getContext());
                EventManager.Instance().DisPatch(NotifyType.CameraInfoSetComplete, null);
                hide();
            }
        });
    }

}