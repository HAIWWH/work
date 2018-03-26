package app.cddic.com.smarter.entity;

/**
 * Created by yfs on 5/26 0026.
 */

public class GetMSG extends MsgObject {
    byte cla = 0;
    int num = 0;
    byte dsort = 3;
    int ret;
    String err;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public byte getDsort() {
        return dsort;
    }

    public void setDsort(byte dsort) {
        this.dsort = dsort;
    }

    public byte getCla() {
        return cla;
    }

    public void setCla(byte cla) {
        this.cla = cla;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
