package com.example.hai.controlscm2.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hai.controlscm2.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Hai on 2018/3/14.
 */

public class actionFragment extends Fragment {

    public static final String TAG = "actionFragment";
    @BindView(R.id.front_bt)
    Button frontBt;
    @BindView(R.id.left_bt)
    Button leftBt;
    @BindView(R.id.right_bt)
    Button rightBt;
    @BindView(R.id.behind)
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

    @OnClick({R.id.front_bt, R.id.left_bt, R.id.right_bt, R.id.behind, R.id.relay_sw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.front_bt:
                break;
            case R.id.left_bt:
                break;
            case R.id.right_bt:
                break;
            case R.id.behind:
                break;
            case R.id.relay_sw:
                break;
        }
    }



}
