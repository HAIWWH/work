package app.cddic.com.smarter.fragment.contact;

import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.adapter.DeviceContactItemsAdapter;
import app.cddic.com.smarter.entity.DeviceContactMSG;
import app.cddic.com.smarter.fragment.base.BaseFragment;
import app.cddic.com.smarter.widget.TopView;


public class DeviceContactFragment extends BaseFragment {
    private ListView mDeviceContactListView;
    private TopView mTopView;

    protected void initViews() {
        mDeviceContactListView = (ListView)mView.findViewById(R.id.device_contact_listView);
        mTopView =(TopView)mView.findViewById (R.id.device_contact_topView);
        mTopView.setText("联系人","设备关联人",null);
    }
    @Override
    protected void setupAdapters() {
        List<DeviceContactMSG>deviceContactItemsList = new ArrayList<>();
        for(int i =1 ;i<6;i++)
        {
            DeviceContactMSG item = null;
            switch (i){
                case 1:
                    item = new DeviceContactMSG("名称：张师傅","关联设备1",1);
                    break;
                case 2:
                    item = new DeviceContactMSG("名称：李妍","关联设备2",0);
                    break;
                case 3:
                    item = new DeviceContactMSG("名称：妍蝉","关联设备3",1);
                    break;
                case 4:
                    item = new DeviceContactMSG("名称：民人","关联设备4",0);
                    break;
                case 5:
                    item = new DeviceContactMSG("名称：胡适","关联设备5",1);
                    break;

            }
           deviceContactItemsList.add(item);
        }
        DeviceContactItemsAdapter deviceContactItemsAdapter = new DeviceContactItemsAdapter(getActivity(),deviceContactItemsList);
        mDeviceContactListView.setAdapter(deviceContactItemsAdapter);
    }

    @Override
    protected void setupListeners() {
        mTopView.setupListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        },null);

    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_device_contact;
    }
}

