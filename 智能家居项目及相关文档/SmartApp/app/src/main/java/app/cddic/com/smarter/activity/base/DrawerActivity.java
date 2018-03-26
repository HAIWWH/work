package app.cddic.com.smarter.activity.base;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import app.cddic.com.smarter.fragment.drawer.MyCollectionFragment;
import app.cddic.com.smarter.fragment.drawer.MyInformationFragment;
import app.cddic.com.smarter.fragment.drawer.SupportFragment;

/**
 * SmartApp
 * app.cddic.com.smarter.activity.base
 * Created by Pantiy on 2017/5/13.
 * Copyright © 2017 All rights Reserved by Pantiy
 */

public class DrawerActivity extends SingleFragmentActivity {

    private static final String EXTRA_TYPE = "type";

    public static Intent newInstance(Context context, int type) {
        Intent intent = new Intent(context, DrawerActivity.class);
        intent.putExtra(EXTRA_TYPE, type);
        return intent;
    }

    @Override
    public void onHandleMsg(int MsgType) {

    }

    @Override
    protected Fragment createFragment() {
        switch (getIntent().getIntExtra(EXTRA_TYPE, -1)) {
            case Type.MY_INFORMATION:
                return new MyInformationFragment();
            case Type.MY_COLLECTION:
                return new MyCollectionFragment();
            case Type.SUPPORT:
                return new SupportFragment();
            default:
                return null;
        }
    }

    public static final class Type {
        public static final int MY_INFORMATION = 0;
        public static final int MY_COLLECTION = 1;
        public static final int SUPPORT = 3;
    }
}
