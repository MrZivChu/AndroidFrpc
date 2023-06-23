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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hcnetsdk.jna.CameraHelper;
import com.hikvision.netsdk.NET_DVR_TIME;
import com.hikvision.netsdk.NET_DVR_VOD_PARA;
import com.ironxiao.frpc.helper.AndroidUtils;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PlaybackActivity extends Fragment {
    private static final String TAG = "--zwh-- PlaybackActivity";
    public static final int ENUM_RecoverPlayback = 1;
    public static final int ENUM_Tick = 2;

    TextView currentTimeTV_;
    SeekBar seekBar_;
    private SurfaceView surfaceView_ = null;
    private Lock lockPlayBack_ = new ReentrantLock(true);
    long allTimSec_ = 0;
    long currentTimeSec_ = 0;

    NET_DVR_TIME showTimeStart = new NET_DVR_TIME();
    NET_DVR_TIME showTimeStop = new NET_DVR_TIME();
    NET_DVR_TIME playTimeStart = new NET_DVR_TIME();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_playback, container, false);
        OnRegisterTick();

        TextView startTimeTextView = view.findViewById(R.id.startTimeTextView);
        TextView endTimeTextView = view.findViewById(R.id.endTimeTextView);
        currentTimeTV_ = view.findViewById(R.id.currentTimeTextView);
        surfaceView_ = view.findViewById(R.id.surfaceView2);
        seekBar_ = view.findViewById(R.id.seekBar);

        Calendar calendar = Calendar.getInstance();
        showTimeStart.dwYear = calendar.get(Calendar.YEAR);
        showTimeStart.dwMonth = calendar.get(Calendar.MONTH) + 1;
        showTimeStart.dwDay = calendar.get(Calendar.DAY_OF_MONTH);
        showTimeStart.dwHour = 0;
        showTimeStart.dwMinute = 0;
        showTimeStart.dwSecond = 0;
        playTimeStart.dwYear = showTimeStart.dwYear;
        playTimeStart.dwMonth = showTimeStart.dwMonth;
        playTimeStart.dwDay = showTimeStart.dwDay;
        playTimeStart.dwHour = showTimeStart.dwHour;
        playTimeStart.dwMinute = showTimeStart.dwMinute;
        playTimeStart.dwSecond = showTimeStart.dwSecond;
        showTimeStop.dwYear = showTimeStart.dwYear;
        showTimeStop.dwMonth = showTimeStart.dwMonth;
        showTimeStop.dwDay = showTimeStart.dwDay;
        showTimeStop.dwHour = calendar.get(Calendar.HOUR_OF_DAY);
        showTimeStop.dwMinute = showTimeStart.dwMinute;
        showTimeStop.dwSecond = showTimeStart.dwSecond;
        InitAllTimeSecCurrentTimeSec(0);
        startTimeTextView.setText(AndroidUtils.FormatTimer(showTimeStart.dwYear, showTimeStart.dwMonth, showTimeStart.dwDay, showTimeStart.dwHour, showTimeStart.dwMinute));
        endTimeTextView.setText(AndroidUtils.FormatTimer(showTimeStop.dwYear, showTimeStop.dwMonth, showTimeStop.dwDay, showTimeStop.dwHour, showTimeStop.dwMinute));

        seekBar_.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    Log.d(TAG, "onProgressChanged: " + i);
                    InitAllTimeSecCurrentTimeSec(i);
                    UpdateCurrentTimeTV();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStartTrackingTouch: " + seekBar.getProgress());
                updateSeekbar = false;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Calendar showStartTimeCal = Calendar.getInstance();
                showStartTimeCal.set(Calendar.YEAR, showTimeStart.dwYear);
                showStartTimeCal.set(Calendar.MONTH, showTimeStart.dwMonth);
                showStartTimeCal.set(Calendar.DAY_OF_MONTH, showTimeStart.dwDay);
                showStartTimeCal.set(Calendar.HOUR_OF_DAY, showTimeStart.dwHour);
                showStartTimeCal.set(Calendar.MINUTE, showTimeStart.dwMinute);
                showStartTimeCal.set(Calendar.SECOND, 0);
                InitAllTimeSecCurrentTimeSec(seekBar.getProgress());
                showStartTimeCal.add(Calendar.SECOND, (int) currentTimeSec_);
                playTimeStart.dwYear = showStartTimeCal.get(Calendar.YEAR);
                playTimeStart.dwMonth = showStartTimeCal.get(Calendar.MONTH);
                playTimeStart.dwDay = showStartTimeCal.get(Calendar.DAY_OF_MONTH);
                playTimeStart.dwHour = showStartTimeCal.get(Calendar.HOUR_OF_DAY);
                playTimeStart.dwMinute = showStartTimeCal.get(Calendar.MINUTE);
                playTimeStart.dwSecond = showStartTimeCal.get(Calendar.SECOND);
                OnPlayback();
            }
        });

        startTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeSelectDialog dialog = new TimeSelectDialog(getContext());
                dialog.SetCallBack(new TimeSelectDialog.ClickCallback() {
                    @Override
                    public void onClick(int year, int month, int day, int hour, int minute) {
                        updateSeekbar = false;
                        showTimeStart.dwYear = year;
                        showTimeStart.dwMonth = month;
                        showTimeStart.dwDay = day;
                        showTimeStart.dwHour = hour;
                        showTimeStart.dwMinute = minute;
                        showTimeStart.dwSecond = 0;
                        playTimeStart.dwYear = showTimeStart.dwYear;
                        playTimeStart.dwMonth = showTimeStart.dwMonth;
                        playTimeStart.dwDay = showTimeStart.dwDay;
                        playTimeStart.dwHour = showTimeStart.dwHour;
                        playTimeStart.dwMinute = showTimeStart.dwMinute;
                        playTimeStart.dwSecond = showTimeStart.dwSecond;
                        InitAllTimeSecCurrentTimeSec(0);
                        seekBar_.setProgress(0);
                        currentTimeTV_.setText("");
                        startTimeTextView.setText(AndroidUtils.FormatTimer(showTimeStart.dwYear, showTimeStart.dwMonth, showTimeStart.dwDay, showTimeStart.dwHour, showTimeStart.dwMinute));
                        dialog.hide();
                        OnPlayback();
                    }
                });
                dialog.show();
                dialog.UpdateTimer(showTimeStart.dwYear, showTimeStart.dwMonth, showTimeStart.dwDay, showTimeStart.dwHour, showTimeStart.dwMinute);
            }
        });
        endTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeSelectDialog dialog = new TimeSelectDialog(getContext());
                dialog.SetCallBack(new TimeSelectDialog.ClickCallback() {
                    @Override
                    public void onClick(int year, int month, int day, int hour, int minute) {
                        updateSeekbar = false;
                        showTimeStop.dwYear = year;
                        showTimeStop.dwMonth = month;
                        showTimeStop.dwDay = day;
                        showTimeStop.dwHour = hour;
                        showTimeStop.dwMinute = minute;
                        showTimeStop.dwSecond = 0;
                        InitAllTimeSecCurrentTimeSec(0);
                        seekBar_.setProgress(0);
                        currentTimeTV_.setText("");
                        endTimeTextView.setText(AndroidUtils.FormatTimer(showTimeStop.dwYear, showTimeStop.dwMonth, showTimeStop.dwDay, showTimeStop.dwHour, showTimeStop.dwMinute));
                        dialog.hide();
                        OnPlayback();
                    }
                });
                dialog.show();
                dialog.UpdateTimer(showTimeStop.dwYear, showTimeStop.dwMonth, showTimeStop.dwDay, showTimeStop.dwHour, showTimeStop.dwMinute);
            }
        });
        return view;
    }

    void InitAllTimeSecCurrentTimeSec(int seekbarProgress) {
        Calendar showStartTimeCal = Calendar.getInstance();
        showStartTimeCal.set(Calendar.YEAR, showTimeStart.dwYear);
        showStartTimeCal.set(Calendar.MONTH, showTimeStart.dwMonth);
        showStartTimeCal.set(Calendar.DAY_OF_MONTH, showTimeStart.dwDay);
        showStartTimeCal.set(Calendar.HOUR_OF_DAY, showTimeStart.dwHour);
        showStartTimeCal.set(Calendar.MINUTE, showTimeStart.dwMinute);
        showStartTimeCal.set(Calendar.SECOND, showTimeStart.dwSecond);
        Calendar showEndTimeCal = Calendar.getInstance();
        showEndTimeCal.set(Calendar.YEAR, showTimeStop.dwYear);
        showEndTimeCal.set(Calendar.MONTH, showTimeStop.dwMonth);
        showEndTimeCal.set(Calendar.DAY_OF_MONTH, showTimeStop.dwDay);
        showEndTimeCal.set(Calendar.HOUR_OF_DAY, showTimeStop.dwHour);
        showEndTimeCal.set(Calendar.MINUTE, showTimeStop.dwMinute);
        showEndTimeCal.set(Calendar.SECOND, showTimeStop.dwSecond);
        allTimSec_ = (showEndTimeCal.getTimeInMillis() - showStartTimeCal.getTimeInMillis()) / 1000;
        currentTimeSec_ = (long) (allTimSec_ * (seekbarProgress / 100.0f));
        Log.d(TAG, "总共秒数" + allTimSec_ + ",当前第" + currentTimeSec_ + "秒");
    }

    void OnRegisterTick() {
        Runnable command = new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = ENUM_Tick;
                hander.sendMessage(msg);
            }
        };
        AndroidUtils.GethreadPool().scheduleAtFixedRate(command, 0, 1, TimeUnit.SECONDS);
    }

    boolean updateSeekbar = false;

    void OnTick() {
        if (updateSeekbar) {
            currentTimeSec_++;
            int progress = (int) (currentTimeSec_ / (allTimSec_ * 1.0f) * 100);
            seekBar_.setProgress(progress);
            UpdateCurrentTimeTV();
        }
    }

    void UpdateCurrentTimeTV() {
        Calendar showStartTimeCal = Calendar.getInstance();
        showStartTimeCal.set(Calendar.YEAR, showTimeStart.dwYear);
        showStartTimeCal.set(Calendar.MONTH, showTimeStart.dwMonth);
        showStartTimeCal.set(Calendar.DAY_OF_MONTH, showTimeStart.dwDay);
        showStartTimeCal.set(Calendar.HOUR_OF_DAY, showTimeStart.dwHour);
        showStartTimeCal.set(Calendar.MINUTE, showTimeStart.dwMinute);
        showStartTimeCal.set(Calendar.SECOND, 0);
        showStartTimeCal.add(Calendar.SECOND, (int) (currentTimeSec_ - 3)); // 回放视频会有3秒的前置播放特性
        currentTimeTV_.setText(AndroidUtils.FormatTimer(showStartTimeCal.get(Calendar.YEAR), showStartTimeCal.get(Calendar.MONTH),
                showStartTimeCal.get(Calendar.DAY_OF_MONTH), showStartTimeCal.get(Calendar.HOUR_OF_DAY), showStartTimeCal.get(Calendar.MINUTE),
                showStartTimeCal.get(Calendar.SECOND)));
    }

    void OnPlayback() {
        Log.d(TAG, "OnPlayback");
        int userID = CameraHelper.GetUserID();
        if (userID == -1) {
            Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d(TAG, "请求回放");
        ClosePlayback();
        NET_DVR_VOD_PARA vodParma = new NET_DVR_VOD_PARA();
        vodParma.struBeginTime = playTimeStart;
        vodParma.struEndTime = showTimeStop;
        vodParma.byStreamType = 0;
        vodParma.struIDInfo.dwChannel = 1;
        vodParma.hWnd = surfaceView_.getHolder().getSurface();
        updateSeekbar = CameraHelper.OnPlayBackByTime(vodParma);
        if (!updateSeekbar) {
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
                case ENUM_Tick:
                    OnTick();
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