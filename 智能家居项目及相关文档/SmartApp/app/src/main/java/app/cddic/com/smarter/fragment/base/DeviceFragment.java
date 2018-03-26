package app.cddic.com.smarter.fragment.base;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import java.util.List;
import app.cddic.com.smarter.R;
import app.cddic.com.smarter.activity.base.DeviceActivity;
import app.cddic.com.smarter.adapter.DeviceItemsAdapter;
import app.cddic.com.smarter.entity.DeviceMSG;
import app.cddic.com.smarter.utils.StaticClass;


/**
 * Created by Pantiy on 2017/3/12.
 * Copyright © 2016 All rights Reserved by Pantiy
 */

public class DeviceFragment extends BaseFragment {

    private ListView mDeviceItemsListView;
    private LinearLayout mLinearLayout;
    @Override
    protected void initViews() {
        mDeviceItemsListView = (ListView) mView.findViewById(R.id.deviceItems_listView);
        mLinearLayout = (LinearLayout)mView.findViewById(R.id.findNewDevice_linear);
    }

    @Override
    protected void setupAdapters() {
        List<DeviceMSG> deviceMSGList = mMSGUtil.getMSGList(StaticClass.MSG_DEVICE);
        DeviceItemsAdapter deviceItemsAdapter = new DeviceItemsAdapter(mActivity, deviceMSGList);
        mDeviceItemsListView.setAdapter(deviceItemsAdapter);
    }

    @Override
    protected void setupListeners() {
        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = DeviceActivity.newInstance(getActivity(), DeviceActivity.Type.SEARCH_DEVICE, null);
                startActivity(intent);
            }
        });

    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_device;
    }
}
