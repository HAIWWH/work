package app.cddic.com.smarter.activity.base;

import android.os.Bundle;

import app.cddic.com.smarter.R;

public class DeviceTestActivity extends BaseActivity {

    @Override
    public void onHandleMsg(int MsgType) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_test);
    }
}
