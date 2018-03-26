package app.cddic.com.smarter.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.activity.base.DeviceActivity;
import app.cddic.com.smarter.entity.DeviceManageMSG;
import app.cddic.com.smarter.utils.CommonViewHolder;

/**
 * Created by VileWind on 2017/7/27 0027.
 */

public class DeviceManageELVAdapter extends BaseExpandableListAdapter {
    private static final String TAG = "DeviceManageELVAdapter";
    private static final String[] sDeviceParent = new String[]{"设备关联"};
    private static final String[][] sDeviceChild=new String[][]{{"mmmm","李师傅","谢谢"},{}};
    private Context context;
    private List<DeviceManageMSG> mDeviceManageMSGList;
    public static final int EXPANDABLE_POSITION = 0;
    public static final int BUTTON_LIST=1;

    public DeviceManageELVAdapter(Context mContext,List<DeviceManageMSG> deviceManageMSGList){
        super();
        context=mContext;
        mDeviceManageMSGList = deviceManageMSGList;
    }


    @Override
    public int getGroupCount() {
        return sDeviceParent.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(groupPosition==EXPANDABLE_POSITION){
        return 3;}else {
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return sDeviceParent[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return sDeviceChild[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (groupPosition == EXPANDABLE_POSITION) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.list_device_manage, parent, false);
            }
            TextView textView = CommonViewHolder.get(convertView, R.id.elvList_textView);
            textView.setText(sDeviceParent[groupPosition]);
            textView.setTextColor(android.graphics.Color.parseColor("#000000"));
            TextView correlationCount=CommonViewHolder.get(convertView,R.id.countCorrelation_textView);
            correlationCount.setTextColor(android.graphics.Color.parseColor("#000000"));
            correlationCount.setText("1/3");
            return convertView;
        }
                Button mDelAllCorrelationBtn=CommonViewHolder.get(convertView,R.id.deleteAllCorrelation_btn);
                Button mForceOffLineBtn=CommonViewHolder.get(convertView,R.id.forceAllOffLine_btn);
        return null;
    }
    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(groupPosition==EXPANDABLE_POSITION){
            if(convertView==null){
                convertView = LayoutInflater.from(context).inflate(R.layout.list_item_device_correlation, parent, false);
            }
            ImageView head_picture=CommonViewHolder.get(convertView,R.id.correlation_imageView);
            TextView correlation_name=CommonViewHolder.get(convertView,R.id.name_Tv);
            correlation_name.setText(mDeviceManageMSGList.get(childPosition).getDeviceName());
            TextView mDeviceCorrelationDateTV=CommonViewHolder.get(convertView,R.id.time_textView);
            mDeviceCorrelationDateTV.setText(mDeviceManageMSGList.get(childPosition).getDeviceDate());
            TextView mDeviceState=CommonViewHolder.get(convertView,R.id.stateDeviceCorrelation_textView);
            mDeviceState.setText(mDeviceManageMSGList.get(childPosition).getDeviceState());
            LinearLayout btn= CommonViewHolder.get(convertView,R.id.correlationClick_linearLayout);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startDeviceActivity(DeviceActivity.Type.DEVICE_DETAIL, "","");
                }
            });
            return convertView;
        }
        return null;
    }

    private void startDeviceActivity(int type, String deviceName,String deviceIcon) {
        Intent intent = DeviceActivity.newInstance(context, type, deviceName);
        context.startActivity(intent);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
