package app.cddic.com.smarter.entity;

import app.cddic.com.smarter.utils.StaticClass;

/**
 * Created by 小帆哥 on 2017/7/28.
 */

public class RealTimeVideoMSG extends MsgObject {
    private String mVideoName;

    private String mVideoTime;

    public String getVideoName() {return mVideoName;}

    public String getVideoTime() {return mVideoTime;}

    public RealTimeVideoMSG(String videoName, String videoTime){
        mVideoName = videoName;
        mVideoTime = videoTime;
        setType(StaticClass.MSG_VIDEO);
    }
}
