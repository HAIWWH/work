package com.example.hai.controlscm2.Activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.hai.controlscm2.MyApplication;
import com.example.hai.controlscm2.R;
import com.example.hai.controlscm2.UDP.UDPServer;
import com.example.hai.controlscm2.UDP.UdpSend;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SensorsBrilliancyActivity extends Activity {

    private UDPServer clientSensor;
    private TextView mTextView;
    private SeekBar mSeekBar;
    private MyApplication app;

    @BindView(R.id.get_light_intensity_bt)
    Button getLightIntensityBt;
    @BindView(R.id.set_light_intensity_bt)
    Button setLightIntensityBt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors_brilliancy);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.get_light_intensity_bt,  R.id.set_light_intensity_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.get_light_intensity_bt:

                String s = "10";
                SendMsg(s);
                break;
            case R.id.set_light_intensity_bt:
                break;
        }

    }

    /*初始化*/
    protected void initView() {
        mTextView = (TextView)findViewById(R.id.light_intensity_tv);
        mSeekBar = (SeekBar)findViewById(R.id.set_light_intensity_sb);
    }

    /*事件监听*/
    protected void setupListeners() {

    }

    /*注册broadcastReceiver接收器*/
    private void bindReceiver(){
        IntentFilter udpRcvIntentFilter = new IntentFilter("udpRcvMsg");
       this.registerReceiver(broadcastReceiver,udpRcvIntentFilter);
    }
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    };



    //更新界面
    private  class MyHandler extends Handler {
        private final WeakReference<Activity> mFragment;   /*弱引用避免造成内存泄漏*/

        public MyHandler(Activity fragment) {
            mFragment = new WeakReference<Activity>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    //udpRcvStrBuf.replace(0,mFront.getText().length(),msg.obj.toString());
                    mTextView.setText("");
                    break;
            }
        }
    }

        /*发送广播*/
        public void SendMsg(String msg){
             UdpSend udpSend = new UdpSend(msg);
             new Thread(udpSend).start();
        }

}


