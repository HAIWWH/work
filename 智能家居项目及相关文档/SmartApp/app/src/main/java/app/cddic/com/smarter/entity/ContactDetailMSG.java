package app.cddic.com.smarter.entity;

import app.cddic.com.smarter.utils.StaticClass;

/**
 * Created by asus on 2017/7/16.
 */

public class ContactDetailMSG extends MsgObject {
    private String mConnectName;
    private String mConnectType;
    private String mConnectSex;
    private String mConnectState;
    private String mConnectTime;
    private String mConnectGroup;
    private String mConnectElseName;

    public ContactDetailMSG(String connectName, String connectType, String connectSex, String connectState, String connectTime, String connectGroup, String connectElseName )
    {
        mConnectName = connectName;
        mConnectType = connectType;
        mConnectSex = connectSex;
        mConnectState = connectState;
        mConnectTime= connectTime;
        mConnectGroup = connectGroup;
        mConnectElseName = connectElseName;
        setType(StaticClass.MSG_CONTACT_DETAIL);
    }
    public String getConnectName() {
        return mConnectName;
    }

    public void setConnectName(String connectName) {
        mConnectName = connectName;
    }

    public String getConnectType() {
        return mConnectType;
    }

    public void setConnectType(String connectType) {
        mConnectType = connectType;
    }

    public String getConnectSex() {
        return mConnectSex;
    }

    public void setConnectSex(String connectSex) {
        mConnectSex = connectSex;
    }

    public String getConnectState() {
        return mConnectState;
    }

    public void setConnectState(String connectState) {
        mConnectState = connectState;
    }

    public String getConnectTime() {
        return mConnectTime;
    }

    public void setConnectTime(String connectTime) {
        mConnectTime = connectTime;
    }

    public String getConnectGroup() {
        return mConnectGroup;
    }

    public void setConnectGroup(String connectGroup) {
        mConnectGroup = connectGroup;
    }

    public String getConnectElseName() {
        return mConnectElseName;
    }

    public void setConnectElseName(String connectElseName) {
        mConnectElseName = connectElseName;
    }



}
