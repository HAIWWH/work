package app.cddic.com.smarter.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * SmartApp
 * app.cddic.com.smarter.entity
 * Created by Pantiy on 2017/5/4.
 * Copyright © 2017 All rights Reserved by Pantiy
 */

public class ChatMSGLab {

    private static ChatMSGLab sChatMSGLab;

    private List<ChatMSG> mChatMSGList = new ArrayList<>();

    public static ChatMSGLab touch() {
        if (sChatMSGLab == null) {
            sChatMSGLab = new ChatMSGLab();
        }
        return sChatMSGLab;
    }

    private ChatMSGLab() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                ChatMSG chatMSG = ChatMSG.get();
                chatMSG.setFromMe(false);
                chatMSG.setMsg("hello");
                mChatMSGList.add(chatMSG);
            } else {
                ChatMSG chatMSG = ChatMSG.get();
                chatMSG.setFromMe(true);
                chatMSG.setMsg("Hi");
                mChatMSGList.add(chatMSG);
            }
        }
    }

    public List<ChatMSG> get() {
        return mChatMSGList;
    }

    public void add(ChatMSG chatMSG) {
        mChatMSGList.add(chatMSG);
    }
}
