package app.cddic.com.smarter.entity;

import app.cddic.com.smarter.utils.StaticClass;

/**
 * Created by asus on 2017/8/8.
 */

public class AccountMSG extends MsgObject {
    private String mName;
    private String mId;

    public AccountMSG(String name, String id) {
        mName = name;
        mId = id;
        setType(StaticClass.MSG_ACCOUNT);
    }


    public String getName() {
        return mName;
    }

    public String getId() {
        return mId;
    }
}
