package app.cddic.com.smarter.fragment.base;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.activity.base.ContactActivity;
import app.cddic.com.smarter.adapter.ContactItemsAdapter;
import app.cddic.com.smarter.entity.ContactGroupMSG;
import app.cddic.com.smarter.entity.ContactMSG;


/**
 * Created by Pantiy on 2017/3/12.
 * Copyright © 2016 All rights Reserved by Pantiy
 */

public class ContactFragment extends BaseFragment {
    private View inflate;
    private TextView choosePhoto;
    private TextView takePhoto;
    private Dialog dialog;
    private LinearLayout mNewFriends;
    private LinearLayout mDeviceContact;
    private ExpandableListView mContactElv;
    List<ContactGroupMSG> mContactGroupMSGList = new ArrayList<>();
    List<ContactMSG> contactMSGList = new ArrayList<>();
    @Override
        protected void initViews() {
        mContactElv = findView(R.id.contact_elv);
        mNewFriends = findView(R.id.newFriends);
        mDeviceContact = findView(R.id.deviceContact);
    }

    @Override
    protected void setupAdapters() {

        mContactGroupMSGList.add(new ContactGroupMSG(100, "我的好友"));
        mContactGroupMSGList.add(new ContactGroupMSG(200, "同事"));

        for (int i = 0; i < 5; i++) {
            ContactMSG contactMSG;
            if (i % 2 == 0) {
                contactMSG = new ContactMSG(100, "老王");
            } else {
                contactMSG = new ContactMSG(200, "小李");
            }
            contactMSGList.add(contactMSG);
        }
        ContactItemsAdapter adapter = new ContactItemsAdapter(getActivity(),
                mContactGroupMSGList, contactMSGList);
        mContactElv.setAdapter(adapter);
    }

    @Override
    protected void setupListeners() {

        mNewFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startContactActivity(ContactActivity.Type.NEW_FRIENDS);
            }
        });

        mDeviceContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startContactActivity(ContactActivity.Type.DEVICE_CONTACT);
            }
        });

        mContactElv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                ContactMSG contactMSG = (ContactMSG) parent.getExpandableListAdapter()
                        .getChild(groupPosition, childPosition);
                String contactName =  contactMSG.getContactName();
                startContactActivity(ContactActivity.Type.CONTACT_DETAIL, contactName);
                return true;
            }
        });

        mContactElv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final long packedPosition = mContactElv.getExpandableListPosition(position);
                final int groupPosition = ExpandableListView.getPackedPositionGroup(packedPosition);
                final int childPosition = ExpandableListView.getPackedPositionChild(packedPosition);
                if (groupPosition!=-1&&childPosition==-1) {
                    dialog = new Dialog(getContext(),R.style.ActionSheetDialogStyle);
                    //填充对话框的布局
                    inflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_layout, null);

                    choosePhoto = (TextView) inflate.findViewById(R.id.choosePhoto);
                    takePhoto = (TextView) inflate.findViewById(R.id.takePhoto);;
                    dialog.setContentView(inflate);
                    choosePhoto.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();

                        }
                    });
                    takePhoto.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent  = ContactActivity.newInstance(getContext(),ContactActivity.Type.GROUP_MANAGE);
                             startActivity(intent);
                        }
                    });
                    //获取当前Activity所在的窗体
                    Window dialogWindow = dialog.getWindow();
                    //设置Dialog从窗体底部弹出
                    dialogWindow.setGravity( Gravity.BOTTOM);
                    //获得窗体的属性
                    WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                    lp.y = 20;//设置Dialog距离底部的距离
//    将属性设置给窗体
                    dialogWindow.setAttributes(lp);
                    dialog.show();//显示对话框
                }
                    if (childPosition != -1) {
                    new AlertDialog.Builder(getActivity())
                            .setItems(new String[]{"关联", "删除"},
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    })
                            .show();
                }
                return true;
            }
        });
    }

    private void startContactActivity(int type) {
        Intent intent = ContactActivity.newInstance(getActivity(), type);
        startActivity(intent);
    }

    private void startContactActivity(int type, String contactName) {
        Intent intent = ContactActivity.newInstance(getActivity(), type, contactName);
        startActivity(intent);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_contact;
    }
}
