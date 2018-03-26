package app.cddic.com.smarter.fragment.device;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.adapter.DeviceRealTimeVideoItemsAdapter;
import app.cddic.com.smarter.entity.RealTimeVideoMSG;
import app.cddic.com.smarter.fragment.base.BaseFragment;
import app.cddic.com.smarter.widget.TopView;

import static app.cddic.com.smarter.adapter.DeviceRealTimeVideoItemsAdapter.getCheckState;


public class DeviceRealTimeVideoFragment extends BaseFragment {
    private TopView mTopView;
    private  List<RealTimeVideoMSG> mRealTimeVideoMSGList;
    private Button mEditBtn;
    private Button mSelectAllBtn;
    private Button mDelateBtn;
    private ListView mListView;
    private LinearLayout mLinearLayout;
    private DeviceRealTimeVideoItemsAdapter deviceRealTimeVideoitemsAdapter;
    private int mListLength;

    @Override
    protected void initViews() {
        mTopView = findView(R.id.topView);
        mTopView.setText("设备","实时设备","厨房设备");
        mEditBtn = findView(R.id.edit_btn);
        mListView = findView(R.id.listView);
        mSelectAllBtn = findView(R.id.selectAll_Btn);
        mDelateBtn = findView(R.id.delete_btn);
        mLinearLayout = findView(R.id.linearLayout);
        mDelateBtn.setVisibility(View.INVISIBLE);
        mSelectAllBtn.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void setupAdapters() {
        mRealTimeVideoMSGList = new ArrayList<>();
        for(int i = 0; i < 4; i++) {
            RealTimeVideoMSG item = null;
            switch (i) {
                case 0:
                    item = new RealTimeVideoMSG("摄像头1", "拍摄时间:2016/6/4  16:12");
                    break;
                case 1:
                    item = new RealTimeVideoMSG("摄像头2", "拍摄时间:2016/6/3  9:03");
                    break;
                case 2:
                    item = new RealTimeVideoMSG("摄像头3", "拍摄时间:2016/6/2  15:16");
                    break;
                case 3:
                    item = new RealTimeVideoMSG("摄像头4", "拍摄时间:2016/6/1  12:12");
                    break;
            }
            mRealTimeVideoMSGList.add(item);
        }
            mListLength= mRealTimeVideoMSGList.size();
         deviceRealTimeVideoitemsAdapter = new DeviceRealTimeVideoItemsAdapter(getActivity(), mRealTimeVideoMSGList);
        mListView.setAdapter(deviceRealTimeVideoitemsAdapter);
    }

    @Override
    protected void setupListeners() {
        mTopView.setupListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditBtn.getText().toString().equals("编辑")) {
                    mEditBtn.setText("完成");
                    mSelectAllBtn.setVisibility(View.VISIBLE);
                    mDelateBtn.setVisibility(View.VISIBLE);
                    mLinearLayout.setVisibility(View.INVISIBLE);
                    DeviceRealTimeVideoItemsAdapter.setShowState(true);
                    dataChanged();
                }
                else {
                    mEditBtn.setText("编辑");
                    mSelectAllBtn.setVisibility(View.INVISIBLE);
                    mDelateBtn.setVisibility(View.INVISIBLE);
                    mLinearLayout.setVisibility(View.VISIBLE);
                    DeviceRealTimeVideoItemsAdapter.setShowState(false);
                    dataChanged();

                }


            }
        });
        mSelectAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < mRealTimeVideoMSGList.size(); i++) {
                    getCheckState().put(i, true);
                }
                dataChanged();
            }
        });
        mDelateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=mListLength-1;i>=0;i--){
                    if(getCheckState().get(i))
                    {
                        mRealTimeVideoMSGList.remove(i);
                    }
                }
                mListLength = mRealTimeVideoMSGList.size();
                deviceRealTimeVideoitemsAdapter = new DeviceRealTimeVideoItemsAdapter(getContext(), mRealTimeVideoMSGList);
                mListView.setAdapter(deviceRealTimeVideoitemsAdapter);
            }
        });

    }
    private void dataChanged() {
        // 通知listView刷新
        deviceRealTimeVideoitemsAdapter.notifyDataSetChanged();
    };

    @Override
        protected int setLayoutRes() {
            return R.layout.fragment_real_time_video_device;
        }

    }
