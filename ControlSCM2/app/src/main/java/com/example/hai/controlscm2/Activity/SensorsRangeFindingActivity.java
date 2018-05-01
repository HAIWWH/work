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

public class SensorsRangeFindingActivity extends Activity {
    @BindView(R.id.front_distance_tv)
    TextView frontDistanceTv;
    @BindView(R.id.left_distance)
    TextView leftDistance;
    @BindView(R.id.right_distance_tv)
    TextView rightDistanceTv;
    @BindView(R.id.ranging_bt)
    Button rangingBt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors_range_finding);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.front_distance_tv, R.id.left_distance, R.id.right_distance_tv, R.id.ranging_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.front_distance_tv:
                break;
            case R.id.left_distance:
                break;
            case R.id.right_distance_tv:
                break;
            case R.id.ranging_bt:
                break;
        }
    }
}
