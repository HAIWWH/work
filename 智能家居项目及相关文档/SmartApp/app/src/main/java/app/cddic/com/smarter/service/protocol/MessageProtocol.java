package app.cddic.com.smarter.service.protocol;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import app.cddic.com.smarter.entity.AlarmMSG;
import app.cddic.com.smarter.entity.ChatMSG;
import app.cddic.com.smarter.entity.GetMSG;
import app.cddic.com.smarter.entity.InfoMSG;
import app.cddic.com.smarter.entity.MsgObject;
import app.cddic.com.smarter.entity.NoticeMSG;
import app.cddic.com.smarter.service.PacketMsg;
import app.cddic.com.smarter.service.SmartService;
import app.cddic.com.smarter.utils.StaticClass;

/**
 * Created by yfs on 4/24 0024.
 */

public class MessageProtocol extends SmartProtocol {
    int sign = 0;

    public MessageProtocol(SmartService Srv) {
        setSrv(Srv);
    }

    public PacketMsg createPkt(MsgObject msg){
        GetMSG getMSG = (GetMSG)msg;
        PacketMsg pkt = new PacketMsg(null);

        pkt.dsort = getMSG.getDsort(); //发给设备
        pkt.cmd = 5;
        pkt.type = 3;
        pkt.msg = msg;

        return pkt;
    }

    public boolean sendProc(PacketMsg pkt){
        boolean ret = true;
        GetMSG msg = (GetMSG)pkt.msg;
        JSONObject jsonProt = new JSONObject();//创建json格式的数据
        JSONObject jsonProtData = new JSONObject();

        pkt.count = 3; //如果没返回，要求重发三次

        try {
            if(pkt.type == 3) {
                jsonProtData.put("CLASS", msg.getCla());
                jsonProtData.put("NUM", msg.getNum());
                if (pkt.sort == 3) { //访问服务器的消息
                    jsonProtData.put("LINK", commInfo.linkNum);
                } else {
                    jsonProtData.put("LINK", commInfo.linkDevNum);
                }
            }else if(pkt.type == 5){
                jsonProtData.put("RET", msg.getRet());
                if(msg.getRet() < 0)
                    jsonProtData.put("INFO", msg.getErr());
                else
                    jsonProtData.put("INFO",sign);
            }

            jsonProt.put("DATA", jsonProtData);//再将这个json格式的放到最终的json对象中。
            pkt.data = jsonProt.toString();
            Log.i("message Send:", jsonProt.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            ret = false;
        }
        return ret;
    }

    public PacketMsg recvProc(PacketMsg pkt){
        Log.i("message Parse:",pkt.data);
        int num = 0;
        int sign2 = 0;
        JSONArray info= null;

        int ret = 0;
        String err = null;
        PacketMsg nextPkt = null;

        try {
            JSONObject jsonMsg = new JSONObject(pkt.data);//创建json格式的数据
            num = jsonMsg.getInt("NUM");
            info = jsonMsg.getJSONArray("INFO");
            sign2 = jsonMsg.getInt("SIGN");
        } catch (JSONException e) {
            e.printStackTrace();
            ret = -1;
            err = "packet error";
        }

        if(num>0) {
            if (sign == sign2) {//收到重复消息
                ret = 1;
            } else { //收到新的消息的处理,写入数据库
                sign = sign2;
                ret = parseInfo(pkt, info,num);
                if(ret < 0){
                    err="Message error";
                }
            }
        }else{
            ret = -1;
            err = "NO Message";
        }

        GetMSG getMSG = new GetMSG();
        getMSG.setType(StaticClass.MSG_GET);
        getMSG.setRet(ret);
        getMSG.setDsort(pkt.sort);
        getMSG.setErr(err);

        nextPkt = new PacketMsg(null); //构建应答报文
        nextPkt.cmd = 5;
        nextPkt.type = 5;
        nextPkt.msg = getMSG;

        return nextPkt;
    }

    int parseInfo(PacketMsg pkt, JSONArray info,int number){
        int ret = 0;
        int classes = 0;
        int user = 0;
        long date = 0;
        MsgObject retMsg = null;

        JSONObject inform = null;
        JSONObject content = null;

        for (int i = 0; i < number; i++) {
            try {
                inform = info.getJSONObject(i);
                classes = inform.getInt("CLASS");
                user = inform.getInt("USER");
                date = inform.getLong("DATE");
                content = inform.getJSONObject("CONTENT");
            }catch (JSONException e) {
                e.printStackTrace();
                ret =  -1;
                break;
            }

            try {
                switch (classes) {
                    case 1:
                        AlarmMSG alarm = new AlarmMSG();
                        alarm.setType(1);
                        alarm.setTime(date);
                        alarm.setState((byte)0);
                        alarm.setDevid(user);
                        alarm.setSort((byte)content.getInt("SORT"));
                        alarm.setLevel((byte)content.getInt("LEVEL"));
                        alarm.setUrl(content.getString("ATTACH"));
                        alarm.setLurl(null);

                        retMsg = alarm;
                        break;

                    case 2:
                        ChatMSG chat = new ChatMSG(null,null,null);
                        chat.setType(2);
                        chat.setState((byte)0);
                        chat.setTime(date);

                        chat.setPeerid(user);
                        chat.setIssend(false); //接收信息

                        chat.setMsg(content.getString("MSG"));
                        chat.setColor(content.getInt("COLOR"));
                        chat.setCtype(content.getInt("TYPE"));
                        retMsg = chat;
                        break;
                    case 4:
                        InfoMSG infoMsg = new InfoMSG();
                        infoMsg.setType(4);
                        infoMsg.setState((byte)0);
                        infoMsg.setTime(date);
                        infoMsg.setOfferid(user);

                        infoMsg.setMessage(content.getString("MSG"));
                        infoMsg.setTitle(content.getString("TITLE"));
                        infoMsg.setSet(content.getString("SET"));
                        infoMsg.setAttach(content.getString("ATTACH"));
                        retMsg = infoMsg;
                        break;
                    case 8:
                        NoticeMSG notice = new NoticeMSG();
                        notice.setType(8);
                        notice.setState((byte)0);
                        notice.setTime(date);
                        notice.setUserid(user);
                        notice.setSort((byte)0); //联系人通知类型

                        notice.setKind((byte)content.getInt("KIND"));
                        notice.setMessage(content.getString("MSG"));
                        retMsg = notice;
                        break;
                    case 16:
                        NoticeMSG notice2 = new NoticeMSG();
                        notice2.setType(16);
                        notice2.setState((byte)0);
                        notice2.setTime(date);
                        notice2.setUserid(user);
                        notice2.setSort((byte)1); //关联人通知类型

                        notice2.setKind((byte)content.getInt("KIND"));
                        notice2.setMessage(content.getString("MSG"));

                        notice2.setDev(content.getInt("PUSER"));
                        notice2.setRight((byte)content.getInt("RIGHT"));
                        notice2.setPass(content.getString("PASS"));

                        retMsg = notice2;
                        break;
                    default:
                        ret = -1;
                        break;
                }
            }catch (JSONException e) {
                e.printStackTrace();
                ret = -1;
                break;
            }
            mSrv.addData(retMsg);
        }

        if (ret > 0){
            mSrv.setRet(StaticClass.MSG_GET,pkt, null,ret,null);
        }

        return ret;
    }

    public void timeoutProc(PacketMsg pkt){
        mSrv.setRet(StaticClass.MSG_GET, pkt, null,StaticClass.MSG_TIMEOUT,null);
    }
}
