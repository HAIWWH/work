package app.cddic.com.smarter.fragment.device;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.fragment.base.BaseFragment;
import app.cddic.com.smarter.widget.TopView;

/**
 * Created by Hai on 2017/6/15.
 */

public class SendDeviceMessageAsFragment extends BaseFragment{

    private TopView mTopView;
    private Switch mSwitchAllAssociatePerson;
    private Spinner mSpinnerRecipient;
    private LinearLayout mLinearLayoutRecipient;

    @Override
    protected void initViews() {
        mTopView = findView(R.id.sendDeviceMessageAssociate_topView);
        mTopView.setText("设备消息","厨房设备",null);
        mSwitchAllAssociatePerson = findView(R.id.sendDeviceMessageAssociateAllAssociatePerson_switch);
        mSpinnerRecipient = findView(R.id.sendDeviceMessageAssociateRecipient_spinner);
        mLinearLayoutRecipient = findView(R.id.sendDeviceMessageAssociateRecipient_linearlayout);
    }

    @Override
    protected void setupAdapters() {
        String str1[]={"张三","李四"};

        ArrayAdapter<String> a1 = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,str1);

        mSpinnerRecipient.setAdapter(a1);
    }

    @Override
    protected void setupListeners() {
        mTopView.setupListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        },null);
        mSwitchAllAssociatePerson.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    mLinearLayoutRecipient.setVisibility(View.GONE);
                }else{
                    mLinearLayoutRecipient.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_send_devicemessage_associate;
    }
}
