package app.cddic.com.smarter.entity;

/**
 * Created by pantiy on 2017/7/20.
 * Copyright © 2016 All rights Reserved by Pantiy
 */

public class InformMSG extends MsgObject {

    private String mTitle;
    private String mContent;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }
}
