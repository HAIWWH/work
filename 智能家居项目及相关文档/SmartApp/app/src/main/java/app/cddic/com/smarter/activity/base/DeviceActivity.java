package app.cddic.com.smarter.activity.base;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import app.cddic.com.smarter.entity.ConnectMSG;
import app.cddic.com.smarter.entity.RetMSG;
import app.cddic.com.smarter.fragment.LoginandOutFragment;
import app.cddic.com.smarter.fragment.device.AssociateNewDeviceFragment;
import app.cddic.com.smarter.fragment.device.DeviceDetailsFragment;
import app.cddic.com.smarter.fragment.device.DeviceMessageFragment;
import app.cddic.com.smarter.fragment.device.SearchDeviceFragment;
import app.cddic.com.smarter.utils.StaticClass;

/**
 * SmartApp
 * app.cddic.com.smarter.activity.base
 * Created by Pantiy on 2017/5/21.
 * Copyright ? 2017 All rights Reserved by Pantiy
 */

public class DeviceActivity extends SingleFragmentActivity {

    private static final String EXTRA_TYPE = "extraType";
    private static final String EXTRA_DEVICE_NAME = "deviceName";

    public static Intent newInstance(Context context, int type, String deviceName) {
        Intent intent = new Intent(context, DeviceActivity.class);
        intent.putExtra(EXTRA_TYPE, type);
        intent.putExtra(EXTRA_DEVICE_NAME, deviceName);
        return intent;
    }

    @Override
    public void onHandleMsg(int MsgType) {
        Log.i("test","MsgType = " + MsgType);

        RetMSG retMSG = (RetMSG) getMsg (MsgType);
        if (MsgType != retMSG.getType()){
            Log.i("test","消息已经过期。。。");
        }

        switch (MsgType){
            case StaticClass.MSG_LANFIND:
                //将消息送到界面
                Log.i("test","送消息到界面");
                Log.i("test","retMsg = " + retMSG.toString());
                SearchDeviceFragment.mDevice.add(retMSG);
        }
    }

    @Override
    protected Fragment createFragment() {

        switch (getIntent().getIntExtra(EXTRA_TYPE, -1)) {

            case Type.DEVICE_DETAIL:
                return new DeviceDetailsFragment();
            case Type.DEVICE_MESSAGE:
                return DeviceMessageFragment.newInstance(getIntent().getStringExtra(EXTRA_DEVICE_NAME));
            case Type.DEVICE_LOGIN:
                return new LoginandOutFragment();
            case Type.SEARCH_DEVICE:
                ConnectMSG loginMsg = new ConnectMSG();
                loginMsg.setUsername("88888888");
                loginMsg.setPort(8765);
                loginMsg.setType(StaticClass.MSG_LANFIND);
                setMsg(loginMsg);
                return new SearchDeviceFragment();
            case Type.ADD_DEVICE:
                return new AssociateNewDeviceFragment();
            default:
                return null;
        }

    }

    public static final class Type {
        public static final int DEVICE_DETAIL = 0;
        public static final int DEVICE_MESSAGE = 1;
        public static final int DEVICE_LOGIN=2;
        public static final int SEARCH_DEVICE = 3;
        public static final int ADD_DEVICE = 4;
    }
}