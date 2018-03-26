package app.cddic.com.smarter.entity;

import app.cddic.com.smarter.utils.StaticClass;

/**
 * Created by asus on 2017/9/16.
 */

public class GroupMSG extends MsgObject {
    String mGroupName;

    public GroupMSG(String groupName){
        mGroupName = groupName;
        setType(StaticClass.MSG_GROUP);
    }
    public String getGroupName() {
        return mGroupName;
    }

    public void setGroupName(String groupName) {
        mGroupName = groupName;
    }
}
