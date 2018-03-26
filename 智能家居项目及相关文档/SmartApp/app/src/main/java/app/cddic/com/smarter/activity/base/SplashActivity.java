package app.cddic.com.smarter.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.global.SmartApplication;
import app.cddic.com.smarter.utils.StaticClass;
import app.cddic.com.smarter.utils.UtilTools;

/**
 * Created by ASUS on 2017/3/5.
 * 闪屏页，程序入口
 * 1.延迟两秒进入
 * 2.全屏主题
 * 3.设置字体
 */

public class SplashActivity extends AppCompatActivity {

    private TextView tv_splash;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case StaticClass.MSG_SPLASH:
                    if (isFirst()){
                        startActivity(new Intent(SplashActivity.this,GuideActivity.class));
                        finish();
                        break;
                    }else{
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                        break;
                    }
                    default:
                        break;
            }
        }
    };

    //判断是否第一次进入程序
    private boolean isFirst() {
        SmartApplication mAPP = (SmartApplication) this.getApplication();
        if(mAPP.getCount()<=1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //加载初始化方法
        initView();
    }

    //初始化view
    private void initView() {
        tv_splash = (TextView) findViewById(R.id.tv_splash);
        //延迟两秒钟发送消息
        handler.sendEmptyMessageDelayed(StaticClass.MSG_SPLASH,2000);

        //设置字体
        UtilTools.setFont(this,tv_splash);
    }

    //禁止返回键
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
