package app.cddic.com.smarter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import app.cddic.com.smarter.entity.MyCollectionMSG;
import app.cddic.com.smarter.R;

/**
 * Created by 小帆哥 on 2017/7/13.
 */

public class MyCollectionItemsAdapter extends BaseAdapter {
    private Context mContext;
    private List<MyCollectionMSG> mMyCollectionMSGList;

    public MyCollectionItemsAdapter(Context context) {
        super();
        mContext = context;
    }

    public MyCollectionItemsAdapter(Context context,List<MyCollectionMSG> myCollectionMSGList){
        this(context);
        mMyCollectionMSGList = myCollectionMSGList;
    }
    @Override
    public int getCount() {
        return mMyCollectionMSGList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.list_item_for_my_collection,viewGroup,false);
        }

        TextView name = (TextView) view.findViewById(R.id.name_textView);
        name.setText(mMyCollectionMSGList.get(i).getName());
        TextView message = (TextView) view.findViewById(R.id.message_textView);
        message.setText(mMyCollectionMSGList.get(i).getMessage());
        TextView time = (TextView) view.findViewById(R.id.time_textView);
        time.setText(mMyCollectionMSGList.get(i).getTime());
        return view;
    }
}
