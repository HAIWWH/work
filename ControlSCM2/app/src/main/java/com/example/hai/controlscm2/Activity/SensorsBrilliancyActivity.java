package com.example.hai.controlscm2.Activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.hai.controlscm2.Protocol.Protocol;
import com.example.hai.controlscm2.Protocol.ReceiveData;
import com.example.hai.controlscm2.R;


import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SensorsBrilliancyActivity extends BaseActivity {

    private TextView mTextView;
    private SeekBar mSeekBar;


    private final MyHandler myHandler = new MyHandler(this);


    private StringBuffer udpRcvStrBuf=new StringBuffer(),udpSendStrBuf=new StringBuffer();

    @BindView(R.id.get_light_intensity_bt)
    Button getLightIntensityBt;
    @BindView(R.id.set_light_intensity_bt)
    Button setLightIntensityBt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors_brilliancy);
        initView();
        bindReceiver();
        ButterKnife.bind(this);
    }

    @OnClick({R.id.get_light_intensity_bt,  R.id.set_light_intensity_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.get_light_intensity_bt:
                SendMsg(new Protocol(0,1,0,0).sendProtocol());
                break;
            case R.id.set_light_intensity_bt:
                SendMsg(new Protocol(0,1,0,1,mSeekBar.getProgress()).sendProtocol());
                break;
        }

    }

    @Override
    /*初始化*/
  public void initView() {
        mTextView = (TextView)findViewById(R.id.light_intensity_tv);
        mSeekBar = (SeekBar)findViewById(R.id.set_light_intensity_sb);
    }

    @Override
    /*事件监听*/
    public void setupListeners() {

    }

    /*注册broadcastReceiver接收器*/
    private void bindReceiver(){
        IntentFilter udpRcvIntentFilter = new IntentFilter("udpRcvMsg");
        this.registerReceiver(broadcastReceiver,udpRcvIntentFilter);
    }
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra("udpRcvMsg"))  {
                Message message = new Message();
                ReceiveData a = (ReceiveData) intent.getSerializableExtra("udpRcvMsg"); /*得到通过了协议处理后的数据报*/
                int b =a.getR_DEVICE_CLASS();/* 类名*/
                int c =a.getR_DEVICE();/*设备名*/
                int d = a.getDEVICE_CONTROL_ID();/*控制号*/
                int e = Integer.parseInt(a.getRECEIVE_DATA()); /* d 是要显示的数据*/
                message.obj= Integer.toString(e);
                if(b == 0) {
                    if(c == 1){
                        switch (d) {
                            case 0:
                                message.what = 1;
                                break;
                            case 1:
                                if(e==0){
                                    Popup("成功");
                                }
                                else Popup("失败");
                                break;
                        }
                        Log.i("主界面Broadcast","收到");
                        myHandler.sendMessage(message);
                    }

                }

            }
        }
    };

//更新界面
    private  class MyHandler extends Handler {

        private final WeakReference<Activity> mActivity;   /*弱引用避免造成内存泄漏*/
        public MyHandler(Activity activity) {
            mActivity = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    udpRcvStrBuf.replace(0,mTextView.getText().length(),msg.obj.toString());
                    mTextView.setText(udpRcvStrBuf.toString());
                    break;
            }
        }
    }


}


