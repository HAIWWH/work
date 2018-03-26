package app.cddic.com.smarter.service.protocol;

import app.cddic.com.smarter.entity.ConnectMSG;
import app.cddic.com.smarter.entity.MsgObject;
import app.cddic.com.smarter.service.PacketMsg;
import app.cddic.com.smarter.service.SmartService;
import app.cddic.com.smarter.utils.StaticClass;

/**
 * Created by yfs on 4/21 0021.
 */

public class TestProtocol extends SmartProtocol {
    public TestProtocol(SmartService Srv) {
        setSrv(Srv);
    }

    public void timeoutProc(PacketMsg pkt){mSrv.setRet(StaticClass.MSG_TEST, pkt, null,StaticClass.MSG_TIMEOUT,null); }

    public boolean sendProc(PacketMsg pkt){
        return true;
    }

    public PacketMsg recvProc(PacketMsg pkt){
        mSrv.setRet(StaticClass.MSG_TEST, pkt, null,0,null);return pkt;
    }
    public PacketMsg createPkt(MsgObject msgObject){
        ConnectMSG msg = (ConnectMSG)msgObject;
        PacketMsg pkt = new PacketMsg(null);

        if (msg.getPort() != 0) { //不是发给服务器的
            pkt.dsort = 2; //发给设备
            pkt.address = msg.getAddress();
            pkt.port = msg.getPort();
        }else{
            pkt.dsort = 3; //发给平台
        }

        pkt.cmd = 0;
        pkt.type = 0;
        pkt.msg = msgObject;

        return pkt;
    }
}
