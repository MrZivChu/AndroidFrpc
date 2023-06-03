package com.ironxiao.frpc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputPwdDialog extends Dialog {

    public InputPwdDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_pwd_dialog);

        Button okBtn = findViewById(R.id.okBtn3);
        Button cancelBtn = findViewById(R.id.cancelBtn2);
        EditText pwdEdit = findViewById(R.id.editPwd);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide();
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pwdEdit.getText().toString().equals("admin123")){
                    hide();
                    CameraInfoSetting cameraInfoSetting = new CameraInfoSetting(view.getContext());
                    cameraInfoSetting.show();
                }
            }
        });
    }
}