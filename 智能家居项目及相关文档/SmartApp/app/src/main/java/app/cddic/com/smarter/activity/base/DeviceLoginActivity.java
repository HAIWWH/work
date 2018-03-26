package app.cddic.com.smarter.activity.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.entity.RetMSG;

public class DeviceLoginActivity extends BaseActivity {
    private Button login;
    private Button associate;
    private RetMSG mRetMSG;
    private static String  ARG_DEVICELOGIN;

    @Override
    public void onHandleMsg(int MsgType) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_login);
        initView();
        setupListeners();
    }

    public void initView() {
        login = (Button)findViewById(R.id.btn_loginApp);
        associate = (Button)findViewById(R.id.btn_associate);
        mRetMSG = (RetMSG) getIntent().getSerializableExtra(ARG_DEVICELOGIN);
        Log.i("test",mRetMSG.getFromIP());
    }
    public void setupListeners() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeviceLoginActivity.this,DeviceTestActivity.class);
                startActivity(intent);
            }
        });
        associate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = DeviceActivity.newInstance(DeviceLoginActivity.this, DeviceActivity.Type.ADD_DEVICE, null);
                startActivity(intent);
            }
        });
    }
    public static Intent newInstance(Context context, RetMSG s){
        Intent intent = new Intent(context,DeviceLoginActivity.class);
        intent.putExtra( ARG_DEVICELOGIN,s);
        return intent;
    }
}
