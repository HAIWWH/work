package com.example.hai.controlscm2.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.hai.controlscm2.Activity.MainActivity;
import com.example.hai.controlscm2.UDP.UDPClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class LoginService extends Service {

    public static UDPClient udpClient;
    public int Por;
    public String Ip;

    public LoginService() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {




            /*分别获得登录界面传来的ip和por*/
            int por = Integer.valueOf(intent.getStringExtra("por"));
            String ip = intent.getStringExtra("IP");
            udpClient = new UDPClient(por,ip);
            Log.i("HAHHAH", "lalall");

            Thread thread = new Thread(udpClient);
            thread.start();

        }catch (Exception e){

        }finally {


        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        udpClient.setUdpLife(false);/*关闭线程生命因子*/
        super.onDestroy();
    }
}
