package com.example.hai.controlscm2.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

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

        ExecutorService exec = Executors.newCachedThreadPool();
        udpClient = new UDPClient(1211,"ahah");
        exec.execute(udpClient);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
