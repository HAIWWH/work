package app.cddic.com.smarter.entity;

import app.cddic.com.smarter.utils.StaticClass;

/**
 * Created by asus on 2017/9/12.
 */

public class DeviceAlarmMSG extends MsgObject {
    String mName;
    String mState;
    String mTime;

    public DeviceAlarmMSG(String name, String state, String time) {
        mName = name;
        mState = state;
        mTime = time;
        setType(StaticClass.MSG_DEVICE_ALARM);
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }


}
