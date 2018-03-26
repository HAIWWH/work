package app.cddic.com.smarter.service;

import java.io.File;
import java.io.FileOutputStream;

//文件下载的有关信息
public class FileInfo {
    String url;     //文件获取路径
    int id;     //对应消息编号
    public String MD5;     //文件校验
    public int size;       //文件总大小
    byte sort;       //0,app; 1,插件；2,消息附件；3，通知附件；
                      // 4，设备文件：0图片；1，视频；2，日志；3，配置；4，其他
    FileOutputStream out;      //文件写出对象
    long openTime;  //打开时间
    String path;     //文件本地存储路径和文件名
    public long lastTime;  //最近发送报文时间
    public int sized;      //文件写入大小

    boolean fromPlat; //文件来源
    String devSerial;   //如果来源于设备，设备的序列号
}
