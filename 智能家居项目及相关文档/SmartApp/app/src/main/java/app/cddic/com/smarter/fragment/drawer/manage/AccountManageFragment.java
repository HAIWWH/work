package app.cddic.com.smarter.fragment.drawer.manage;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.activity.base.SettingActivity;
import app.cddic.com.smarter.widget.TopView;


/**
 * Created by asus on 2017/3/28.
 */

public class AccountManageFragment extends ManageFragment {

    private TopView mTopView;
    private TextView mAddId;
    private TextView mLoginSetting;
    private TextView mExitId;
    private TextView mChooseFirst;
    private TextView mChooseSecond;
    private TextView mNameFirst;
    private TextView mNameSecond;
    private TextView mIdFirst;
    private TextView mIdSecond;
    private FragmentManager mFm;
    private Fragment mFragment;
    private static final String DIALOG_DATE = "DialogDate";
    private TextView mDelete;
    @Override
    protected void setFragmentName() {
        mFragmentName="账号管理";
    }


    @Override
    protected void initViews() {
        mAddId = (TextView) mView.findViewById(R.id.addId_textView);
        mLoginSetting = (TextView)mView.findViewById(R.id.loginSetting_btn);
        mExitId = (TextView)mView.findViewById(R.id.exitId_textView);
        mChooseFirst = (TextView)mView.findViewById(R.id.chooseFirst_textView);
        mChooseSecond = (TextView)mView.findViewById(R.id.chooseSecond_textView);
        mNameFirst= (TextView)mView.findViewById(R.id.nameFirst_textView);
        mNameSecond = (TextView)mView.findViewById(R.id.nameSecond_textView);
        mIdFirst = (TextView)mView.findViewById(R.id.idFirst_textView);
        mIdSecond = (TextView)mView.findViewById(R.id.idSecond_textView);
        mChooseFirst.setVisibility(View.VISIBLE);
        mChooseSecond.setVisibility(View.INVISIBLE);
        mDelete = (TextView)mView.findViewById(R.id.deleteId_textView);
    }

    @Override
    protected void setupAdapters() {

    }

    @Override
    protected void setupListeners() {

        mFm = getActivity().getSupportFragmentManager();
        mAddId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = SettingActivity.newInstance(getActivity(), Type.ADDACCOUNT);
                startActivity(intent);

            }
        });


        mLoginSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = SettingActivity.newInstance(getActivity(), Type.LOGINSETTING);
                startActivity(intent);
            }
        });
        mExitId.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager manager = getFragmentManager();

            }
        });
        mNameFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChooseFirst.setVisibility(View.VISIBLE);
                mChooseSecond.setVisibility(View.INVISIBLE);
            }
        });
        mNameSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChooseFirst.setVisibility(View.INVISIBLE);
                mChooseSecond.setVisibility(View.VISIBLE);
            }
        });

        mExitId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ad = null;
                ad = new AlertDialog.Builder(getActivity());
                ad.setTitle("是否确定退出当前设备");
                ad.setMessage("登录时间:7月9号12：23-10号15：23\n\n远程登录         时长:27小时");
                ad.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                ad.setNegativeButton("确认退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                ad.show();

            }
        });
        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(captureImage);

            }
        });
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_account_manage;
    }
}
