package app.cddic.com.smarter.service.protocol;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import app.cddic.com.smarter.entity.FileMSG;
import app.cddic.com.smarter.entity.MsgObject;
import app.cddic.com.smarter.service.PacketMsg;
import app.cddic.com.smarter.service.SmartService;
import app.cddic.com.smarter.utils.StaticClass;

/**
 * Created by yfs on 4/24 0024.
 */

public class MyFileProtocol extends SmartProtocol {
    FileMSG mFileMSG;
    public MyFileProtocol(SmartService Srv) {
        setSrv(Srv);
    }

    public PacketMsg createPkt(MsgObject msg){
        mFileMSG = (FileMSG)msg;

        PacketMsg pkt = new PacketMsg(null);
        pkt.cmd = 7;
        pkt.address = ((FileMSG) msg).getAddress();
        pkt.port = ((FileMSG) msg).getPort();
        if(mFileMSG.getSize() == 0)
            pkt.type = 4;
        else if(mFileMSG.getSize() != mFileMSG.getLen())
            pkt.type = 1;
        else
            pkt.type = 3; //size ！=0 且 size == len

        pkt.msg = mFileMSG;
        if(mFileMSG.isSrv())
            pkt.dsort = 3;
        else
            pkt.dsort = 2;
        return pkt;
    }

    public void timeoutProc(PacketMsg pkt){}

    public boolean sendProc(PacketMsg pkt){
        boolean ret = true;
        mFileMSG = (FileMSG)pkt.msg;
        JSONObject jsonProt = new JSONObject();//创建json格式的数据
        JSONObject jsonProtData = new JSONObject();

        if(pkt.type == 1 || pkt.type == 4)
            pkt.count = 3; //如果是请求包没返回，要求重发三次
        else
            pkt.count = 0;

        try {
            if(pkt.type == 1) {
                jsonProtData.put("CLASS", mFileMSG.getSort());
                jsonProtData.put("FILE", mFileMSG.getFile());
                jsonProtData.put("FROM", mFileMSG.getFrom());

                if(mFileMSG.getLen()>3072)
                    jsonProtData.put("SIZE",3072);//一次不超过3KB
                else
                    jsonProtData.put("SIZE", mFileMSG.getLen());

            }else if(pkt.type == 3) {
                jsonProtData.put("FILE", mFileMSG.getFile());
            }else if(pkt.type == 3) {
                jsonProtData.put("FILE", mFileMSG.getFile());
                jsonProtData.put("CLASS", mFileMSG.getSort());
            }

            if (pkt.sort == 3) { //访问服务器的消息
                jsonProtData.put("LINK", commInfo.linkNum);
            } else {
                jsonProtData.put("LINK", commInfo.linkDevNum);
            }

            jsonProt.put("DATA", jsonProtData);//再将这个json格式的放到最终的json对象中。
            pkt.data = jsonProt.toString();

      //      mFileMSG.getFileInfo().lastTime = System.currentTimeMillis();

            Log.i("file Send:", jsonProt.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            ret = false;
        }
        return ret;
    }
    public PacketMsg recvProc(PacketMsg pkt){
        Log.i("file Parse:",pkt.data);

        int ret = 0;
        int left = -1;
        int total = 0;
        String err = null;
        PacketMsg nextPkt = null;

        try {
            JSONObject jsonMsg = new JSONObject(pkt.data);//创建json格式的数据
            ret = jsonMsg.getInt("RET");
            if(pkt.type == 2){ //获得文件内容处理
                if(ret < 0){
                    err = jsonMsg.getString("INFO");
                }else{
                    total = jsonMsg.getInt("INFO");
                    int len = jsonMsg.getInt("LEN");
                    if(ret == 1){
                        String content = jsonMsg.getString("CONTENT");
                        if(content.equals("OK")){
                            if(mFileMSG.getFileInfo().sized + len == mFileMSG.getFrom()+total){
                                left = mSrv.updateFile(mFileMSG, pkt,len);
                            }
                        }else{
                            ret = -1;
                        }
                    }
                }
            }else if (pkt.type == 5){ //查询文件信息处理
                if(ret == 0) {
                    JSONObject info = jsonMsg.getJSONObject("INFO");
                    String file = info.getString("FILE");
                    if(file.equals(mFileMSG.getFile())){
                        mFileMSG.setSize(info.getInt("SIZE"));
                        mFileMSG.getFileInfo().size = mFileMSG.getSize();
                        mFileMSG.getFileInfo().MD5 = info.getString("MD5");
                    }
                }else{
                    err = jsonMsg.getString("INFO");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            ret = -1;
            err = "packet error";
        }

        if(ret < 0) { //如果有错误，进行错误处理
            mSrv.setRet(StaticClass.MSG_FILE, pkt, null, ret,err);
            commInfo.fileInfoList.remove(mFileMSG.getFileInfo());
        }else{ //检查是否需要发送停止传输报文或者新的请求报文
            nextPkt = new PacketMsg(null); //构建应答报文
            if(left == 0){ //已经传输完成
                mFileMSG.setFrom(mFileMSG.getFrom()+total);
                nextPkt.cmd = 5;
                nextPkt.type = 3;
                nextPkt.msg = mFileMSG;
                mSrv.finishFile(mFileMSG);
            }else{
                if(mFileMSG.getFileInfo().sized - mFileMSG.getFrom() == total && //收到一次请求所有数据
                        (total == mFileMSG.getLen()|| total == 3072)){
                    mFileMSG.setFrom(mFileMSG.getFrom()+total);
                }else if(mFileMSG.getFileInfo().sized - mFileMSG.getFrom() < total){ //有丢包时
                    mFileMSG.setFrom(mFileMSG.getFileInfo().sized);
                }
                nextPkt.cmd = 5;
                nextPkt.type = 1;
                nextPkt.msg = mFileMSG;
            }
            mSrv.setRet(StaticClass.MSG_FILE, pkt, null, mFileMSG.getSize()-left,null);
        }

        return nextPkt;
    }
}
