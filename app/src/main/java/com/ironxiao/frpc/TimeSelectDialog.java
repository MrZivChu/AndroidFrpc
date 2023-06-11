package com.ironxiao.frpc;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;

public class TimeSelectDialog extends Dialog implements View.OnClickListener {

    public TimeSelectDialog(@NonNull Context context) {
        super(context);
    }

    public interface ClickCallback {
        void onClick(int year, int month, int day, int hour, int minute);
    }

    private ClickCallback ClickCallback_;

    public void SetCallBack(ClickCallback callback) {
        ClickCallback_ = callback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_select_dialog);
        SetDialogWidth();

        Button okBtn = findViewById(R.id.selectTimeBtn2);
        okBtn.setOnClickListener(this);
    }

    public void UpdateTimer(int year, int month, int day, int hour, int minute) {
        DatePicker dp = findViewById(R.id.date_picker2);
        TimePicker tp = findViewById(R.id.time_picker2);
        dp.updateDate(year, month - 1, day);
        tp.setHour(hour);
        tp.setMinute(minute);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.selectTimeBtn2:
                if (ClickCallback_ != null) {
                    DatePicker dp = findViewById(R.id.date_picker2);
                    TimePicker tp = findViewById(R.id.time_picker2);
                    ClickCallback_.onClick(dp.getYear(), dp.getMonth() + 1, dp.getDayOfMonth(), tp.getHour(), tp.getMinute());
                }
                break;
        }
    }

    public void SetDialogWidth() {
        Window window = getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        Display display = window.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        wlp.width = point.x;
        wlp.gravity = Gravity.BOTTOM;
        window.setBackgroundDrawableResource(android.R.color.background_dark);
        window.setAttributes(wlp);
    }
}
