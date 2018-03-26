package app.cddic.com.smarter.fragment.drawer.manage;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.adapter.DragListAdapter;
import app.cddic.com.smarter.entity.GroupMSG;
import app.cddic.com.smarter.widget.DragListView;
import app.cddic.com.smarter.widget.TopView;

/**
 * Created by Hai on 2017/6/15.
 */

public class GroupManagementFragment extends ManageFragment {
    private  DragListView dragListView;
    private DragListAdapter mAdapter = null;
    private ArrayList<String> mData = new ArrayList<String>();
    private TopView topView;
    private ImageButton mAddGroupImageButton;
    private EditText mEditText;
    private String a="0";
    @Override
    protected void setFragmentName() {
        mFragmentName = "分组管理";
    }

    @Override
    protected void initViews() {
        topView = (TopView)findView(R.id.topView_group_manage);
        topView.setText("联系人","分组管理",null);

       initData();
        mAddGroupImageButton = (ImageButton) mView.findViewById(R.id.add_group);
    }

    @Override
    protected void setupAdapters() {
        dragListView = (DragListView)mView.findViewById(R.id.other_drag_list);
        mAdapter = new DragListAdapter(getActivity(), mData);
        dragListView.setAdapter(mAdapter);
    }

    @Override
    protected void setupListeners() {
         mAddGroupImageButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                 dialog.setTitle("添加分组");
                 dialog.setMessage("请输入新的分组名称");
                 View mView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_group_manage,null);
                 mEditText = (EditText)mView.findViewById(R.id.edit_dialog_group_manage);
                 dialog.setView(mView);

                 dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         a=mEditText.getText().toString();
                         mData.add(a);
                         mAdapter = new DragListAdapter(getActivity(),mData);
                         dragListView.setAdapter(mAdapter);

                     }
                 });
                 dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         dialog.dismiss();

                     }
                 });
                 dialog.show();

             }
         });
        topView.setupListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();

        }
        },null);

        }

@Override
protected int setLayoutRes() {
        return R.layout.fragment_group_management;
        }
public void initData() {
        // 数据结果
        String a = null;
        mData = new ArrayList<String>();
        List<GroupMSG> groupMSGList = new ArrayList<>();
    for(int i =1 ;i<6;i++)
    {
        GroupMSG item = null;
        switch (i){
            case 1:
                item = new GroupMSG("好友");
                break;
            case 2:
                item = new GroupMSG("同学");
                break;
            case 3:
                item = new GroupMSG("家人");
                break;
            case 4:
                item =new GroupMSG("朋友");
                break;
            case 5:
                item = new GroupMSG("老师");
                break;

        }
        groupMSGList.add(item);
    }

    for (int i=0; i < 5; i++) {
        switch (i){
        case 0:
        a= groupMSGList.get(i).getGroupName();
        break;
        case 1:
        a= groupMSGList.get(i).getGroupName();
        break;
        case 2:
        a= groupMSGList.get(i).getGroupName();
        break;
        case 3:
        a= groupMSGList.get(i).getGroupName();
        break;
        case 4:
        a= groupMSGList.get(i).getGroupName();
        break;
        }
        mData.add(a);

        }
        }
        }
