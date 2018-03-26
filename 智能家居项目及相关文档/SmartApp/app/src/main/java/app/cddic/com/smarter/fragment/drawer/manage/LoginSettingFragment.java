package app.cddic.com.smarter.fragment.drawer.manage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.widget.TopView;

/**
 * Created by asus on 2017/3/28.
 */

public class LoginSettingFragment extends ManageFragment {
    private FragmentManager fm;
    private Fragment fragment;
    private TopView mTopView;
    private TextView textViewLoginSettingIn;
    private TextView textViewLoginSettingOut;
    private TextView textviewLoginSettingTest1;
    private TextView textviewLoginSettingTest2;
    @Override
    protected void setFragmentName() {
        mFragmentName = "登录设置";

    }

    @Override
    protected void initViews() {

        textViewLoginSettingIn = (TextView) mView.findViewById(R.id.textView_login_setting_in);
        textViewLoginSettingOut = (TextView)mView.findViewById(R.id.textView_login_setting_out);
        textviewLoginSettingTest1 = (TextView)mView.findViewById(R.id.textview_login_setting_test1);
        textviewLoginSettingTest2 = (TextView)mView.findViewById(R.id.textview_login_setting_test2);
        textviewLoginSettingTest1.setVisibility(View.VISIBLE);
        textviewLoginSettingTest2.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void setupAdapters() {

    }

    @Override
    protected void setupListeners() {
        fm = getActivity().getSupportFragmentManager();
        textViewLoginSettingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textviewLoginSettingTest1.setVisibility(View.VISIBLE);
                textviewLoginSettingTest2.setVisibility(View.INVISIBLE);
            }
        });
        textViewLoginSettingOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textviewLoginSettingTest1.setVisibility(View.INVISIBLE);
                textviewLoginSettingTest2.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_login_setting;
    }
}
