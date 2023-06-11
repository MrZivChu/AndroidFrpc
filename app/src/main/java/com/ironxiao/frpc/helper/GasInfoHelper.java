package com.ironxiao.frpc.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.util.Log;

import org.xutils.http.RequestParams;
import org.xutils.x;

public class GasInfoHelper {
    private static final String TAG = "--zwh-- GasInfoHelper";
    private final String SharedPreferencesKey = "gasInfo";
    private final String MachineAddrKey = "machineAddr";
    private final String GasesKey = "gases";
    private final String CommandKey = "command";
    private static GasInfoHelper instance;

    public static GasInfoHelper Instance() {
        if (instance == null) {
            instance = new GasInfoHelper();
        }
        return instance;
    }

    public void SyncDataToServer(Context context) {
        if (GetGases(context) == null || GetMachineID(context) == null) {
            return;
        }
        Log.d(TAG, "开始同步数据到服务器");
        RequestParams params = new RequestParams("http://www.huaiantegang.com/Handler/Camera.ashx");
        params.addBodyParameter("requestType", "InsertGasBaseData");
        params.addBodyParameter("androidID", AndroidUtils.GetAndroidID(context));
        params.addBodyParameter("machineID", GetMachineID(context));
        params.addBodyParameter("gases", GetGases(context));
        x.http().post(params, new org.xutils.common.Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d(TAG, "同步气体基础数据到服务器成功");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d(TAG, "同步气体基础数据到服务器出错:" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    public void SaveData(Context context, String machineAddr, String gas1Addr, String gas1Type, String gas1Min, String gas1Max,
                         String gas2Addr, String gas2Type, String gas2Min, String gas2Max,
                         String gas3Addr, String gas3Type, String gas3Min, String gas3Max,
                         String gas4Addr, String gas4Type, String gas4Min, String gas4Max) {
        SharedPreferences pref = context.getSharedPreferences(SharedPreferencesKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(MachineAddrKey, machineAddr);
        editor.putString(GasesKey,
                gas1Addr + "," + gas1Type + "," + gas1Min + "," + gas1Max + ";" +
                        gas2Addr + "," + gas2Type + "," + gas2Min + "," + gas2Max + ";" +
                        gas3Addr + "," + gas3Type + "," + gas3Min + "," + gas3Max + ";" +
                        gas4Addr + "," + gas4Type + "," + gas4Min + "," + gas4Max);

        String firstHex = String.format("%04x", Integer.parseInt(gas1Addr));
        String endHex = String.format("%04x", Integer.parseInt(gas4Addr));
        String machineHex = String.format("%02x", Integer.parseInt(machineAddr));
        StringBuilder sb = new StringBuilder();
        sb = sb.append(machineHex).append("03").append(firstHex).append(endHex);
        editor.putString(CommandKey, GetCheckCode(sb.toString()));

        editor.commit();
    }


    public String GetGases(Context context) {
        SharedPreferences ref = context.getSharedPreferences(SharedPreferencesKey, Context.MODE_PRIVATE);
        return ref.getString(GasesKey, null);
    }

    public String GetMachineID(Context context) {
        SharedPreferences ref = context.getSharedPreferences(SharedPreferencesKey, Context.MODE_PRIVATE);
        return ref.getString(MachineAddrKey, null);
    }

    public String GetCommand(Context context) {
        SharedPreferences ref = context.getSharedPreferences(SharedPreferencesKey, Context.MODE_PRIVATE);
        return ref.getString(CommandKey, null);
    }

    static String GetCheckCode(String data) {
        //处理数字转换
        String sendnoNull1 = data.trim();//去掉字符串前后的空格
        String sendnoNull2 = sendnoNull1.replace(" ", "");//去掉字符串中间的空格
        String sendNOComma = sendnoNull2.replace(',', ' ');    //去掉英文逗号
        String sendNOComma1 = sendNOComma.replace('，', ' '); //去掉中文逗号
        String strSendNoComma2 = sendNOComma1.replace("0x", "");   //去掉0x
        String sendBuf = strSendNoComma2.replace("0X", "");   //去掉0X

        byte[] crcbuf = StrToHexByte(sendBuf);//将16进制字符串转换成字节
        String crcString = String.format("%04x", CRCForModbus(crcbuf)); //获得校验码
        return data + crcString;//返回数据+校验码
    }

    static byte[] StrToHexByte(String hexString) {
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

    static short CRCForModbus(byte[] data) {
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
