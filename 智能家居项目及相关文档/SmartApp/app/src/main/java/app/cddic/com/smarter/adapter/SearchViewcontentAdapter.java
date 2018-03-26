package app.cddic.com.smarter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import app.cddic.com.smarter.R;

/**
 * Created by Hai on 2017/7/31.
 */

public class SearchViewcontentAdapter extends BaseAdapter {
    private Context mContext;
    public SearchViewcontentAdapter(Context context){
        super();
        mContext = context;
    }

    @Override
    public int getCount() {
        return 4;
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

        if(convertView == null){

            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_for_device,parent,false);
        }
        return convertView;
    }
}
