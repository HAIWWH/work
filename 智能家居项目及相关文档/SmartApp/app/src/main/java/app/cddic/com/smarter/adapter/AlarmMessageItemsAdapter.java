package app.cddic.com.smarter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.utils.CommonViewHolder;

/**
 * SmartApp
 * app.cddic.com.smarter.adapter
 * Created by Pantiy on 2017/5/10.
 * Copyright © 2017 All rights Reserved by Pantiy
 */

public class AlarmMessageItemsAdapter extends BaseAdapter {

    private static final String[] sDeviceNameItems = new String[] {"厨房设备", "照明设备", "主卧设备"};
    private static final String[] sAlarmClassItems = new String[] {"一般警报", "严重警报", "一般警报"};
    private static final String[] sAlarmMessageTimeItems = new String[] {"16:30", "昨天" ,"5月1日"};

    private Context mContext;

    public AlarmMessageItemsAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return sDeviceNameItems.length;
    }

    @Override
    public Object getItem(int position) {
        return sDeviceNameItems[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.list_item_for_alarm_message, parent, false);
        }
        TextView deviceNameTv = CommonViewHolder.get(convertView, R.id.deviceName_tv);
        deviceNameTv.setText(sDeviceNameItems[position]);
        TextView alarmClassTv = CommonViewHolder.get(convertView, R.id.alarmClass_tv);
        alarmClassTv.setText(sAlarmClassItems [position]);
        TextView alarmMessageTimeTv = CommonViewHolder.get(convertView, R.id.alarmMessageTime_tv);
        alarmMessageTimeTv.setText(sAlarmMessageTimeItems[position]);
        return convertView;
    }
}
