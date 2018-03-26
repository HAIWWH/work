package app.cddic.com.smarter.entity;

import app.cddic.com.smarter.utils.StaticClass;

/**
 * Created by Hai on 2017/8/8.
 */

public class SettingMSG extends MsgObject {
    private int mSettingId;
    private String mSettingUserName;
    private int mSettingSound;
    private String mDeviceAlias;
    private String mDeviceLongitude;//设备经度
    private String mDeviceLatitude;//设备纬度
    private String mDeviceLogPassWord;


    public SettingMSG(int settingId, String settingUserName){
        setmSettingId(settingId);
        setmSettingUserName(settingUserName);
        setType(StaticClass.MSG_SETTING);
    }
    public SettingMSG(String deviceAlias, String deviceLongitude, String deviceLatitude, String settingUserName, String deviceLogPassWord){
        mDeviceAlias = deviceAlias;
        mDeviceLatitude = deviceLatitude;
        mDeviceLongitude = deviceLongitude;
        mSettingUserName = settingUserName;
        mDeviceLogPassWord = deviceLogPassWord;
        setType(StaticClass.MSG_SETTING);
    }

    public int getmSettingId() {
        return mSettingId;
    }

    public void setmSettingId(int mSettingId) {
        this.mSettingId = mSettingId;
    }

    public int getmSettingSound() {
        return mSettingSound;
    }

    public void setmSettingSound(int mSettingSound) {
        this.mSettingSound = mSettingSound;
    }

    public void setmSettingUserName(String mSettingUserName) {
        this.mSettingUserName = mSettingUserName;
    }

    public String getSettingUserName() {
        return mSettingUserName;
    }

    public void setSettingUserName(String settingUserName) {
        mSettingUserName = settingUserName;
    }

    public String getDeviceAlias() {
        return mDeviceAlias;
    }

    public void setDeviceAlias(String deviceAlias) {
        mDeviceAlias = deviceAlias;
    }

    public String getDeviceLongitude() {
        return mDeviceLongitude;
    }

    public void setDeviceLongitude(String deviceLongitude) {
        mDeviceLongitude = deviceLongitude;
    }

    public String getDeviceLatitude() {
        return mDeviceLatitude;
    }

    public void setDeviceLatitude(String deviceLatitude) {
        mDeviceLatitude = deviceLatitude;
    }

    public String getDeviceLogPassWord() {
        return mDeviceLogPassWord;
    }

    public void setDeviceLogPassWord(String deviceLogPassWord) {
        mDeviceLogPassWord = deviceLogPassWord;
    }
}
