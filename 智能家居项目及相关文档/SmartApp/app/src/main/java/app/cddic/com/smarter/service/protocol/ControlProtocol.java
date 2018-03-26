package app.cddic.com.smarter.service.protocol;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import app.cddic.com.smarter.entity.ControlMSG;
import app.cddic.com.smarter.entity.MsgObject;
import app.cddic.com.smarter.service.PacketMsg;
import app.cddic.com.smarter.service.SmartService;

/**
 * Created by yfs on 4/24 0024.
 */

public class ControlProtocol extends SmartProtocol {
    public ControlProtocol(SmartService Srv) {
        setSrv(Srv);
    }

    public PacketMsg createPkt(MsgObject msg){
        Log.i("ControlProtocol","createPkt");
        ControlMSG controlMSG = (ControlMSG) msg;
        PacketMsg pkt = new PacketMsg(null);

        if (controlMSG.getPort()!=0){  //发送给前端设备
            pkt.dsort = 2; //发给设备
            pkt.msg = controlMSG;
            pkt.address =mSrv.getmRetMsg().getFromIP();
            pkt.port = mSrv.getmRetMsg().getPktType();
        }else { //发送给web平台

        }

        pkt.msg = controlMSG;
        pkt.cmd = 3;
        pkt.type = 1;

        return pkt;
    }
    public boolean sendProc(PacketMsg pkt){
        Log.i("ControlProtocol","send");
        Boolean ret = true;
        ControlMSG controlMSG = (ControlMSG) pkt.msg;
        JSONObject jsonProtData = new JSONObject();
        JSONObject jsonProt = new JSONObject();
        try {
            jsonProtData.put("USER", controlMSG.getUsername());
            jsonProtData.put("INST", controlMSG.getInst());
            jsonProtData.put("PARA", controlMSG.getPara());
            jsonProtData.put("LINK", controlMSG.getLink());
            jsonProt.put("DATA",jsonProtData);
            pkt.data = jsonProt.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            ret = false;
        }

        return ret;
    }
    public PacketMsg recvProc(PacketMsg pkt){
        Log.i("ControlProtocol","pkt.data:"+pkt.data);
        JSONObject jsonMsg = new JSONObject();
        String info = null;
        try {
            int ret = jsonMsg.getInt("RET");
            if (ret == -1){
                Log.i("ControlProtocol","请求失败");
                Log.i("ControlProtocol","错误原因"+jsonMsg.getString("INFO"));
            }else{
                info = jsonMsg.getString("INFO");
                Log.i("ControlProtocol","请求成功"+info);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
    public void timeoutProc(PacketMsg pkt){

    }
}
