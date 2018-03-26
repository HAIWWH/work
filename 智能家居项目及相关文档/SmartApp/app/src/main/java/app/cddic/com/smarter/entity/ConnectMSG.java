package app.cddic.com.smarter.entity;

/**
 * 项目名：  SmartApp
 * 包名：    app.cddic.com.smarter.entity
 * 文件名：  ConnectMSG
 * 创建者：
 * 创建时间： 2017/3/26 19:28
 * 描述：
 */

public class ConnectMSG extends MsgObject {
    private String username;
    private String password;
    private String address;
    private String code;
    private String snum;
    private String sver;
    private int port;  //登录设备编号，登录平台时，此成员为0
    private int did;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSnum() {
        return snum;
    }

    public void setSnum(String num) {
        this.snum = num;
    }

    public String getSver() {
        return sver;
    }

    public void setSver(String ver) {
        this.sver = ver;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
