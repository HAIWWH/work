package app.cddic.com.smarter.fragment.device;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.fragment.base.BaseFragment;
import app.cddic.com.smarter.widget.TopView;

/**
 * Created by asus on 2017/7/27.
 */

public class AssociateNewDeviceFragment extends BaseFragment {
    private Spinner mSpinner;
    private TopView mTopView;
    private Button mCancel;
    private Button mApply;
        @Override
        protected void initViews() {
            mTopView = findView(R.id.associateDevice_topView);
            mTopView.setText("设备","关联新设备",null);
            mSpinner = (Spinner)mView.findViewById(R.id.applyPower_spinner);
            mCancel = (Button)mView.findViewById(R.id.cancel_btn);
            mApply = (Button)mView.findViewById(R.id.apply_associate_btn);



    }

    @Override
    protected void setupAdapters() {
        String str[] = {"可控制","不可控制"};
        ArrayAdapter a = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,str);
        mSpinner.setAdapter(a);


    }

    @Override
    protected void setupListeners() {
        mTopView.setupListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        },null);
        mApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"关联成功",Toast.LENGTH_SHORT).show();
            }
        });
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_associate_new_device;
    }

}
