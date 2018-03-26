package app.cddic.com.smarter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.utils.CommonViewHolder;

/**
 * SmartApp
 * app.cddic.com.smarter.adapter
 * Created by Pantiy on 2017/5/10.
 * Copyright © 2017 All rights Reserved by Pantiy
 */

public class ChatMessageItemsAdapter extends BaseAdapter {

    private static final String[] sContactNameItems = {"张三", "李四"};
    private static final String[] sChatContentItems = {"你在家么？", "有事想找你"};
    private static final String[] sChatMessageTimeItems = {"昨天", "5月1日"};
    private Context mContext;

    public ChatMessageItemsAdapter(Context context) {
        mContext = context;

    }

    @Override
    public int getCount() {
        return sChatContentItems.length;
    }

    @Override
    public Object getItem(int position) {
        return sContactNameItems[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.list_item_for_chat_message, parent, false);
        }
        TextView contactNameTv = CommonViewHolder.get(convertView, R.id.contactName_tv);
        contactNameTv.setText(sContactNameItems[position]);
        TextView chatContentTv = CommonViewHolder.get(convertView, R.id.chatContent_tv);
        chatContentTv.setText(sChatContentItems[position]);
        TextView chatMessageTimeTv = CommonViewHolder.get(convertView, R.id.chatTime_tv);
        chatMessageTimeTv.setText(sChatMessageTimeItems[position]);
        return convertView;
    }
}
