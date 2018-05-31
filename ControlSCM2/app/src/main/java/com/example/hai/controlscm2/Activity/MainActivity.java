package com.example.hai.controlscm2.Activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hai.controlscm2.Protocol.Protocol;
import com.example.hai.controlscm2.Protocol.ReceiveData;
import com.example.hai.controlscm2.R;


import com.example.hai.controlscm2.fragment.BaseFragment;
import com.example.hai.controlscm2.fragment.iconicFragment;

import com.example.hai.controlscm2.fragment.locationFragment;
import com.example.hai.controlscm2.fragment.actionFragment;

import com.example.hai.controlscm2.fragment.soundFragment;
import com.example.hai.controlscm2.fragment.sensorsFragment;


import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    public static Context context;

    private BaseFragment mShowFragment;
    private FragmentManager fm;
    private ActionBar actionBar;
    private  NavigationView navView;

    private final MyHandler myHandler = new MyHandler(this);
    private StringBuffer udpRcvStrBuf=new StringBuffer(),udpSendStrBuf=new StringBuffer();


    Button  electricityBt,getPowerSupplyBt,setPowerSupplyBt;
    Spinner  powerSupplySp;
    TextView electricityAndPowersupplyTv;
    MyBtnClick myBtnClick = new MyBtnClick();

    /*ButterKnife插件生成*/
    @BindView(R.id.home_tab_action)
    RadioButton homeTabMain;
    @BindView(R.id.home_tab_sensors)
    RadioButton homeTabSearch;
    @BindView(R.id.home_tab_sound)
    RadioButton homeTabCategory;
    @BindView(R.id.home_tab_location)
    RadioButton homeTabCart;
    @BindView(R.id.home_tab_iconic)
    RadioButton homeTabPersonal;

    @BindView(android.R.id.tabcontent)
    FrameLayout tabcontent;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;


    @OnClick({R.id.home_tab_action, R.id.home_tab_sensors, R.id.home_tab_sound, R.id.home_tab_location, R.id.home_tab_iconic})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_tab_action:
                switchPages(actionFragment.TAG, actionFragment.class);
                break;
            case R.id.home_tab_sensors:
                switchPages(sensorsFragment.TAG, sensorsFragment.class);
                break;
            case R.id.home_tab_sound:
                switchPages(soundFragment.TAG, soundFragment.class);
                break;
            case R.id.home_tab_location:
                switchPages(locationFragment.TAG, locationFragment.class);
                break;
            case R.id.home_tab_iconic:
                switchPages(iconicFragment.TAG, iconicFragment.class);
                break;
        }
    }

    /*侧边框的实现点击菜单可以弹出侧边框*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setupListeners();
        ButterKnife.bind(this);
    }


    /*初始化函数*/
    public void initView() {
        //实现对fragment.TAG的初始化
        fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mShowFragment = new actionFragment();
        transaction.add(android.R.id.tabcontent, mShowFragment, actionFragment.TAG);
        transaction.commit();

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = (getSupportActionBar());
        navView = (NavigationView)findViewById(R.id.nav_view);
        navView.setCheckedItem(R.id.nav_location);


        View drawview = navView.inflateHeaderView(R.layout.nav_header);
        //Button user_pic = (ImageView) drawview.findViewById(R.id.imageViewIcon);
        electricityBt = (Button)drawview.findViewById(R.id.electricity_bt);
        getPowerSupplyBt = (Button)drawview.findViewById(R.id.get_power_supply_bt);
        setPowerSupplyBt = (Button)drawview.findViewById(R.id.set_power_supply_bt);
        powerSupplySp = (Spinner)drawview.findViewById(R.id.power_supply_sp);
        electricityAndPowersupplyTv = (TextView)drawview.findViewById(R.id.electricity_and_powersupply_tv);

    }

    /*事件处理*/
    public void setupListeners() {

        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.main_bottom_tab_personal_normal);
        }
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                return true;
            }
        });
        electricityBt.setOnClickListener(myBtnClick);
        getPowerSupplyBt.setOnClickListener(myBtnClick);
        setPowerSupplyBt.setOnClickListener(myBtnClick);

    }
    class MyBtnClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.electricity_bt:
                    SendMsg(new Protocol(4,0,0,0).sendProtocol());
                    break;
                case R.id.get_power_supply_bt:
                    SendMsg(new Protocol(4,0,0,1).sendProtocol());
                    break;
                case R.id.set_power_supply_bt:
                    break;

            }
        }
    }

    /*不同fragment之间的切换（运用java的反射机制）*/
    private void switchPages(String tag, Class<? extends BaseFragment> cls) {
        FragmentTransaction transaction = fm.beginTransaction();
        //隐藏显示页面
        transaction.hide(mShowFragment);
        //根据TAG去FragmentManager寻找碎片
        mShowFragment = (BaseFragment) fm.findFragmentByTag(tag);
        if (mShowFragment != null) {
            transaction.show(mShowFragment);
        } else {
            try {
                //使用反射实例化一个对象
                mShowFragment = cls.getConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            transaction.add(android.R.id.tabcontent, mShowFragment, tag);
        }
        transaction.commit();
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

                if(b == 4) {
                        switch (d) {
                            case 0:
                                message.what = 1;
                                message.obj= "电量为："+Integer.toString(e)+"%";
                                break;
                            case 1:
                                message.what = 1;
                                if(e == 0){
                                    message.obj= "供电方式为"+"\n"+"电池供电";
                                }else if(e == 1){
                                    message.obj= "供电方式为"+"\n"+"无线供电";
                                }
                                break;
                        }
                        Log.i("主界面Broadcast","收到");
                        myHandler.sendMessage(message);
                    }



            }
        }
    };
    /*注册broadcastReceiver接收器*/
    private void bindReceiver(){
        IntentFilter udpRcvIntentFilter = new IntentFilter("udpRcvMsg");
        this.registerReceiver(broadcastReceiver,udpRcvIntentFilter);
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

                    udpRcvStrBuf.replace(0,electricityAndPowersupplyTv.getText().length(),msg.obj.toString());
                    electricityAndPowersupplyTv.setText(udpRcvStrBuf.toString());
        }
    }


}