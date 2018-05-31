package com.example.hai.controlscm2.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;


import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hai.controlscm2.R;
import com.example.hai.controlscm2.Service.LoginService;
import com.example.hai.controlscm2.UDP.Send;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {


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
        ButterKnife.bind(this);
    }

    @OnClick({ R.id.login_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                if (!ipEt.getText().toString().isEmpty() && !porEt.getText().toString().isEmpty()) {
                    int mPort = Integer.parseInt(porEt.getText().toString());
                    String ip = ipEt.getText().toString();
                    Send.setIp(ip);
                    Send.setSERVER_PORT(mPort);

                    /*这里的条件需要设定*/

                    Intent in = new Intent(this, LoginService.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    in.putExtra("IP",ipEt.getText().toString());
                    in.putExtra("POR",porEt.getText().toString());
                    Log.i("HAHHAH", "hahhah");
                    this.startService(in);
                    finish();

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


}
