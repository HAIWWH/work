package com.example.hai.controlscm2.UDP;

/**
 * Created by Hai on 2017/11/24.
 */

import android.content.Intent;
import android.util.Log;


import com.example.hai.controlscm2.Activity.MainActivity;
import com.example.hai.controlscm2.Protocol.Protocol;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class UDPClient implements Runnable {
    private static int udpPort = 0;  /*服务器端端口号*/
    private static String hostIp = null; /*服务器端ip号*/
    private static DatagramSocket socket = null;    /*码头*/
    private static DatagramPacket packetSend,packetRcv; /*创建发送和接收的数据报*/
    private boolean udpLife = true; //udp生命线程
    private byte[] msgRcv = new byte[1024]; //接收消息


    private Protocol protocol;

    /*构造函数*/
    public UDPClient(int udpPort, String hostIp){
        super();
        this.udpPort = udpPort;
        this.hostIp = hostIp;

    }

    //返回udp生命线程因子是否存活
    public boolean isUdpLife(){
        if (udpLife){
            return true;
        }

        return false;
    }

    //更改UDP生命线程因子
    public void setUdpLife(boolean b){
        udpLife = b;
    }

    //发送消息
    public byte[] send(byte[] msgSend){
        InetAddress hostAddress = null;
        Log.i("udpClient",hostIp);

        try {
            hostAddress = InetAddress.getByName(hostIp);
        } catch (UnknownHostException e) {
            Log.i("udpClient","未找到服务器");
            e.printStackTrace();
        }



        packetSend = new DatagramPacket(msgSend , msgSend.length,hostAddress,udpPort);

        try {
            socket.send(packetSend);
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("udpClient","发送失败");
        }
        //   socket.close();
        return msgSend;
    }

    @Override
    public void run() {

        try {
            socket = new DatagramSocket();
            socket.setSoTimeout(3000);//设置超时为3s
        } catch (SocketException e) {
            Log.i("udpClient","建立接收数据报失败");
            e.printStackTrace();
        }
        packetRcv = new DatagramPacket(msgRcv,msgRcv.length);

        while (udpLife){
            try {
                Log.i("udpClient", "UDP监听");
                //线程进行监听状态接收传来的数据
                socket.receive(packetRcv);
                byte [] RcvMsg = packetRcv.getData();

                //String RcvMsg = new String(packetRcv.getData(),packetRcv.getOffset(),packetRcv.getLength());

                //调用协议进行数据处理
                 protocol = new Protocol(RcvMsg);
                String msgData = protocol.receiveProtocol();

                //将收到的消息发给主界面
                Intent RcvIntent = new Intent();
                RcvIntent.setAction("udpRcvMsg");
                RcvIntent.putExtra("udpRcvMsg", msgData);
               //sendBroadcast(RcvIntent);
                MainActivity.context.sendBroadcast(RcvIntent);

                Log.i("Rcv",msgData);
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        Log.i("udpClient","UDP监听关闭");
        socket.close();
    }


}