package app.cddic.com.smarter.fragment.base;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.activity.base.MessageActivity;
import app.cddic.com.smarter.adapter.AlarmMessageItemsAdapter;
import app.cddic.com.smarter.adapter.ChatMessageItemsAdapter;
import app.cddic.com.smarter.adapter.InformMessageItemsAdapter;
import app.cddic.com.smarter.entity.ChatMSG;
import app.cddic.com.smarter.entity.InfoMSG;
import app.cddic.com.smarter.utils.StaticClass;


/**
 * Created by Pantiy on 2017/3/12.
 * Copyright © 2016 All rights Reserved by Pantiy
 */

public class MessageFragment extends BaseFragment {

    private static final int ALARM = 0;
    private static final int INFORM = 1;
    private static final int CHAT = 2;

    private List<RadioButton> mRadioButtons = new ArrayList<>();
    private List<ListView> mListViews = new ArrayList<>();

    private RadioGroup mRadioGroup;

    @Override
    protected void initViews() {

        mRadioGroup = (RadioGroup) mView.findViewById(R.id.message_radioGroup);
        RadioButton radioButton = (RadioButton) mView.findViewById(R.id.alarm_radioButton);
        mRadioButtons.add(ALARM, radioButton);
        radioButton = (RadioButton) mView.findViewById(R.id.inform_radioButton);
        mRadioButtons.add(INFORM, radioButton);
        radioButton = (RadioButton) mView.findViewById(R.id.chat_radioButton);
        mRadioButtons.add(CHAT, radioButton);

        ListView listView = (ListView) mView.findViewById(R.id.alarm_listView);
        mListViews.add(ALARM, listView);
        listView = (ListView) mView.findViewById(R.id.inform_listView);
        mListViews.add(INFORM, listView);
        listView = (ListView) mView.findViewById(R.id.chat_listView);
        mListViews.add(CHAT, listView);
    }

    @Override
    protected void setupAdapters() {
        List<ChatMSG> chatMSGList = mMSGUtil.getMSGList(StaticClass.MSG_CHAT);
        List<InfoMSG> infoMSGList = mMSGUtil.getMSGList(StaticClass.MSG_INFO);
        mListViews.get(ALARM).setAdapter(new AlarmMessageItemsAdapter(getActivity()));
        mListViews.get(INFORM).setAdapter(new InformMessageItemsAdapter(getActivity()));
        mListViews.get(CHAT).setAdapter(new ChatMessageItemsAdapter(getActivity()));
    }

    @Override
    protected void setupListeners() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int current;
                switch (checkedId) {
                    case R.id.alarm_radioButton:
                        current = ALARM;
                        break;
                    case R.id.inform_radioButton:
                        current = INFORM;
                        break;
                    case R.id.chat_radioButton:
                        current = CHAT;
                        break;
                    default:
                        current = -1;
                        break;
                }
                switchMessageListView(current);
            }
        });

//        mListViews.get(ALARM).setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = MessageActivity.newInstance(getActivity(),
//                        (String) mListViews.get(ALARM).getAdapter().getItem(position),MessageActivity.type.ALARMMESSAGE);
//                startActivity(intent);
//            }
//        });

        mListViews.get(INFORM).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InformMessageItemsAdapter.ViewHolder holder = (InformMessageItemsAdapter.ViewHolder)view.getTag();
                if(holder.informTitleTv.getText().equals("通知")) {
                    Intent intent = MessageActivity.newInstance(getActivity(),
                            (String) mListViews.get(INFORM).getAdapter().getItem(position), MessageActivity.type.INFORMMESSAGE);
                    startActivity(intent);
                }
                else if (holder.informTitleTv.getText().equals("公告")){
                    Intent intent = MessageActivity.newInstance(getActivity(),
                            (String) mListViews.get(INFORM).getAdapter().getItem(position), MessageActivity.type.SYSTEMMESSAGE);
                    startActivity(intent);
                }
            }
        });

        mListViews.get(CHAT).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = MessageActivity.newInstance(getActivity(),
                        (String) mListViews.get(CHAT).getAdapter().getItem(position), MessageActivity.type.CHATMESSAGE);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_message;
    }

    private void switchMessageListView(int current) {
        for (int i = 0; i < mListViews.size(); i++) {
            if (i == current) {
                continue;
            }
            mListViews.get(i).setVisibility(View.GONE);
        }
        mListViews.get(current).setVisibility(View.VISIBLE);
    }
}