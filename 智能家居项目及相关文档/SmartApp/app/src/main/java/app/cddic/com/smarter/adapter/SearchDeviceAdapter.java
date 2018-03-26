package app.cddic.com.smarter.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.activity.base.DeviceLoginActivity;
import app.cddic.com.smarter.entity.RetMSG;

/**
 * Created by asus on 2017/11/30.
 */

public class SearchDeviceAdapter extends BaseAdapter {
    private Context mContext;
    private Set<RetMSG> mDevice = new HashSet< RetMSG>();
    private List< RetMSG> mRetMSGList = new ArrayList<>();
    public SearchDeviceAdapter(Context context) {
        super();
        mContext = context;
    }
    public SearchDeviceAdapter(Context context,Set< RetMSG> device) {
        super();
        mContext = context;
        mDevice = device;
        Iterator< RetMSG> iterable = device.iterator();
        while (iterable.hasNext()){
            RetMSG retMSG = iterable.next();
           mRetMSGList.add(retMSG);
        }
    }
    @Override
    public int getCount() {
        return mDevice.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_for_search_device,parent,false);
        TextView deviceName = (TextView)convertView.findViewById(R.id.deviceName_textView);

        if (mRetMSGList.size()!=0)
            deviceName.setText(mRetMSGList.get(position).getInfo());
        LinearLayout linearLayout = (LinearLayout)convertView.findViewById(R.id.item_linear);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = DeviceLoginActivity.newInstance(mContext,mRetMSGList.get(position));
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }
}
