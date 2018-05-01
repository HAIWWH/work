package com.example.hai.controlscm2.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hai.controlscm2.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SensorsTemperatureActivity extends Activity {
    @BindView(R.id.temperature_and_humidity_tv)
    TextView temperatureAndHumidityTv;
    @BindView(R.id.get_temperature_bt)
    Button getTemperatureBt;
    @BindView(R.id.get_humidity_bt)
    Button getHumidityBt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors_temperature);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.temperature_and_humidity_tv, R.id.get_temperature_bt, R.id.get_humidity_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.temperature_and_humidity_tv:
                break;
            case R.id.get_temperature_bt:
                break;
            case R.id.get_humidity_bt:
                break;
        }
    }
}
