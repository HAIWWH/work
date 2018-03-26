package app.cddic.com.smarter.fragment.device;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.activity.base.MainActivity;
import app.cddic.com.smarter.fragment.base.BaseFragment;
import app.cddic.com.smarter.widget.TopView;

/**
 * Created by VileWind on 2017/6/13 0013.
 */

public class UpdatePluginFragment extends BaseFragment {
    private TopView mTopView;
    private TextView mPluginNameTextView;
    private TextView mPluginTextView;
    private ImageButton mDelPluginIBtn;
    private Button mUpdateNowBtn;
    private TextView mUpdateMessageTextView;
    @Override
    protected void initViews() {

        mTopView=(TopView)mView.findViewById(R.id.updatePlugin_topView);
        mTopView.setText("返回","插件更新",null);
        mPluginNameTextView=(TextView)mView.findViewById(R.id.updatePlugin_deviceName_textView);
        mPluginTextView=(TextView)mView.findViewById(R.id.updatePlugin_plugin_textView);
        mDelPluginIBtn=(ImageButton)mView.findViewById(R.id.updatePlugin_delPlugin_btn);
        mUpdateNowBtn=(Button)mView.findViewById(R.id.updatePlugin_updateNow_btn);
        mUpdateMessageTextView=(TextView)mView.findViewById(R.id.updatePlugin_updateMessage_textView);
    }

    @Override
    protected void setupAdapters() {

    }

    @Override
    protected void setupListeners() {
        mTopView.setupListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), MainActivity.class);
                startActivity(i);

            }
        },null);


        //设备名称
        mPluginNameTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //当前插件
        mPluginTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //删除当前插件
        mDelPluginIBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //插件立即更新
        mUpdateNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //主要更新内容
        mUpdateMessageTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_update_plugin;
    }
}
