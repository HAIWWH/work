package app.cddic.com.smarter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.entity.PluginManageMSG;

/**
 * Created by VileWind on 2017/5/5 0005.
 */

public class PluginManageItemsAdapter extends BaseAdapter {
    private Context mContext;
    private List<PluginManageMSG> mPluginManageMSGList;
    public PluginManageItemsAdapter(Context context) {
        super();
             mContext = context;
             }
    public PluginManageItemsAdapter(Context context, List<PluginManageMSG>pluginItemsList) {
        this(context);
        mPluginManageMSGList = pluginItemsList;
    }

@Override
public int getCount() {
        return 6;
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
        view = LayoutInflater.from(mContext).inflate(R.layout.list_item_for_plugin,viewGroup,false);
        }
    TextView deviceName = (TextView) view.findViewById(R.id.name_textView);
    deviceName.setText(mPluginManageMSGList.get(i).getDeviceName());
    TextView pluginName = (TextView)view.findViewById(R.id.pluginId_textView);
    pluginName.setText(mPluginManageMSGList.get(i).getPluginName());



        return view;
        }
        }
