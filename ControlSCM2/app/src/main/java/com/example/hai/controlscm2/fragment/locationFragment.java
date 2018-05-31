package com.example.hai.controlscm2.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hai.controlscm2.Protocol.Protocol;
import com.example.hai.controlscm2.Protocol.ReceiveData;
import com.example.hai.controlscm2.R;

import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by Hai on 2018/3/15.
 */

public class locationFragment extends BaseFragment {

    private final MyHandler myHandler = new MyHandler(this);
    private StringBuffer udpRcvStrBuf=new StringBuffer(),udpSendStrBuf=new StringBuffer();
    public static final String TAG = "locationFragment";

    Unbinder unbinder;
    @BindView(R.id.latitude_longitude_and_time_lv)
    TextView latitudeLongitudeAndTimeLv;
    @BindView(R.id.get_latitude_and_longitude_bt)
    Button getLatitudeAndLongitudeBt;
    @BindView(R.id.get_time_bt)
    Button getTimeBt;
    @BindView(R.id.get_longitude_bt)
    Button getLongitudeBt;
    @BindView(R.id.get_all_data)
    Button getAllData;

    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_location_class, container, false);
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
            if (intent.hasExtra("udpRcvMsg")) {
                Message message = new Message();
                ReceiveData a = (ReceiveData) intent.getSerializableExtra("udpRcvMsg"); /*得到通过了协议处理后的数据报*/
                int b = a.getR_DEVICE_CLASS();/* 类名*/
                int c = a.getR_DEVICE();/*设备名*/
                int d = a.getDEVICE_CONTROL_ID();/*控制号*/
                String e = a.getRECEIVE_DATA(); /* d 是要显示的数据*/
                byte[] by = e.getBytes();
                //message.obj = Integer.toString(e);
                if (b == 5) {
                        switch (d) {
                            case 0:
                                message.what = 0;
                               byte[] by1 = new byte[4];
                               byte[] by2 = new byte[4];
                               System.arraycopy(by,0,by1,0,4);
                               System.arraycopy(by,3,by2,0,4);
                                String s1,s2,s3= null;
                                try {
                                    s1 = new String(by1,"UTF8");
                                    s2 = new String(by2,"UTF8");
                                    s3 = "经度为："+s1+"\n"+"纬度为："+s2;
                                } catch (UnsupportedEncodingException e1) {
                                    e1.printStackTrace();
                                }
                                message.obj = s3;
                                break;
                            case 1:
                                message.what = 1;
                                int i1,i2,i3;
                                i1 = by[0];
                                i2 = by[1];
                                i3 = by[2];
                                message.obj = "卫星时间为："+Integer.toString(i1)+":"+Integer.toString(i2)+":"+Integer.toString(i3);
                                break;
                            case 2:
                                message.what = 3;
                                message.obj = e;
                                break;
                            case 3:
                                message.what = 3;

                                byte[] byt1 = new byte[4];
                                byte[] byt2 = new byte[4];
                                byte[] byt6 = new byte[2];
                                System.arraycopy(by,0,byt1,0,4);
                                System.arraycopy(by,3,byt2,0,4);
                                System.arraycopy(by,11,byt6,0,2);

                                String data1= null,data2 = null,data6=null;
                                try {
                                   data1 = new String(byt1,"UTF8");
                                   data2 = new String(byt2,"UTF8");
                                   data6 = new String(byt2,"UTF8");
                                } catch (UnsupportedEncodingException e1) {
                                    e1.printStackTrace();
                                }
                                int data3 = by[8];
                                int data4 = by[9];
                                int data5 = by[10];

                                String s = "经度为："+data1+"\n" +
                                        "纬度为："+data2+"\n"+"" +
                                        "卫星时间为："+Integer.toString(data3)+":"+Integer.toString(data4)+":"+Integer.toString(data5)+
                                        "海拔高度为："+data6;
                                message.obj = s;

                                break;
                        }
                        Log.i("主界面Broadcast", "收到");
                        myHandler.sendMessage(message);

                }

            }
        }
    };


    @Override
    public void initView() {

    }

    @Override
    public void setupListeners() {

    }

    @OnClick({R.id.get_latitude_and_longitude_bt, R.id.get_time_bt, R.id.get_longitude_bt, R.id.get_all_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.get_latitude_and_longitude_bt:
                SendMsg(new Protocol(5,0,0,0).sendProtocol());
                break;
            case R.id.get_time_bt:
                SendMsg(new Protocol(5,0,0,2).sendProtocol());
                break;
            case R.id.get_longitude_bt:
                SendMsg(new Protocol(5,0,0,1).sendProtocol());
                break;
            case R.id.get_all_data:
                SendMsg(new Protocol(5,0,0,3).sendProtocol());
                break;
        }
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
            udpRcvStrBuf.replace(0,latitudeLongitudeAndTimeLv.getText().length(),msg.obj.toString());
                    latitudeLongitudeAndTimeLv.setText(udpRcvStrBuf.toString());
                     }

        }

    }




