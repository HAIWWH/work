package app.cddic.com.smarter.entity;

import app.cddic.com.smarter.utils.StaticClass;

/**
 * Created by asus on 2017/9/17.
 */

public class DeviceLoginMSG extends MsgObject {
    private String mBarCode;
    private String mAddress;
    private String  mAuthority;
    private String mId;
    private String mLoginKey;

    public DeviceLoginMSG(String barCode, String address, String authority, String id, String loginKey)
    {
        mBarCode = barCode;
        mAddress = address;
        mAuthority = authority;
        mId = id;
        mLoginKey = loginKey;
        setType(StaticClass.MSG_DEVICE_LOGIN);
    }


    public String getBarCode() {
        return mBarCode;
    }

    public void setBarCode(String barCode) {
        mBarCode = barCode;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getAuthority() {
        return mAuthority;
    }

    public void setAuthority(String authority) {
        mAuthority = authority;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getLoginKey() {
        return mLoginKey;
    }

    public void setLoginKey(String loginKey) {
        mLoginKey = loginKey;
    }
}
