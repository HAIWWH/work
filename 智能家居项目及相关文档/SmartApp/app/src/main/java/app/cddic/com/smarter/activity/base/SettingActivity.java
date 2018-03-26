package app.cddic.com.smarter.activity.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.View;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.fragment.drawer.manage.AccountManageFragment;
import app.cddic.com.smarter.fragment.drawer.manage.AddAccountFragment;
import app.cddic.com.smarter.fragment.drawer.manage.DataManageFragment;
import app.cddic.com.smarter.fragment.drawer.manage.HistoryManageFragment;
import app.cddic.com.smarter.fragment.drawer.manage.LoginSettingFragment;
import app.cddic.com.smarter.fragment.drawer.manage.ManageFragment;
import app.cddic.com.smarter.fragment.drawer.manage.MessageManageFragment;
import app.cddic.com.smarter.fragment.drawer.manage.PluginManageFragment;
import app.cddic.com.smarter.fragment.drawer.manage.PrivacyManageFragment;
import app.cddic.com.smarter.widget.TopView;

/**
 * SmartApp
 * app.cddic.com.smarter.activity.base
 * Created by Pantiy on 2017/5/4.
 * Copyright © 2017 All rights Reserved by Pantiy
 */

public class SettingActivity extends BaseActivity {
    private static final String EXTRA_FRAGMENT_TYPE = "extra_fragmentType";

    private TopView mTopView;
    private ManageFragment mManageFragment;

    public static Intent newInstance(Context context, int fragmentType) {
        Intent intent = new Intent(context, SettingActivity.class);
        intent.putExtra(EXTRA_FRAGMENT_TYPE, fragmentType);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initViews();
        setupListeners();
    }

    @Override
    public void onHandleMsg(int MsgType) {

    }

    private void initViews() {
        mTopView = (TopView) findViewById(R.id.topView);
        FragmentManager fragmentManager = getSupportFragmentManager();
        int fragmentType = getIntent().getIntExtra(EXTRA_FRAGMENT_TYPE, -1);
        initFragment(fragmentType);
        if (mManageFragment != null) {
            fragmentManager.beginTransaction().add(R.id.fragment_container, mManageFragment).commit();
            mTopView.setText("返回", mManageFragment.getFragmentName(), null);
        }
    }

    private void setupListeners() {
        mTopView.setupListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        }, null);
    }

    private void initFragment(int fragmentType) {
        switch (fragmentType) {
            case ManageFragment.Type.ACCOUNT:
                mManageFragment = new AccountManageFragment();
                break;
            case ManageFragment.Type.MESSAGE:
                mManageFragment = new MessageManageFragment();
                break;
            case ManageFragment.Type.DATA:
                mManageFragment = new DataManageFragment();
                break;
            case ManageFragment.Type.HISTORY:
                mManageFragment = new HistoryManageFragment();
                break;
            case ManageFragment.Type.PRIVACY:
                mManageFragment = new PrivacyManageFragment();
                break;
            case ManageFragment.Type.PLUGIN:
                mManageFragment = new PluginManageFragment();
                break;
            case ManageFragment.Type.ADDACCOUNT:
                mManageFragment = new AddAccountFragment();
                break;
            case ManageFragment.Type.LOGINSETTING:
                mManageFragment = new LoginSettingFragment();
                break;
            default:
                break;
        }
    }
}
