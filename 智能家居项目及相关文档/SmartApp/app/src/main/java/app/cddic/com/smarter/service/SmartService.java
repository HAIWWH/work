package app.cddic.com.smarter.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import app.cddic.com.smarter.R;
import app.cddic.com.smarter.activity.base.TestActivity;
import app.cddic.com.smarter.entity.ConnectMSG;
import app.cddic.com.smarter.entity.FileMSG;
import app.cddic.com.smarter.entity.MsgObject;
import app.cddic.com.smarter.entity.RetMSG;
import app.cddic.com.smarter.entity.UpdateMSG;
import app.cddic.com.smarter.global.SmartApplication;
import app.cddic.com.smarter.service.protocol.Protocol;
import app.cddic.com.smarter.service.protocol.SmartProtocol;
import app.cddic.com.smarter.utils.StaticClass;

/**
 * 项目名：  SmartApp
 * 包名：    app.cddic.com.smarter.service
 * 文件名：  SmartService
 * 创建者：  zhl
 * 创建时间： 2017/3/6 11:24
 * 描述：服务类
 */

public class SmartService extends Service implements CallBackFromNet {
    int mBind = 0;              //统计绑定activity计数
    SmartApplication mAPP;      //记录全局变量的信息
    RetMSG mRetMSG;        //用于记录返回信息
    CommInfo commInfo = new CommInfo();  //通信信息;         //通信过程信息

    Protocol mProto;            //协议处理对象W
    NetHandler mNetHandler;     //网络通信对象
    Thread mThread;             //接收处理线程

    private SmartBinder mBinder = new SmartBinder();

    public CommInfo getCommInfo() {
        return commInfo;
    }

    public SmartApplication getmAPP() {
        return mAPP;
    }

    public RetMSG getmRetMsg() {
        return mRetMSG;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("smartService", "onBind方法执行了");
        mBind++;
        return mBinder;
    }

    public class SmartBinder extends Binder {  //返回当前的service实例
        public SmartService getService() {
            Log.d("smartService", "返回service实例");
            return SmartService.this;
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mBind--;
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAPP = (SmartApplication) getApplication();
        SmartProtocol.setSrv(this);
        mAPP.setSrv(this);

        commInfo = new CommInfo();
        mProto = new Protocol(this);
        mRetMSG = new RetMSG();

        Log.i("smartService", "开始启动接收线程");
        startThread();

        Log.i("smartService", "开始启动定时器");
        timer.schedule(task, 3000, 3000); // 30s后执行task,经过30s再次执行，真实运行时需要修改
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAPP.setSrv(null);
    }

    void startThread() {
        if (mThread != null && mThread.isAlive()) {
            Log.i("Service", "start: thread is alive");
        } else {
            mNetHandler = new NetHandler(commInfo.bindPort);
            mNetHandler.setCallBack(this);

            Thread mThread = new Thread(mNetHandler);
            mThread.start();
        }
    }

    public void stopThread() {
        if (mThread != null && mThread.isAlive()) {
            mThread.interrupt();
            mThread = null;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("smartService", "onStartCommand运行了");
        return super.onStartCommand(intent, flags, startId);
    }

    //发送通知到通知栏，然后启动主页界面接收通知。
    void sendMsgToNoticeBar(int MsgType) {
        String tipMsg;

        if (MsgType < 10) {//编号小于10的才需要发消息到通知栏
            switch (MsgType) {
                case 3:
                    tipMsg = "发现附近前端设备,请点击查看";
                    break;
                case 4:
                    tipMsg = "已经下载新版本，请点击安装";
                    break;
                case 7:
                    tipMsg = "发现新版本，请点击下载";
                    break;
                default:
                    tipMsg = "有新消息请点击查看";
                    break;
            }
        } else
            return;

        Intent intent = new Intent(this, TestActivity.class); //此句今后得修改到合适页面
        intent.putExtra("MsgType", MsgType);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(this);
        notification.setSmallIcon(R.drawable.notification)
                .setWhen(System.currentTimeMillis())
                .setContentText(tipMsg)
                .setTicker("消息")
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
                .setContentIntent(pi);

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(1, notification.build());
    }

    //发送消息类型广播到所有activity
    void sendMsgToActivity(int MsgType) {
        //这里要发送一个广播通知
        Intent receiverIntent = new Intent();
        receiverIntent.setAction(StaticClass.ACTION);
        receiverIntent.putExtra("MsgType", MsgType);
        sendBroadcast(receiverIntent);
    }

    public void setRet(int type, PacketMsg pkt, MsgObject msg, int ret, String info) {
        mRetMSG.setType(type);
        mRetMSG.setSort(pkt.sort);
        mRetMSG.setCmd(pkt.cmd);
        mRetMSG.setPktType(pkt.type);
        mRetMSG.setFromIP(pkt.address);
        mRetMSG.setPort(pkt.port);
        mRetMSG.setObject(msg);
        mRetMSG.setState((byte) ret);
        mRetMSG.setInfo(info);
        commInfo.devAddr = pkt.address;
        commInfo.devPort = pkt.port;
        pushMsg(type);
    }

    //发送消息类型给界面时，调用该方法
    public void pushMsg(int MsgType) {
        //没有界面运行，发送通知
        if (mBind == 0) {
            sendMsgToNoticeBar(MsgType);
        } else {
            if (mAPP.getActTop()) {
                //在前台运行，只发送广播到activity
                sendMsgToActivity(MsgType);
            } else {
                //有activity在后台但是不是在最前面，要发送到通知栏
                sendMsgToNoticeBar(MsgType);
                sendMsgToActivity(MsgType);
            }
        }
    }

    //get message from service
    public RetMSG pickMsg(int type) {
        if (mRetMSG.getType() == type)
            return mRetMSG;
        else
            return null;
    }

    public void createFileInfo(FileMSG fileMSG) {
        FileInfo fileInfo = new FileInfo();

        fileInfo.url = fileMSG.getFile();
        fileInfo.size = fileMSG.getSize();
        fileInfo.sort = fileMSG.getSort();
        fileInfo.sized = fileMSG.getFrom();
        fileInfo.fromPlat = fileMSG.isSrv();
        if (!fileInfo.fromPlat) {
            fileInfo.devSerial = fileMSG.getSerial();
        }

        String fName = fileInfo.url.trim();
        fileInfo.path = fName.substring(fName.lastIndexOf("\\") + 1);
        try {
            fileInfo.out = new FileOutputStream(new File(fileInfo.path));
            fileInfo.openTime = System.currentTimeMillis();
        } catch (Exception e) {
            e.printStackTrace();
        }

        fileInfo.lastTime = 0;
        fileInfo.id = fileMSG.getId();
    }

    public boolean finishFile(FileMSG fileMSG) {
        boolean ret = false;
        FileInfo fileInfo = fileMSG.getFileInfo();
        //关闭文件，更新数据库或者启动安装，删除集合
        try {
            fileInfo.out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file = new File(fileInfo.path);
        if (fileInfo.MD5.equals(getFileMD5(file))) {
            modData(fileInfo.sort, fileInfo.id, fileInfo.path);
            ret = true;
        }
        commInfo.fileInfoList.remove(fileInfo);
        return ret;
    }

    public int updateFile(FileMSG fileMSG, PacketMsg pkt, int len) {
        FileInfo fileInfo = fileMSG.getFileInfo();
        //写入文件，更新数据
        try {
            fileInfo.out.write(pkt.message,pkt.length - len, len);
        } catch (IOException e) {
            e.printStackTrace();
        }

        fileInfo.sized += len;
        fileInfo.lastTime = System.currentTimeMillis();

        return (fileInfo.size - fileInfo.sized);
    }


    // 根据文件计算出文件的MD5
    public String getFileMD5(File file) {

        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());

        return bigInt.toString(16);
    }

    //用于定时器构建一个文件对象，启动文件传输
    void fileProc(FileInfo fileInfo) {
        FileMSG msg = new FileMSG();

        msg.setType(StaticClass.MSG_FILE);
        msg.setFile(fileInfo.url);      //app代码为1
        msg.setSize(fileInfo.size);     //当前版本
        msg.setFrom(fileInfo.sized + 1);
        msg.setIndex(1);                //支持带内二进制模式
        msg.setFileInfo(fileInfo);

        if (fileInfo.fromPlat) {
            msg.setSrv(true);
        } else {
            msg.setSrv(false);
            msg.setSerial(mAPP.getDevSerial());
        }
        mProto.sendProc(msg);
    }

    //重传队列检查
    void reSendProc(long curTime) {
        PacketMsg pkt;
        int num = 0;
        //遍历重发队列，对于超时的报文进行删除或者重发处理
        Map<Integer, PacketMsg> map = commInfo.sendPkt;
        Iterator<Map.Entry<Integer, PacketMsg>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, PacketMsg> entry = it.next();
            pkt = entry.getValue();
            num++;
            if (curTime - pkt.time > commInfo.resendValue) {
                if (pkt.count-- > 0) {
                    SendPkt(pkt, false);
                } else {
                    mProto.timeoutProc(pkt); //协议相关的超时处理
                    it.remove();
                }
            }
        }
        if (num > 0) {
            Log.i("定时处理:", "重发报文数" + num);
        }
    }

    void timerCheck(long curTime) {
        Log.i("定时处理:", "平台状态" + mAPP.getLoginState());
        if (mAPP.getLoginState() == 3) { //如果已经平台在线
            if (commInfo.isKeepPlat && (curTime - commInfo.rcvTime > commInfo.keepValue)) {    //平台需要保活管理
                ConnectMSG keepMsg = new ConnectMSG();
                keepMsg.setType(StaticClass.MSG_KEEPLIVE);
                keepMsg.setPort(0); //表示服务器保活管理
                mProto.sendProc(keepMsg);
            }
            //自动更新检查
            if (commInfo.isUpdate && (curTime - commInfo.lastUpdateTime >= commInfo.updateValue)) {
                UpdateMSG msg = new UpdateMSG();
                msg.setType(StaticClass.MSG_UPDATE);
                msg.setSerial((byte) 1);     //app代码为1
                msg.setVersion("010101");   //当前版本
                mProto.sendProc(msg);
            }

            if (commInfo.isFile) {
                FileInfo fileInfo;
                boolean tag = false;
                Log.i("定时处理:", "文件传输检查");
                int size = commInfo.fileInfoList.size();
                for (int i = 0; i < size; i++) {
                    fileInfo = commInfo.fileInfoList.get(i);
                    //如果是新加入的，或者已经停止传输的，重新启动传输处理
                    if (fileInfo.fromPlat && (curTime - fileInfo.lastTime >= commInfo.keepValue)) {
                        fileProc(fileInfo);
                    }
                    tag = true;
                }

                if (!tag) {
                    commInfo.isFile = false; //如果没有等到传输的文件队列，则置下次不检查
                } else {
                    Log.i("定时处理:", "没有平台文件传输了");
                }
            }
        } else if (mAPP.getLoginState() == 0) {//平台处于离线状态
            if (commInfo.isLoginPlat
                    && (curTime - commInfo.lastLoginTime >= commInfo.loginValue)
                    && mAPP.isLoginWay() && mAPP.isOfflineMode()) { //要是允许自动登录平台
                Log.i("定时处理:", "进行自动平台登录");
                ConnectMSG msg = new ConnectMSG();
                msg.setUsername(mAPP.getUserName());
                msg.setPassword(mAPP.getPassWord());
                msg.setType(StaticClass.MSG_LOGIN);
                mProto.sendProc(msg);
            }
        }

        if (mAPP.getLoginDevState() == 3) {//如果已经设备在线
            if (commInfo.isKeepDev && (curTime - commInfo.rcvDevTime > commInfo.keepValue)) {    //需要保活处理
                ConnectMSG keepMsg = new ConnectMSG();
                keepMsg.setType(StaticClass.MSG_KEEPLIVE);
                keepMsg.setPort(commInfo.devPort);
                mProto.sendProc(keepMsg);
            }

            if (commInfo.isDevFile) {
                FileInfo fileInfo;
                boolean tag = false;
                int size = commInfo.fileInfoList.size();
                for (int i = 0; i < size; i++) {
                    fileInfo = commInfo.fileInfoList.get(i);
                    //如果是新加入的，或者已经停止传输的，重新启动传输处理
                    if ((!fileInfo.fromPlat) && (curTime - fileInfo.lastTime >= commInfo.keepValue)) {
                        if (fileInfo.devSerial.equals(mAPP.getDevSerial()))
                            fileProc(fileInfo);
                    }
                    tag = true;
                }
                if (!tag) {
                    commInfo.isDevFile = false; //如果没有等到传输的文件队列，则置下次不检查
                } else {
                    Log.i("定时处理:", "没有设备文件传输");
                }
            }
        }
    }

    //下面是消息处理有关函数，在消息中处理接收和定时器，避免有关结合访问的互斥要求
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Log.d("handleMessage", "what=1，process timer");

                    long curTime = System.currentTimeMillis();
                    reSendProc(curTime); //重发队列处理,检查是否有超时
                    timerCheck(curTime);//检查comminfo，是否需要执行定时处理事务
                    break;
                case 2:
                    Log.d("handleMessage", "what=2，process receiver");
                    PacketMsg pkt = (PacketMsg) msg.obj;
                    Log.d("asd","received pkt.port = "+pkt.port);
                    mProto.recvProc(pkt);
                    break;
                default:
                    Log.d("handleMessage", "error msg");
                    break;
            }
            super.handleMessage(msg);
        }
    };

    //定时器定义及处理
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            handler.sendEmptyMessage(1);
        }
    };

    @Override
    public void NetNotify(PacketMsg pkt) {
        Log.d("smartService", "处理接收的报文");
        PrintData("recv msg:", pkt.message);

        Message msg = handler.obtainMessage();
        msg.what = 2;
        msg.obj = pkt;
        handler.sendMessage(msg);
    }

    //execute command coming from activity
    public boolean execMsg(MsgObject msgObject) {
        boolean ret = true;
        if (msgObject.getType() == StaticClass.MSG_EXIT) {
            stopThread();
            timer.cancel();
        } else {
            ret = mProto.sendProc(msgObject);
        }

        return ret;
    }

    private void PrintData(String tip, byte[] b) {
        String prints = "";

        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            prints = prints + hex + " ";
        }
        Log.i(tip, prints);
    }

    public boolean SendPkt(PacketMsg pkt, boolean reSend) {
        boolean ret = false;

        Log.i("test","SendPkt");
        if (mNetHandler.sendPacket(pkt)) {
            PrintData("send msg:", pkt.message);

            if (pkt.count > 0 && reSend) { //表示需要重发,记录到重发集合
                pkt.time = System.currentTimeMillis();
                commInfo.sendPkt.put(pkt.seq, pkt);
            }
            ret = true;
        }
        return ret;
    }

    public void addData( MsgObject msgObject) {
    }

    public void modData(int sort, int no, String path) {
    }

    public void modData(int sort, int id, byte state) {
    }

    public void delData(int type, int no) {
    }
}