package app.cddic.com.smarter.fragment.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.activity.base.MainActivity;
import app.cddic.com.smarter.fragment.base.BaseFragment;
import app.cddic.com.smarter.widget.TopView;


/**
 * Created by asus on 2017/3/28.
 */

public class NewPassWordLoginFragment extends BaseFragment {
    private TopView mTopView;
    private Fragment mFragment;
    private FragmentManager fm;
    private Button mButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "NewPassWordLogin create", Toast.LENGTH_SHORT).show();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initViews() {
        mTopView = (TopView)mView.findViewById(R.id.newPasswordLogin_topView);
        mTopView.setText("返回","新密码登录","通知");
        mButton = (Button)mView.findViewById(R.id.next_btn);

    }

    @Override
    protected void setupAdapters() {

    }

    @Override
    protected void setupListeners() {
        mTopView.setupListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        },null);
        fm = getActivity().getSupportFragmentManager();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragment = new KeyFindSuccessFragment();
                fm.beginTransaction().replace(R.id.container,mFragment).commit();
            }
        });


    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_newpassword_login;
    }
}
