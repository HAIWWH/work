package app.cddic.com.smarter.service;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by yfs on 4/7 0007.
 */

class NetHandler implements Runnable {
    private  Boolean isThreadWorking = true;//指示监听线程是否终止
    private int mBindPort;
    private DatagramSocket mSocket;
    private CallBackFromNet callBack = null;

    NetHandler(int bindPort) {
        mBindPort = bindPort;
    }

    public void setWorking(Boolean status){
        isThreadWorking = status;
    }

    public void setCallBack(CallBackFromNet callBack) {
        this.callBack = callBack;
    }

    private void Receiver( )  {
        try {
            // 建立Socket连接
            Log.d("Receiver","bind port="+mBindPort);
            mSocket = new DatagramSocket(mBindPort);
            mSocket.setBroadcast(true);
            try {
                while (isThreadWorking) {
                    byte[] message = new byte[1514]; // 接收缓冲区，客户端发送的数据不能超过这个大小
                    DatagramPacket datagramPacket = new DatagramPacket(message, message.length);

                    mSocket.receive(datagramPacket); //阻塞接收信息
                    PacketMsg pkt = new PacketMsg(message);

                    ByteArrayInputStream bais = new ByteArrayInputStream(pkt.message);
                    DataInputStream dis = new DataInputStream(bais);

                    dis.skipBytes(4);
                    pkt.cmd = dis.readByte();

                    pkt.length = datagramPacket.getLength();
                    pkt.address = datagramPacket.getAddress().toString().substring(1);
                    pkt.port = datagramPacket.getPort();

                    callBack.NetNotify(pkt);
                }
            } catch (IOException e) { //IOException
                e.printStackTrace();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public boolean sendPacket(PacketMsg packetMsg) {
        boolean ret = true;

        InetAddress Address = null;
        try {
            Address = InetAddress.getByName(packetMsg.address);
        }catch (UnknownHostException e){
            e.printStackTrace();
        }

        DatagramPacket p = new DatagramPacket(packetMsg.message,
                packetMsg.length, Address, packetMsg.port);
        try {
            Log.d("test"," sendaddress:"+packetMsg.address);
            Log.d("test"," sendport:"+packetMsg.port);
            Log.i("test","msocket.send");
            mSocket.send(p);
        } catch (IOException e) {
            e.printStackTrace();
            ret = false;
        }
        return ret;
    }

    @Override
    public void run() {
        Receiver( );
    }
}
