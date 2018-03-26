package app.cddic.com.smarter.entity;

import java.io.Serializable;

/**
 * Created by yfs on 4/14 0014.
 */

public class RetMSG extends MsgObject implements Serializable {
    private byte ret;
    private String fromIP;
    private int fromPort;
    private byte cmd;
    private byte pktType;
    private byte sort;
    private String info;
    private MsgObject object; //附带信息，与具体协议有关

    public MsgObject getObject() {
        return object;
    }

    public void setObject(MsgObject object) {
        this.object = object;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public byte getSort() {
        return sort;
    }

    public void setSort(byte sort) {
        this.sort = sort;
    }

    public byte getCmd() {
        return cmd;
    }

    public void setCmd(byte cmd) {
        this.cmd = cmd;
    }

    public byte getPktType() {
        return pktType;
    }

    public void setPktType(byte type) {
        pktType = type;
    }

    public byte getState() {
        return ret;
    }

    public void setState(byte state) {
        this.ret = state;
    }

    public String getFromIP() {
        return fromIP;
    }

    public void setFromIP(String fromIP) {
        this.fromIP = fromIP;
    }

    public int getPort() {
        return fromPort;
    }

    public void setPort(int fromPort) {
        this.fromPort = fromPort;
    }
}
