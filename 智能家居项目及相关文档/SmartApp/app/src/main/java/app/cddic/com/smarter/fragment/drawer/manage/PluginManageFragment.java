package app.cddic.com.smarter.fragment.drawer.manage;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.adapter.PluginManageItemsAdapter;
import app.cddic.com.smarter.entity.PluginManageMSG;

/**
 * SmartSecurity-Manager
 * app.edu.cdu.com.smartsecurity_manager.fragment.manage
 * Created by Pantiy on 2017/3/30.
 * Copyright © 2017 All rights Reserved by Pantiy
 */

public class PluginManageFragment extends ManageFragment {

    private ListView mPluginManageListView;
    private List<PluginManageMSG> mPluginManageMSGList = new ArrayList<>();

    @Override
    protected void setFragmentName() {
        mFragmentName="插件管理";
    }

    @Override
    protected void initViews() {

        mPluginManageListView=(ListView)mView.findViewById(R.id.pluginManage_listView);
    }

    @Override
    protected void setupAdapters() {
        for (int i = 0; i < 6; i++) {
            PluginManageMSG item = null;
            switch (i) {
                case 0:
                    item = new PluginManageMSG("厨房设备", "    插件：88888888880   v2.1");
                    break;
                case 1:
                    item = new PluginManageMSG("照明设备", "    插件：88888888881   v2.1");
                    break;
                case 2:
                    item = new PluginManageMSG("卧室设备", "    插件：88888888882   v2.1");
                    break;
                case 3:
                    item = new PluginManageMSG("厕所设备", "    插件：88888888883   v2.1");
                    break;
                case 4:
                    item = new PluginManageMSG("客厅设备", "    插件：88888888884   v2.1");
                    break;
                case 5:
                    item = new PluginManageMSG("制冷设备", "    插件：88888888885   v2.1");
                    break;
            }
            mPluginManageMSGList.add(item);

        }
        PluginManageItemsAdapter pluginManageItemsAdapter = new PluginManageItemsAdapter(getActivity(), mPluginManageMSGList);
        mPluginManageListView.setAdapter(pluginManageItemsAdapter);
    }

    @Override
    protected void setupListeners() {
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_plugin_manage;
    }
}
