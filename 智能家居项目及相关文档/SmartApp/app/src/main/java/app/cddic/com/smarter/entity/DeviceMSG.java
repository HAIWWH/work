package app.cddic.com.smarter.entity;

import app.cddic.com.smarter.utils.StaticClass;

/**
 * Created by pantiy on 2017/7/16.
 * Copyright © 2016 All rights Reserved by Pantiy
 */

public class DeviceMSG extends MsgObject {

    private int mState;
    private String mDeviceName;

    public DeviceMSG(String deviceName, int state) {
        mDeviceName = deviceName;
        mState = state;
        setType(StaticClass.MSG_DEVICE);
    }

    public String getState() {
        return mState == 1 ? "可控制" : "不可控";
    }

    public int getStateInt() {
        return mState;
    }

    public String getName(){
        return mDeviceName;
    }
}
