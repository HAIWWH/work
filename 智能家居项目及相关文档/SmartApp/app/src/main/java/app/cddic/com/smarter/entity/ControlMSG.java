package app.cddic.com.smarter.entity;

/**
 * 项目名：  SmartApp
 * 包名：    app.cddic.com.smarter.entity
 * 文件名：  ControlMSG
 * 创建者：
 * 创建时间： 2017/8/1 14:10
 * 描述：
 */

public class ControlMSG extends MsgObject {
    private String username;    //请求者账号
    private byte inst;  //指令代码（详见通信协议）
    private String para;    //命令参数
    private int link;   //增强安全性（可选）
    private int ret;    //返回代码
    private String info;    //结果信息(错误信息或者控制结果信息)
    private int port;    //

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte getInst() {
        return inst;
    }

    public void setInst(byte inst) {
        this.inst = inst;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public int getLink() {
        return link;
    }

    public void setLink(int link) {
        this.link = link;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
