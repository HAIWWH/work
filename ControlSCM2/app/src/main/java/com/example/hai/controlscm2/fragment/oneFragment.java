package com.example.hai.controlscm2.fragment;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hai.controlscm2.R;

/**
 * Created by Hai on 2018/3/14.
 */

public class oneFragment extends Fragment {

    public static final String TAG = "oneFragment";

    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_one, container, false);


        return mView;
    }

}
