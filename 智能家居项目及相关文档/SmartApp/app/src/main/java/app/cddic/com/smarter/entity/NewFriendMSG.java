package app.cddic.com.smarter.entity;

import app.cddic.com.smarter.utils.StaticClass;

/**
 * Created by asus on 2017/7/16.
 */

public class NewFriendMSG extends MsgObject {
    private String mFriendName;
    private String mFriendNews;
    private String mAddTime;
    private int mFriendState;
    public NewFriendMSG(String friendName, String friendNews, String addTime, int friendState)
    {
        mFriendName = friendName;
        mFriendNews = friendNews;
        mAddTime = addTime;
        mFriendState = friendState;
        setType(StaticClass.MSG_NEW_FRIEND);
    }

    public String getFriendName() {
        return mFriendName;
    }

    public String getFriendNews() {
        return mFriendNews;
    }

    public String getAddTime(){return mAddTime;}

    public String getFriendState() {
        return mFriendState==1?"已经同意":"拒绝";
    }
    public int getFriendStateInt(){
        return mFriendState;
    }
}
