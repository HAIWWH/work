package app.cddic.com.smarter.activity.base;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import app.cddic.com.smarter.fragment.InformNewsDetailFragment;
import app.cddic.com.smarter.fragment.message.ChatFileFragment;
import app.cddic.com.smarter.fragment.message.ChatFragment;
import app.cddic.com.smarter.fragment.message.SystemNewsDetailFragment;

/**
 * SmartApp
 * app.cddic.com.smarter.activity.base
 * Created by Pantiy on 2017/5/9.
 * Copyright © 2017 All rights Reserved by Pantiy
 */

public class MessageActivity extends SingleFragmentActivity {

    public static final String EXTRA_CONTACT_NAME = "contactName";
    public static final String EXTRA_MESSAGE= "messagetype";

    public static Intent newInstance(Context context, String contactName,int type) {
        Intent intent = new Intent(context, MessageActivity.class);
        intent.putExtra(EXTRA_CONTACT_NAME, contactName);
        intent.putExtra(EXTRA_MESSAGE,type);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        String contactName = getIntent().getStringExtra(EXTRA_CONTACT_NAME);
        int type = getIntent().getIntExtra(EXTRA_MESSAGE,0);
        switch (type){
            case 0:
                return ChatFragment.newInstance(contactName);
            case 1:
                return new InformNewsDetailFragment();
            case 2:
                return ChatFragment.newInstance(contactName);
            case 3:
                return new ChatFileFragment();
            case 4:
                return new SystemNewsDetailFragment();
            default: return null;
        }
    }
    public static class type{
        public static int ALARMMESSAGE=0;
        public static int INFORMMESSAGE=1;
        public static int CHATMESSAGE=2;
        public static int CHATFILE=3;
        public static int SYSTEMMESSAGE=4;
          }
}
