package app.cddic.com.smarter.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import app.cddic.com.smarter.R;

/**
 * 项目名：  SmartApp
 * 包名：    app.cddic.com.smarter.ui
 * 文件名：  GuideActivity
 * 创建者：  zhl
 * 创建时间： 2017/3/5 11:43
 * 描述：引导页
 */

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mViewPager;
    private View view1;
    private View view2;
    private View view3;
    List<View> mView;
    private ImageView guide_skip;
    private ImageView point1;
    private ImageView point2;
    private ImageView point3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initView();
    }

    //初始化view
    private void initView() {
        mView = new ArrayList<>();
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        guide_skip = (ImageView) findViewById(R.id.guide_skip);

        guide_skip.setOnClickListener(this);

        point1 = (ImageView) findViewById(R.id.guide_point1);
        point2 = (ImageView) findViewById(R.id.guide_point2);
        point3 = (ImageView) findViewById(R.id.guide_point3);
        //设置默认图片
        setPoint(true,false,false);

        view1 = View.inflate(this,R.layout.guide_item_1,null);
        view2 = View.inflate(this,R.layout.guide_item_2,null);
        view3 = View.inflate(this,R.layout.guide_item_3,null);

        //添加到mView中
        mView.add(view1);
        mView.add(view2);
        mView.add(view3);

        mViewPager.setAdapter(new GuideAdapter());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        guide_skip.setVisibility(View.VISIBLE);
                        setPoint(true,false,false);
                        break;
                    case 1:
                        guide_skip.setVisibility(View.VISIBLE);
                        setPoint(false,true,false);
                        break;
                    case 2:
                        guide_skip.setVisibility(View.GONE);
                        setPoint(false,false,true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //设置选项点的显示
    private void setPoint(Boolean isChecked1,Boolean isChecked2,Boolean isChecked3) {
        if(isChecked1){
            point1.setImageResource(R.drawable.point_on);
        }else{
            point1.setImageResource(R.drawable.point_off);
        }
        if(isChecked2){
            point2.setImageResource(R.drawable.point_on);
        }else{
            point2.setImageResource(R.drawable.point_off);
        }
        if(isChecked3){
            point3.setImageResource(R.drawable.point_on);
        }else{
            point3.setImageResource(R.drawable.point_off);
        }
    }
    //按钮监听
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.guide_skip:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                default:
                    break;
        }
    }

    private class GuideAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mView.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //添加页面
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager)container).addView(mView.get(position));
            return mView.get(position);
        }

        //删除页面
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager)container).removeView(mView.get(position));
        }
    }

    //禁止返回键
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
