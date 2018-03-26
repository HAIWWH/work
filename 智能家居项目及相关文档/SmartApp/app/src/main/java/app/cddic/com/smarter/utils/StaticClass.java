package app.cddic.com.smarter.utils;

/**
 * 项目名：  SmartApp
 * 包名：    app.cddic.com.smarter.utils
 * 文件名：  StaticClass
 * 创建者：  zhl
 * 创建时间： 2017/3/5 11:23
 * 描述：静态类   数据/常量
 */

public class StaticClass {

    public static final String PATH = "SmartAPP.db";
    public static final int MSG_SPLASH = 1101;//闪屏页发送的消息
    public static final String SPLASH_IS_FIRST = "isFirst";    //判断程序是否第一次进入
    public static final String SERVICE_NAME = "SmartService";    //service类名
    public static final String ACTION = "ActivityMsg.NOTIFY";    //广播action

    public static final int MSG_TIMEOUT=-10; //超时
    public static final int MSG_TEST=10;//连通测试
    public static final int MSG_LOGIN =11;//登录信息
    public static final int MSG_LOGOUT=12;//登出信息
    public static final int MSG_REGISTER=13;//注册
    public static final int MSG_REGMOD=14;//修改注册信息
    public static final int MSG_REGOUT=15;//注销信息
    public static final int MSG_CONTROL=16;//控制信息
    public static final int MSG_ADDREL=17;//添加关联人
    public static final int MSG_DELREL=18;//删除关联人
    public static final int MSG_GETREL=19;//获取关联人
    public static final int MSG_MODREL=20;//添加关联人
    public static final int MSG_ADDCON=21;//添加联系人
    public static final int MSG_DELCON=22;//删除联系人
    public static final int MSG_GETCON=23;//获取联系人
    public static final int MSG_MODCON=24;//添加联系人
    public static final int MSG_EXCEP=25;    //网络变化通知信息
    public static final int MSG_ENCODE=26;    //加密处理
    public static final int MSG_KEEPLIVE=27;    //保活或者超时通知
    public static final int MSG_DOCUMENT=28;
    public static final int MSG_ACCOUNT=29; //账号信息
    public static final int MSG_DEVICE = 30;//设备信息
    public static final int MSG_INFO = 31;
    public static final int MSG_NOTICE = 32;//通知信息
    public static final int MSG_CONTACT = 33;
    public static final int MSG_SETTING = 34;
    public static final int MSG_CHAT = 35;//聊天消息
    public static final int MSG_DEVICE_ALARM = 36; //设备警报消息
    public static final int MSG_CONTACT_DETAIL = 37; //联系人详情
    public static final int MSG_PLUGIN_MANAGE = 38; //插件管理信息
    public static final int MSG_NEW_FRIEND = 39; //新朋友信息
    public static final int MSG_MY_COLLECTION = 40; //我的收藏信息
    public static final int MSG_MY_INFORMATION = 41; //我的资料信息
    public static final int MSG_DEVICE_CONTACT = 42; //设备关联人信息
    public static final int MSG_GROUP = 43; //联系人分组信息
    public static final int MSG_VIDEO = 44; //视频信息
    public static final int MSG_DEVICE_LOGIN = 45; //设备登录信息
    public static final int MSG_DEVICE_MANAGE = 46; //设备管理信息

    public static final int MSG_EXIT = 0; //退出信息
    public static final int MSG_QUERY=1;    //查询信息
    public static final int MSG_GET=2;      //请求信息
    public static final int MSG_LANFIND=3;  //局域网发现信息
    public static final int MSG_FILE=4;     //文件下载通知
    public static final int MSG_ACKREL=5; //关联通告确认
    public static final int MSG_ACKCON=6; //联系通告确认
    public static final int MSG_UPDATE=7; //更新通知


}
