package com.ironxiao.frpc.helper;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

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

    public static int GetNowTimeStampUnix() {
        long timeStamp = System.currentTimeMillis();
        return (int) (timeStamp / 1000);
    }

    // 月份记得减1
    public static int GetStampUnix(int year, int month, int day, int hour, int minute, int second) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, second);
        return (int) (c.getTimeInMillis() / 1000);
    }

    public static int GetStampUnix(Calendar c) {
        return (int) (c.getTimeInMillis() / 1000);
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
        String thour = String.valueOf(hour);
        String tminute = String.valueOf(minute);
        if (String.valueOf(thour).length() == 1) {
            thour = "0" + thour;
        }
        if (String.valueOf(tminute).length() == 1) {
            tminute = "0" + tminute;
        }
        return FormatTimer(year, month, day) + " " + thour + ":" + tminute;
    }

    static ScheduledExecutorService executorService_ = null;

    public static void CreateThreadPool(int poolSize) {
        executorService_ = Executors.newScheduledThreadPool(poolSize);
    }

    public static ScheduledExecutorService GethreadPool() {
        return executorService_;
    }
}
