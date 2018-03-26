package app.cddic.com.smarter.entity;

import app.cddic.com.smarter.utils.StaticClass;

/**
 * Created by yfs on 5/27 0027.
 */

public class ChatMSG extends MsgObject {
    int peerid;
    long time;
    byte state;
    boolean issend; //是否发送
    String msg;
    int ctype; //字型
    int color;
    String mName;
    String mContent;
    String mDate;
    public ChatMSG(String name, String content, String date)
    {
        mName = name;
        mContent = content;
        mDate = date;
        setType(StaticClass.MSG_CHAT);
    }
    private Boolean mIsFromMe;

    public Boolean getFromMe() {
        return mIsFromMe;
    }

    public void setFromMe(Boolean mIsFromMe) {
        this.mIsFromMe = mIsFromMe;
    }

    public static ChatMSG get(){
        return new ChatMSG(null,null,null);
    }

    public int getPeerid() {
        return peerid;
    }

    public void setPeerid(int peerid) {
        this.peerid = peerid;
    }

    public boolean issend() {
        return issend;
    }

    public void setIssend(boolean issend) {
        this.issend = issend;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCtype() {
        return ctype;
    }

    public void setCtype(int ctype) {
        this.ctype = ctype;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        this.mContent = content;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String data) {
        this.mDate = data;
    }
}
