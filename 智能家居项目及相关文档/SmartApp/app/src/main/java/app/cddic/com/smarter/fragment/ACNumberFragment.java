package app.cddic.com.smarter.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.activity.base.RegisterActivity;
import app.cddic.com.smarter.db.DBHelper;
import app.cddic.com.smarter.fragment.base.BaseFragment;
import app.cddic.com.smarter.fragment.drawer.ResultFragment;
import app.cddic.com.smarter.utils.StaticClass;

/**
 * 项目名：  SmartApp
 * 包名：    app.cddic.com.smarter.fragment
 * 文件名：  ACNumberFragment
 * 创建者：
 * 创建时间： 2017/4/15 18:45
 * 描述：用户名和密码页面
 */

public class ACNumberFragment extends BaseFragment {
    private Button register;
    private TextView back3;
    private EditText username;
    private EditText pass;
    private EditText rePass;
    private InforFragment fragment1;
    private ResultFragment fragment3;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_acnumber,null);
        register = (Button) v.findViewById(R.id.btn_register);
        back3 = (TextView) v.findViewById(R.id.tv_back3);
        username = (EditText) v.findViewById(R.id.username_register);
        pass = (EditText) v.findViewById(R.id.userpass_register);
        rePass = (EditText) v.findViewById(R.id.repass_register);

        back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.container,fragment1).commit();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!pass.getText().toString().equals(rePass.getText().toString())){
                    Toast.makeText(v.getContext(),"两次密码不一致",Toast.LENGTH_SHORT).show();
                }else{
                    ((RegisterActivity)mActivity).getRegisterMsg().setType(StaticClass.MSG_REGISTER);
                    ((RegisterActivity)mActivity).getRegisterMsg().setUsername(username.getText().toString());
                    ((RegisterActivity)mActivity).getRegisterMsg().setPassword(pass.getText().toString());
                    DBHelper db = new DBHelper(v.getContext());
                    db.addData(((RegisterActivity)mActivity).getRegisterMsg());
                    getActivity().getSupportFragmentManager().beginTransaction().
                            replace(R.id.container,fragment3).commit();
                    db.close();
                }
            }
        });
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragment1 = (InforFragment) ((RegisterActivity)mActivity).getList().get(1);
        fragment3 = (ResultFragment) ((RegisterActivity)mActivity).getList().get(3);
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
