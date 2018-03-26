package app.cddic.com.smarter.adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.entity.ContactGroupMSG;
import app.cddic.com.smarter.entity.ContactMSG;
import app.cddic.com.smarter.utils.CommonViewHolder;

/**
 * SmartSecurity-Manager
 * app.edu.cdu.com.smartsecurity_manager.adapter
 * Created by Pantiy on 2017/3/29.
 * Copyright © 2017 All rights Reserved by Pantiy
 */

public class ContactItemsAdapter extends BaseExpandableListAdapter  {

    private static final String TAG = "ContactItemsAdapter";
    private Context mContext;
    private int[] mGroupIds;
    private List<ContactGroupMSG> mContactGroupMSGList;
    private List<ContactMSG> mContactMSGList;
    private SparseArray<List<ContactMSG>> mContactSparseArray;

    private ContactItemsAdapter(Context context) {
        mContext = context;
    }

    public ContactItemsAdapter(Context context, List<ContactGroupMSG> contactGroupMSGList,
                               List<ContactMSG> contactMSGList) {
        this(context);
        mContactGroupMSGList = contactGroupMSGList;
        mContactMSGList = contactMSGList;
        mContactSparseArray = new SparseArray<>();
        mGroupIds = new int[mContactGroupMSGList.size()];
        parse(mContactGroupMSGList, mContactMSGList);
    }

    @Override
    public int getGroupCount() {
        return mContactSparseArray.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mContactSparseArray.get(mGroupIds[groupPosition]).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mContactGroupMSGList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mContactSparseArray.get(mGroupIds[groupPosition]).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.list_item_for_contact_group, parent, false);
        }
        ContactGroupMSG contactGroupMSG = mContactGroupMSGList.get(groupPosition);
        TextView contactGroupName = CommonViewHolder.get(convertView, R.id.contactGroupName_tv);
        contactGroupName.setText(contactGroupMSG.getGroupName());
        TextView contactGroupState = CommonViewHolder.get(convertView, R.id.contactGroupState_tv);
        contactGroupState.setText("1/2");
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_for_contact, parent, false);
        }
        ContactMSG contactMSG = mContactSparseArray.get(mGroupIds[groupPosition]).get(childPosition);
        TextView contactName = CommonViewHolder.get(convertView, R.id.contactName_tv);
        contactName.setText(contactMSG.getContactName());
        TextView contactUserType = CommonViewHolder.get(convertView, R.id.contactUserType_tv);
        contactUserType.setText("一般用户");
        TextView contactState = CommonViewHolder.get(convertView, R.id.contactState_tv);
        contactState.setText("在线");
        LinearLayout linearLayout = CommonViewHolder.get(convertView,R.id.test_linearLayout);


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private void parse(List<ContactGroupMSG> contactGroupMSGList, List<ContactMSG> contactMSGList) {

        for (int i = 0; i < contactGroupMSGList.size(); i++) {

            ContactGroupMSG contactGroupMSG = contactGroupMSGList.get(i);
            mGroupIds[i] = contactGroupMSG.getGroupId();
            List<ContactMSG> contactMSGs = new ArrayList<>();

            for (int j = 0; j < contactMSGList.size(); j++){
                ContactMSG contactMSG = contactMSGList.get(j);
                if (contactMSG.belong(contactGroupMSG)) {
                    contactMSGs.add(contactMSG);
                }
            }

            Log.i(TAG, contactGroupMSG.getGroupId() + "  " + contactMSGs.size());
            mContactSparseArray.put(contactGroupMSG.getGroupId(), contactMSGs);
        }
    }
}
