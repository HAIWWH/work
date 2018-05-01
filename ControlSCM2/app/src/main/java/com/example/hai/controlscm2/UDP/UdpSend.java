package com.example.hai.controlscm2.UDP;


public class UdpSend implements Runnable {

    private Send client;
    private String sendInfo;
    private  String msg;

    public UdpSend(String msg) {
        this.msg = msg;
    }

    public String getSendInfo() {
        return sendInfo;
    }

    public void setSendInfo(String sendInfo) {
        this.sendInfo = sendInfo;
    }

    public void run() {
        client = new Send(msg);
        sendInfo=client.send();
  }
}
