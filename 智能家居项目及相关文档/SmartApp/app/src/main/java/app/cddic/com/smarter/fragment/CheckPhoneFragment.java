package app.cddic.com.smarter.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.activity.base.ChangePasswordActivity;
import app.cddic.com.smarter.activity.base.RegisterActivity;
import app.cddic.com.smarter.fragment.base.BaseFragment;

/**
 * 项目名：  SmartApp
 * 包名：    app.cddic.com.smarter.fragment
 * 文件名：  CheckPhoneFragment
 * 创建者：
 * 创建时间： 2017/4/15 13:39
 * 描述：手机验证页面
 */

public class CheckPhoneFragment extends BaseFragment {
    private TextView next;
    private EditText phoneNum;
    private Button checkNumber;
    InforFragment fragmeng1;

    private int index;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_checkphone,null);
        next = (TextView) v.findViewById(R.id.tv_next);
        phoneNum = (EditText) v.findViewById(R.id.mobileNumber);
        checkNumber = (Button) v.findViewById(R.id.btn_getCheckNum);

        checkNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //手机没有注册
                Toast.makeText(getContext(),"获取验证码",Toast.LENGTH_SHORT).show();
            }
        });

        if (index == 1){//进入忘记密码，验证手机的操作
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //1.验证信息输入不对

                    //2.验证成功
                    startActivity(new Intent(mActivity, ChangePasswordActivity.class));
                    mActivity.finish();
                }
            });
        }else if(index == 2){//注册操作
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().getSupportFragmentManager().beginTransaction().
                            replace(R.id.container,fragmeng1).commit();
                }
            });
        }
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //从activity获取intent的参数
        index = ((RegisterActivity)mActivity).getIntent().getIntExtra("fragId",0);


        fragmeng1 = (InforFragment) ((RegisterActivity) mActivity).getList().get(1);

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void setupAdapters() {

    }

    @Override
    protected void setupListeners() {

    }

    @Override
    protected int setLayoutRes() {
        return 0;
    }
}
