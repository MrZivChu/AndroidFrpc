package com.ironxiao.frpc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.ironxiao.frpc.helper.AndroidUtils;
import com.ironxiao.frpc.helper.DataDefines;
import com.ironxiao.frpc.helper.GasInfoHelper;
import com.ironxiao.frpc.helper.ProjectUtils;
import com.ironxiao.frpc.sql.CameraHistoryModel;
import com.ironxiao.frpc.sql.HistoryDBManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HistoryActivity extends Fragment {
    private static final String TAG = "--zwh-- HistoryActivity";
    ListView listView_;
    TextView pageTV_;
    Button timeBtn_;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_history, container, false);

        listView_ = view.findViewById(R.id.listView);
        pageTV_ = view.findViewById(R.id.pageTV);
        Button preBtn = view.findViewById(R.id.preBtn);
        Button nextBtn = view.findViewById(R.id.nextBtn);
        timeBtn_ = view.findViewById(R.id.timeBtn);

        preBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pageIndex_ - 1 > 0) {
                    pageIndex_--;
                    RefreshData();
                }
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pageIndex_ + 1 <= pageCount_) {
                    pageIndex_++;
                    RefreshData();
                }
            }
        });

        timeBtn_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnTimeClick();
            }
        });

        return view;
    }

    void OnTimeClick() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                pageIndex_ = 1;
                year_ = year;
                month_ = monthOfYear;
                day_ = dayOfMonth;
                RefreshData();
            }
        }, year_, month_, day_);
        DatePicker datePicker = datePickerDialog.getDatePicker();
        datePicker.setMaxDate(new Date().getTime()); // 设置日期的上限日期
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -6);
        datePicker.setMinDate(cal.getTime().getTime());
        datePickerDialog.show();
    }

    int year_;
    int month_;
    int day_;
    int pageIndex_ = 1;
    int pageCount_ = 1;
    int pageSize_ = 30;
    HistoryItemAdapter adapter_ = null;
    ArrayList<DataDefines.SGasInfo> list_ = new ArrayList<>();

    void RefreshData() {
        int startTimeStamp = (int) AndroidUtils.GetStampUnix(year_, month_, day_, 0, 0, 0);
        int endTimeStamp = (int) AndroidUtils.GetStampUnix(year_, month_, day_, 24, 0, 0);
        int allCount = HistoryDBManager.QueryAllCount(startTimeStamp, endTimeStamp);
        list_.clear();
        if (allCount > 0) {
            pageCount_ = allCount / pageSize_ + ((allCount % pageSize_) > 0 ? 1 : 0);
            List<CameraHistoryModel> dataList = HistoryDBManager.QueryAllInfo(pageSize_, pageIndex_, startTimeStamp, endTimeStamp);
            for (int i = 0; i < dataList.size(); i++) {
                ArrayList<DataDefines.SGasInfo> array = ProjectUtils.GasValuesToSGasInfoList(dataList.get(i).GasValues, dataList.get(i).TimeStamp);
                list_.addAll(array);
            }
        } else {
            pageCount_ = 0;
            pageIndex_ = 0;
        }
        pageTV_.setText(String.valueOf(pageIndex_) + "/" + String.valueOf(pageCount_));
        timeBtn_.setText(AndroidUtils.FormatTimer(year_, month_ + 1, day_));
        if (adapter_ == null) {
            adapter_ = new HistoryItemAdapter(getContext());
            adapter_.InitData(list_, pageIndex_, pageSize_);
            listView_.setAdapter(adapter_);
        } else {
            adapter_.InitData(list_, pageIndex_, pageSize_);
            adapter_.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Calendar cal = Calendar.getInstance();
        year_ = cal.get(Calendar.YEAR);
        month_ = cal.get(Calendar.MONTH);
        day_ = cal.get(Calendar.DAY_OF_MONTH);
        RefreshData();
    }
}