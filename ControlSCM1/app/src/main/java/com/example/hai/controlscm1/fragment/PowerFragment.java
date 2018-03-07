package com.example.hai.controlscm1.fragment;

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
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hai.controlscm1.R;
import com.example.hai.controlscm1.UDP.UDPClient;

import java.lang.ref.WeakReference;

import static com.example.hai.controlscm1.activity.MainActivity.udpClient;

/**
 * Created by Hai on 2017/11/18.
 */

public class PowerFragment extends Fragment {

    protected View mView;
    private UDPClient clientPower = udpClient;

    private Button mElectricQuantityGainBt;
    private Button mPowerSupplyModeGainBt;
    private Button mPowerSupplyModeSetBt;
    private Button mObtainLongitudeBt;
    private Button mObtainLatitudeBt;
    private Button mObtainAltitudeBt;
    private Button mObtainSatelliteTime;

    private TextView mElectricQuantityTv;
    private TextView mPowerSupplyModeTv;
    private TextView mLongitudeTv;
    private TextView mLatitudeTv;
    private TextView mAltitudeTv;
    private TextView mSatelliteTimeTv;

    private Spinner mSpinner;
    private StringBuffer udpRcvStrBuf=new StringBuffer(),udpSendStrBuf=new StringBuffer();

    private MyBtnClick myBtnClickPpwer = new MyBtnClick();
    private final MyHandlerPower myHandlerPower = new MyHandlerPower(this);
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_power, container, false);

        initView();
        setupListeners();
        bindReceiver();
        return mView;
    }

    /*初始化*/
    protected void initView() {
        mElectricQuantityGainBt = (Button) mView.findViewById(R.id.electric_quantity_gain_btn);
        mPowerSupplyModeGainBt = (Button) mView.findViewById(R.id.power_supply_mode_gain_btn);
        mPowerSupplyModeSetBt = (Button) mView.findViewById(R.id.power_supply_mode_set_btn);
        mObtainLongitudeBt = (Button) mView.findViewById(R.id.obtain_longitude_btn);
        mObtainLatitudeBt = (Button) mView.findViewById(R.id.obtain_latitude_btn);
        mObtainAltitudeBt = (Button) mView.findViewById(R.id.obtain_altitude_btn);
        mObtainSatelliteTime = (Button) mView.findViewById(R.id.obtain_satellite_time);

        mElectricQuantityTv = (TextView) mView.findViewById(R.id.electric_quantity_tv);
        mPowerSupplyModeTv = (TextView) mView.findViewById(R.id.power_supply_mode_tv);
        mLongitudeTv = (TextView) mView.findViewById(R.id.longitude_tv);
        mLatitudeTv = (TextView) mView.findViewById(R.id.latitude_tv);
        mAltitudeTv = (TextView) mView.findViewById(R.id.altitude_tv);
        mSatelliteTimeTv = (TextView) mView.findViewById(R.id.satellite_time_tv);

        mSpinner = (Spinner) mView.findViewById(R.id.spinner);

    }

    /*事件监听*/
    protected void setupListeners() {
        mElectricQuantityTv.setOnClickListener(myBtnClickPpwer);
        mPowerSupplyModeGainBt.setOnClickListener(myBtnClickPpwer);
        mPowerSupplyModeSetBt.setOnClickListener(myBtnClickPpwer);
        mObtainLongitudeBt.setOnClickListener(myBtnClickPpwer);
        mObtainLatitudeBt.setOnClickListener(myBtnClickPpwer);
        mObtainAltitudeBt.setOnClickListener(myBtnClickPpwer);
        mObtainSatelliteTime.setOnClickListener(myBtnClickPpwer);

    }


    class MyBtnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
//                case R.id.electric_quantity_gain_btn:
//                    sendPower("1");
//                    break;
//                case R.id.power_supply_mode_gain_btn:
//                    sendPower("2");
//                    break;
//                case R.id.power_supply_mode_set_btn:
//                    sendPower("3");
//                break;
//                case R.id.obtain_longitude_btn:
//                    sendPower("4");
//                    break;
//                case R.id.obtain_latitude_btn:
//                        sendPower("5");
//                    break;
//                case R.id.obtain_altitude_btn:
//                    sendPower("6");
//                    break;
//                case R.id.obtain_satellite_time:
//                    sendPower("7");
//                    break;

            }
        }
    }


//    public void sendPower(final String masg){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                // Message message = new Message();
//                if (masg!=""){
//                    Log.i("udpClient",masg);
//                    clientPower.send(masg+" ");
//                }
//            }
//        }).start();
//    }






    private void bindReceiver(){/*注册broadcastReceiver接收器*/
        IntentFilter udpRcvIntentFilter = new IntentFilter("udpRcvMsg");
        getActivity().registerReceiver(broadcastReceiver,udpRcvIntentFilter);
    }
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra("udpRcvMsg"))  {
                Message message = new Message();
                String a = intent.getStringExtra("udpRcvMsg");
                int c = Integer.parseInt(a.substring(1));
                message.obj= Integer.toString(c);
                int b =Integer.parseInt(a.substring(0,1));
                if(b == 2) {
                    switch (c) {
                        case 1:
                            message.what = 1;  /*显示距离前*/
                            break;
                        case 2:
                            message.what = 2; /*显示距离左*/
                            break;
                        case 3:
                            message.what = 3;/*显示距离右*/
                            break;
                        case 4:
                            message.what = 4;/*显示当前亮度*/
                            break;
                        case 5:
                            message.what = 5;/*显示烟雾浓度*/
                            break;
                        case 6:
                            message.what = 6;/*显示当前温度*/
                            break;
                        case 7:
                            message.what = 7;/*显示当前湿度*/
                            break;
                    }
                    Log.i("主界面Broadcast","收到");
                    myHandlerPower.sendMessage(message);
                }
               // myHandler.sendMessage(message);
            }
        }
    };
    private  class MyHandlerPower extends Handler {
        private final WeakReference<Fragment> mFragment;   /*弱引用避免造成内存泄漏*/
        public MyHandlerPower(Fragment fragment){
            mFragment = new WeakReference<Fragment>(fragment);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    udpRcvStrBuf.replace(0,mElectricQuantityTv.getText().length(),msg.obj.toString());
                    mElectricQuantityTv.setText(udpRcvStrBuf.toString());
                    break;
                case 2:
                    udpRcvStrBuf.replace(0,mPowerSupplyModeTv.getText().length(),msg.obj.toString());
                    mPowerSupplyModeTv.setText(udpRcvStrBuf.toString());
                    break;
                case 3:
                    udpRcvStrBuf.replace(0,mLongitudeTv.getText().length(),msg.obj.toString());
                    mLongitudeTv.setText(udpRcvStrBuf.toString());
                    break;
                case 4:
                    udpRcvStrBuf.replace(0,mLatitudeTv.getText().length(),msg.obj.toString());
                    mLatitudeTv.setText(udpRcvStrBuf.toString());
                    break;
                case 5:
                    udpRcvStrBuf.replace(0,mAltitudeTv.getText().length(),msg.obj.toString());
                    mAltitudeTv.setText(udpRcvStrBuf.toString());
                    break;
                case 6:
                    udpRcvStrBuf.replace(0,mSatelliteTimeTv.getText().length(),msg.obj.toString());
                    mSatelliteTimeTv.setText(udpRcvStrBuf.toString());
                    break;

            }
        }
    }
}
