package app.cddic.com.smarter.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.entity.RegisterMSG;
import app.cddic.com.smarter.fragment.ACNumberFragment;
import app.cddic.com.smarter.fragment.base.BaseFragment;
import app.cddic.com.smarter.fragment.CheckPhoneFragment;
import app.cddic.com.smarter.fragment.InforFragment;
import app.cddic.com.smarter.fragment.drawer.ResultFragment;

/**
 * 项目名：  SmartApp
 * 包名：    app.cddic.com.smarter.activity.base
 * 文件名：  RegisterActivity
 * 创建者：
 * 创建时间： 2017/4/11 16:08
 * 描述：
 */

public class RegisterActivity extends BaseActivity {
    private RegisterMSG registerMsg;
    private List<BaseFragment> list;

    private CheckPhoneFragment fragment0;
    private InforFragment fragment1;
    private ACNumberFragment fragment2;
    private ResultFragment fragment3;

    public void setRegisterMsg(RegisterMSG registerMsg) {
        this.registerMsg = registerMsg;
    }

    public RegisterMSG getRegisterMsg(){
        return registerMsg;
    }

    public List<BaseFragment> getList() {
        return list;
    }

    public void setList(List<BaseFragment> list) {
        this.list = list;
    }


    @Override
    public void onHandleMsg(int MsgType) {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initData();

        int index = getIntent().getIntExtra("fragId",0);
        Log.i("test","index="+index);
        mFragmentManager.beginTransaction().add(R.id.container,fragment0,null).commit();
    }

    private void initData() {
        registerMsg = new RegisterMSG();
        list = new ArrayList<>();
        fragment0 = new CheckPhoneFragment();
        fragment1 = new InforFragment();
        fragment2 = new ACNumberFragment();
        fragment3 = new ResultFragment();

        list.add(fragment0);
        list.add(fragment1);
        list.add(fragment2);
        list.add(fragment3);
    }


}
