package com.ironxiao.frpc.helper;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;

import com.hikvision.netsdk.NET_DVR_TIME;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static int GetTimeStampUnix() {
        long timeStamp = System.currentTimeMillis();
        return (int) (timeStamp / 1000);
    }

    public static String GetFormatTimeFromStampUnix(int stampUnix) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date(stampUnix * 1000L));
    }

    public static String FormatTimer(int year, int month, int day) {
        String tyear = String.valueOf(year);
        String tmonth = String.valueOf(month);
        String tday = String.valueOf(day);
        if (String.valueOf(tmonth).length() == 1) {
            tmonth = "0" + tmonth;
        }
        if (String.valueOf(tday).length() == 1) {
            tday = "0" + tday;
        }
        return tyear + "/" + tmonth + "/" + tday;
    }

    public static String FormatTimer(int year, int month, int day, int hour, int minute) {
        String tyear = String.valueOf(year);
        String tmonth = String.valueOf(month);
        String tday = String.valueOf(day);
        String thour = String.valueOf(hour);
        String tminute = String.valueOf(minute);
        if (String.valueOf(tmonth).length() == 1) {
            tmonth = "0" + tmonth;
        }
        if (String.valueOf(tday).length() == 1) {
            tday = "0" + tday;
        }
        if (String.valueOf(thour).length() == 1) {
            thour = "0" + thour;
        }
        if (String.valueOf(tminute).length() == 1) {
            tminute = "0" + tminute;
        }
        return tyear + "/" + tmonth + "/" + tday + " " + thour + ":" + tminute;
    }
}
