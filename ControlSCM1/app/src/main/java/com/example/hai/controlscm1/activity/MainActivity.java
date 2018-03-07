package com.example.hai.controlscm1.activity;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hai.controlscm1.R;
import com.example.hai.controlscm1.UDP.UDPClient;
import com.example.hai.controlscm1.fragment.CarFragment;
import com.example.hai.controlscm1.fragment.PowerFragment;
import com.example.hai.controlscm1.fragment.SensorFragment;


public class MainActivity extends FragmentActivity{
        private LinearLayout mCarLL ;
        private LinearLayout mDateLL;
        private LinearLayout mSensor;
        private Fragment  mFragment;
        private FragmentManager fm;
        private TextView mTimeTV;
        public static Context context;
        public static UDPClient udpClient;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            context = this;
            fm = getSupportFragmentManager();
            mFragment= new CarFragment();
            fm.beginTransaction().add(R.id.conter,mFragment).commit();
            initView();
            setupListeners();
        }

   /*初始化*/
    protected void initView() {
        mCarLL =(LinearLayout) findViewById(R.id.car);
        mDateLL = (LinearLayout)findViewById(R.id.power);
        mSensor = (LinearLayout)findViewById(R.id.sensor);
        mTimeTV = (TextView)findViewById(R.id.time);
    }

    /*事件监听*/
    protected void setupListeners() {

        mCarLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragment = new CarFragment();
                fm.beginTransaction().replace(R.id.conter,mFragment).commit();
            }
        });

        mDateLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragment = new PowerFragment();
                fm.beginTransaction().replace(R.id.conter,mFragment).commit();
            }
        });
        mSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragment = new SensorFragment();
                fm.beginTransaction().replace(R.id.conter,mFragment).commit();
            }
        });

        new TimeThread().start();
    }

    
    /*时间线程*/
    class TimeThread extends Thread {
        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = 1;  //消息(一个整型值)
                    mHandler.sendMessage(msg);// 每隔1秒发送一个msg给mHandler
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    }

    // /在主线程里面处理消息并更新UI界面
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    long sysTime = System.currentTimeMillis();
                    CharSequence sysTimeStr = DateFormat.format("yyyy/MM/dd HH:mm:ss", sysTime);
                    mTimeTV.setText(sysTimeStr); //更新时间
                        break;
                case 2:
                    break;
                default:
                        break;

            }
        }
    };

}


