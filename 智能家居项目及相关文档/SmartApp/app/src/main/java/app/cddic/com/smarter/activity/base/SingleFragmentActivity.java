package app.cddic.com.smarter.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import app.cddic.com.smarter.R;

/**
 * SmartApp
 * app.cddic.com.smarter.activity.base
 * Created by Pantiy on 2017/5/4.
 * Copyright © 2017 All rights Reserved by Pantiy
 */

public abstract class SingleFragmentActivity extends BaseActivity {

    private static final String TAG = "SingleFragmentActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);
        mFragmentManager = getSupportFragmentManager();
        Fragment fragment = createFragment();
        if (fragment != null) {
            mFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment, getFragmentName()).commit();
        }
        Log.i(TAG, getFragmentName());
    }

    @Override
    public void onHandleMsg(int MsgType) {

    }

    public void skipToFragment(Fragment skipTo, String currentFragmentTag) {
        mFragmentManager.beginTransaction()
                .hide(mFragmentManager.findFragmentByTag(currentFragmentTag))
                .add(R.id.fragment_container, skipTo, skipTo.getClass().getSimpleName())
                .commit();
    }

    public void backToFragment(String backToFragmentTag, String currentFragmentTag) {
        mFragmentManager.beginTransaction()
                .remove(mFragmentManager.findFragmentByTag(currentFragmentTag))
                .show(mFragmentManager.findFragmentByTag(backToFragmentTag))
                .commit();
    }

    protected abstract Fragment createFragment();

    private String getFragmentName() {
        return createFragment().getClass().getSimpleName();
    }

    public void replaceCurrentFragment(Fragment fragment) {
        mFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }
}
