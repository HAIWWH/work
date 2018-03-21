package com.example.hai.controlscm2.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hai.controlscm2.R;
import com.example.hai.controlscm2.Service.LoginService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences mSpf;
    private SharedPreferences.Editor mSpfE;


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
        initView();
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
                    mSpfE = mSpf.edit();
                    mSpfE.putString("ip",ipEt.getText().toString());
                    mSpfE.putString("port",porEt.getText().toString());
                    mSpfE.apply();
                    Intent intent = new Intent(this, LoginService.class);
                    intent.putExtra("IP",ipEt.getText());
                    intent.putExtra("por",mPort);
                    startService(intent);
                }

                break;
        }
    }

    /*初始化控件*/
    public void initView(){
        mSpf = PreferenceManager.getDefaultSharedPreferences(this);
        ipEt.setText(mSpf.getString("ip",""));
        porEt.setText(mSpf.getString("port",""));
    }
}
