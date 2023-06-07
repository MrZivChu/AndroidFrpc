package com.ironxiao.frpc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GasInfoSetting extends Dialog {

    private static final String TAG = "--zwh-- GasInfoSetting";

    public GasInfoSetting(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gasinfo_setting);

        Button okBtn = findViewById(R.id.button);
        Button cancelBtn = findViewById(R.id.button2);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnOk();
                hide();
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
        EditText machineEdit = findViewById(R.id.et0);

        EditText gas1AddrEdit = findViewById(R.id.et5y);
        EditText gas1TypeEdit = findViewById(R.id.et6y);
        EditText gas1OneWarnEdit = findViewById(R.id.et7y);
        EditText gas1TwoWarnEdit = findViewById(R.id.et8y);

        EditText gas2AddrEdit = findViewById(R.id.et5);
        EditText gas2TypeEdit = findViewById(R.id.et6);
        EditText gas2OneWarnEdit = findViewById(R.id.et7);
        EditText gas2TwoWarnEdit = findViewById(R.id.et8);

        EditText gas3AddrEdit = findViewById(R.id.et1);
        EditText gas3TypeEdit = findViewById(R.id.et2);
        EditText gas3OneWarnEdit = findViewById(R.id.et3);
        EditText gas3TwoWarnEdit = findViewById(R.id.et4);

        EditText gas4AddrEdit = findViewById(R.id.et5m);
        EditText gas4TypeEdit = findViewById(R.id.et6m);
        EditText gas4OneWarnEdit = findViewById(R.id.et7m);
        EditText gas4TwoWarnEdit = findViewById(R.id.et8m);

        String firstHex = String.format("%04x", Integer.parseInt(gas1AddrEdit.getText().toString()));
        String endHex = String.format("%04x", Integer.parseInt(gas4AddrEdit.getText().toString()));
        String machineHex = String.format("%02x", Integer.parseInt(machineEdit.getText().toString()));
        StringBuilder sb = new StringBuilder();
        sb = sb.append(machineHex).append("03").append(firstHex).append(endHex);
        String cmd = GetTxtSendText(sb.toString());
        Log.d(TAG, "cmd:" + cmd);

        SharedPreferences pref = getContext().getSharedPreferences("deviceInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("command", cmd);
        editor.commit();
        SocketClientHelper.Instance().ReStart(getContext());
    }

    public static String GetTxtSendText(String data) {
        //处理数字转换
        String sendnoNull1 = data.trim();//去掉字符串前后的空格
        String sendnoNull2 = sendnoNull1.replace(" ", "");//去掉字符串中间的空格
        String sendNOComma = sendnoNull2.replace(',', ' ');    //去掉英文逗号
        String sendNOComma1 = sendNOComma.replace('，', ' '); //去掉中文逗号
        String strSendNoComma2 = sendNOComma1.replace("0x", "");   //去掉0x
        String sendBuf = strSendNoComma2.replace("0X", "");   //去掉0X

        byte[] crcbuf = StrToHexByte(sendBuf);//将16进制字符串转换成字节
        String crcString = String.format("%04x", CRCForModbus(crcbuf)); //获得校验码
        return data + " " + crcString;//返回数据+校验码
    }

    public static byte[] StrToHexByte(String hexString) {
        hexString = hexString.replace(" ", "");
        if ((hexString.length() % 2) != 0)
            hexString += " ";
        byte[] returnBytes = new byte[hexString.length() / 2];
        for (int i = 0; i < returnBytes.length; i++) {
            String str = hexString.substring(i * 2, i * 2 + 2).replace(" ", "");
            returnBytes[i] = (byte) Integer.parseInt(str, 16);
        }
        return returnBytes;
    }

    public static short CRCForModbus(byte[] data) {
        //计算并填写CRC校验码
        int crc = 0xffff;
        for (int n = 0; n < data.length; n++) {
            byte i;
            crc = crc ^ data[n];
            for (i = 0; i < 8; i++) {
                int TT;
                TT = crc & 1;
                crc = crc >> 1;
                crc = crc & 0x7fff;
                if (TT == 1) {
                    crc = crc ^ 0xa001;
                }
                crc = crc & 0xffff;
            }
        }
        crc = ((crc & 0xFF) << 8 | (crc >> 8));//高低字节换位
        return (short) crc;
    }
}