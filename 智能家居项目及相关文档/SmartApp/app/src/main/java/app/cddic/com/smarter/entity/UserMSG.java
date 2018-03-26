package app.cddic.com.smarter.entity;

/**
 * 项目名：  SmartApp
 * 包名：    app.cddic.com.smarter.entity
 * 文件名：  UserMSG
 * 创建者：
 * 创建时间： 2017/3/26 19:28
 * 描述：
 */

public class UserMSG extends MsgObject {
    private String username;
    private String password;

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
