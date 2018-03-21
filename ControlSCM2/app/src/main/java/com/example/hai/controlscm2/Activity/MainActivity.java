package com.example.hai.controlscm2.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.example.hai.controlscm2.R;
import com.example.hai.controlscm2.UDP.UDPClient;
import com.example.hai.controlscm2.fragment.fiveFragment;
import com.example.hai.controlscm2.fragment.fourFragment;
import com.example.hai.controlscm2.fragment.oneFragment;
import com.example.hai.controlscm2.fragment.threeFragment;
import com.example.hai.controlscm2.fragment.twoFragment;

import java.lang.reflect.InvocationTargetException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static Context context;

    private Fragment mShowFragment;
    private FragmentManager fm;
    private ActionBar actionBar;

    private  NavigationView navView;


    /*ButterKnife插件生成*/
    @BindView(R.id.home_tab_main)
    RadioButton homeTabMain;
    @BindView(R.id.home_tab_search)
    RadioButton homeTabSearch;
    @BindView(R.id.home_tab_category)
    RadioButton homeTabCategory;
    @BindView(R.id.home_tab_cart)
    RadioButton homeTabCart;
    @BindView(R.id.home_tab_personal)
    RadioButton homeTabPersonal;

    @BindView(android.R.id.tabcontent)
    FrameLayout tabcontent;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;


    @OnClick({R.id.home_tab_main, R.id.home_tab_search, R.id.home_tab_category, R.id.home_tab_cart, R.id.home_tab_personal})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_tab_main:
                switchPages(oneFragment.TAG, oneFragment.class);
                break;
            case R.id.home_tab_search:
                switchPages(twoFragment.TAG, twoFragment.class);
                break;
            case R.id.home_tab_category:
                switchPages(threeFragment.TAG, threeFragment.class);
                break;
            case R.id.home_tab_cart:
                switchPages(fourFragment.TAG, fourFragment.class);
                break;
            case R.id.home_tab_personal:
                switchPages(fiveFragment.TAG, fiveFragment.class);
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
    protected void initView() {
        //实现对fragment.TAG的初始化
        fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mShowFragment = new oneFragment();
        transaction.add(android.R.id.tabcontent, mShowFragment, oneFragment.TAG);
        transaction.commit();

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = (getSupportActionBar());

        navView = (NavigationView)findViewById(R.id.nav_view);
        navView.setCheckedItem(R.id.nav_call);
    }

    /*事件处理*/
    protected void setupListeners() {

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
    }


    /*不同fragment之间的切换（运用java的反射机制）*/
    private void switchPages(String tag, Class<? extends Fragment> cls) {
        FragmentTransaction transaction = fm.beginTransaction();
        //隐藏显示页面
        transaction.hide(mShowFragment);
        //根据TAG去FragmentManager寻找碎片
        mShowFragment = fm.findFragmentByTag(tag);
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

}