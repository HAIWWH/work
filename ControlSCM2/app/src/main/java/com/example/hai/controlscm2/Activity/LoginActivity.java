package com.example.hai.controlscm2.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hai.controlscm2.R;
import com.example.hai.controlscm2.Service.LoginService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

   // private SharedPreferences mSpf = null;
   // private SharedPreferences.Editor mSpfE;


    @BindView(R.id.ip_et)
    EditText ipEt;
    @BindView(R.id.por_et)
    EditText porEt;
    @BindView(R.id.login_button)
    Button loginUtton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       // initView();
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ip_et, R.id.por_et, R.id.login_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ip_et:
                break;
            case R.id.por_et:
                break;
            case R.id.login_button:
                if (!ipEt.getText().toString().isEmpty() && !porEt.getText().toString().isEmpty()) {
                    int mPort = Integer.parseInt(porEt.getText().toString());
//                    mSpfE = mSpf.edit();
//                    mSpfE.putString("ip",ipEt.getText().toString());
//                    mSpfE.putString("port",porEt.getText().toString());
//                    mSpfE.apply();
                    Intent intent = new Intent(this, LoginService.class);
//                    intent.putExtra("IP",ipEt.getText());
//                    intent.putExtra("por",mPort);
                    intent.putExtra("IP","12323");
                    intent.putExtra("por","122");
                    Log.i("HAH","haai");
                    startService(intent);
                    /*这里的条件需要设定*/
                    Intent in = new Intent(this, MainActivity.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Log.i("HAHHAH", "hahhah");
                    this.startActivity(in);


                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    //    设置Title的图标
                    builder.setIcon(R.drawable.ic_launcher_background);
                    //    设置Title的内容
                    builder.setTitle("弹出警告框");
                    //    设置Content来显示一个信息
                    builder.setMessage("IP和Pro不能为空！！！");
                    //    设置一个PositiveButton
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {

                        }
                    });
                    //    设置一个NegativeButton
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {

                        }
                    });
                    //    设置一个NeutralButton

                    //    显示出该对话框
                    builder.show();
                }

                break;
        }
    }

    /*初始化控件*/
//    public void initView(){
//        mSpf = PreferenceManager.getDefaultSharedPreferences(this);
//        ipEt.setText(mSpf.getString("ip",""));
//        porEt.setText(mSpf.getString("port",""));
//    }
}
