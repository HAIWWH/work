package com.example.hai.controlscm2.UDP;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.util.Log;

import com.example.hai.controlscm2.MyApplication;

public class Send {
    private static int SERVER_PORT ;
    private DatagramSocket dSocket = null;
    private static String ip;
    private String msg;
    private MyApplication app;
    public Send(String msg ) {
        super();
        this.msg = msg;
    }

    public int getSERVER_PORT() {
        return SERVER_PORT;
    }

    public static void setSERVER_PORT(int SERVER_PORT) {
        Send.SERVER_PORT = SERVER_PORT;
    }

    public String getIp() {
        return ip;
    }

    public static void setIp(String ip) {
        Send.ip = ip;
    }


    public String send() {
        StringBuilder sb = new StringBuilder();
        InetAddress local = null;
//        String string = ip;
//        int i = SERVER_PORT;

        try {
            local = InetAddress.getByName(ip); // 本机测试
            sb.append("已找到服务器,连接中...").append("/n");
        } catch (UnknownHostException e) {
            sb.append("未找到服务器.").append("/n");
            e.printStackTrace();
        }
        try {
            dSocket = new DatagramSocket(); // 注意此处要先在配置文件里设置权限,否则会抛权限不足的异常
            sb.append("正在连接服务器...").append("/n");
        } catch (SocketException e) {
            e.printStackTrace();
            sb.append("服务器连接失败.").append("/n");
        }
        int msg_len = msg == null ? 0 : msg.length();
        DatagramPacket dPacket = new DatagramPacket(msg.getBytes(),msg_len,local, SERVER_PORT);
        try {
            dSocket.send(dPacket);
            Log.d("tian", "msg=="+msg+"dpackage="+dPacket.getData()+"dPacket.leng="+dPacket.getLength());
            Log.d("IP","ip =="+local);
            Log.d("POR","POR=="+ SERVER_PORT);
            sb.append("消息发送成功!").append("/n");
        } catch (IOException e) {
            e.printStackTrace();
            sb.append("消息发送失败.").append("/n");
        }
        dSocket.close();
        return sb.toString();
    }
}