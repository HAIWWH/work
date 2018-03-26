package app.cddic.com.smarter.entity;

import app.cddic.com.smarter.utils.StaticClass;

/**
 * Created by asus on 2017/7/14.
 */

public class DeviceContactMSG extends MsgObject {
    private String mContactName;
    private String mDeviceName;
    private int mDeciceState;
    private String mPicturePath;
    public DeviceContactMSG(String contactName, String deviceName, int deviceState)
    {
        mContactName = contactName;
        mDeviceName = deviceName;
        mDeciceState = deviceState;
        setType(StaticClass.MSG_DEVICE_CONTACT);
    }
    public String getContactName() {
        return mContactName;
    }

    public void setContactName(String contactName) {
        mContactName = contactName;
    }

    public String getDeviceName() {
        return mDeviceName;
    }

    public void setDeviceName(String deviceName) {
        mDeviceName = deviceName;
    }

    public String getDeciceState() {
        return mDeciceState==1?"在线":"离线";
    }

    public int getDeciceStateInt(){
        return mDeciceState;
    }

    public void setDeciceState(int deciceState) {
        mDeciceState = deciceState;
    }





}
