package app.cddic.com.smarter.fragment.drawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.activity.base.LoginActivity;
import app.cddic.com.smarter.activity.base.RegisterActivity;
import app.cddic.com.smarter.fragment.base.BaseFragment;

/**
 * 项目名：  SmartApp
 * 包名：    app.cddic.com.smarter.fragment
 * 文件名：  RResultFragment
 * 创建者：
 * 创建时间： 2017/4/15 18:57
 * 描述：
 */

public class ResultFragment extends BaseFragment {
    private Thread t;
    private TextView username;
    private TextView password;

    @Override
    public void onResume() {
        super.onResume();
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    mActivity.startActivity(new Intent(getContext(), LoginActivity.class));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_result,null);
        username = (TextView) v.findViewById(R.id.result_username_textView);
        password = (TextView) v.findViewById(R.id.result_password_textView);

        username.setText("你的账号："+((RegisterActivity)mActivity).getRegisterMsg().getUsername());
        password.setText("密码："+((RegisterActivity)mActivity).getRegisterMsg().getPassword());
        return v;
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        t.interrupt();
    }
}
