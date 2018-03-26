package app.cddic.com.smarter.fragment.device;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.adapter.DeviceManageELVAdapter;
import app.cddic.com.smarter.entity.DeviceManageMSG;
import app.cddic.com.smarter.fragment.base.BaseFragment;
import app.cddic.com.smarter.fragment.contact.ContactDetailFragment;
import app.cddic.com.smarter.widget.TopView;

import static app.cddic.com.smarter.R.id.deleteAllCorrelation_btn;

/**
 * Created by VileWind on 2017/7/27 0027.
 */

public class DeviceManageFragment extends BaseFragment {
    private DeviceManageMSG manage;
    private TopView mTopView;
    private EditText mDeviceNaET;
    private EditText mDeviceAliasET;
    private ExpandableListView mELV;
    private List<DeviceManageMSG> mDeviceManageMSGList;
    private Button mDelAlCorrelationBtn;
    private Button mForceOffLineBtn;
    @Override
    protected void initViews() {
        mTopView=(TopView)findView(R.id.deviceManage_topView);
        mTopView.setText("设备","管理",null);
        mDeviceNaET=(EditText)findView(R.id.deviceName_editText);
        mDeviceNaET.setEnabled(false);
        mDeviceAliasET=(EditText)findView(R.id.deviceAlias_editText);
        mDeviceAliasET.setEnabled(false);

        mELV=(ExpandableListView)findView(R.id.device_manage_ELV);
        mELV.setGroupIndicator(null);
        mELV.setDivider(null);

        mDelAlCorrelationBtn=(Button)findView(deleteAllCorrelation_btn);

        mForceOffLineBtn=(Button)findView(R.id.forceAllOffLine_btn);
    }

    @Override
    protected void setupAdapters() {
        mDeviceManageMSGList = new ArrayList<>();
        for(int i=0;i<3;i++){
            DeviceManageMSG item = null;
            switch (i){
                case 0:
                    item = new DeviceManageMSG("张师傅","16日  13：24","在线");
                    break;
                case 1:
                    item = new DeviceManageMSG("李师傅","16日  13：24","在线");
                    break;
                case 2:
                    item = new DeviceManageMSG("谢懈","16日  13：24","在线");
                    break;
                case 3:
                    item = new DeviceManageMSG("李奎","16日  13：24","在线");
                    break;
            }
           mDeviceManageMSGList.add(item);
        }
        DeviceManageELVAdapter mELVAdapter=new DeviceManageELVAdapter(getContext(), mDeviceManageMSGList);
        mELV.setAdapter(mELVAdapter);
    }

    @Override
    protected void setupListeners() {
        mTopView.setupListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        },null);

        mDeviceNaET.addTextChangedListener(new TextWatcher() {
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
        mDeviceAliasET.addTextChangedListener(new TextWatcher() {
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

        mELV.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                return false;
            }
        });
        mELV.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                FragmentManager fm=getFragmentManager();
                Fragment fragment=new ContactDetailFragment();
                fm.beginTransaction().add(R.id.fragment_container,fragment).commit();
                return true;
            }
        });

    }


    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_device_manage;
    }
}
