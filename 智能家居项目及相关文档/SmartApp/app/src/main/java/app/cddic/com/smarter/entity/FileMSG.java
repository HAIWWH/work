package app.cddic.com.smarter.entity;

import app.cddic.com.smarter.service.FileInfo;

/**
 * Created by yfs on 4/21 0021.
 */

public class FileMSG extends MsgObject {
    String url; //文件名保持不变
    byte sort; //文件类别 //0,app; 1,插件；2,消息附件；3，通知附件；4，设备文件：40图片；41，视频；42，日志；43，配置；44，其他
    int size;   //文件大小
    int from;  //起始字节
    int index; //传输方式
    int len;   //传输数量
    FileInfo fileInfo; //对应的文件下载信息
    int id; //对应消息编号或者设备id
    boolean isSrv;
    String devSerial; //如果来自设备，设备的序列号

    String address;
    int port;
    public FileMSG() {
    }
    public FileMSG(String mUrl, byte mSort, int mId) {
        url = mUrl;
        sort = mSort;
        id = mId;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FileInfo getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(FileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }

    public byte getSort() {
        return sort;
    }

    public void setSort(byte sort) {
        this.sort = sort;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public boolean isSrv() {
        return isSrv;
    }

    public void setSrv(boolean srv) {
        isSrv = srv;
    }

    public String getSerial() {
        return devSerial;
    }

    public void setSerial(String dsort) {
        devSerial = dsort;
    }

    public String getFile() {
        return url;
    }

    public void setFile(String file) {
        url = file;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
