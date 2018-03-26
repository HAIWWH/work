package app.cddic.com.smarter.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.activity.base.ContactActivity;
import app.cddic.com.smarter.entity.DeviceContactMSG;

/**
 * Created by VileWind on 2017/5/5 0005.
 */

public class DeviceContactItemsAdapter extends BaseAdapter {
    private Context mContext;
    private List<DeviceContactMSG>mDeviceContactItemsList;
    public DeviceContactItemsAdapter(Context context) {
        super();
        mContext = context;
    }
    public DeviceContactItemsAdapter(Context context, List<DeviceContactMSG> deviceContactItemsList)
    {
        this(context);
        mDeviceContactItemsList= deviceContactItemsList;
    }
    @Override
    public int getCount() {
        return 5;
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
            view = LayoutInflater.from(mContext).inflate(R.layout.list_item_for_device_contact,viewGroup,false);
            TextView contactName = (TextView)view.findViewById(R.id.name_textView);
            contactName.setText(mDeviceContactItemsList.get(i).getContactName());
            TextView deviceName = (TextView)view.findViewById(R.id.connectDevice_textView);
            deviceName.setText(mDeviceContactItemsList.get(i).getDeviceName());
            TextView deviceState = (TextView)view.findViewById(R.id.state_textView);
            deviceState.setText(mDeviceContactItemsList.get(i).getDeciceState());
            ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = ContactActivity.newInstance(mContext, ContactActivity.Type.CONTACT_DETAIL,"");
                    mContext.startActivity(intent);
                }
            });
            if(mDeviceContactItemsList.get(i).getDeciceState().equals("在线"))
            {
                deviceState.setTextColor(android.graphics.Color.parseColor("#6C0124"));
            }
            else
                {
                    deviceState.setTextColor(android.graphics.Color.parseColor("#6E6363"));
                }
        }

        return view;
    }
}
