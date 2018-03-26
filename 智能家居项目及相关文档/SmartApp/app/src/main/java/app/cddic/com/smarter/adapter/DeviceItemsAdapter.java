package app.cddic.com.smarter.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.activity.base.DeviceActivity;
import app.cddic.com.smarter.entity.DeviceMSG;
import app.cddic.com.smarter.utils.CommonViewHolder;


/**
 * Created by Pantiy on 2017/3/13.
 * Copyright © 2016 All rights Reserved by Pantiy
 */

public class DeviceItemsAdapter extends BaseAdapter {

    private Context mContext;
    private List<DeviceMSG> mDeviceItemList;

    private DeviceItemsAdapter(Context context) {
        super();
        mContext = context;
    }

    public DeviceItemsAdapter(Context context, List<DeviceMSG> deviceItemList) {
        this(context);
        mDeviceItemList = deviceItemList;
    }

    @Override
    public int getCount() {
        return mDeviceItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return mDeviceItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.list_item_for_device,viewGroup,false);
        }

        TextView state = (TextView) view.findViewById(R.id.deviceState_textView);
        state.setText(mDeviceItemList.get(i).getState());
        TextView deviceName = (TextView) view.findViewById(R.id.deviceName_textView);
        deviceName.setText(mDeviceItemList.get(i).getName());
        final TextView mNewsMessageNumber = (TextView)view.findViewById(R.id.newMessageNumber_textView);
        LinearLayout left = CommonViewHolder.get(view, R.id.leftDeviceItem_linearLayout);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDeviceActivity(DeviceActivity.Type.DEVICE_DETAIL, "");

            }
        });

        LinearLayout right = CommonViewHolder.get(view, R.id.rightDeviceItem_linearLayout);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDeviceActivity(DeviceActivity.Type.DEVICE_MESSAGE, "");
                mNewsMessageNumber.setVisibility(View.INVISIBLE);

            }
        });

        return view;
    }

    private void startDeviceActivity(int type, String deviceName) {
        Intent intent = DeviceActivity.newInstance(mContext, type, deviceName);
        mContext.startActivity(intent);
    }
}
