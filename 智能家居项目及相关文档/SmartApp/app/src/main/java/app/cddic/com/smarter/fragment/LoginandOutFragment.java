package app.cddic.com.smarter.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.activity.base.MainActivity;
import app.cddic.com.smarter.entity.DeviceLoginMSG;
import app.cddic.com.smarter.fragment.base.BaseFragment;
import app.cddic.com.smarter.widget.TopView;


/**
 * Created by asus on 2017/3/28.
 */

public class LoginandOutFragment extends BaseFragment {

    private TopView mTopView;
    private Button mLoginButton;
    private EditText mBarCode;
    private EditText mAddress;
    private EditText mAuthority;
    private EditText mId;
    private EditText mLoginKey;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "LoginandOut create", Toast.LENGTH_SHORT).show();
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(android.R.layout.simple_list_item_1,container,false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    protected void initViews() {

        mTopView = (TopView)mView.findViewById(R.id.topView);
        mTopView.setText("返回","设备登陆","退出当前设备");
        mLoginButton=(Button)mView.findViewById(R.id.login_btn);
        mBarCode = (EditText)mView.findViewById(R.id.deviceBarCode_editText);
        mAddress = (EditText)mView.findViewById(R.id.deviceAddress_editText);
        mAuthority = (EditText)mView.findViewById(R.id.deviceAuthority_editText);
        mId = (EditText)mView.findViewById(R.id.deviceId_editText);
        mLoginKey = (EditText)mView.findViewById(R.id.loginPassword_editText);
        List<DeviceLoginMSG> deviceLoginMSGList = new ArrayList<>();
        DeviceLoginMSG deviceLoginMSG = new DeviceLoginMSG("#1234abc","成都","可控制","123456789","123456789");
        deviceLoginMSGList.add(deviceLoginMSG);
        mBarCode.setText(deviceLoginMSGList.get(0).getBarCode());
        mAddress.setText("成都");
        mAddress.setText("成都");
        mAuthority.setText("可控制");
        mId.setText("123456789");
        mLoginKey.setText("123456789");
        mBarCode.setSelection(mBarCode.getText().length());

    }
    @Override
    protected void setupAdapters() {

    }

    @Override
    protected void setupListeners() {

        mTopView.setupListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();

            }
        }, new View.OnClickListener() {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            @Override
            public void onClick(View v) {

                builder.setTitle("退出当前设备").setMessage("退出当前设备可能会影响您的。。。确定退出？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                     Intent intent=new Intent(getActivity(),MainActivity.class);
                        startActivity(intent);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //取消按钮的点击事件
                    }
                }).show();
            }
        });
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"登录成功",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_login_and_out;
    }
}
