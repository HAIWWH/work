package app.cddic.com.smarter.entity;

import app.cddic.com.smarter.utils.StaticClass;

/**
 * Created by VileWind on 2017/7/27 0027.
 */

public class DeviceManageMSG extends MsgObject {
    private String mDeviceName;
    private String mDeviceDate;
    private String mDeviceState;
    public DeviceManageMSG(String deviceName, String deviceDate, String deviceState){
        mDeviceName = deviceName;
        mDeviceDate = deviceDate;
        mDeviceState = deviceState;
        setType(StaticClass.MSG_DEVICE_MANAGE);
    }

    public String getDeviceDate() {
        return mDeviceDate;
    }

    public String getDeviceName() {
        return mDeviceName;
    }

    public String getDeviceState() {
        return mDeviceState;
    }
}

