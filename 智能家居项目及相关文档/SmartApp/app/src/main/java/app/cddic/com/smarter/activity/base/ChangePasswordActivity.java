package app.cddic.com.smarter.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.widget.TopView;

/**
 * 项目名：  SmartApp
 * 包名：    app.cddic.com.smarter.activity.base
 * 文件名：  ChangePasswordActivity
 * 创建者：
 * 创建时间： 2017/7/23 14:09
 * 描述：
 */

public class ChangePasswordActivity extends BaseActivity implements View.OnClickListener{
    private EditText newPWD;
    private EditText checkPWD;
    private Button back;
    private Button ok;
    private TopView mTopView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initView();

        back.setOnClickListener(this);
        ok.setOnClickListener(this);

    }

    @Override
    public void onHandleMsg(int MsgType) {

    }

    void initView(){
        newPWD = (EditText) findViewById(R.id.edit_PWD);
        checkPWD = (EditText) findViewById(R.id.edit_rePWD);
        back = (Button) findViewById(R.id.button_change_password_back);
        ok = (Button) findViewById(R.id.button_change_password_ok);
        mTopView = (TopView)findViewById(R.id.change_password_topview);
        mTopView.setText("返回","修改密码",null);
    }
    protected void setupListeners() {
        mTopView.setupListeners(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        },null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_change_password_back:
                startActivity(new Intent(this,LoginActivity.class));
                finish();
                break;
            case R.id.button_change_password_ok:
                if (!(newPWD.getText().toString().equals(checkPWD.getText().toString())&&
                        newPWD.getText().toString().length() >0)){
                    Toast.makeText(this,"新密码不能为空或者两次输入不一致",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(this,"修改成功",Toast.LENGTH_LONG).show();

                }
                break;

        }
    }
}
