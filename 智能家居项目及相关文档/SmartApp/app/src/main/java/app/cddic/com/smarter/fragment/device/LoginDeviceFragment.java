package app.cddic.com.smarter.fragment.device;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.fragment.drawer.manage.ManageFragment;
import app.cddic.com.smarter.widget.TopView;


/**
 * Created by asus on 2017/3/28.
 */

public class LoginDeviceFragment extends ManageFragment {

    private FragmentManager mFm;
    private Fragment mFragment;
    private TopView mTopView;
    @Override
    protected void setFragmentName() {
        mFragmentName = "登录设备";

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "AddDevice create", Toast.LENGTH_SHORT).show();
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(android.R.layout.simple_list_item_1,container,false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    protected void initViews() {

        mTopView = (TopView) mView.findViewById(R.id.addDevice_topView);
        mTopView.setText("返回","设备登录","退出当前设备");

    }
    @Override
    protected void setupAdapters() {

    }

    @Override
    protected void setupListeners() {
        mFm = getActivity().getSupportFragmentManager();
        mTopView.setupListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();

            }
        },null);

    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_add_device;
    }
}

