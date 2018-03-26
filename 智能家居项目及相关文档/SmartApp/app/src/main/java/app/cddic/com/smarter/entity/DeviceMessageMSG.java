package app.cddic.com.smarter.entity;

/**
 * Created by asus on 2017/7/31.
 */

public class DeviceMessageMSG {
    private String mName;
    private String mType;
    private String mDate;
    public DeviceMessageMSG(String name, String type, String date){
        mName = name;
        mDate = date;
        mType = type;

    }
    public String getDate() {
        return mDate;
    }

    public String getName() {
        return mName;
    }

    public String getType() {
        return mType;
    }
}
