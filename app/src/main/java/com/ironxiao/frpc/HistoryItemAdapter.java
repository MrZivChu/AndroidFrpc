package com.ironxiao.frpc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ironxiao.frpc.helper.AndroidUtils;
import com.ironxiao.frpc.helper.DataDefines;
import com.ironxiao.frpc.helper.ProjectUtils;
import com.ironxiao.frpc.sql.CameraHistoryModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryItemAdapter extends BaseAdapter {
    Context context_;
    ArrayList<DataDefines.SGasInfo> list_;
    int pageIndex_ = 0;
    int pageSize_ = 0;

    public HistoryItemAdapter(Context context) {
        context_ = context;
    }

    public void InitData(ArrayList<DataDefines.SGasInfo> list, int pageIndex, int pageSize) {
        pageIndex_ = pageIndex;
        pageSize_ = pageSize;
        list_ = list;
    }

    @Override
    public int getCount() {
        return list_.size();
    }

    @Override
    public Object getItem(int i) {
        return list_.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context_).inflate(R.layout.activity_history_item_adapter, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        DataDefines.SGasInfo model = list_.get(i);
        holder.checkTimeTV.setText(AndroidUtils.GetFormatTimeFromStampUnix(model.timeStamp));
        holder.gasTypeTV.setText(model.gasName);
        holder.gasValueTV.setText(String.valueOf(model.gasValue));
        holder.minTV.setText(String.valueOf(model.firstValue));
        holder.maxTV.setText(String.valueOf(model.secondValue));
        holder.gasValueTV.setTextColor(ProjectUtils.GetWarnColor(model.level));
        holder.idTV.setText(String.valueOf((pageIndex_ - 1) * pageSize_ + (i + 1)));
        return view;
    }

    class ViewHolder {
        TextView idTV, checkTimeTV, gasTypeTV, gasValueTV, minTV, maxTV;

        public ViewHolder(View itemView) {
            idTV = itemView.findViewById(R.id.index);
            checkTimeTV = itemView.findViewById(R.id.checkTimeTV);
            gasTypeTV = itemView.findViewById(R.id.gasTypeTV);
            gasValueTV = itemView.findViewById(R.id.gasValueTV);
            minTV = itemView.findViewById(R.id.minTV);
            maxTV = itemView.findViewById(R.id.maxTV);

        }
    }
}