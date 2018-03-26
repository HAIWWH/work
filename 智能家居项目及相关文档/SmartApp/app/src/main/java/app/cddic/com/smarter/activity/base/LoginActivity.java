package app.cddic.com.smarter.activity.base;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.entity.ConnectMSG;
import app.cddic.com.smarter.entity.RetMSG;
import app.cddic.com.smarter.utils.StaticClass;


/**
 * 项目名：  SmartApp
 * 包名：    app.cddic.com.smarter.ui
 * 文件名：  LoginActivity
 * 创建者：
 * 创建时间： 2017/3/24 15:13
 * 描述：
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener{
    private Button login;
    private TextView toRegister;
    private Button noLogin;
    private TextView forget;
    private EditText username;
    private EditText password;
    private TextView tv_loginFailed;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        initView();
        initData();
        Log.i("test","lala");
    }

    private void initData() {
        intent = new Intent();
        username.setText(getSharedPreferences("loginMsg",1).getString("pUser",""));
        password.setText(getSharedPreferences("loginMsg",1).getString("pWord",""));
        Log.i("test","aaaa");
    }

    public void initView(){
        login = (Button) findViewById(R.id.btn_loginApp);
        noLogin = (Button) findViewById(R.id.btn_noLogin);
        toRegister = (TextView) findViewById(R.id.btn_gotoRegister);
        forget = (TextView) findViewById(R.id.btn_forget);

        login.setOnClickListener(this);
        noLogin.setOnClickListener(this);
        toRegister.setOnClickListener(this);
        forget.setOnClickListener(this);

        username = (EditText) findViewById(R.id.edt_username);
        password = (EditText) findViewById(R.id.edt_password);
        tv_loginFailed = (TextView) findViewById(R.id.tv_networkInfo);
        tv_loginFailed.setVisibility(View.GONE);
        Log.i("test","lalalalalalal");
    }



    //不同的activity有不同的实现
    @Override
    public void onHandleMsg(int MsgType) {
        Log.i("Test","get a msg"+MsgType);


        RetMSG retMSG = (RetMSG) getMsg(MsgType);

        if (MsgType != retMSG.getType()){
            Log.i("test","消息已经过期。。。");
        }
        switch (MsgType){
            case StaticClass.MSG_LOGIN:
                if (retMSG.getSort() == 3){
                    if (retMSG.getState() >= 0){//登录平台成功
                        SharedPreferences sp = getSharedPreferences("login",1);
                        sp.edit().putString("pUser",username.toString()).apply();
                        sp.edit().putString("pWord",password.toString()).apply();

                        Toast.makeText(this,"登录平台成功",Toast.LENGTH_SHORT).show();
                        intent.setClass(this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }else{
                    if (retMSG.getState() >=0){
                        Toast.makeText(this,"登录设备成功",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this,"登录设备失败",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
                break;
        }

    }

    @Override
    public void onClick(View v) {
        Log.i("test","进入登陆");
        switch (v.getId()){
            case R.id.btn_loginApp:
                if(!(username.length() > 0 && password.length()>0)) {
                    TextView show = (TextView) findViewById(R.id.show_devinf);
                    show.setText("输入信息不完整，请重新输入！");
                }else {
                    if(mAPP.getLoginState() == 0) {
                        ConnectMSG loginMsg = new ConnectMSG();
                        loginMsg.setPassword(password.getText().toString());
                        loginMsg.setUsername(username.getText().toString());
                        loginMsg.setSnum("test");
                        loginMsg.setSver("010101");
                        loginMsg.setPort(0);
                        loginMsg.setType(StaticClass.MSG_LOGIN);
                        setMsg(loginMsg);
                    }
                }
                break;
            case R.id.btn_noLogin:
                //进入APP
                intent.setClass(this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_gotoRegister:
                intent.setClass(this,RegisterActivity.class);
                intent.putExtra("fragId",2);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_forget:
                //这里需要进入手机验证界面，验证成功可以进行修改密码的操作
                intent.setClass(this,RegisterActivity.class);
                intent.putExtra("fragId",1);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tv_loginFailed.setVisibility(View.GONE);
    }
}
