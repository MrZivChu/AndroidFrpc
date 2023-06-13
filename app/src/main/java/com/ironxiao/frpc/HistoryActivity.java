package com.ironxiao.frpc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;

import com.ironxiao.frpc.helper.AndroidUtils;
import com.ironxiao.frpc.sql.CameraHistoryModel;
import com.ironxiao.frpc.sql.HistoryDBManager;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HistoryActivity extends Fragment {

    ListView listView_;
    Button timeBtn_;
    HistoryItemAdapter adapter_;
    List<CameraHistoryModel> dataList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_history, container, false);

        dataList = HistoryDBManager.QueryAllInfo(30, 1);
        listView_ = view.findViewById(R.id.listView);
        adapter_ = new HistoryItemAdapter(getContext(), dataList);
        listView_.setAdapter(adapter_);

        timeBtn_ = view.findViewById(R.id.timeBtn);
        timeBtn_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnTimeClick();
            }
        });

        return view;
    }

    void OnTimeClick() {
        Calendar cal = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                timeBtn_.setText(AndroidUtils.FormatTimer(year, (monthOfYear + 1), dayOfMonth));
            }
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        DatePicker datePicker = datePickerDialog.getDatePicker();
        datePicker.setMaxDate(new Date().getTime()); // 设置日期的上限日期
        cal.add(Calendar.DAY_OF_MONTH, -6);
        datePicker.setMinDate(cal.getTime().getTime());
        datePickerDialog.show();
    }
}