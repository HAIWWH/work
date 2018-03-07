package com.example.hai.controlscm1.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.hai.controlscm1.R;
import com.example.hai.controlscm1.UDP.UDPClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.hai.controlscm1.activity.MainActivity.udpClient;


/**
 * Created by Hai on 2017/11/18.
 */

public class CarFragment extends Fragment {
    private View mView;
    private EditText mIpEt;
    private EditText mPortEt;
    private Button mConnectBt;
    private Button mDisconnectBt;
    private Button mForwardBt;
    private Button mBackwardBt;
    private Button mLeftBt;
    private Button mRightBt;
    private Button mStopBt;

    private UDPClient clientCar = udpClient;


    private MyBtnClick myBtnClick = new MyBtnClick();
    private SharedPreferences mSpf;
    private SharedPreferences.Editor mSpfE;

    private static boolean connect = true;

    private StringBuffer udpRcvStrBuf=new StringBuffer(),udpSendStrBuf=new StringBuffer();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_car, container, false);
        initView();
        setupListeners();
        iniWidget();

        return mView;
    }

    /*初始化*/
    protected void initView() {
        mIpEt = (EditText)mView.findViewById(R.id.ip_et);
        mPortEt = (EditText)mView.findViewById(R.id.port_et);
        mConnectBt = (Button)mView.findViewById(R.id.connect_btn);
        mDisconnectBt = (Button) mView.findViewById(R.id.disconnect_btn);

        mForwardBt = (Button) mView.findViewById(R.id.forward_btn);
        mBackwardBt = (Button) mView.findViewById(R.id.backward_btn);
        mLeftBt = (Button) mView.findViewById(R.id.left_btn);
        mRightBt = (Button) mView.findViewById(R.id.right_btn);
        mStopBt = (Button) mView.findViewById(R.id.stop_btn);

        mSpf = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mIpEt.setText(mSpf.getString("ip",""));
        mPortEt.setText(mSpf.getString("port",""));
    }

    /*事件监听*/
    protected void setupListeners() {
        mConnectBt.setOnClickListener(myBtnClick);
        mDisconnectBt.setOnClickListener(myBtnClick);
        mForwardBt.setOnClickListener(myBtnClick);
        mBackwardBt.setOnClickListener(myBtnClick);
        mLeftBt.setOnClickListener(myBtnClick);
        mRightBt.setOnClickListener(myBtnClick);
        mStopBt.setOnClickListener(myBtnClick);

    }


    /*    //接收数据用的更新主界面
    private void bindReceiver(){//注册broadcastReceiver接收器
        IntentFilter udpRcvIntentFilter = new IntentFilter("udpRcvMsg");
        getActivity().registerReceiver(broadcastReceiver,udpRcvIntentFilter);
    }
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra("udpRcvMsg"))  {
                Message message = new Message();
                message.obj = intent.getStringExtra("udpRcvMsg");
                message.what = 1;
                Log.i("主界面Broadcast","收到");
                //myHandler.sendMessage(message);
            }
        }
    };


    private  class MyHandler extends Handler {
        private final WeakReference<CarFragment> mActivity;   //弱引用避免造成内存泄漏
        public MyHandler(CarFragment carFragment){
            mActivity = new WeakReference<CarFragment>(carFragment);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        }
    }
*/

    private class MyBtnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.connect_btn:
                    if (!mIpEt.getText().toString().isEmpty() && !mPortEt.getText().toString().isEmpty()) {
                        int mPort = Integer.parseInt(mPortEt.getText().toString());
                        ExecutorService exec = Executors.newCachedThreadPool();
                        udpClient = new UDPClient(mPort,mIpEt.getText().toString());
                        exec.execute(udpClient);

                    } else {
                        Log.i("DebugInfo", "请输入Ip或者Port");
                    }
                    mSpfE = mSpf.edit();
                    mSpfE.putString("ip",mIpEt.getText().toString());
                    mSpfE.putString("port",mPortEt.getText().toString());
                    mSpfE.apply();
                    mConnectBt.setEnabled(false);
                    mDisconnectBt .setEnabled(true);
                    connect = false;
                    break;
                case R.id.disconnect_btn:
                    udpClient.setUdpLife(false);/*关闭线程生命因子*/
                    mConnectBt.setEnabled(true);
                    mDisconnectBt .setEnabled(false);
                    connect = true;
                    break;
//                case R.id.forward_btn:
//                    sendCar("前");
//                    break;
//                case R.id.backward_btn:
//                    sendCar("后");
//                    break;
//                case R.id.left_btn:
//                    sendCar("左");
//                    break;
//                case R.id.right_btn:
//                    sendCar("右");
//                    break;
//                case R.id.stop_btn:
//                    sendCar("停止");
//                    break;

            }
        }
    }



    public void sendCar(final  byte [] masg){  //发送事件
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Message message = new Message();
                if (masg.length != 0){
                    Log.i("udpClient",masg.toString());
                    clientCar.send(masg);
                }
            }
        }).start();
    }

    private void iniWidget(){/* 初始化控件状态*/
        mDisconnectBt .setEnabled(false);/*发送按钮不可以使用*/
        if(connect){
            mConnectBt.setEnabled(true);
            mDisconnectBt .setEnabled(false);
        }else {
            mConnectBt.setEnabled(false);
            mDisconnectBt .setEnabled(true);
        }
    }

}


