package app.cddic.com.smarter.entity;

/**
 * Created by yfs on 4/21 0021.
 */

public class UpdateMSG extends MsgObject {
    byte Serial;
    String Version;
    boolean isManual; //是否人工启动更新的

    public void setSerial(byte  serial) {
        Serial = serial;
    }

    public byte getSerial() {
        return Serial;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public boolean isManual() {
        return isManual;
    }

    public void setManual(boolean manual) {
        isManual = manual;
    }
}
