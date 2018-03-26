package app.cddic.com.smarter.fragment.message;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.activity.base.MessageActivity;
import app.cddic.com.smarter.fragment.base.BaseFragment;
import app.cddic.com.smarter.utils.CommonViewHolder;
import app.cddic.com.smarter.widget.TopView;

/**
 * Created by Hai on 2017/4/25.
 */

public class ChatSettingsFragment extends BaseFragment {

    private static final String KEY_CONTACT_NAME = "contactName";

    private String mContactName;

    private TopView mTopView;
    private TextView mChatLogTv;
    private TextView mClearChatInformationTv;
    private TextView mChatFileTv;
    private TextView mContactNameTv;
    private Button DeleteContactBtn;

    public static ChatSettingsFragment newInstance(String contactName) {
        ChatSettingsFragment chatSettingsFragment = new ChatSettingsFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_CONTACT_NAME, contactName);
        chatSettingsFragment.setArguments(args);
        return chatSettingsFragment;
    }

    @Override
    protected void initViews() {

        mContactName = getArguments().getString(KEY_CONTACT_NAME);

        mTopView =  CommonViewHolder.get(mView,R.id.chatSetting_topView);
        mTopView.setText("返回","聊天设置",null);
        mContactNameTv = findView(R.id.contactName_tv);
        mContactNameTv.setText(mContactName);
        mChatLogTv = CommonViewHolder.get(mView, R.id.chatLog_textView);
        mChatFileTv =  CommonViewHolder.get(mView,R.id.chatFile_textView);
        mClearChatInformationTv = CommonViewHolder.get(mView,R.id.clearInformation_textView);
        DeleteContactBtn = CommonViewHolder.get(mView,R.id.deleteContact_button);
    }

    @Override
    protected void setupAdapters() {

    }

    @Override
    protected void setupListeners() {

        mTopView.setupListeners(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MessageActivity)getActivity()).backToFragment(
                        ChatFragment.class.getSimpleName(),
                        ChatSettingsFragment.class.getSimpleName());
            }
        }, null);

        mChatLogTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mChatFileTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MessageActivity.newInstance(getContext(),"",MessageActivity.type.CHATFILE);
                startActivity(intent);


            }
        });

        mClearChatInformationTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        DeleteContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_chat_settings;
    }
}
