package app.cddic.com.smarter.service.protocol;

import app.cddic.com.smarter.entity.MsgObject;
import app.cddic.com.smarter.service.PacketMsg;
import app.cddic.com.smarter.service.SmartService;

/**
 * Created by yfs on 4/24 0024.
 */

public class EncodeProcotol extends SmartProtocol {
    public EncodeProcotol(SmartService Srv) {
        setSrv(Srv);
    }

    @Override
    public PacketMsg createPkt(MsgObject msg) {
        return null;
    }

    public boolean sendProc(PacketMsg packetMsg){
        return true;
    }
    public PacketMsg recvProc(PacketMsg packetMsg){
        return null;
    }
    public void timeoutProc(PacketMsg packetMsg){

    }
}
