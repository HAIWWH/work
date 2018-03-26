package app.cddic.com.smarter.fragment.drawer.manage;


import android.app.Dialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.cddic.com.smarter.R;

public class AddAccountFragment extends ManageFragment {
    private View mInflate;
    private Dialog mDialog;
    private EditText mAddAccountAccount;
    private EditText mAddAccountPassword;
    private Button mAddAccountLogin;
    private TextView mAddAccountUnableToLogin;
    private TextView mAddAccountNewUserRegistration;

    @Override
    protected void setFragmentName() {
        mFragmentName = "添加账号";
    }
    @Override
    protected void initViews() {
        mAddAccountAccount = findView(R.id.account_editText);
        mAddAccountPassword = findView(R.id.password_editText);
        mAddAccountLogin = findView(R.id.login_btn);
        mAddAccountUnableToLogin = findView(R.id.unableLogin_textView);
        mAddAccountNewUserRegistration = findView(R.id.newAccountRegister_textView);
    }

    @Override
    protected void setupAdapters() {

    }

    @Override
    protected void setupListeners() {
        mAddAccountLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"登录成功",Toast.LENGTH_SHORT).show();
            }
        });
        mAddAccountUnableToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog = new Dialog(getContext(),R.style.ActionSheetDialogStyle);
                //填充对话框的布局
                mInflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_account, null);
                mDialog.setContentView(mInflate);
                //获取当前Activity所在的窗体
                Window dialogWindow = mDialog.getWindow();
                //设置Dialog从窗体底部弹出
                dialogWindow.setGravity( Gravity.BOTTOM);
                //获得窗体的属性
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                lp.y = 20;//设置Dialog距离底部的距离
//    将属性设置给窗体
                dialogWindow.setAttributes(lp);
                mDialog.show();//显示对话框
                TextView mCancel = (TextView)mInflate.findViewById(R.id.textView_dialog_add_account_cancel) ;
                mCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                    }
                });

            }
        });
        mAddAccountNewUserRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_add_account;
    }


}
