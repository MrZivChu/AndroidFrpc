package com.ironxiao.frpc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CameraInfoSetting extends Dialog {

    private static final String TAG = CameraInfoSetting.class.getName();

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
        EditText portEdit = findViewById(R.id.editPort2);

        SharedPreferences ref = getContext().getSharedPreferences("cameraInfo", Context.MODE_PRIVATE);
        ipEdit.setText(ref.getString("ip", "192.168.0.103"));
        pwdEdit.setText(ref.getString("pwd", "HikCDJDNF"));
        portEdit.setText(String.valueOf(ref.getInt("port", 8000)));

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
                editor.putInt("port", Integer.parseInt(portEdit.getText().toString()));
                editor.commit();
                hide();
                EventManager.Instance().DisPatch(NotifyType.CameraInfoSetComplete, null);
            }
        });
    }
}