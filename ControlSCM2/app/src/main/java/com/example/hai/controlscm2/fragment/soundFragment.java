package com.example.hai.controlscm2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;

import com.example.hai.controlscm2.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Hai on 2018/3/15.
 */

public class soundFragment extends Fragment {

    public static final String TAG = "soundFragment";

    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_sound_class, container, false);

        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


}