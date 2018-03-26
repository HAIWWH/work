package app.cddic.com.smarter.activity.base;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import app.cddic.com.smarter.fragment.LoginandOutFragment;
import app.cddic.com.smarter.fragment.device.DeviceManageFragment;
import app.cddic.com.smarter.fragment.device.DeviceMessageFragment;
import app.cddic.com.smarter.fragment.device.DeviceRealTimeVideoFragment;
import app.cddic.com.smarter.fragment.device.DeviceSettingsFragment;
import app.cddic.com.smarter.fragment.device.DeviceShareFragment;

/**
 * Created by asus on 2017/7/30.
 */

public class DeviceDetailActivity extends SingleFragmentActivity {
    private static String EXTRA_DEVICE_DETAIL="devicedetail";
    private Fragment fragment;
    private int type;

    @Override
    public void onHandleMsg(int MsgType) {

    }

    @Override
    protected Fragment createFragment() {
        type = getIntent().getIntExtra(EXTRA_DEVICE_DETAIL,0);
        switch (type){
            case 0:
                fragment = new DeviceSettingsFragment();
                break;
            case 1:
                fragment =new DeviceShareFragment();
                break;
            case 2:
                fragment = new DeviceManageFragment();
                break;
            case 3:
                break;
            case 4:
                fragment = new DeviceMessageFragment().newInstance("厨房设备");
                break;
            case 5:
                fragment = new DeviceRealTimeVideoFragment();
                break;
            case 6:
                fragment = new  LoginandOutFragment();
                break;
        }
        return fragment;
    }


    public static Intent newIntent(Context context, int type){
        Intent i = new Intent(context,DeviceDetailActivity.class);
        i.putExtra(EXTRA_DEVICE_DETAIL,type);
        return i;
    }

}
