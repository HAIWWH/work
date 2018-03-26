package app.cddic.com.smarter.service.protocol;

import android.util.Base64;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import app.cddic.com.smarter.entity.ConnectMSG;
import app.cddic.com.smarter.entity.MsgObject;
import app.cddic.com.smarter.entity.QueryMSG;
import app.cddic.com.smarter.service.CommInfo;
import app.cddic.com.smarter.service.PacketMsg;
import app.cddic.com.smarter.service.SmartService;
import app.cddic.com.smarter.utils.StaticClass;

/**
 * Created by yfs on 4/13 0013.
 */

public class LoginProtocol extends SmartProtocol {
    ConnectMSG loginMsg;
    String rand = null;

    public LoginProtocol(SmartService Srv) {
        setSrv(Srv);
    }

    public PacketMsg createPkt(MsgObject msgObject){
        loginMsg = (ConnectMSG)msgObject;

        PacketMsg pkt = new PacketMsg(null);
        if (loginMsg.getPort() != 0) { //不是发给服务器的
            pkt.dsort = 2; //发给设备
            if (msgObject.getType() == StaticClass.MSG_LOGIN){
                pkt.opt = 8;
            }
            if (commInfo.devPort == 0) { //如果还没有记录设备地址
                pkt.address = loginMsg.getAddress();
                pkt.port = loginMsg.getPort();
            }else{
                pkt.address = commInfo.devAddr;
                pkt.port = commInfo.devPort;
            }
        }else{
            pkt.dsort = 3; //发给平台
        }
        pkt.cmd = 1;
        pkt.msg = msgObject;

        switch (msgObject.getType()){
            case StaticClass.MSG_LOGIN:
                pkt.type = 1;
                break;
            case StaticClass.MSG_LOGOUT:
                pkt.type = 5;
                break;
            case StaticClass.MSG_KEEPLIVE:
                long curTime = System.currentTimeMillis();

                pkt.type = 6;
                if(curTime - commInfo.rcvDevTime > commInfo.keepOutValue) {
                    timeoutProc(pkt); //发送保活都一直超时没有应答
                    return null;
                }
                break;
            default:
                Log.i("Login","Error Type");
                return null;
        }
        return pkt;
    }

    public boolean sendProc(PacketMsg pkt) {
        boolean ret = true;
        if(pkt.type < 6) {
            JSONObject jsonProt = new JSONObject();//创建json格式的数据
            JSONObject jsonProtData = new JSONObject();

            pkt.count = 3; //要求重发三次
            try {
                if (pkt.type == 1) {  //用户验证
                    if (pkt.dsort == 2)
                        mSrv.getmAPP().setLoginDevState((byte) 1);
                    else

                        mSrv.getmAPP().setLoginState((byte) 1);

                    jsonProtData.put("SNUM", loginMsg.getSnum());
                    jsonProtData.put("SVER", loginMsg.getSver());
                    jsonProtData.put("USER", loginMsg.getUsername());
                    Log.i("username",loginMsg.getUsername());
                } else if (pkt.type == 3) { //密码验证
                    if (pkt.dsort == 2)
                        mSrv.getmAPP().setLoginDevState((byte) 2);
                    else
                        mSrv.getmAPP().setLoginState((byte) 2);
                    if(rand != null) {
                        jsonProtData.put("CODE", enCode(loginMsg.getPassword(), rand));
                    }else{
                        jsonProtData.put("CODE", enCode(loginMsg.getPassword(), "123"));
                    }
                    jsonProtData.put("USER", loginMsg.getUsername());
                } else if (pkt.type == 5) { //登出报文
                    jsonProtData.put("USER", loginMsg.getUsername());
                    if (pkt.dsort == 2) {
                        jsonProtData.put("LINK", commInfo.linkDevNum);
                    } else {
                        jsonProtData.put("LINK", commInfo.linkNum);
                    }
                }
                jsonProt.put("DATA", jsonProtData);//再将这个json格式的放到最终的json对象中。
                pkt.data = jsonProt.toString();
                Log.i("login Send:", jsonProt.toString());

            } catch (JSONException e) {
                e.printStackTrace();
                ret = false;
            }
        }else
            pkt.count = 0; //保活报文不需要重发。

        if(ret){ //进行网络发送，并根据协议要求是否超时重发。
            if(pkt.type == 5){//对于登出处理，成功发送完直接通知界面
                mSrv.setRet(StaticClass.MSG_LOGOUT,pkt,null,0,null);
            }
        }
        return ret;
    }

    //将msg用password进行异或运算，结果进行base64编码后返回。
    public String enCode(String password, String msg){
        String ret;
        byte[] passBytes = password.getBytes();
        byte[] msgBytes = msg.getBytes();
        byte[] bytes = new byte[msgBytes.length];

        //加密过程
        if (passBytes.length <= msgBytes.length){
            int j=0;
            for (int i = 0; i<msgBytes.length; i++){
                bytes[i] = (byte) (msgBytes[i]^passBytes[j++]);
                if(j == passBytes.length)
                    j=0;
            }
        }else {
            for (int i = 0;i<msgBytes.length;i++){
                bytes[i] = (byte) (msgBytes[i]^passBytes[i]);
            }
        }

        //Base64编码
        ret = Base64.encodeToString(bytes,1);
        Log.i("test","加密后的字符串："+ret);
        return ret;
    }

    public  PacketMsg recvProc(PacketMsg pkt){
        Log.i("LOGIN Parse:",pkt.data);
        if(pkt.type > 6) {//表示保活报文,直接返回
            return null;
        }

        int ret;
        String info;
        int link = 0;
        PacketMsg nextPkt = null;
        CommInfo commInfo = mSrv.getCommInfo();

        //解析登录协议数据区选项，只有登陆协议有数据
        try {
            JSONObject jsonMsg = new JSONObject(pkt.data);//创建json格式的数据
            ret = jsonMsg.getInt("RET");
            if(ret == 0)
                link = jsonMsg.getInt("LINK");
            info = jsonMsg.getString("INFO");
            Log.d("aaa","info ="+info);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        //对结果进行分析和处理
        if(ret == 0) { //表示正确返回了
            if(commInfo.sid != 0) {
                commInfo.sid = Integer.parseInt(info); //登记其id
            }

            if(pkt.sort == 2) { //来自设备的报文
                Log.d("asd","pkt.sort = "+pkt.sort);
                mSrv.getmAPP().setLoginDevState((byte) 3);
                commInfo.devAddr = pkt.address;
                commInfo.devPort = pkt.port;
                commInfo.linkDevNum = link;
            }
            else {
                mSrv.getmAPP().setLoginState((byte) 3);
                commInfo.linkNum = link;
            }

            //构造一个数据查询报文进行数据查询
            QueryMSG queryMSG = new QueryMSG();
            queryMSG.setType(StaticClass.MSG_QUERY);
            queryMSG.setCode((byte)0);
            queryMSG.setDsort(pkt.sort);

            nextPkt = new PacketMsg(null);
            nextPkt.cmd = 4;
            nextPkt.type =1;
            nextPkt.msg = queryMSG;
        }else if(ret == 1) { //表示需要认证
            if( pkt.type == 2 ){//构造认证报文返回
                nextPkt = new PacketMsg(null);
                nextPkt.cmd = 1;
                nextPkt.type = 3;
                nextPkt.msg = loginMsg;
                nextPkt.dsort = pkt.sort;
                nextPkt.address = pkt.address;
                nextPkt.port = pkt.port;
                rand = info;
            }
        }else if(ret == -1){  //如果出错,状态复位
            if(pkt.dsort == 3)
                mSrv.getmAPP().setLoginState((byte)0);
            else
                mSrv.getmAPP().setLoginDevState((byte)0);
        }else{
            Log.i("RecvProc:","收到错误的RET值");
        }

        mSrv.setRet(StaticClass.MSG_LOGIN,pkt, null,ret,info);
        return nextPkt;
    }

    //超时处理
    public void timeoutProc(PacketMsg pkt){
        if(pkt.dsort == 3)
            mSrv.getmAPP().setLoginState((byte)0);
        else
            mSrv.getmAPP().setLoginDevState((byte)0);

        mSrv.setRet(StaticClass.MSG_LOGIN, pkt, null,StaticClass.MSG_TIMEOUT,null);
    }
}
