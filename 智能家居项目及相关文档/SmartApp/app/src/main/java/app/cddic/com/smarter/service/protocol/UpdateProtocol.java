package app.cddic.com.smarter.service.protocol;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import app.cddic.com.smarter.entity.FileMSG;
import app.cddic.com.smarter.entity.MsgObject;
import app.cddic.com.smarter.entity.UpdateMSG;
import app.cddic.com.smarter.entity.UpdateRetMSG;
import app.cddic.com.smarter.service.PacketMsg;
import app.cddic.com.smarter.service.SmartService;
import app.cddic.com.smarter.utils.StaticClass;

/**
 * Created by yfs on 4/24 0024.
 */

public class UpdateProtocol extends SmartProtocol {
    public UpdateProtocol(SmartService Srv) {
        setSrv(Srv);
    }

    public PacketMsg createPkt(MsgObject msgObject){
        PacketMsg pkt = new PacketMsg(null);

        pkt.dsort = 3; //发给服务器
        pkt.cmd = 11;
        pkt.type = 1;
        pkt.msg = msgObject;

        return pkt;
    }

    public boolean sendProc(PacketMsg pkt){
        boolean ret = true;
        UpdateMSG msg = (UpdateMSG)pkt.msg;
        JSONObject jsonProt = new JSONObject();//创建json格式的数据
        JSONObject jsonProtData = new JSONObject();
        pkt.count = 2; //要求重发二次

        try {
            jsonProtData.put("USER", mSrv.getmAPP().getUserName());
            jsonProtData.put("SERIAL", msg.getSerial());
            jsonProtData.put("VERSION",msg.getVersion());

            jsonProt.put("DATA", jsonProtData);//再将这个json格式的放到最终的json对象中。
            pkt.data = jsonProt.toString();
            Log.i("update Send:", jsonProt.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            ret = false;
        }

        return ret;
    }

    public PacketMsg recvProc(PacketMsg pkt){
        Log.i("Update Parse:",pkt.data);
        int ret = -1;
        String info= null;
        PacketMsg nextPkt = null;

        try {
            JSONObject jsonMsg = new JSONObject(pkt.data);//创建json格式的数据
            ret = jsonMsg.getInt("RET");
            info = jsonMsg.getString("INFO");
            if(ret > 0){ //有新版本
                JSONObject update = new JSONObject(info);
                UpdateMSG msg=(UpdateMSG)pkt.msg;
                UpdateRetMSG retMsg = new UpdateRetMSG();
                retMsg.setVersion(update.getString("VERSION"));
                retMsg.setUrl(update.getString("URL"));

                if(msg.isManual()){ //是人工发起的，向界面发通知
                    mSrv.setRet(StaticClass.MSG_UPDATE, pkt, retMsg, ret,info);
                }else if(commInfo.isUpdate){ //自动发起的，构造文件下载报文进行文件下载处理
                    //构造一个数据查询报文进行数据查询
                    FileMSG fileMSG = new FileMSG();
                    fileMSG.setType(StaticClass.MSG_FILE);
                    fileMSG.setFile(retMsg.getUrl());
                    fileMSG.setSrv(true);

                    nextPkt = new PacketMsg(null);
                    nextPkt.cmd = 7;
                    nextPkt.type = 1;
                    nextPkt.msg = fileMSG;
                    nextPkt.dsort = 3;
                }
            }else {
                mSrv.setRet(StaticClass.MSG_UPDATE, pkt, null, ret,info);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return nextPkt;
    }

    public void timeoutProc(PacketMsg pkt){
        mSrv.setRet(StaticClass.MSG_UPDATE, pkt, null,StaticClass.MSG_TIMEOUT,null);
    }
}
