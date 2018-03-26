package app.cddic.com.smarter.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.activity.base.RegisterActivity;
import app.cddic.com.smarter.fragment.base.BaseFragment;

/**
 * 项目名：  SmartApp
 * 包名：    app.cddic.com.smarter.fragment
 * 文件名：  ImforFragment
 * 创建者：
 * 创建时间： 2017/4/15 18:16
 * 描述：用户其他信息页面
 */

public class InforFragment extends BaseFragment {
    private TextView next2;
    private TextView back2;
    private Spinner mSpinner;
    private ACNumberFragment fragment2;
    private CheckPhoneFragment fragment0;
    private EditText email;
    private EditText nickName;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_info,null);
        next2 = (TextView) v.findViewById(R.id.tv_next2);
        email = (EditText) v.findViewById(R.id.email_register);
        nickName = (EditText) v.findViewById(R.id.nickname_register);
        mSpinner = (Spinner) v.findViewById(R.id.sp_sexSpinner);
        back2 = (TextView) v.findViewById(R.id.tv_back2);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        ((RegisterActivity)mActivity).getRegisterMsg().setSex((byte)0);
                        break;
                    case 1:
                        ((RegisterActivity)mActivity).getRegisterMsg().setSex((byte)1);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.container,fragment0).commit();
            }
        });

        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RegisterActivity)mActivity).getRegisterMsg().setNickName(nickName.getText().toString());
                ((RegisterActivity)mActivity).getRegisterMsg().setEmail(email.getText().toString());
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.container,fragment2).commit();
            }
        });
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragment2 = (ACNumberFragment) ((RegisterActivity)mActivity).getList().get(2);
        fragment0 = (CheckPhoneFragment) ((RegisterActivity)mActivity).getList().get(0);
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
