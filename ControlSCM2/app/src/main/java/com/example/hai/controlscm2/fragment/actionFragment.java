package com.example.hai.controlscm2.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hai.controlscm2.Protocol.Protocol;
import com.example.hai.controlscm2.Protocol.ReceiveData;
import com.example.hai.controlscm2.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Hai on 2018/3/14.
 */

public class actionFragment extends BaseFragment {

    public static final String TAG = "actionFragment";
    @BindView(R.id.front_bt)
    Button frontBt;
    @BindView(R.id.left_bt)
    Button leftBt;
    @BindView(R.id.right_bt)
    Button rightBt;
    @BindView(R.id.behind_bt)
    Button behind;
    @BindView(R.id.relay_sw)
    SwitchCompat relaySw;
    Unbinder unbinder;

    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_action_class, container, false);


        unbinder = ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.front_bt, R.id.left_bt, R.id.right_bt, R.id.behind_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.front_bt:
                SendMsg(new Protocol(1,0,0,0).sendProtocol());
                break;
            case R.id.left_bt:
                SendMsg(new Protocol(1,0,0,1).sendProtocol());
                break;
            case R.id.right_bt:
                SendMsg(new Protocol(1,0,0,2).sendProtocol());
                break;
            case R.id.behind_bt:
                SendMsg(new Protocol(1,0,0,3).sendProtocol());
                break;

        }
    }


    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra("udpRcvMsg"))  {
                ReceiveData a = (ReceiveData) intent.getSerializableExtra("udpRcvMsg"); /*得到通过了协议处理后的数据报*/
                int b =a.getR_DEVICE_CLASS();/* 类名*/
                int c =a.getR_DEVICE();/*设备名*/
                int d = a.getDEVICE_CONTROL_ID();/*控制号*/
                int e = Integer.parseInt(a.getRECEIVE_DATA()); /* d 是要显示的数据*/
                if(b == 1) {
                    if(c == 0){
                        switch (d) {
                            case 0:
                                if(e==0){
                                    Popup("成功");
                                }
                                else Popup("失败");
                                break;
                            case 1:
                                if(e==0){
                                    Popup("成功");
                                }
                                else Popup("失败");
                                break;
                            case 2:
                                if(e==0){
                                    Popup("成功");
                                }
                                else Popup("失败");
                                break;
                            case 3:
                                if(e==0){
                                    Popup("成功");
                                }
                                else Popup("失败");
                                break;
                        }

                        Log.i("主界面Broadcast","收到");
                    }
                    else if(c == 1){
                        switch (d) {
                            case 0:
                                if (e == 0) {
                                    Popup("成功");
                                } else Popup("失败");
                                break;
                            case 1:
                                if (e == 0) {
                                    Popup("成功");
                                } else Popup("失败");
                                break;
                        }
                    }

                }

            }
        }
    };
    /*注册broadcastReceiver接收器*/
    private void bindReceiver(){
        IntentFilter udpRcvIntentFilter = new IntentFilter("udpRcvMsg");
        this.getActivity().registerReceiver(broadcastReceiver,udpRcvIntentFilter);
    }

    @Override
    public void initView() {

    }

    @Override
    public void setupListeners() {

    }


}
