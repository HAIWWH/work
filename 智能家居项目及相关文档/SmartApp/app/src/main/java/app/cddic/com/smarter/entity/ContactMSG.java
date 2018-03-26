package app.cddic.com.smarter.entity;

/**
 * Created by pantiy on 2017/7/17.
 * Copyright © 2016 All rights Reserved by Pantiy
 */

public class ContactMSG extends MsgObject {

    private int mGroupId;
    private int mUserType;
    private int mState;
    private String mContactName;

    public ContactMSG(int groupId, String contactName) {
        setGroupId(groupId);
        setContactName(contactName);
    }



    public int getGroupId() {
        return mGroupId;
    }

    public void setGroupId(int groupId) {
        mGroupId = groupId;
    }

    public int getUserType() {
        return mUserType;
    }

    public void setUserType(int userType) {
        mUserType = userType;
    }

    public int getState() {
        return mState;
    }

    public void setState(int state) {
        mState = state;
    }

    public String getContactName() {
        return mContactName;
    }

    public void setContactName(String contactName) {
        mContactName = contactName;
    }

    public boolean belong(ContactGroupMSG contactGroupMSG) {
        return getGroupId() == contactGroupMSG.getGroupId();
    }
}
