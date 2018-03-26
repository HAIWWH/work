package app.cddic.com.smarter.entity;

import app.cddic.com.smarter.utils.StaticClass;

/**
 * Created by 小帆哥 on 2017/7/13.
 */

public class MyCollectionMSG extends MsgObject{

    private String mName;
    private String mMessage;
    private String mTime;

    public String getName() {
        return mName;
    }


    public String getMessage() {
        return mMessage;
    }


    public String getTime() {
        return mTime;
    }


    public MyCollectionMSG(String name, String message, String time) {
        mName = name;
        mMessage = message;
        mTime = time;
        setType(StaticClass.MSG_MY_COLLECTION);
    }

}
