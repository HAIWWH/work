package app.cddic.com.smarter.entity;

/**
 * Created by yfs on 5/27 0027.
 */

public class AlarmMSG extends MsgObject {
    int devid;
    byte sort;
    byte level;
    long time;
    String url;
    String lurl;
    byte state;


    public byte getSort() {
        return sort;
    }

    public void setSort(byte sort) {
        this.sort = sort;
    }

    public int getDevid() {
        return devid;
    }

    public void setDevid(int devid) {
        this.devid = devid;
    }

    public byte getLevel() {
        return level;
    }

    public void setLevel(byte level) {
        this.level = level;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLurl() {
        return lurl;
    }

    public void setLurl(String lurl) {
        this.lurl = lurl;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }
}
