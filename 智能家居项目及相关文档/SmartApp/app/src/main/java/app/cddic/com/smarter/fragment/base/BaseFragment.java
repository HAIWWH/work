package app.cddic.com.smarter.fragment.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.cddic.com.smarter.db.MSGUtil;
import app.cddic.com.smarter.utils.CommonViewHolder;

/**
 * Created by Pantiy on 2017/3/12.
 * Copyright © 2016 All rights Reserved by Pantiy
 */

public abstract class BaseFragment extends Fragment {

    protected Activity mActivity;
    protected View mView;
    protected MSGUtil mMSGUtil;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMSGUtil = MSGUtil.touch(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(setLayoutRes(), container, false);
        initViews();
        setupAdapters();
        setupListeners();
        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }


    protected abstract void initViews();
    protected <T extends View> T findView(int id) {
        return CommonViewHolder.get(mView, id);
    }

    protected abstract void setupAdapters();

    protected abstract void setupListeners();

    protected abstract int setLayoutRes();
}
