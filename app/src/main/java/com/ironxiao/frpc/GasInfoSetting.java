package com.ironxiao.frpc;

import androidx.annotation.NonNull;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ironxiao.frpc.helper.CameraDataHelper;
import com.ironxiao.frpc.helper.DataDefines;
import com.ironxiao.frpc.helper.EventManager;
import com.ironxiao.frpc.helper.GasInfoHelper;
import com.ironxiao.frpc.helper.SocketClientHelper;

public class GasInfoSetting extends Dialog {

    private static final String TAG = "--zwh-- GasInfoSetting";

    EditText machineEdit;
    EditText gas1AddrEdit;
    EditText gas1TypeEdit;
    EditText gas1OneWarnEdit;
    EditText gas1TwoWarnEdit;
    EditText gas2AddrEdit;
    EditText gas2TypeEdit;
    EditText gas2OneWarnEdit;
    EditText gas2TwoWarnEdit;
    EditText gas3AddrEdit;
    EditText gas3TypeEdit;
    EditText gas3OneWarnEdit;
    EditText gas3TwoWarnEdit;
    EditText gas4AddrEdit;
    EditText gas4TypeEdit;
    EditText gas4OneWarnEdit;
    EditText gas4TwoWarnEdit;

    public GasInfoSetting(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gasinfo_setting);

        machineEdit = findViewById(R.id.et0);

        gas1AddrEdit = findViewById(R.id.et5y);
        gas1TypeEdit = findViewById(R.id.et6y);
        gas1OneWarnEdit = findViewById(R.id.et7y);
        gas1TwoWarnEdit = findViewById(R.id.et8y);

        gas2AddrEdit = findViewById(R.id.et5);
        gas2TypeEdit = findViewById(R.id.et6);
        gas2OneWarnEdit = findViewById(R.id.et7);
        gas2TwoWarnEdit = findViewById(R.id.et8);

        gas3AddrEdit = findViewById(R.id.et1);
        gas3TypeEdit = findViewById(R.id.et2);
        gas3OneWarnEdit = findViewById(R.id.et3);
        gas3TwoWarnEdit = findViewById(R.id.et4);

        gas4AddrEdit = findViewById(R.id.et5m);
        gas4TypeEdit = findViewById(R.id.et6m);
        gas4OneWarnEdit = findViewById(R.id.et7m);
        gas4TwoWarnEdit = findViewById(R.id.et8m);

        machineEdit.setText(GasInfoHelper.Instance().GetMachineID(getContext()));
        String gases = GasInfoHelper.Instance().GetGases(getContext());
        if (!TextUtils.isEmpty(gases)) {
            String[] gasArray = gases.split(";");
            if (gasArray.length >= 4) {
                String[] gas1Array = gasArray[0].split(",");
                if (gas1Array.length >= 4) {
                    gas1AddrEdit.setText(gas1Array[0]);
                    gas1TypeEdit.setText(gas1Array[1]);
                    gas1OneWarnEdit.setText(gas1Array[2]);
                    gas1TwoWarnEdit.setText(gas1Array[3]);
                }
                String[] gas2Array = gasArray[1].split(",");
                if (gas2Array.length >= 4) {
                    gas2AddrEdit.setText(gas2Array[0]);
                    gas2TypeEdit.setText(gas2Array[1]);
                    gas2OneWarnEdit.setText(gas2Array[2]);
                    gas2TwoWarnEdit.setText(gas2Array[3]);
                }
                String[] gas3Array = gasArray[2].split(",");
                if (gas3Array.length >= 4) {
                    gas3AddrEdit.setText(gas3Array[0]);
                    gas3TypeEdit.setText(gas3Array[1]);
                    gas3OneWarnEdit.setText(gas3Array[2]);
                    gas3TwoWarnEdit.setText(gas3Array[3]);
                }
                String[] gas4Array = gasArray[3].split(",");
                if (gas4Array.length >= 4) {
                    gas4AddrEdit.setText(gas4Array[0]);
                    gas4TypeEdit.setText(gas4Array[1]);
                    gas4OneWarnEdit.setText(gas4Array[2]);
                    gas4TwoWarnEdit.setText(gas4Array[3]);
                }
            }
        }

        Button okBtn = findViewById(R.id.button);
        Button cancelBtn = findViewById(R.id.button2);

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide();
                OnOk();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide();
            }
        });
    }

    void OnOk() {
        GasInfoHelper.Instance().SaveData(getContext(), machineEdit.getText().toString(),
                gas1AddrEdit.getText().toString(), gas1TypeEdit.getText().toString(), gas1OneWarnEdit.getText().toString(), gas1TwoWarnEdit.getText().toString(),
                gas2AddrEdit.getText().toString(), gas2TypeEdit.getText().toString(), gas2OneWarnEdit.getText().toString(), gas2TwoWarnEdit.getText().toString(),
                gas3AddrEdit.getText().toString(), gas3TypeEdit.getText().toString(), gas3OneWarnEdit.getText().toString(), gas3TwoWarnEdit.getText().toString(),
                gas4AddrEdit.getText().toString(), gas4TypeEdit.getText().toString(), gas4OneWarnEdit.getText().toString(), gas4TwoWarnEdit.getText().toString());
        GasInfoHelper.Instance().SyncDataToServer(getContext());
        SocketClientHelper.Instance().ReStart(getContext());
    }

}