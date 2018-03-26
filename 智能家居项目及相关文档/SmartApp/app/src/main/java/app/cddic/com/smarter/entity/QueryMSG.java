package app.cddic.com.smarter.entity;

/**
 * Created by yfs on 5/4 0004.
 */

public class QueryMSG extends MsgObject {
    String para = null;
    byte code = 0;
    byte dsort = 3; //查询对象

    public String getPara() {
        return para;
    }

    public void setPara(String user) {
        this.para = user;
    }

    public byte getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    public byte getDsort() {
        return dsort;
    }

    public void setDsort(byte dsort) {
        this.dsort = dsort;
    }
}
