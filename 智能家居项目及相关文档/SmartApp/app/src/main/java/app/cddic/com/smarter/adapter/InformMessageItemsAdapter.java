package app.cddic.com.smarter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import app.cddic.com.smarter.R;

/**
 * SmartApp
 * app.cddic.com.smarter.adapter
 * Created by Pantiy on 2017/5/10.
 * Copyright © 2017 All rights Reserved by Pantiy
 */

public class InformMessageItemsAdapter extends BaseAdapter {

    private static final String[] sInformTitleItems = new String[] {"通知", "公告"};
    private static final String[] sInformContentItems = new String[] {
            "关联请求确认", "平台最新活动"};
    private static final String[] sInformMessageTimeItems = new String[] {"17:30", "16:30"};
    private Context mContext;

    public InformMessageItemsAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return sInformTitleItems.length;
    }

    @Override
    public Object getItem(int position) {
        return sInformTitleItems[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.list_item_for_inform_message, parent, false);
        }
        ViewHolder holder = new ViewHolder();
        holder.informTitleTv = (TextView) convertView.findViewById(R.id.inform_title_tv);
        holder.informTitleTv.setText(sInformTitleItems[position]);
        holder.informContentTv =(TextView)convertView.findViewById( R.id.inform_content_tv);
        holder.informContentTv.setText(sInformContentItems[position]);
        holder.informMessageTimeTv = (TextView)convertView.findViewById(R.id.inform_time_tv);
        holder.informMessageTimeTv.setText(sInformMessageTimeItems[position]);
        convertView.setTag(holder);
        return convertView;
    }
    public static class ViewHolder{
         public TextView informTitleTv;
        TextView informContentTv;
        TextView informMessageTimeTv;
    }
}
