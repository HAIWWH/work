package app.cddic.com.smarter.fragment.drawer;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.activity.base.ChangePasswordActivity;
import app.cddic.com.smarter.entity.MyInformationMSG;
import app.cddic.com.smarter.fragment.base.BaseFragment;
import app.cddic.com.smarter.widget.TopView;

/**
 * Created by 小帆哥 on 2017/5/9.
 */

public class MyInformationFragment extends BaseFragment {

    private TopView mTopView;
    private TextView mUserNameTv;
    private TextView mUserTypeTv;
    private TextView mUserSexTv;
    private TextView mUserAccountTv;
    private TextView mRegistrationTimeTv;
    private TextView mStateTv;
    private TextView mMailboxTv;
    private TextView mModifyKeyTv;
    private ImageView mQRCodeIv;

    @Override
    protected void initViews() {
        mTopView =findView(R.id.topView);
        mTopView.setText("设置", "我的资料", null);
        MyInformationMSG mMyInformationMSG = new MyInformationMSG("名称：李师傅","一般用户","性别:男","用户账号:20141215414","注册时间：2012/02/02","当前状态：在线","邮箱:978029808@qq.com");
        List<MyInformationMSG> myInformationMSGList = new ArrayList<>();
        myInformationMSGList.add(mMyInformationMSG);
        mUserNameTv = findView(R.id.userName_textView);
        mUserNameTv.setText("名称："+ myInformationMSGList.get(0).getName());
        mUserTypeTv = findView(R.id.userType_textView);
        mUserTypeTv.setText(myInformationMSGList.get(0).getUserType());
        mUserSexTv = findView(R.id.userSex_textView);
        mUserSexTv.setText("性别:"+ myInformationMSGList.get(0).getSex());
        mUserAccountTv= findView(R.id.userAccount_textView);
        mUserAccountTv.setText("用户账号："+ myInformationMSGList.get(0).getUserId());
        mRegistrationTimeTv = findView(R.id.registrationTime_textView);
        mRegistrationTimeTv.setText("注册时间："+ myInformationMSGList.get(0).getRegistrationTime());
        mStateTv = findView(R.id.state_textView);
        mStateTv.setText("当前状态："+ myInformationMSGList.get(0).getCurrentState());
        mMailboxTv = findView(R.id.mailBox_textView);
        mMailboxTv.setText("邮箱:"+ myInformationMSGList.get(0).getMailBox());
        mQRCodeIv = findView(R.id.QRCode_imageView);
        mQRCodeIv.setImageResource(R.drawable.erweima);
        mModifyKeyTv = findView(R.id.changePassword_textView);
    }

    @Override
    protected void setupAdapters() {

    }

    @Override
    protected void setupListeners() {
        mTopView.setupListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        }, null);
        mModifyKeyTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_my_information;
    }
}

