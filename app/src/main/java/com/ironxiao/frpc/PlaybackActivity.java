package com.ironxiao.frpc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hcnetsdk.jna.CameraHelper;
import com.hikvision.netsdk.NET_DVR_TIME;
import com.hikvision.netsdk.NET_DVR_VOD_PARA;
import com.ironxiao.frpc.helper.AndroidUtils;

import java.util.Calendar;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PlaybackActivity extends Fragment {
    private static final String TAG = "--zwh-- PlaybackActivity";
    public static final int ENUM_RecoverPlayback = 1;

    private SurfaceView surfaceView_ = null;
    private Lock lockPlayBack_ = new ReentrantLock(true);

    NET_DVR_TIME timeStart = new NET_DVR_TIME();
    NET_DVR_TIME timeStop = new NET_DVR_TIME();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_playback, container, false);

        TextView startTimeTextView = view.findViewById(R.id.startTimeTextView);
        TextView endTimeTextView = view.findViewById(R.id.endTimeTextView);
        surfaceView_ = view.findViewById(R.id.surfaceView2);

        Calendar calendar = Calendar.getInstance();
        timeStart.dwYear = calendar.get(Calendar.YEAR);
        timeStart.dwMonth = calendar.get(Calendar.MONTH) + 1;
        timeStart.dwDay = calendar.get(Calendar.DAY_OF_MONTH);
        timeStart.dwHour = 00;
        timeStart.dwMinute = 00;
        timeStop.dwYear = timeStart.dwYear;
        timeStop.dwMonth = timeStart.dwMonth;
        timeStop.dwDay = timeStart.dwDay;
        timeStop.dwHour = calendar.get(Calendar.HOUR_OF_DAY);
        timeStop.dwMinute = timeStart.dwMinute;
        startTimeTextView.setText(AndroidUtils.FormatTimer(timeStart.dwYear, timeStart.dwMonth, timeStart.dwDay, timeStart.dwHour, timeStart.dwMinute));
        endTimeTextView.setText(AndroidUtils.FormatTimer(timeStop.dwYear, timeStop.dwMonth, timeStop.dwDay, timeStop.dwHour, timeStop.dwMinute));

        startTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeSelectDialog dialog = new TimeSelectDialog(getContext());
                dialog.SetCallBack(new TimeSelectDialog.ClickCallback() {
                    @Override
                    public void onClick(int year, int month, int day, int hour, int minute) {
                        timeStart.dwYear = year;
                        timeStart.dwMonth = month;
                        timeStart.dwDay = day;
                        timeStart.dwHour = hour;
                        timeStart.dwMinute = minute;
                        startTimeTextView.setText(AndroidUtils.FormatTimer(timeStart.dwYear, timeStart.dwMonth, timeStart.dwDay, timeStart.dwHour, timeStart.dwMinute));
                        dialog.hide();
                        OnPlayback();
                    }
                });
                dialog.show();
                dialog.UpdateTimer(timeStart.dwYear, timeStart.dwMonth, timeStart.dwDay, timeStart.dwHour, timeStart.dwMinute);
            }
        });
        endTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeSelectDialog dialog = new TimeSelectDialog(getContext());
                dialog.SetCallBack(new TimeSelectDialog.ClickCallback() {
                    @Override
                    public void onClick(int year, int month, int day, int hour, int minute) {
                        timeStop.dwYear = year;
                        timeStop.dwMonth = month;
                        timeStop.dwDay = day;
                        timeStop.dwHour = hour;
                        timeStop.dwMinute = minute;
                        endTimeTextView.setText(AndroidUtils.FormatTimer(timeStop.dwYear, timeStop.dwMonth, timeStop.dwDay, timeStop.dwHour, timeStop.dwMinute));
                        dialog.hide();
                        OnPlayback();
                    }
                });
                dialog.show();
                dialog.UpdateTimer(timeStop.dwYear, timeStop.dwMonth, timeStop.dwDay, timeStop.dwHour, timeStop.dwMinute);
            }
        });
        return view;
    }

    void OnPlayback() {
        Log.d(TAG, "OnPlayback");
        int userID = CameraHelper.GetUserID();
        if (userID == -1) {
            return;
        }
        Log.d(TAG, "请求回放");
        ClosePlayback();
        NET_DVR_VOD_PARA vodParma = new NET_DVR_VOD_PARA();
        vodParma.struBeginTime = timeStart;
        vodParma.struEndTime = timeStop;
        vodParma.byStreamType = 0;
        vodParma.struIDInfo.dwChannel = 1;
        vodParma.hWnd = surfaceView_.getHolder().getSurface();
        boolean isSuccess = CameraHelper.OnPlayBackByTime(vodParma);
        if (!isSuccess) {
            Toast.makeText(getContext(), "播放失败：" + CameraHelper.GetLastErrorMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        ClosePlayback();
    }

    void ClosePlayback() {
        try {
            lockPlayBack_.lock();
            CameraHelper.OnStopPlayBack();
        } finally {
            lockPlayBack_.unlock();
        }
    }

    private final Handler hander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ENUM_RecoverPlayback:
                    OnPlayback();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");

        //由于这些回调函数不能直接涉及UI，所以只能使用hander
        Message msg = new Message();
        msg.what = ENUM_RecoverPlayback;
        hander.sendMessage(msg);
    }
}