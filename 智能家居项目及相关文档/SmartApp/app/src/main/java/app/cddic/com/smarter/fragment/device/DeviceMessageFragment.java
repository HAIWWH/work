package app.cddic.com.smarter.fragment.device;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.activity.base.DeviceDetailActivity;
import app.cddic.com.smarter.adapter.DeviceMessageAdapter;
import app.cddic.com.smarter.entity.DeviceMessageMSG;
import app.cddic.com.smarter.fragment.base.BaseFragment;
import app.cddic.com.smarter.utils.CommonViewHolder;
import app.cddic.com.smarter.widget.TopView;

/**
 * Created by Hai on 2017/4/26.
 */

public class DeviceMessageFragment extends BaseFragment {

    private static final String KEY_DEVICE_NAME = "deviceName";

    private String mDeviceName;
    private ListView mListView;
    private FragmentManager fm;
    private Fragment fragment;
    private TopView mTopView;
    private Button mSendMessageBtn;

    public static DeviceMessageFragment newInstance(String deviceName) {
        DeviceMessageFragment deviceMessageFragment = new DeviceMessageFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_DEVICE_NAME, deviceName);
        deviceMessageFragment.setArguments(args);
        return deviceMessageFragment;
    }

    @Override
    protected void initViews() {

        mDeviceName = getArguments().getString(KEY_DEVICE_NAME);
        mListView = (ListView)mView.findViewById(R.id.listviewDeviceMessage_listView);
        mTopView = CommonViewHolder.get(mView, R.id.deviceMessage_topView);
        mTopView.setText("返回", mDeviceName, null);
        mSendMessageBtn = CommonViewHolder.get(mView, R.id.sendMessage_btn);

    }

    @Override
    protected void setupAdapters() {
        List<DeviceMessageMSG> mDeviceMessageMSGList = new ArrayList<>();
        DeviceMessageMSG mDeviceMessageMSG = null;
        for (int i=0;i<4;i++){
            switch (i){
                case 0:
                    mDeviceMessageMSG = new DeviceMessageMSG("报警1","一般警报","前天");
                    break;
                case 1:
                    mDeviceMessageMSG = new DeviceMessageMSG("报警2","严重警报","5月13日");
                    break;
                case 2:
                    mDeviceMessageMSG = new DeviceMessageMSG("报警3","一般警报","5月12日");
                    break;
                case 3:
                    mDeviceMessageMSG = new DeviceMessageMSG("报警4","严重警报","5月10日");
                    break;
            }
            mDeviceMessageMSGList.add(mDeviceMessageMSG);
        }
        Adapter mAdapter = new DeviceMessageAdapter(getActivity(), mDeviceMessageMSGList);
        mListView.setAdapter((ListAdapter) mAdapter);


    }

    @Override
    protected void setupListeners() {
        mTopView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        fm = getActivity().getSupportFragmentManager();

        mSendMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DeviceDetailActivity) mActivity).replaceCurrentFragment(
                        new SendDeviceMessageAsFragment());

            }
        });
        mTopView.setupListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();

            }
        },null);

    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_device_message;
    }
}
