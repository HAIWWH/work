package app.cddic.com.smarter.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.cddic.com.smarter.R;


/**
 * SmartApp
 * app.cddic.com.smarter.widget
 * Created by Pantiy on 2017/5/4.
 * Copyright © 2017 All rights Reserved by Pantiy
 */

public class TopView extends LinearLayout {

    private static final String TAG = "TopView";

    private static final String LEFT = "左边";
    private static final String TITLE = "标题";
    private static final String RIGHT = "右边";

    private static final int LEFT_BTN = 0;
    private static final int TITLE_TV = 1;
    private static final int PHOTO_IV = 2;
    private static final int RIGHT_BTN = 3;
    private static final int RIGHT_IV = 4;
    private static int PhoneId = R.drawable.device;

    private Context mContext;

    private int mLeftBtnWidth;
    private int mRightIvHeight = 80;

    private TextView mTitleTv;
    private Button mLeftBtn;
    private Button mRightBtn;
    private ImageView mRightIv;
    private ImageView mPhotoIv;

    public TopView(Context context) {
        super(context);
        mContext = context;
        initViews();
    }

    public TopView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initViews();
    }

    public TopView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initViews();
    }


    private void initViews() {

        Log.i(TAG, "initViews()" );

        int horizontalPadding = 15;

        setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(horizontalPadding, 0, horizontalPadding, 0);

        LayoutParams leftBtnLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        LayoutParams titleTvLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
        ViewGroup.LayoutParams photoLaLayoutParams = new LayoutParams(60,60);
        LayoutParams ivLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                mRightIvHeight);

        mLeftBtn = new Button(mContext);
        mLeftBtn.setId(R.id.leftBtnInTopView);
        mLeftBtn.setBackgroundColor(Color.TRANSPARENT);
        mLeftBtn.setText(LEFT);
        mLeftBtn.setTextColor(Color.WHITE);
        mLeftBtn.setTextSize(16f);
        mLeftBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_left_arrow, 0, 0, 0);
        mLeftBtn.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
        addView(mLeftBtn, LEFT_BTN, leftBtnLayoutParams);

        mTitleTv = new TextView(mContext);
        mTitleTv.setId(R.id.titleTvInTopView);
        mTitleTv.setGravity(Gravity.CENTER);
        mTitleTv.setBackgroundColor(Color.TRANSPARENT);
        mTitleTv.setText(TITLE);
        mTitleTv.setTextSize(18f);
        mTitleTv.setTextColor(Color.WHITE);
        addView(mTitleTv, TITLE_TV, titleTvLayoutParams);

        mPhotoIv = new ImageView(mContext);
        mPhotoIv.setImageResource(PhoneId);
        addView(mPhotoIv,PHOTO_IV,photoLaLayoutParams);


        mRightBtn = new Button(mContext);
        mRightBtn.setId(R.id.rightBtnInTopView);
        mRightBtn.setBackgroundColor(Color.TRANSPARENT);
        mRightBtn.setText(RIGHT);
        mRightBtn.setTextSize(16f);
        mRightBtn.setTextColor(Color.WHITE);
        mRightBtn.setPadding(0, 0 , 15, 0);
        mRightBtn.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
        addView(mRightBtn, RIGHT_BTN);

        mRightIv = new ImageView(mContext);
        mRightIv.setId(R.id.rightIvInTopView);
        mRightIv.setImageResource(R.drawable.ic_contact_light);
        mRightIv.setVisibility(GONE);
        addView(mRightIv, RIGHT_IV, ivLayoutParams);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChild(mLeftBtn, widthMeasureSpec, heightMeasureSpec);
        mLeftBtnWidth = mLeftBtn.getMeasuredWidth();
        Log.i(TAG, "leftBtnWidth = " + mLeftBtnWidth);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(TAG, "onSizeChanged()");

        LayoutParams rightBtnLayoutParams = new LayoutParams(mLeftBtnWidth,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mRightBtn.setLayoutParams(rightBtnLayoutParams);

        LayoutParams rightIvLayoutParams = new LayoutParams(mLeftBtnWidth, mRightIvHeight);
        mRightIv.setLayoutParams(rightIvLayoutParams);
        mRightIv.setPadding(mLeftBtnWidth - mRightIvHeight - 10 , 0, 10, 0);
    }

    public void setText(String left, String title, String right) {
        mLeftBtn.setText(left);
        mTitleTv.setText(title);
        mRightBtn.setText(right);
    }

    public void setupListeners(OnClickListener left, OnClickListener right) {
        mLeftBtn.setOnClickListener(left);
        mRightBtn.setOnClickListener(right);
        mRightIv.setOnClickListener(right);
    }

    public void showRightAsImageView() {
        mRightBtn.setVisibility(GONE);
        mRightIv.setVisibility(VISIBLE);
    }
}
