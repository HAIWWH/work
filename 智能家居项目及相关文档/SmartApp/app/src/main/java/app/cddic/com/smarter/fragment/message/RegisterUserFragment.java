package app.cddic.com.smarter.fragment.message;

/**
 * Created by asus on 2017/7/27.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.fragment.base.BaseFragment;
import app.cddic.com.smarter.widget.TopView;

public class RegisterUserFragment extends BaseFragment {
    private Spinner mSex;
    private Spinner mUserType;
    private FragmentManager mFm;
    private Fragment mFragment;
    private TopView mTopView;
    @Override
    protected void initViews() {
        mTopView = findView(R.id.newFriend_topView);
        mTopView.setText("返回","注册用户",null);
        mSex = (Spinner)mView.findViewById(R.id.registerUser_sex_spinner);
        mUserType = (Spinner)mView.findViewById(R.id.type_spinner);



    }

    @Override
    protected void setupAdapters() {
        String str1[] = {"男","女"};
        String str2[] = {"一般用户","中级用户","高级用户"};
        ArrayAdapter a1 = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,str1);
        ArrayAdapter a2 = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,str2);;
        mSex.setAdapter(a1);
        mUserType.setAdapter(a2);

    }

    @Override
    protected void setupListeners() {
        mTopView.setupListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        },null);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_register_user;
    }

}
