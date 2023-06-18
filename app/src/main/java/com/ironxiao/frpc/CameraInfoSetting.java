package com.ironxiao.frpc;

import androidx.annotation.NonNull;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ironxiao.frpc.helper.CameraDataHelper;
import com.ironxiao.frpc.helper.DataDefines;
import com.ironxiao.frpc.helper.EventManager;
import com.ironxiao.frpc.helper.FrpcHelper;
import com.ironxiao.frpc.helper.SocketClientHelper;

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

        ipEdit.setText(CameraDataHelper.Instance().GetCameraIP(getContext()));
        pwdEdit.setText(CameraDataHelper.Instance().GetCameraPwd(getContext()));
        serverIpEdit.setText(SocketClientHelper.Instance().GetSocketIp(getContext()));
        serverPortEdit.setText(SocketClientHelper.Instance().GetSocketPort(getContext()));

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide();
            }
        });
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide();
                FrpcHelper.Instance().SaveData(getContext(), ipEdit.getText().toString());
                FrpcHelper.Instance().QuitFrpc(getContext());
                FrpcHelper.Instance().StartFrpc(getContext());
                CameraDataHelper.Instance().SaveData(getContext(), ipEdit.getText().toString(), pwdEdit.getText().toString());
                CameraDataHelper.Instance().SyncDataToServer(getContext());
                SocketClientHelper.Instance().SaveData(getContext(), serverIpEdit.getText().toString(), serverPortEdit.getText().toString());
                SocketClientHelper.Instance().ReStart(getContext());
                EventManager.Instance().DisPatch(DataDefines.NotifyType.CameraInfoSetComplete, null);
            }
        });
    }

}