package app.cddic.com.smarter.fragment.contact;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.entity.ContactDetailMSG;
import app.cddic.com.smarter.fragment.base.BaseFragment;
import app.cddic.com.smarter.widget.TopView;


/**
 * Created by asus on 2017/3/28.
 */

public class ContactDetailFragment extends BaseFragment {

    private static final String KEY_CONTACT_NAME = "contactName";

    private TopView mTopView;
    private TextView mContactNameTv;
    private TextView mTextViewNameType;
    private TextView mTextViewSex;
    private TextView mTextViewState;
    private TextView mTextViewTime;
    private TextView mTextViewGroup;
    private TextView mTextViewElseName;
    private Button mSendMessageBtn;

    public static ContactDetailFragment newInstance(String contactName) {
        ContactDetailFragment contactDetailFragment = new ContactDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_CONTACT_NAME, contactName);
        contactDetailFragment.setArguments(args);
        return contactDetailFragment;
    }

    protected void initViews() {

        mTopView = (TopView)mView.findViewById(R.id.accoutDetail_topView);
        mTopView.setText("联系人","联系人详情",null);
        mContactNameTv = findView(R.id.contactName_tv);
        mContactNameTv.setText(getArguments().getString(KEY_CONTACT_NAME));
        mSendMessageBtn = findView(R.id.sendMessage_btn);
        mTextViewNameType = (TextView)mView.findViewById(R.id.type_textView);
        mTextViewSex = (TextView)mView.findViewById(R.id.userSex_textView);
        mTextViewState = (TextView)mView.findViewById(R.id.state_textView);
        mTextViewTime = (TextView)mView.findViewById(R.id.time_textView);
        mTextViewGroup = (TextView)mView.findViewById(R.id.group_textView);
        mTextViewElseName = (TextView)mView.findViewById(R.id.elseName_editText);
        ContactDetailMSG contactDetailMSG = new ContactDetailMSG("张师傅", "一般用户", "男", "在线", "2012/02/02", "我的好友", "张师傅");
        mContactNameTv.setText(contactDetailMSG.getConnectName());
        mTextViewNameType.setText(contactDetailMSG.getConnectType());
        mTextViewSex.setText(contactDetailMSG.getConnectSex());
        mTextViewState.setText( contactDetailMSG.getConnectState());
        mTextViewTime.setText( contactDetailMSG.getConnectTime() );
        mTextViewGroup.setText(contactDetailMSG.getConnectGroup());
        mTextViewElseName.setText( contactDetailMSG.getConnectElseName());
    }
    @Override
    protected void setupAdapters() {

    }

    @Override
    protected void setupListeners() {
//        mSendMessageBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = ChatActivity.newInstance(getActivity(),
//                        getArguments().getString(KEY_CONTACT_NAME));
//                startActivity(intent);
//            }
//        });
        mTopView.setupListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        },null);

    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_contact_detail;
    }
}
