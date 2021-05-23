package com.example.rgd_monitor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.rgd_monitor.R;
import com.example.rgd_monitor.RusRailwaysInfo;

import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter<RusRailwaysInfo> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<RusRailwaysInfo> list;

    public CustomArrayAdapter(@NonNull Context context, int resource, ArrayList<RusRailwaysInfo> list) {
        super(context, resource, list);
        this.list = list;
        layout = resource;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        RusRailwaysInfo item = list.get(position);

        viewHolder.tvExtSysName.setText(item.getExtSysName());
        viewHolder.tvDescriptionTickedId.setText(item.getDescription() + "\n" + item.getTickedId());
        viewHolder.tvBeginDate.setText(item.getIsKnownErrorDate());
        viewHolder.tvEndDate.setText(item.getTargetFinish());
        viewHolder.tvStatus.setText(item.getStatus());

        return convertView;
    }

    private class ViewHolder {
        public TextView tvExtSysName;
        public TextView tvDescriptionTickedId;
        public TextView tvBeginDate;
        public TextView tvEndDate;
        public TextView tvStatus;

        ViewHolder(View v) {
            tvExtSysName = v.findViewById(R.id.tv_extSysName);
            tvDescriptionTickedId = v.findViewById(R.id.tv_description_tickedId);
            tvBeginDate = v.findViewById(R.id.tv_begin_date);
            tvEndDate = v.findViewById(R.id.tv_end_date);
            tvStatus = v.findViewById(R.id.tv_status);
        }
    }

    public void updateAdapter(ArrayList<RusRailwaysInfo> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }
}
