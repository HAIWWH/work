package com.example.hai.controlscm2.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;

import com.example.hai.controlscm2.Protocol.Protocol;
import com.example.hai.controlscm2.R;
import com.example.hai.controlscm2.UDP.UdpSend;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Hai on 2018/3/15.
 */

public class soundFragment extends BaseFragment {

    private final MyHandler myHandler = new MyHandler(this);
    public static final String TAG = "soundFragment";
    @BindView(R.id.tape_ib)
    ImageButton tapeIb;
    @BindView(R.id.tape_list_lv)
    ListView tapeListLv;
    @BindView(R.id.show_down_bt)
    ImageButton showDownBt;
    @BindView(R.id.speed_bt)
    ImageButton speedBt;
    @BindView(R.id.volume_br)
    SeekBar volumeBr;
    Unbinder unbinder;

    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_sound_class, container, false);

        unbinder = ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }


    /*注册broadcastReceiver接收器*/
    private void bindReceiver() {
        IntentFilter udpRcvIntentFilter = new IntentFilter("udpRcvMsg");
        this.getActivity().registerReceiver(broadcastReceiver, udpRcvIntentFilter);
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
                    }
                    Log.i("主界面Broadcast","收到");
                    myHandler.sendMessage(message);
                }

            }
        }
    };

    @OnClick({R.id.tape_ib, R.id.show_down_bt, R.id.speed_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tape_ib:
                SendMsg(new Protocol(2,0,0,0).sendProtocol());
                break;
            case R.id.show_down_bt:
                SendMsg(new Protocol(2,1,0,0).sendProtocol());
                break;
            case R.id.speed_bt:
                break;
        }
    }

    @Override
    public void initView() {

    }

    @Override
    public void setupListeners() {

    }


    //更新界面
    private class MyHandler extends Handler {
        private final WeakReference<BaseFragment> mFragment;   /*弱引用避免造成内存泄漏*/

        public MyHandler(BaseFragment fragment) {
            mFragment = new WeakReference<BaseFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    //udpRcvStrBuf.replace(0,mFront.getText().length(),msg.obj.toString());
                    break;
            }
        }
    }


}