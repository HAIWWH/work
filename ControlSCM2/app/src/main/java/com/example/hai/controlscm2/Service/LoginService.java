package com.example.hai.controlscm2.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


import com.example.hai.controlscm2.Activity.MainActivity;
import com.example.hai.controlscm2.UDP.UDPServer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class LoginService extends Service {

    private UDPServer udpServer;
    public int Por;
    public String Ip;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {

            /*分别获得登录界面传来的ip和por*/
            Log.i("HAHHAH", "lalall");
            ExecutorService exec = Executors.newCachedThreadPool();
            udpServer = (UDPServer)intent.getSerializableExtra("udp");
            exec.execute(udpServer);

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


        /*这里的条件需要设定*/
        Intent in = new Intent(getApplicationContext(),MainActivity.class);
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Log.i("HAHHAH", "hahhah");
        startActivity(in);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       // app.getUdpClient().setUdpLife(false);/*关闭线程生命因子*/
    }
}
