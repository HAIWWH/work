package app.cddic.com.smarter.service.protocol;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import app.cddic.com.smarter.entity.GetMSG;
import app.cddic.com.smarter.entity.MsgObject;
import app.cddic.com.smarter.entity.QueryMSG;
import app.cddic.com.smarter.service.PacketMsg;
import app.cddic.com.smarter.service.SmartService;
import app.cddic.com.smarter.utils.StaticClass;

/**
 * Created by yfs on 4/24 0024.
 */

public class QueryProtocol extends SmartProtocol {
    public QueryProtocol(SmartService Srv) {
        setSrv(Srv);
    }

    public PacketMsg createPkt(MsgObject msg){
        PacketMsg pkt = new PacketMsg(null);

        pkt.cmd = 4;
        pkt.type = 1;
        pkt.msg = msg;

        return pkt;
    }

    public boolean sendProc(PacketMsg pkt){
        boolean ret = true;
        QueryMSG msg = (QueryMSG)pkt.msg;
        JSONObject jsonProt = new JSONObject();//创建json格式的数据
        JSONObject jsonProtData = new JSONObject();

        pkt.count = 3; //要求重发三次
        pkt.dsort = msg.getDsort();

        try {
            jsonProtData.put("USER", mSrv.getmAPP().getUserName());
            jsonProtData.put("CODE", msg.getCode());
            if(msg.getPara() != null)
                jsonProtData.put("PARA",msg.getPara());

            jsonProt.put("DATA", jsonProtData);//再将这个json格式的放到最终的json对象中。
            pkt.data = jsonProt.toString();
            Log.i("query Send:", jsonProt.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            ret = false;
        }

        return ret;
    }

    public PacketMsg recvProc(PacketMsg pkt){
        Log.i("query Parse:",pkt.data);
        int ret = -1;
        String info= null;
        PacketMsg nextPkt = null;

        try {
            JSONObject jsonMsg = new JSONObject(pkt.data);//创建json格式的数据
            ret = jsonMsg.getInt("RET");
            info = jsonMsg.getString("INFO");
            if(ret == 0 && info.equals("16")){ //查询ID
                commInfo.sid = jsonMsg.getInt("ID");
            }else if(ret > 0){ //有新消息，启动请求消息报文
                //构造一个数据查询报文进行数据查询
                GetMSG getMSG = new GetMSG();
                getMSG.setType(StaticClass.MSG_GET);
                getMSG.setCla((byte)0);
                getMSG.setNum(0);
                getMSG.setDsort(pkt.sort);

                nextPkt = new PacketMsg(null);
                nextPkt.cmd = 5;
                nextPkt.type =3;
                nextPkt.msg = getMSG;
            }else{ //错误，发送一个报告
                mSrv.setRet(StaticClass.MSG_QUERY, pkt, null, ret,info);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return nextPkt;
    }

    public void timeoutProc(PacketMsg pkt){
        mSrv.setRet(StaticClass.MSG_QUERY, pkt, null,StaticClass.MSG_TIMEOUT,null);
    }
}
