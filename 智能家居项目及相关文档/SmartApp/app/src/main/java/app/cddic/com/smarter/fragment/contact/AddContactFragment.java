package app.cddic.com.smarter.fragment.contact;

import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

;
import app.cddic.com.smarter.R;
import app.cddic.com.smarter.activity.base.ContactActivity;
import app.cddic.com.smarter.fragment.base.BaseFragment;
import app.cddic.com.smarter.widget.TopView;


/**
 * Created by VileWind on 2017/5/3 0003.
 */

public class AddContactFragment extends BaseFragment {

    private TopView mTopView;
    private ImageButton mScanQRCode;
    private ImageButton mConditionalSearch;
    private LinearLayout mSearchByCondition;

    @Override
    protected void initViews() {

        mTopView=(TopView)mView.findViewById(R.id.add_contact_topView);
        mTopView.setText("联系人","添加",null);
        mSearchByCondition = findView(R.id.searchByCondition);

        mScanQRCode=(ImageButton)mView.findViewById(R.id.Scan_QR_code_btn);

        mConditionalSearch=(ImageButton)mView.findViewById(R.id.conditional_search_btn);
    }

    @Override
    protected void setupAdapters() {

    }

    @Override
    protected void setupListeners() {

        mTopView.setupListeners(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getActivity().finish();
        }
        },null);

        mScanQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //扫描二维码
            }
        });

        mSearchByCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ContactActivity) mActivity).replaceCurrentFragment(
                        new ConditionalSearchFragment());
            }
        });
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_add_contact;
    }
}
