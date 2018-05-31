package com.example.hai.controlscm2.Activity;


import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.example.hai.controlscm2.R;
import com.example.hai.controlscm2.UDP.UdpSend;

abstract class BaseActivity extends AppCompatActivity {



    /*初始化函数*/
    public abstract void initView();
    /*事件监听函数*/
    public abstract void setupListeners();

    /*消息发送*/
    public void SendMsg(String msg){
        UdpSend udpSend = new UdpSend(msg);
        new Thread(udpSend).start();
    }

    /*弹出框*/
    public void Popup(String string){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //    设置Title的图标
        builder.setIcon(R.drawable.ic_launcher_background);
        //    设置Title的内容
        builder.setTitle("弹出警告框");
        //    设置Content来显示一个信息
        builder.setMessage("阀值设置"+string);
        //    设置一个PositiveButton
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });
        //    设置一个NegativeButton
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });
        //    设置一个NeutralButton

        //    显示出该对话框
        builder.show();

    }

}
