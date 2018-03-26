package app.cddic.com.smarter.fragment.device;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.entity.DeviceShareMSG;
import app.cddic.com.smarter.fragment.base.BaseFragment;
import app.cddic.com.smarter.widget.TopView;


/**
 * Created by 小帆哥 on 2017/4/26.
 */

public class DeviceShareFragment extends BaseFragment {

    private TopView mTopView;
    private Button mConfirmShareBtn;
    private EditText mLeaveWordEt;
    private TextView mDeviceNameTv;
    private TextView mDeviceAliasTv;
    private TextView mCurrentAssociationTv;
    private TextView mMaxAssociationsTv;
    private Spinner mSelectRelevantPersonSp;
    private Spinner mSpecifiedPermissionsSp;


    @Override
    protected void initViews(){
        mTopView = findView(R.id.topView);
        mTopView.setText("设备","分享","取消");
        mSelectRelevantPersonSp = findView(R.id.selectRelatedPerson_spinner);
        mSpecifiedPermissionsSp = findView(R.id.specifiedPermissions_spinner);
        mConfirmShareBtn =findView(R.id.confirmShare_btn);
        DeviceShareMSG mDeviceShareMSG = new DeviceShareMSG("厨房设备","厨房设备","12人","12人");
        mLeaveWordEt = findView(R.id.leaveWord_editText);
        mLeaveWordEt.setSelection(mLeaveWordEt.getText().length());
        mDeviceNameTv = findView(R.id.deviceName_textView);
        mDeviceNameTv.setText(mDeviceShareMSG.getDeviceName());
        mDeviceAliasTv = findView(R.id.deviceAlias_textView);
        mDeviceAliasTv.setText(mDeviceShareMSG.getDevicealias());
        mCurrentAssociationTv = findView(R.id.currentAssociations_textView);
        mCurrentAssociationTv.setText(mDeviceShareMSG.getCurrentAssociation());
        mMaxAssociationsTv = findView(R.id.maxAssociations_textView);
        mMaxAssociationsTv.setText(mDeviceShareMSG.getMaxAssociations());

    }

    @Override
    protected void setupAdapters() {
        String str1[]={"张三","李四"};
        String str2[]={"可控制","可管理","可查看","可接受"};

        ArrayAdapter<String> a1 = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item,str1);
        ArrayAdapter<String> a2 = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item,str2);

        mSelectRelevantPersonSp.setAdapter(a1);
        mSpecifiedPermissionsSp.setAdapter(a2);
    }

    @Override
    protected void setupListeners() {
        mTopView.setupListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        },new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        mConfirmShareBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"分享成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_device_share;
    }
}
