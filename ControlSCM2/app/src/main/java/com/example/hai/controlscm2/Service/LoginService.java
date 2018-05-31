package com.example.hai.controlscm2.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Log;


import com.example.hai.controlscm2.Activity.LoginActivity;
import com.example.hai.controlscm2.Activity.MainActivity;
import com.example.hai.controlscm2.Protocol.Protocol;
import com.example.hai.controlscm2.Protocol.ReceiveData;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class LoginService extends Service {

    public int Por;
    public String Ip;

   private DatagramSocket socket;    /*码头*/
    private static DatagramPacket packetSend,packetRcv; /*创建发送和接收的数据报*/
    private boolean udpLife = true; //udp生命线程
    private byte[] msgRcv = new byte[1024]; //接收消息
    private Protocol protocol;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            Ip = intent.getStringExtra("IP");
            Por = Integer.parseInt(intent.getStringExtra("POR"));
            /*分别获得登录界面传来的ip和por*/

            Log.i("HAHHAH", "lalall");
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.execute(new UdpServer(Por,Ip));
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





    /*接收类*/
    class UdpServer implements Runnable{

        private  int udpPort = 0;  /*服务器端端口号*/
        private  String hostIp = null; /*服务器端ip号*/

        public UdpServer(int udpPort, String hostIp){
            super();
            this.udpPort = udpPort;
            this.hostIp = hostIp;

        }
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
        @Override
        public void run() {

            try {
                socket = new DatagramSocket();
                socket.setSoTimeout(3000);//设置超时为3s
            } catch (Exception e) {
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
                   ReceiveData msgData = protocol.receiveProtocol();

                    //将收到的消息发给主界面
                    Intent RcvIntent = new Intent();
                    RcvIntent.setAction("udpRcvMsg");
                    RcvIntent.putExtra("udpRcvMsg", msgData);
                    // sendBroadcast(RcvIntent);
                    sendBroadcast(RcvIntent);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            Log.i("udpClient","UDP监听关闭");
            socket.close();
        }
    }

}
