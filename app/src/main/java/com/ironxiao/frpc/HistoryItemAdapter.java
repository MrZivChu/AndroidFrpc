package com.ironxiao.frpc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ironxiao.frpc.helper.AndroidUtils;
import com.ironxiao.frpc.sql.CameraHistoryModel;

import java.util.List;

public class HistoryItemAdapter extends BaseAdapter {
    Context context_;
    List<CameraHistoryModel> list_;

    public HistoryItemAdapter(Context context, List<CameraHistoryModel> list) {
        context_ = context;
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
        CameraHistoryModel bean = list_.get(i);
        holder.checkTimeTV.setText(AndroidUtils.GetFormatTimeFromStampUnix(Integer.parseInt(bean.TimeStamp)));
        holder.gasTypeTV.setText("类型");
        holder.gasValueTV.setText(String.valueOf(bean.GasValue));
        holder.minTV.setText("0");
        holder.maxTV.setText("100");
        holder.idTV.setText(String.valueOf(i + 1));
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