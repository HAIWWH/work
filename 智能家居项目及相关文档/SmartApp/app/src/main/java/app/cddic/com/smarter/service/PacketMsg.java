package app.cddic.com.smarter.service;

import java.net.InetAddress;

import app.cddic.com.smarter.entity.MsgObject;

/**
 * 项目名：  SmartApp
 * 包名：    app.cddic.com.smarter.service
 * 文件名：  PacketMsg
 * 创建者：  zhl
 * 创建时间： 2017/3/7 12:53
 * 描述：
 */
public class PacketMsg {
    //基本首部成员
//    public byte[] tag;
    public byte cmd;    //协议类别
    public byte type;   //协议子类型
    public byte opt;
    public byte sort;
    public int sid;
    public int seq;
    public int ack;

    //首部选项成员
    public int did;
    public int keySeq;
    public String sip;
    public int sport;

    public byte dsort;      //报文接收方类型
    public int length;    //报文总长度
    public long time;		//报文收发时间（秒）
    public byte count;      //重发次数
    public byte[] message; //协议数据缓冲区
    public String hopt;     //辅助信息
    public String data;     //辅助信息
    public MsgObject msg;  //解析后的消息,type是消息编号

    public String address;		//发送地址或者接收地址
    public int port;

    public PacketMsg(byte[] message) {
        this.message = message;
    } //构造函数
}

