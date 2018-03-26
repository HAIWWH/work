package app.cddic.com.smarter.fragment.device;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.activity.base.DeviceDetailActivity;
import app.cddic.com.smarter.fragment.base.BaseFragment;
import app.cddic.com.smarter.widget.TopView;

/**
 * Created by Hai on 2017/5/10.
 */

public class DeviceDetailsFragment extends BaseFragment {

    private GridView mGridview;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter mSimpleAdapter;
    // 图片封装为一个数组
    private int[] icon = {R.drawable.drawing_board, R.drawable.drawing_board, R.drawable.drawing_board,
            R.drawable.drawing_board, R.drawable.drawing_board, R.drawable.drawing_board};
    private String[] iconName = { "设置", "分享", "管理", "控制", "消息", "视频"};
    private String mAuthority;
    private TextView mDeviceAuthority;
    private TopView mTopView;
    private Button mLoginBtn;


    @Override
    protected void initViews() {
        mTopView = findView(R.id.deviceDetails_topView);
        mTopView.setText("设备","设备详情","厨房设备");
        mLoginBtn = findView(R.id.login_button);
        mDeviceAuthority = (TextView)mView.findViewById(R.id.deviceDetailAuthority_textView);
        mAuthority = mDeviceAuthority.getText().toString();

    }

    @Override
    protected void setupAdapters() {
        mGridview = findView(R.id.deviceDetails_gridView);
        //新建List
        data_list = new ArrayList<>();
        //获取数据
        getData();
        //新建适配器
        String [] from ={"imageView","textView"};
        int [] to = {R.id.imageView, R.id.textView};
        mSimpleAdapter = new SimpleAdapter(mActivity, data_list, R.layout.activity_device_details_item, from, to);
        //配置适配器
        mGridview.setAdapter(mSimpleAdapter);
        mGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==3){
                    Toast.makeText(getContext(),"没有控制插件",Toast.LENGTH_SHORT).show();
                }
                else {
                    if(mAuthority.equals("可接受")) {
                        if (position == 4) {
                            Intent intent = DeviceDetailActivity.newIntent(getContext(), position);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getContext(),"权限不够，无法查看",Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(mAuthority.equals("可查看")){
                        if (position==4||position==5){
                            Intent intent = DeviceDetailActivity.newIntent(getContext(), position);
                            startActivity(intent);

                        }
                        else {
                            Toast.makeText(getContext(),"权限不够，无法查看",Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(mAuthority.equals("可控制")){
                        if (position==0||position==3||position==4||position==5){
                            Intent intent = DeviceDetailActivity.newIntent(getContext(), position);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getContext(),"权限不够，无法查看",Toast.LENGTH_SHORT).show();
                        }
                    }
                    if ((mAuthority.equals("可管理"))){
                        Intent intent = DeviceDetailActivity.newIntent(getContext(), position);
                        startActivity(intent);
                    }
                }

            }
        });
    }

    @Override
    protected void setupListeners() {
        mTopView.setupListeners(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getActivity().finish();
            }
        }, null);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = DeviceDetailActivity.newIntent(getContext(), 6);
                startActivity(intent);


            }
        });
        mTopView.setupListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();

            }
        },null);

    }

    @Override
    protected int setLayoutRes()
    {
        return R.layout.fragment_device_details;
    }


    public List<Map<String, Object>> getData(){
        //cion和iconName的长度是相同的，这里任选其一都可以
        for(int i=0;i<icon.length;i++){
            Map<String, Object> map = new HashMap<>();
            map.put("imageView", icon[i]);
            map.put("textView", iconName[i]);
            data_list.add(map);
        }

        return data_list;
    }

}
