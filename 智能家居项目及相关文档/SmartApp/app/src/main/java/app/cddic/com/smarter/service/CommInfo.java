package app.cddic.com.smarter.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yfs on 4/6 0006.
 */

public class CommInfo {
    public int sendSeq;	//最近发送报文序号
    public int sendDevSeq; //最近发送设备序号
    public int rcvSeq;		//最近收到平台报文序号
    public int rcvDevSeq;  //最近收到设备序号
    public long rcvTime;	//最近收到平台报文时间
    public long rcvDevTime;//最近收到设备报文时间
    public int linkNum;
    public int linkDevNum;

    long lastLoginTime;		    //上次自动登录时间
    long lastUpdateTime;        //上次更新查询时间

    boolean isFile;             //是否要进行平台文件传输：有文件传输时，要根据情况决定是否传输
    boolean isDevFile;          //是否要进行设备文件传输
    public String devAddr;	//登录设备IP地址,表示转发过来的。通过服务器中转时，与srvPort一致。
    public int devPort;	//登录设备端口，通过服务器中转时，与srvPort一致。

    public String srvAddr;	//登录服务器IP地址,表示转发过来的。
    public short srvPort;	//登录服务器端口
    public short bindPort;
    public int sid;
    public int did;
    public boolean isUpdate;		    //是否执行自动更新

    boolean isLoginPlat;		 //是否定时器启动自动登录平台
    boolean isKeepPlat;		    //保活平台
    boolean isKeepDev;          //保活设备

    int loginValue;         //自动登录间隔
    int keepValue;          //保活发送时间间隔
    int updateValue;        //更新时间间隔
    int resendValue;        //重发时间间隔

    public int keepOutValue;       //保活超时时间间隔
    public Map<Integer,PacketMsg> sendPkt; //等待重发的报文集合
    public ArrayList<FileInfo> fileInfoList;  //正在下载文件队列

    CommInfo() { //以下应该根据配置赋值
        resendValue = 3000;     //3 seconds
        updateValue = 3600000;  //one hour
        keepValue = 5000;       //5 seconds
        keepOutValue = 15000;   //15 seconds
        loginValue = 30000;     //30 seconds

        sendPkt = new HashMap<Integer, PacketMsg>();	//等待重发报文列表
        fileInfoList = new ArrayList<FileInfo>();
        isUpdate = true;
        isLoginPlat = true;
        isKeepPlat = true;
        isKeepDev = true;
        srvAddr = "192.168.1.156";
        srvPort = 5678;
        sid = 1;
    }
}
