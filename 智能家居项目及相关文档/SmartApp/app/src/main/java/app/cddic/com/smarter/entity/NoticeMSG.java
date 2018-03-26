package app.cddic.com.smarter.entity;

/**
 * Created by yfs on 5/27 0027.
 */

public class NoticeMSG extends MsgObject {
    int mRequserid;
    byte mSort;
    byte mState;
    long mTime;

    byte mKind;

    String mMessage;

    byte mRight; //关联权限
    int mDev;  //关联设备id
    String mPass; //密码

    public NoticeMSG(int requserid, byte sort, byte state,long time,byte kind,String message,int dev,String pass){
        mRequserid = requserid;
        mSort = sort;
        mState = state;
        mTime = time;
        mKind = kind;
        mMessage = message;
        mDev = dev;
        mPass = pass;
    }

    public NoticeMSG(){

    }
    public int getUserid() {return mRequserid;}

    public void setUserid(int userid) {mRequserid = userid;}

    public byte getSort() {return mSort;}

    public void setSort(byte sort) {mSort = sort;}

    public byte getState() {return mState;}

    public void setState(byte state) {mState = state;}

    public long getTime() {return mTime;}

    public void setTime(long time) {mTime = time;}

    public byte getKind() {return mKind;}

    public void setKind(byte kind) {mKind = kind;}

    public String getMessage() {return mMessage;}

    public void setMessage(String message) {mMessage = message;}

    public byte getRight() {return mRight;}

    public void setRight(byte right) {mRight = right;}

    public int getDev() {return mDev;}

    public void setDev(int dev) {mDev = dev;}

    public String getPass() {return mPass;}

    public void setPass(String pass) {mPass = pass;}

    public int getRequserid() {
        return mRequserid;
    }
}
