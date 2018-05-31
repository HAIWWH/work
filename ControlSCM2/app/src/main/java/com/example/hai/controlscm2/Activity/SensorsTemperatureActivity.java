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
import android.widget.TextView;

import com.example.hai.controlscm2.Protocol.Protocol;
import com.example.hai.controlscm2.Protocol.ReceiveData;
import com.example.hai.controlscm2.R;
import com.example.hai.controlscm2.UDP.UdpSend;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SensorsTemperatureActivity extends BaseActivity {

    private final MyHandler myHandler = new MyHandler(this);
    private StringBuffer udpRcvStrBuf=new StringBuffer(),udpSendStrBuf=new StringBuffer();


    @BindView(R.id.temperature_and_humidity_tv)
    TextView temperatureAndHumidityTv;
    @BindView(R.id.get_temperature_bt)
    Button getTemperatureBt;
    @BindView(R.id.get_humidity_bt)
    Button getHumidityBt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors_temperature);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.get_temperature_bt, R.id.get_humidity_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.get_temperature_bt:
                SendMsg(new Protocol(0,3,0,0).sendProtocol());
                break;
            case R.id.get_humidity_bt:
                SendMsg(new Protocol(0,3,0,1).sendProtocol());
                break;
        }
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
                    if(c == 3){
                        switch (d) {
                            case 0:
                                message.what = 0;
                                break;
                            case 1:
                                message.what = 1;
                                break;
                        }
                        Log.i("主界面Broadcast","收到");
                        myHandler.sendMessage(message);
                    }

                }

            }
        }
    };
    /*注册broadcastReceiver接收器*/
    private void bindReceiver(){
        IntentFilter udpRcvIntentFilter = new IntentFilter("udpRcvMsg");
        this.registerReceiver(broadcastReceiver,udpRcvIntentFilter);
    }

    @Override
    public void initView() {

    }

    @Override
    public void setupListeners() {

    }


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
                case 0:
                    udpRcvStrBuf.replace(0,temperatureAndHumidityTv.getText().length(),msg.obj.toString());
                    temperatureAndHumidityTv.setText("温度为："+udpRcvStrBuf.toString());
                    break;
                case 1:
                    udpRcvStrBuf.replace(0,temperatureAndHumidityTv.getText().length(),msg.obj.toString());
                    temperatureAndHumidityTv.setText("湿度为："+udpRcvStrBuf.toString());
                    break;
            }
        }
    }


}
