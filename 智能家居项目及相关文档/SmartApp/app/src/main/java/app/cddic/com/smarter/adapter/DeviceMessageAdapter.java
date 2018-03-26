package app.cddic.com.smarter.adapter;

/**
 * Created by asus on 2017/7/31.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.entity.DeviceMessageMSG;

/**
 * Created by VileWind on 2017/5/5 0005.
 */

public class DeviceMessageAdapter extends BaseAdapter {
    private Context mContext;
    private List<DeviceMessageMSG> mDeviceMessageMSGList;
    public DeviceMessageAdapter(Context context) {
        super();
        mContext = context;
    }
    public DeviceMessageAdapter(Context context, List<DeviceMessageMSG> deviceMessageMSGListd){
        this(context);
        mDeviceMessageMSGList = deviceMessageMSGListd;


    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.list_item_for_device_message,viewGroup,false);
        }
        TextView name = (TextView)view.findViewById(R.id.alarm_textView);
        name.setText(mDeviceMessageMSGList.get(i).getName());
        TextView type = (TextView)view.findViewById(R.id.type_textView);
        type.setText(mDeviceMessageMSGList.get(i).getType());
        TextView date = (TextView)view.findViewById(R.id.date_textView);
        date.setText(mDeviceMessageMSGList.get(i).getDate());

        return view;
    }
}