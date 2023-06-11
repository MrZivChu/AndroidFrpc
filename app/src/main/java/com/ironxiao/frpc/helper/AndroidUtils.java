package com.ironxiao.frpc.helper;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;

public class AndroidUtils {
    private static final String TAG = "--zwh-- AndroidUtils";
    static String androidID_ = null;

    public static String GetAndroidID(Context context) {
        if (androidID_ == null) {
            androidID_ = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            Log.d(TAG, "AndroidId：" + androidID_);
        }
        return androidID_;
    }
}
