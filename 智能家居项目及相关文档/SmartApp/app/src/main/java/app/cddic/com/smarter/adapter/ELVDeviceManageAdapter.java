package app.cddic.com.smarter.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.utils.CommonViewHolder;

/**
 * Created by VileWind on 2017/6/13 0013.
 */

public class ELVDeviceManageAdapter extends BaseExpandableListAdapter {
    private static final String TAG = "ELVDeviceManageAdapter";
    private static final String [] sElvGroupItems=new String[]{"设备关联"};


    private Context mContext;

    public ELVDeviceManageAdapter(Context context) {
        mContext = context;
    }
    @Override
    public int getGroupCount() {
        return 1;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 3;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return 1;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childPosition;
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
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.expandable_item_icon, parent, false);
        }
        ImageView imageView = CommonViewHolder.get(convertView, R.id.contact_picture);
        imageView.setImageResource(R.drawable.ic_qr_code);
        imageView.setBackgroundColor(Color.GRAY);
        TextView PtextView = CommonViewHolder.get(convertView, R.id.power);
        PtextView.setText("权限");
        TextView TtextView=CommonViewHolder.get(convertView,R.id.time);
        TtextView.setText("时间");
        TextView LtextView=CommonViewHolder.get(convertView,R.id.load);
        LtextView.setText("登录状态");
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.expandable_item_for_device_manage, parent, false);
        }

        convertView.setPadding(35, 0, 0, 0);
        ImageView imageView = CommonViewHolder.get(convertView, R.id.contact_ImageView);
        imageView.setImageResource(R.drawable.ic_qr_code);
        imageView.setBackgroundColor(Color.GRAY);
        TextView PtextView = CommonViewHolder.get(convertView, R.id.user_name);
        PtextView.setText("张师傅");
        TextView TtextView=CommonViewHolder.get(convertView,R.id.get_time);
        TtextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getdate();获取时间
            }
        });
        TextView LtextView=CommonViewHolder.get(convertView,R.id.state);
        LtextView.setText("登录状态");

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}


