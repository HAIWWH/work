package com.example.hai.controlscm2.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hai.controlscm2.Activity.SensorsBrilliancyActivity;
import com.example.hai.controlscm2.Activity.SensorsFogConcnActivity;
import com.example.hai.controlscm2.Activity.SensorsRangeFindingActivity;
import com.example.hai.controlscm2.Activity.SensorsTemperatureActivity;
import com.example.hai.controlscm2.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Hai on 2018/3/15.
 */

public class sensorsFragment extends Fragment {

    public static final String TAG = "sensorsFragment";

    @BindView(R.id.range_finding_bt)
    Button rangeFindingBt;
    @BindView(R.id.brilliancy_bt)
    Button brilliancyBt;
    @BindView(R.id.fog_concn_bt)
    Button fogConcnBt;
    @BindView(R.id.sensors_temperature_bt)
    Button sensorsTemperatureBt;
    Unbinder unbinder;
    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fagment_sensors_class, container, false);
        unbinder = ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.range_finding_bt, R.id.brilliancy_bt, R.id.fog_concn_bt, R.id.sensors_temperature_bt})
    public void onViewClicked(View view) {
                Intent intent = null;
        switch (view.getId()) {
            case R.id.range_finding_bt:
                intent = new Intent(getActivity(), SensorsRangeFindingActivity.class);
                startActivity(intent);
                break;
            case R.id.brilliancy_bt:
                intent = new Intent(getActivity(), SensorsBrilliancyActivity.class);
                startActivity(intent);
                break;
            case R.id.fog_concn_bt:
                intent = new Intent(getActivity(), SensorsFogConcnActivity.class);
                startActivity(intent);
                break;
            case R.id.sensors_temperature_bt:
                intent = new Intent(getActivity(), SensorsTemperatureActivity.class);
                startActivity(intent);
                break;
        }

    }


}
