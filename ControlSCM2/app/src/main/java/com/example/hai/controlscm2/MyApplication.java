package com.example.hai.controlscm2;

import android.app.Application;


public class MyApplication extends Application {


    //  private static UDPServer udpClient;
    private static String ip;
    private static int por;

    @Override
    public void onCreate() {
        super.onCreate();
        ip = null;
        por = 0;
    }


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPor() {
        return por;
    }

    public void setPor(int por) {
        this.por = por;
    }


//    public UDPServer getUdpClient() {
//        return udpClient;
//    }
//
//    public  void setUdpClient(UDPServer udpClient) {
//        this.udpClient = udpClient;
//    }

}
