package app.cddic.com.smarter.fragment.drawer.manage;

import android.view.View;
import android.widget.Switch;

import app.cddic.com.smarter.R;

/**
 * SmartSecurity-Manager
 * app.edu.cdu.com.smartsecurity_manager.fragment.manage
 * Created by Pantiy on 2017/3/30.
 * Copyright © 2017 All rights Reserved by Pantiy
 */

public class PrivacyManageFragment extends ManageFragment {

    private Switch mSavePasswordSth;
    private Switch mAddFriendSth;
    private Switch mAssociateDeviceSth;
    private Switch mAutoLoginSth;
    private Switch mAccountVisibleSth;
    private Switch mReciveStrangerMsgSth;

    @Override
    protected void setFragmentName() {
        mFragmentName = "隐私管理";

    }

    @Override
    protected void initViews() {

        mSavePasswordSth = (Switch) mView.findViewById(R.id.savePassword_sth);
        mAccountVisibleSth = (Switch) mView.findViewById(R.id.accountVisible_sth);
        mAddFriendSth = (Switch) mView.findViewById(R.id.addFriend_sth);
        mAssociateDeviceSth = (Switch) mView.findViewById(R.id.associateDevice_sth);
        mAutoLoginSth = (Switch) mView.findViewById(R.id.autoLogin_sth);
        mReciveStrangerMsgSth = (Switch) mView.findViewById(R.id.receiveStrangerMsg_sth);
    }

    @Override
    protected void setupAdapters() {

    }

    @Override
    protected void setupListeners() {



        mReciveStrangerMsgSth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //允许接受陌生人消息
            }
        });
        mAutoLoginSth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //允许自动登录
            }
        });
        mAssociateDeviceSth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关联设备需要验证
            }
        });
        mAddFriendSth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加好友需要验证
            }
        });
        mAccountVisibleSth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //账户他人可见
            }
        });
        mSavePasswordSth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //允许保存登录密码
            }
        });
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_privacy_manage;
    }
}
