package app.cddic.com.smarter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import app.cddic.com.smarter.R;

/**
 * Created by VileWind on 2017/6/13 0013.
 */

public class DevicePluginItemsAdapter extends BaseAdapter {
    private Context mContext;
    private Button mSearchUpdateBtn;
    private Button mDelPluginBtn;

    public DevicePluginItemsAdapter(Context context) {
        super();
        mContext = context;
    }


    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_for_device_manage, parent, false);
        }
    mSearchUpdateBtn=(Button)convertView.findViewById(R.id.searchUpdate_btn);
        mSearchUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });
       mDelPluginBtn=(Button)convertView.findViewById(R.id.updatePlugin_delPlugin_btn);
        mDelPluginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });
        return convertView;
    }
}
