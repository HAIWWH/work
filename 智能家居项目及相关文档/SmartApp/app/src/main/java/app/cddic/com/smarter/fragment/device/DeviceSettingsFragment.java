package app.cddic.com.smarter.fragment.device;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.entity.SettingMSG;
import app.cddic.com.smarter.fragment.base.BaseFragment;
import app.cddic.com.smarter.widget.TopView;

/**
 * Created by Hai on 2017/4/24.
 */

public class DeviceSettingsFragment extends BaseFragment {
    private TopView mTopView;
    private Button mConfirmChangeBtn;
    private Button mLoginAgainBtn;
    private EditText mDeviceAliasEt;
    private EditText mDeviceLongitudeEt;
    private EditText mDeviceLatitudeEt;
    private Switch mLoginPlatformSth;
    private EditText mLoginUsernameEt;
    private EditText mLoginPasswordEt;

    @Override
    protected void initViews() {
        mTopView = findView(R.id.topView);
        mTopView.setText("返回","设备设置",null);
        mConfirmChangeBtn = findView(R.id.confirmChange_btn);
        mLoginAgainBtn = findView(R.id.loginAgain_btn);
        mLoginPlatformSth = findView(R.id.loginPlatform_switch);
        SettingMSG settingMSG = new SettingMSG("厨房设备","东经104.06度","北纬30.67度","123456","123456");
        List<SettingMSG> settingMSGList = new ArrayList<>();
        settingMSGList.add(settingMSG);
        mDeviceAliasEt = findView(R.id.deviceAlias_editText);
        mDeviceAliasEt.setText(settingMSGList.get(0).getDeviceAlias());
        mDeviceAliasEt.setSelection(settingMSGList.get(0).getDeviceAlias().length());
        mDeviceLongitudeEt = findView(R.id.deviceLongitude_editText);
        mDeviceLongitudeEt.setText(settingMSGList.get(0).getDeviceLongitude());
        mDeviceLongitudeEt.setSelection(settingMSGList.get(0).getDeviceLongitude().length());
        mDeviceLatitudeEt = findView(R.id.deviceLatitude_editText);
        mDeviceLatitudeEt.setText(settingMSGList.get(0).getDeviceLatitude());
        mDeviceLatitudeEt.setSelection(settingMSGList.get(0).getDeviceLatitude().length());
        mLoginUsernameEt = findView(R.id.loginUsername_editText);
        mLoginUsernameEt.setText(settingMSGList.get(0).getSettingUserName());
        mLoginUsernameEt.setSelection(settingMSGList.get(0).getSettingUserName().length());
        mLoginPasswordEt = findView(R.id.loginPassword_editText);
        mLoginPasswordEt.setText(settingMSGList.get(0).getDeviceLogPassWord());
        mLoginPasswordEt.setSelection(settingMSGList.get(0).getDeviceLogPassWord().length());

    }

    @Override
    protected void setupAdapters() {

    }

    @Override
    protected void setupListeners() {
        mTopView.setupListeners(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        },null);

        mConfirmChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"修改成功",Toast.LENGTH_SHORT).show();
            }
        });
        mLoginAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重新登录
            }
        });
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_device_settings;
    }
}
