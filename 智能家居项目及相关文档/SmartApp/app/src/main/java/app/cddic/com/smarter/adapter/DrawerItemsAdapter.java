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
 * SmartApp
 * app.cddic.com.smarter.adapter
 * Created by Pantiy on 2017/5/4.
 * Copyright © 2017 All rights Reserved by Pantiy
 */

public class DrawerItemsAdapter extends BaseExpandableListAdapter {

    private static final String TAG = "DrawerItemsAdapter";

    private static final String[] sDrawerGroupItems = new String[]{"我的资料", "我的收藏", "我的设置", "关于与帮助"};
    private static final String[][] sDrawerChildItems = new String[][]{null, null,
            {"账号管理", "消息管理", "流量管理", "记录管理", "隐私管理", "插件管理"},
            null};
    public static final int ABOUT_AND_HELP = 3;
    public static final int EXPANDABLE_POSITION = 2;
    public static final int ACCOUNT_MANAGE = 0;
    public static final int MESSAGE_MANAGE = 1;
    public static final int DATA_MANAGE = 2;
    public static final int HISTORY_MANAGE = 3;
    public static final int PRIVACY_MANAGE = 4;
    public static final int PLUGIN_MANAGE = 5;

    private Context mContext;

    public DrawerItemsAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getGroupCount() {
        return sDrawerGroupItems.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (groupPosition == EXPANDABLE_POSITION) {
            return sDrawerChildItems[EXPANDABLE_POSITION].length;
        } else {
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return sDrawerGroupItems[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return sDrawerChildItems[groupPosition][childPosition];
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_with_icon, parent, false);
        }
        ImageView imageView = CommonViewHolder.get(convertView, R.id.imageView);
        imageView.setImageResource(R.mipmap.ic_setting);
        imageView.setBackgroundColor(Color.GRAY);
        TextView textView = CommonViewHolder.get(convertView, R.id.itemName_textView);
        textView.setText(sDrawerGroupItems[groupPosition]);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (groupPosition == EXPANDABLE_POSITION) {
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_with_icon, parent, false);
            }
            convertView.setPadding(35, 0, 0, 0);
            ImageView imageView = CommonViewHolder.get(convertView, R.id.imageView);
            imageView.setImageResource(R.mipmap.ic_setting);
            imageView.setBackgroundColor(Color.GRAY);
            TextView textView = CommonViewHolder.get(convertView, R.id.itemName_textView);
            textView.setText(sDrawerChildItems[EXPANDABLE_POSITION][childPosition]);
            return convertView;
        }
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
