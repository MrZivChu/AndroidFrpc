package com.ironxiao.frpc;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.xutils.http.RequestParams;
import org.xutils.x;

public class LocalBaseDataHelper {
    private static final String TAG = "--zwh-- LocalBaseDataHelper";
    private final String SharedPreferencesKey = "deviceInfo";
    private final String CameraIpKey = "cameraIp";
    private final String FrpPortKey = "cameraPort";
    private final String CameraPwdKey = "cameraPwd";
    private final String SocketIpKey = "socketIP";
    private final String SocketPortKey = "socketPort";
    private final String CommandKey = "command";
    private static LocalBaseDataHelper instance;

    public static LocalBaseDataHelper Instance() {
        if (instance == null) {
            instance = new LocalBaseDataHelper();
        }
        return instance;
    }

    public void SyncDataToServer(Context context) {
        if (GetCameraIP(context) == null || GetFrpPort(context) == null || GetCameraUserName() == null || GetCameraPwd(context) == null) {
            return;
        }
        Log.d(TAG, "开始同步数据到服务器");
        RequestParams params = new RequestParams("http://www.huaiantegang.com/Handler/Camera.ashx");
        params.addBodyParameter("requestType", "InsertCameraBaseData");
        params.addBodyParameter("ip", GetCameraIP(context));
        params.addBodyParameter("port", GetFrpPort(context));
        params.addBodyParameter("userName", GetCameraUserName());
        params.addBodyParameter("userPwd", GetCameraPwd(context));
        x.http().post(params, new org.xutils.common.Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d(TAG, "同步数据到服务器成功");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d(TAG, "同步数据到服务器出错:" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    public void SaveBaseData(Context context, String ip, String pwd, String socketIP, String socketPort) {
        SharedPreferences pref = context.getSharedPreferences(SharedPreferencesKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(CameraIpKey, ip);
        editor.putString(CameraPwdKey, pwd);
        editor.putString(SocketIpKey, socketIP);
        editor.putString(SocketPortKey, socketPort);
        editor.commit();
    }

    public void SaveCommandData(Context context, String data) {
        SharedPreferences pref = context.getSharedPreferences(SharedPreferencesKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(CommandKey, data);
        editor.commit();
    }

    public void SaveFrpPortData(Context context, String data) {
        SharedPreferences pref = context.getSharedPreferences(SharedPreferencesKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(FrpPortKey, data);
        editor.commit();
    }

    public String GetFrpPort(Context context) {
        SharedPreferences ref = context.getSharedPreferences(SharedPreferencesKey, Context.MODE_PRIVATE);
        return ref.getString(FrpPortKey, null);
    }

    public String GetCameraIP(Context context) {
        SharedPreferences ref = context.getSharedPreferences(SharedPreferencesKey, Context.MODE_PRIVATE);
        return ref.getString(CameraIpKey, null);
    }

    public int GetCameraPort() {
        return 8000;
    }

    public String GetCameraUserName() {
        return "admin";
    }

    public String GetCameraPwd(Context context) {
        SharedPreferences ref = context.getSharedPreferences(SharedPreferencesKey, Context.MODE_PRIVATE);
        return ref.getString(CameraPwdKey, null);
    }

    public String GetSocketIp(Context context) {
        SharedPreferences ref = context.getSharedPreferences(SharedPreferencesKey, Context.MODE_PRIVATE);
        return ref.getString(SocketIpKey, null);
    }

    public String GetSocketPort(Context context) {
        SharedPreferences ref = context.getSharedPreferences(SharedPreferencesKey, Context.MODE_PRIVATE);
        return ref.getString(SocketPortKey, null);
    }

    public String GetCommand(Context context) {
        SharedPreferences ref = context.getSharedPreferences(SharedPreferencesKey, Context.MODE_PRIVATE);
        return ref.getString(CommandKey, null);
    }
}
