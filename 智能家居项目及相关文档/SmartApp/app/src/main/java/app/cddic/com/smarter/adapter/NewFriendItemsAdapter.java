package app.cddic.com.smarter.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.entity.NewFriendMSG;

/**
 * Created by VileWind on 2017/5/5 0005.
 */

public class NewFriendItemsAdapter extends BaseAdapter {
    private Context mContext;
    private List<NewFriendMSG>mNewFriendItemsList;
    public NewFriendItemsAdapter(Context context) {
        super();
        mContext = context;
    }
    public NewFriendItemsAdapter(Context context, List<NewFriendMSG> newFriendItemsList)
    {
        this(context);
        mNewFriendItemsList = newFriendItemsList;
    }

    @Override
    public int getCount() {
        return mNewFriendItemsList.size();
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
            view = LayoutInflater.from(mContext).inflate(R.layout.list_item_for_new_friend,viewGroup,false);
            TextView friendName = (TextView)view.findViewById(R.id.name_textView);
            friendName.setText(mNewFriendItemsList.get(i).getFriendName());
            TextView friendNews = (TextView)view.findViewById(R.id.leaveWord_textView);
            friendNews.setText(mNewFriendItemsList.get(i).getFriendNews());
            TextView addTime = (TextView)view.findViewById(R.id.time_textView);
            addTime.setText(mNewFriendItemsList.get(i).getAddTime());
            TextView friendState = (TextView)view.findViewById(R.id.state_textView);
            friendState.setText(mNewFriendItemsList.get(i).getFriendState());
            if(mNewFriendItemsList.get(i).getFriendState().equals("已经同意"))
            {
                friendState.setBackgroundColor(Color.parseColor("#000000"));
            }
            else
            {
                friendState.setBackgroundColor(Color.parseColor("#808080"));
            }
        }

        return view;
    }
}
