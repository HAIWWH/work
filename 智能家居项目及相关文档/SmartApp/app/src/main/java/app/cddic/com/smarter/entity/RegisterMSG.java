package app.cddic.com.smarter.entity;

/**
 * 项目名：  SmartApp
 * 包名：    app.cddic.com.smarter.entity
 * 文件名：  RegisterMSG
 * 创建者：
 * 创建时间： 2017/4/27 15:48
 * 描述：
 */

public class RegisterMSG extends MsgObject {
    private String username;
    private String password;
    private String nickName;
    private int uid;
    private String phoneNum;
    private byte sex;
    private String time;
    private byte sType; //用户类型
    private String email;
    private byte state; //用户登录状态
    private int did;  //登录设备ID

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getId() {
        return uid;
    }

    public void setId(int uid) {
        this.uid = uid;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public byte getsType() {
        return sType;
    }

    public void setsType(byte sType) {
        this.sType = sType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }
}
