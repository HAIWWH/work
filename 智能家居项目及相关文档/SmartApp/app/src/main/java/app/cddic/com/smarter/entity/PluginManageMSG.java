package app.cddic.com.smarter.entity;

import app.cddic.com.smarter.utils.StaticClass;

/**
 * Created by asus on 2017/7/12.
 */

public class PluginManageMSG extends MsgObject {
    private String mDeviceName;
    private String mPluginName;
    public String getDeviceName() {
        return mDeviceName;
    }

    public void setDeviceName(String deviceName) {
        mDeviceName = deviceName;
    }

    public String getPluginName() {
        return mPluginName;
    }

    public void setPluginName(String pluginName) {
        mPluginName = pluginName;
    }
    public PluginManageMSG(String DeviceName, String PluginName){
        mDeviceName = DeviceName;
        mPluginName = PluginName;
        setType(StaticClass.MSG_PLUGIN_MANAGE);
    }

}
