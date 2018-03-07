package com.example.hai.controlscm1.fragment;

/*传感器界面*/

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hai.controlscm1.R;
import com.example.hai.controlscm1.UDP.UDPClient;
import com.example.hai.controlscm1.protocol.Protocol;

import java.lang.ref.WeakReference;

import static com.example.hai.controlscm1.activity.MainActivity.udpClient;

/**
 * Created by Hai on 2017/11/28.
 */

public class SensorFragment extends Fragment {
    private View mView;
    private UDPClient clientSensor = udpClient;

    private Button mRangingBtn; /*测距按钮*/
    private Button mLuminanceBtn;/*获取当前亮度*/
    private Button mLuminanceThresholdBtn;/*设置亮度阈值*/
    private Button mSmokeDensityBtn;/*获取当前烟雾浓度*/
    private Button mSmokeDensityThresholdBtn;/*设置烟雾浓度阈值*/
    private Button mTemperatureBtn; /*获取温度按钮*/
    private Button mHumidityBtn;/*获取湿度按钮*/

    private TextView mFront,mLeft,mRight;/*测距前、左、右*/
    private TextView mLuminanceTv;/*当前亮度的值*/
    private EditText mLuminanceThresholdEt;/*输入的亮度阈值*/
    private TextView mSmokeDensityTv;/*当前烟雾浓度*/
    private EditText mSmokeDensityThresholdEt;/*设置烟雾浓度的阈值*/
    private TextView mTemperatureTv;/*当前温度的值*/
    private TextView mHumidityTv;/*当前湿度的值*/


    private final MyHandler myHandler = new MyHandler(this);
    private StringBuffer udpRcvStrBuf=new StringBuffer(),udpSendStrBuf=new StringBuffer();

    private Protocol protocol;

   MyBtnClick myBtnClick = new MyBtnClick();

    /*主函数*/
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_sensor, container, false);
        initView();
        setupListeners();
        bindReceiver();
        return mView;
    }

    /*初始化*/
    protected void initView() {

        mRangingBtn = (Button)mView.findViewById(R.id.ranging_btn);
        mLuminanceBtn = (Button)mView.findViewById(R.id.luminance_btn);
        mLuminanceThresholdBtn = (Button)mView.findViewById(R.id.set_luminance_threshold_btn);
        mSmokeDensityBtn = (Button)mView.findViewById(R.id.smoke_density_btn);
        mSmokeDensityThresholdBtn = (Button)mView.findViewById(R.id.set_smoke_density_threshold_btn);
        mTemperatureBtn = (Button)mView.findViewById(R.id.temperature_btn);
        mHumidityBtn = (Button)mView.findViewById(R.id.humidity_btn);

        mFront = (TextView)mView.findViewById(R.id.front_distance_tv);
        mLeft = (TextView)mView.findViewById(R.id.left_distance_tv);
        mRight = (TextView)mView.findViewById(R.id.right_distance_tv);

        mLuminanceTv = (TextView)mView.findViewById(R.id.luminance_tv);
        mLuminanceThresholdEt = (EditText)mView.findViewById(R.id.luminance_threshold_et);

        mSmokeDensityTv = (TextView)mView.findViewById(R.id.smoke_density_tv);
        mSmokeDensityThresholdEt = (EditText)mView.findViewById(R.id.smoke_density_threshold_et);

        mTemperatureTv = (TextView)mView.findViewById(R.id.temperature_tv);
        mHumidityTv = (TextView)mView.findViewById(R.id.humidity_btn);

    }

    /*事件监听*/
    protected void setupListeners() {
        mRangingBtn.setOnClickListener(myBtnClick);
        mLuminanceBtn.setOnClickListener(myBtnClick);
        mLuminanceThresholdBtn.setOnClickListener(myBtnClick);
        mSmokeDensityBtn.setOnClickListener(myBtnClick);
        mSmokeDensityThresholdBtn.setOnClickListener(myBtnClick);
        mTemperatureBtn.setOnClickListener(myBtnClick);
        mHumidityBtn.setOnClickListener(myBtnClick);

    }


    private void bindReceiver(){/*注册broadcastReceiver接收器*/
        IntentFilter udpRcvIntentFilter = new IntentFilter("udpRcvMsg");
        getActivity().registerReceiver(broadcastReceiver,udpRcvIntentFilter);
    }


    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra("udpRcvMsg"))  {
                Message message = new Message();
                String a = intent.getStringExtra("udpRcvMsg"); /*得到通过了协议处理后的数据报*/
                int b =Integer.parseInt(a.substring(0,1));/* b 是判断是不是这个界面的*/
                int c = Integer.parseInt(a.substring(1,2));/* c 是判断哪个组件来显示*/
                int d = Integer.parseInt(a.substring(2)); /* d 是要显示的数据*/
                message.obj= Integer.toString(d);
                if(b == 1) {
                    switch (c) {
                        case 1:
                            message.what = 1;
                            break;
                        case 2:
                            message.what = 2;
                            break;
                        case 3:
                            message.what = 3;
                            break;
                        case 4:
                            message.what = 4;
                            break;
                        case 5:
                            message.what = 5;
                            break;
                        case 6:
                            message.what = 6;
                            break;
                        case 7:
                            message.what = 7;
                            break;
                    }
                    Log.i("主界面Broadcast","收到");
                    myHandler.sendMessage(message);
                }

            }
        }
    };



   class MyBtnClick implements View.OnClickListener{

       @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ranging_btn:
                    protocol = new Protocol(0,0,0,0,0);
                    sendSensor(protocol.sendProtocol());
                    break;
                case R.id.luminance_btn:
                    protocol = new Protocol(0,1,0,0,0);
                    sendSensor(protocol.sendProtocol());
                 //   sendSensor("hah".getBytes());
                    break;
                case R.id.set_luminance_threshold_btn:

                    if(mLuminanceThresholdEt.getText().toString() != "");{
                    protocol = new Protocol(0,1,0,0,Integer.parseInt(mLuminanceThresholdEt.getText().toString()));
                    sendSensor(protocol.sendProtocol());

                }
                    break;
                case R.id.smoke_density_btn:
                    protocol = new Protocol(0,2,0,0,0);
                    sendSensor(protocol.sendProtocol());
                    break;
                case R.id.set_smoke_density_threshold_btn:
                    if (mSmokeDensityThresholdEt.getText().toString() != ""){
                        protocol = new Protocol(0,2,0,0,Integer.parseInt(mSmokeDensityThresholdEt.getText().toString()));
                        sendSensor(protocol.sendProtocol());
                    }
                    break;
                case R.id.temperature_btn:
                    protocol = new Protocol(0,3,0,0);
                    sendSensor(protocol.sendProtocol());
                    break;
                case R.id.humidity_btn:
                    protocol = new Protocol(0,3,0,1);
                    sendSensor(protocol.sendProtocol());
                    break;

            }
        }
    }

     public void sendSensor(final byte[] masg){
           new Thread(new Runnable() {
             @Override
             public void run() {
                // Message message = new Message();
                 if (masg.length!=0){
                     Log.i("udpClient",masg.toString());
                     clientSensor.send(masg);
                      }
             }
         }).start();
     }


    //更新界面
    private  class MyHandler extends Handler {
        private final WeakReference<Fragment> mFragment;   /*弱引用避免造成内存泄漏*/
        public MyHandler(Fragment fragment){
            mFragment = new WeakReference<Fragment>(fragment);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    udpRcvStrBuf.replace(0,mFront.getText().length(),msg.obj.toString());
                    mFront.setText(udpRcvStrBuf.toString());
                    break;
                case 2:
                    udpRcvStrBuf.replace(0,mLeft.getText().length(),msg.obj.toString());
                    mLeft.setText(udpRcvStrBuf.toString());
                    break;
                case 3:
                    udpRcvStrBuf.replace(0,mRight.getText().length(),msg.obj.toString());
                    mRight.setText(udpRcvStrBuf.toString());
                    break;
                case 4:
                    udpRcvStrBuf.replace(0,mLuminanceTv.getText().length(),msg.obj.toString());
                    mLuminanceTv.setText(udpRcvStrBuf.toString());
                    break;
                case 5:
                    udpRcvStrBuf.replace(0,mSmokeDensityTv.getText().length(),msg.obj.toString());
                    mSmokeDensityTv.setText(udpRcvStrBuf.toString());
                    break;
                case 6:
                    udpRcvStrBuf.replace(0,mTemperatureTv.getText().length(),msg.obj.toString());
                    mTemperatureTv.setText(udpRcvStrBuf.toString());
                    break;
                case 7:
                    udpRcvStrBuf.replace(0,mHumidityTv.getText().length(),msg.obj.toString());
                    mHumidityTv.setText(udpRcvStrBuf.toString());
                    break;
            }
        }
    }

}