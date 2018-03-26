package app.cddic.com.smarter.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.fragment.base.BaseFragment;
import app.cddic.com.smarter.widget.TopView;

/**
 * Created by asus on 2017/8/7.
 */

public class SystemNewsDetailFragment extends BaseFragment {
    private TopView mTopView;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initViews() {
        mTopView = (TopView)mView.findViewById(R.id.systemNewsDetail_topView);
        mTopView.setText("消息","消息详情","通知");

    }

    @Override
    protected void setupAdapters() {

    }

    @Override
    protected void setupListeners() {
        mTopView.setupListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        },null);

    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_system_news_detail;
    }
}

