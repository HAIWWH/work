package app.cddic.com.smarter.entity;

public class DeviceSettingsMSG extends MsgObject {
    private String mDeviceAlias;
    private String mDeviceLongitude;//设备经度
    private String mDeviceLatitude;//设备纬度
    private String mDeviceLogUserName;
    private String mDeviceLogPassWord;

    public String getDeviceAlias() {return mDeviceAlias;}

    public String getDeviceLongitude() {return mDeviceLongitude;}

    public String getDeviceLatitude() {return mDeviceLatitude;}

    public String getDeviceLogUserName() {return mDeviceLogUserName;}

    public String getDeviceLogPassWord() {return mDeviceLogPassWord;}

    public DeviceSettingsMSG(String deviceAlias, String deviceLongitude, String deviceLatitude, String deviceLogUserName, String deviceLogPassWord){
        mDeviceAlias = deviceAlias;
        mDeviceLongitude = deviceLongitude;
        mDeviceLatitude = deviceLatitude;
        mDeviceLogUserName = deviceLogUserName;
        mDeviceLogPassWord = deviceLogPassWord;
    }
}
