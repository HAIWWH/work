package app.cddic.com.smarter.fragment.device;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.activity.base.DeviceActivity;
import app.cddic.com.smarter.adapter.SearchDeviceAdapter;
import app.cddic.com.smarter.entity.DeviceMSG;
import app.cddic.com.smarter.entity.RetMSG;
import app.cddic.com.smarter.fragment.base.BaseFragment;
import app.cddic.com.smarter.widget.TopView;

/**
 * Created by asus on 2017/11/30.
 */

public class SearchDeviceFragment extends BaseFragment {
    private TopView mTopView;
    private ListView mListView;
    private Button mChooseAssociate;
    private Button mHandAssociate;
    public static Set<RetMSG> mDevice = new HashSet< RetMSG>();
    @Override
    protected void initViews() {
        mTopView = (TopView)mView.findViewById(R.id.searchDevice_topView);
        mListView = (ListView)mView.findViewById(R.id.serchDevice_listView);
        mChooseAssociate = (Button)mView.findViewById(R.id.chooseAssociate_btn);
        mHandAssociate = (Button)mView.findViewById(R.id.handAssociate_btn);
        mTopView.setText("返回","设备",null);
        DeviceMSG deviceMSG = new DeviceMSG("厨房",1);
        mMSGUtil.insertMSG(deviceMSG);
        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    if(mDevice.size()!=0){
                        SearchDeviceAdapter adapter = new SearchDeviceAdapter(getActivity(),mDevice);
                        mListView.setAdapter(adapter);
                    }
                }
                super.handleMessage(msg);
            }

            ;
        };
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // 需要做的事:发送消息
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        };
        timer.schedule(task,1000,1000);
    }

    @Override
    protected void setupAdapters() {
        SearchDeviceAdapter adapter = new SearchDeviceAdapter(getActivity(),mDevice);
        mListView.setAdapter(adapter);
    }

    @Override
    protected void setupListeners() {
        mTopView.setupListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        },null);
        mHandAssociate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = DeviceActivity.newInstance(getActivity(), DeviceActivity.Type.ADD_DEVICE, null);
                startActivity(intent);
            }
        });
        mChooseAssociate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = DeviceActivity.newInstance(getActivity(), DeviceActivity.Type.ADD_DEVICE, null);
                    startActivity(intent);
            }
        });
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_search_device;
    }
}
