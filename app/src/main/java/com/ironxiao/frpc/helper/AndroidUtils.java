package com.ironxiao.frpc.helper;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.provider.Settings;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.ironxiao.frpc.MainActivity;
import com.ironxiao.frpc.R;

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
        if (String.valueOf(tminute).length() == 1) {
            tminute = "0" + tminute;
        }
        return FormatTimer(year, month, day) + " " + thour + ":" + tminute;
    }

    public static String FormatTimer(int year, int month, int day, int hour, int minute, int seconds) {
        String thour = String.valueOf(hour);
        String tminute = String.valueOf(minute);
        String tseconds = String.valueOf(seconds);
        if (String.valueOf(thour).length() == 1) {
            thour = "0" + thour;
        }
        if (String.valueOf(tminute).length() == 1) {
            tminute = "0" + tminute;
        }
        if (String.valueOf(tminute).length() == 1) {
            tminute = "0" + tminute;
        }
        if (String.valueOf(tseconds).length() == 1) {
            tseconds = "0" + tseconds;
        }
        return FormatTimer(year, month, day) + " " + thour + ":" + tminute + ":" + tseconds;
    }

    static ScheduledExecutorService executorService_ = null;

    public static void CreateThreadPool(int poolSize) {
        executorService_ = Executors.newScheduledThreadPool(poolSize);
    }

    public static ScheduledExecutorService GethreadPool() {
        return executorService_;
    }


    public static void OpenNotificationSettingsForApp(Context context) {
        Intent intent = new Intent();
        intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
        intent.putExtra("app_package", context.getPackageName());
        intent.putExtra("app_uid", context.getApplicationInfo().uid);
        intent.putExtra("android.provider.extra.APP_PACKAGE", context.getPackageName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
