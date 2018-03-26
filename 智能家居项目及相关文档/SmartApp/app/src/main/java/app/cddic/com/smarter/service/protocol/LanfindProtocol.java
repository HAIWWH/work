package app.cddic.com.smarter.service.protocol;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import app.cddic.com.smarter.entity.ConnectMSG;
import app.cddic.com.smarter.entity.MsgObject;
import app.cddic.com.smarter.service.PacketMsg;
import app.cddic.com.smarter.service.SmartService;
import app.cddic.com.smarter.utils.StaticClass;

/**
 * Created by yfs on 4/21 0021.
 */

public class LanfindProtocol extends SmartProtocol {
    public LanfindProtocol(SmartService Srv) {
        setSrv(Srv);
    }

    public PacketMsg createPkt(MsgObject msgObject){
        Log.i("test","LanfindProtocol");
        ConnectMSG msg = (ConnectMSG)msgObject;
        PacketMsg pkt = new PacketMsg(null);

        if (msg.getPort() != 0) { //不是发给服务器的
            pkt.dsort = 2; //发给设备
            pkt.msg = msg;
            pkt.address = "255.255.255.255";
            pkt.port = msg.getPort();
        }else{
            return null; //发给平台,是错误的
        }

        pkt.hopt = "support msg";
        pkt.cmd = 6;
        pkt.type = 1;
        pkt.msg = msgObject;

        return pkt;
    }
    public boolean sendProc(PacketMsg pkt){
        Log.i("test","LanfindSendProt");
        boolean ret = true;
        ConnectMSG msg = (ConnectMSG)pkt.msg;
        JSONObject jsonProt = new JSONObject();//创建json格式的数据
        JSONObject jsonProtData = new JSONObject();
        pkt.count = 3; //如果没返回，要求重发三次
        try {
            jsonProtData.put("USER", msg.getUsername());
            jsonProt.put("DATA", jsonProtData);//再将这个json格式的放到最终的json对象中。
            pkt.data = jsonProt.toString();
            Log.i("lanfind Send:", jsonProt.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            ret = false;
        }

        return ret;
    }

    public PacketMsg recvProc(PacketMsg pkt){
        Log.i("asd:",pkt.data);

        int ret = -1;
        String info= null;
        try {
            JSONObject jsonMsg = new JSONObject(pkt.data);//创建json格式的数据
            ret = jsonMsg.getInt("RET");
            info = jsonMsg.getString("INFO");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("asd","lanfindproc recvProc pkt.port = "+pkt.port);
        mSrv.setRet(StaticClass.MSG_LANFIND, pkt, null, ret,info);
        return null;
    }

    public void timeoutProc(PacketMsg packetMsg){
        mSrv.setRet(StaticClass.MSG_LANFIND, packetMsg, null,StaticClass.MSG_TIMEOUT,null);
    }
}
