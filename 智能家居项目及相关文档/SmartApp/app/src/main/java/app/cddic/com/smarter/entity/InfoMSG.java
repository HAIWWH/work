package app.cddic.com.smarter.entity;

import app.cddic.com.smarter.utils.StaticClass;

/**
 * Created by yfs on 5/27 0027.
 */

public class InfoMSG extends MsgObject {
    int mOfferid;
    long mTime;
    String mContent;
    String mDate;
    String mMessage;
    String mTitle;



    String mSet;
    String mAttach;
    byte mState;

    public InfoMSG() {
    }

    public InfoMSG(String title,String content,String date){
        mTitle = title;
        mContent = content;
        mDate = date;
        setType(StaticClass.MSG_INFO);
    }
    public int getOfferid() {return mOfferid;}

    public void setOfferid(int offerid) {mOfferid = offerid;}

    public long getTime() {return mTime;}

    public void setTime(long time) {mTime = time;}

    public String getMessage() {return mMessage;}

    public void setMessage(String message) {mMessage = message;}

    public String getTitle() {return mTitle;}

    public void setTitle(String title) {mTitle = title;}


    public String getAttach() {return mAttach;}

    public void setAttach(String attach) {mAttach = attach;}
    public String getSet() {
        return mSet;
    }

    public void setSet(String set) {
        mSet = set;
    }

    public byte getState() {return mState;}

    public void setState(byte state) {mState = state;}

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }
}
