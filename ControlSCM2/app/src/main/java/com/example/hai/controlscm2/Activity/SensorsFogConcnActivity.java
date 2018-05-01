package com.example.hai.controlscm2.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.hai.controlscm2.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SensorsFogConcnActivity extends Activity {
    @BindView(R.id.smoke_concentration_tv)
    TextView smokeConcentrationTv;
    @BindView(R.id.get_smoke_concentration_bt)
    Button getSmokeConcentrationBt;
    @BindView(R.id.set_smoke_concentration_sb)
    SeekBar setSmokeConcentrationSb;
    @BindView(R.id.set_smoke_concentration_bt)
    Button setSmokeConcentrationBt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors_fog_concn);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.smoke_concentration_tv, R.id.get_smoke_concentration_bt, R.id.set_smoke_concentration_sb, R.id.set_smoke_concentration_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.smoke_concentration_tv:
                break;
            case R.id.get_smoke_concentration_bt:
                break;
            case R.id.set_smoke_concentration_sb:
                break;
            case R.id.set_smoke_concentration_bt:
                break;
        }
    }
}
