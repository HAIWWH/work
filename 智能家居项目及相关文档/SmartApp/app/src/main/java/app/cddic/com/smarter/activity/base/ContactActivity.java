package app.cddic.com.smarter.activity.base;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import app.cddic.com.smarter.fragment.contact.AddContactFragment;
import app.cddic.com.smarter.fragment.contact.ContactDetailFragment;
import app.cddic.com.smarter.fragment.contact.DeviceContactFragment;
import app.cddic.com.smarter.fragment.contact.NewFriendsFragment;
import app.cddic.com.smarter.fragment.drawer.manage.GroupManagementFragment;

/**
 * SmartApp
 * app.cddic.com.smarter.activity.base
 * Created by Pantiy on 2017/5/11.
 * Copyright © 2017 All rights Reserved by Pantiy
 */

public class ContactActivity extends SingleFragmentActivity {

    private static final String EXTRA_TYPE = "type";
    private static final String EXTRA_CONTACT_NAME = "contactName";

    public static Intent newInstance(Context context, int type) {
        Intent intent = new Intent(context, ContactActivity.class);
        intent.putExtra(EXTRA_TYPE, type);
        return intent;
    }

    public static Intent newInstance(Context context, int type, String contactName) {
        Intent intent = new Intent(context, ContactActivity.class);
        intent.putExtra(EXTRA_TYPE, type);
        intent.putExtra(EXTRA_CONTACT_NAME, contactName);
        return intent;
    }

    @Override
    protected Fragment createFragment() {

        switch (getIntent().getIntExtra(EXTRA_TYPE, -1)) {

            case Type.ADD_CONTACT:
                return new AddContactFragment();

            case Type.CONTACT_DETAIL:
                return ContactDetailFragment.newInstance(getIntent().getStringExtra(EXTRA_CONTACT_NAME));

            case Type.NEW_FRIENDS:
                return new NewFriendsFragment();

            case Type.DEVICE_CONTACT:
                return new DeviceContactFragment();
            case Type.GROUP_MANAGE:
                return new  GroupManagementFragment();

            default:
                return null;
        }
    }

    public static final class Type {
        public static final int ADD_CONTACT = 0;
        public static final int CONTACT_DETAIL = 1;
        public static final int NEW_FRIENDS = 2;
        public static final int DEVICE_CONTACT = 3;
        public static final int GROUP_MANAGE=4;
    }
}
