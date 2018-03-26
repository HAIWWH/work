package app.cddic.com.smarter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.entity.RealTimeVideoMSG;


public class DeviceRealTimeVideoItemsAdapter extends BaseAdapter {
    private static boolean mShowSate=false;
    private static HashMap<Integer, Boolean> mCheckState;
    private Context mContext;
    private List<RealTimeVideoMSG> mRealTimeVideoMSGList;

    public DeviceRealTimeVideoItemsAdapter(Context context) {
        super();
        mContext = context;
    }

    public DeviceRealTimeVideoItemsAdapter(Context context, List<RealTimeVideoMSG> realTimeVideoMSGList){
        this(context);
        mRealTimeVideoMSGList = realTimeVideoMSGList;
        mCheckState = new HashMap<Integer, Boolean>();
        initDate();
    }
    private void initDate() {
        for (int i = 0; i < mRealTimeVideoMSGList.size(); i++) {
            getCheckState().put(i, false);
        }
    }
    @Override
    public int getCount() {
        return mRealTimeVideoMSGList.size();
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.list_item_for_real_time_video_device,viewGroup,false);
        }
        ViewHolder holder = new ViewHolder();
        holder.mCheckBox = (CheckBox)view.findViewById(R.id.checkBox);
        holder.mVideoName = (TextView) view.findViewById(R.id.name_textView);
        holder.mVideoTime = (TextView) view.findViewById(R.id.time_textView);
        view.setTag(holder);
        holder.mCheckBox.setChecked(getCheckState().get(i));
        if(getShowState()) {
            holder.mCheckBox.setVisibility(View.VISIBLE);
        }
        else {
            holder.mCheckBox.setVisibility(View.GONE);
        }
        holder.mVideoName.setText(mRealTimeVideoMSGList.get(i).getVideoName());
        holder.mVideoTime.setText(mRealTimeVideoMSGList.get(i).getVideoTime());
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                getCheckState().put(i,isChecked);
                notifyDataSetChanged();
            }
        });

        return view;
    }
    public static void setShowState(Boolean showState){
        mShowSate = showState;

    }
    public static Boolean getShowState(){
        return mShowSate;
    }
    public static  HashMap<Integer, Boolean> getCheckState(){
        return mCheckState;
    }
    public static class ViewHolder {
        CheckBox mCheckBox;
        TextView mVideoName;
        TextView mVideoTime;

    }
}
