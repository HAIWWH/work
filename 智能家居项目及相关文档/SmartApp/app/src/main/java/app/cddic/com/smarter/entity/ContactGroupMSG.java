package app.cddic.com.smarter.entity;

/**
 * Created by pantiy on 2017/7/17.
 * Copyright © 2016 All rights Reserved by Pantiy
 */

public class ContactGroupMSG extends MsgObject {

    private int mGroupId;
    private int mCount;
    private int mOnlineCount;
    private String mGroupName;

    public ContactGroupMSG(int groupId, String groupName) {
        setGroupId(groupId);
        setGroupName(groupName);
    }

    public int getGroupId() {
        return mGroupId;
    }

    public void setGroupId(int groupId) {
        mGroupId = groupId;
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
    }

    public int getOnlineCount() {
        return mOnlineCount;
    }

    public void setOnlineCount(int onlineCount) {
        mOnlineCount = onlineCount;
    }

    public String getGroupName() {
        return mGroupName;
    }

    public void setGroupName(String groupName) {
        mGroupName = groupName;
    }
}
