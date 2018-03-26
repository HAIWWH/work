package app.cddic.com.smarter.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.cddic.com.smarter.entity.ConnectMSG;
import app.cddic.com.smarter.entity.MsgObject;
import app.cddic.com.smarter.entity.RegisterMSG;
import app.cddic.com.smarter.utils.StaticClass;


/**
 * 项目名：  SmartApp
 * 包名：    app.cddic.com.smarter.db
 * 文件名：  DBHelper
 * 创建者：
 * 创建时间： 2017/4/17 10:59
 * 描述：
 */


public class DBHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private static String DB_NAME = "smartapp.db"; //数据库名
    private static int version = 1; //版本号
    //表名
    private final String ACCOUNT = "account";
    private final String DEVICE = "device";
    private final String ALARM = "alarm";
    private final String DOCUMENT = "document";
    private final String RELATER = "relater";
    private final String CHAT = "chat";
    private final String NOTICE = "notice";
    private final String CONTACT = "contact";
    private final String SETTING = "setting";




    //自己定义的构造方法,调用父类的构造方法
    public DBHelper(Context mContext){
        super(mContext,DB_NAME,null,version);
        db = this.getWritableDatabase();
    }

    /**
     * 数据库创建时使用
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        //1用户账号表(Account)
        String account = "create table account"
                + "(username varchar(20) not null"
                + ",password varchar(20) "
                + ",uid integer"
                + ",phonenum varchar(20)"
                + ",sex byte"
                + ",time varchar(30)"
                + ",type byte"
                + ",email varchar(20)"
                + ",state byte"
                + ",did integer)";

        //2用户设备表信息记录表(Device)
        String device = "create table device"
                + "(uid integer not null"
                + ",devname varchar(20) not null"
                + ",serial varchar(30)"
                + ",code varchar(16)"
                + ",ipaddr integer"
                + ",alias varchar(20)"
                + ",user varchar(20)"
                + ",pass varchar(20)"
                + ",power byte"
                + ",type byte"
                + ",plugin varchar(20)"
                + ",did integer"
                + ",version varchar(10)"
                + ",time varchar(30)"
                + ",longitude float"
                + ",latitude float"
                + ",state byte)";

        //3设备附属文件表(document)
        String document = "create table document"
                + "(id integer not null"
                + ",serial varchar(30) not null"
                + ",sort byte not null"
                + ",time varchar(30) not null"
                + ",url varchar(20))";

        //4我的联系人信息表(contact)
        String contact = "create table contact"
                + "(username varchar(20) not null"
                + ",id integer not null"
                + ",contact varchar(20) not null"
                + ",type byte not null"
                + ",sex byte not null"
                + ",alias varchar(20)"
                + ",online byte"
                + ",settime varchar(30)"
                + ",group varchar(10))";

        //5用户设置表(setting)未完成
        String setting  = "create table setting"
                + "(username varchar(20) not null"
                + ",id integer not null"
                + ",sound byte)";

        //6报警信息表(alarm)
        String alarm = "create table alarm"
                + "(number integer not null"
                + ",id integer not null"
                + ",type byte not null"
                + ",level byte not null"
                + ",time varchar(30) not null"
                + ",url varchar(20)"
                + ",lurl varchar(20)"
                + ",state byte)";


        //7我的聊天消息表(chat)
        String chat = "create table chat"
                + "（number integer not null"
                + ",mid integer not null"
                + ",id integer not null"
                + ",type byte not null"
                + ",time varchar(30) not null"
                + ",content varchar(100) not null"
                + ",type integer"
                + ",color integer"
                + ",state byte)";

        //8我收到的通知信息表(notice)
        String notice = "create table notice"
                + "(number integer not null"
                + ",mid integer not null"
                + ",sort byte"
                + ",kind byte not null"
                + ",time varchar(30) not null"
                + ",message varchar(100)"
                + ",state byte"
                + ",request varchar(20)"
                + ",power byte"
                + ",serial varchar(20) not null"
                + ",pass varchar(20))";

        //9我收到的系统通告(info)
        String info  = "create table info"
                + "(number integer not null"
                + ",mid integer not null"
                + ",id integer not null"
                + ",url varchar(20)"
                + ",lurl varchar(20)"
                + ",time varchar(30) not null"
                + ",message varchar(100) not null"
                + ",title varchar(50)"
                + ",set varchar(10)"
                + ",state byte)";

        db.execSQL(account);
        db.execSQL(device);
        db.execSQL(document);
        db.execSQL(contact);
        db.execSQL(setting);
        db.execSQL(alarm);
        db.execSQL(chat);
        db.execSQL(notice);
        db.execSQL(info);

    }

    //封装增删查改的方法
    public void addData(MsgObject msgObject){
        int msgType = msgObject.getType();
        ContentValues content = new ContentValues();
        switch (msgType){
            //注册信息(用户个人信息)
            case StaticClass.MSG_REGISTER:
                RegisterMSG msg = (RegisterMSG) msgObject;
                content.put("username",msg.getUsername());
                content.put("password",msg.getPassword());
                content.put("uid",msg.getUid());
                content.put("sex",msg.getSex());
                content.put("time",msg.getTime());
                content.put("type",msg.getsType());
                content.put("email",msg.getEmail());
                content.put("state",msg.getState());
                content.put("did",msg.getDid());
                db.insert(ACCOUNT,null,content);
                Log.i("Dababase","添加数据成功");
                db.close();
                break;
            case 2:
                break;
            case 3:
                break;
        }

    }

    public List<MsgObject> findData(int mType){
        switch (mType){
            case 1:
                List<MsgObject> dataList = new ArrayList<>();
                Cursor cur = db.query(ACCOUNT,new String[]{"uid","username","password"},null,null,null,null,null);
                while (cur.moveToNext()) {
                    for (int i = 0; i < cur.getCount(); i++) {
                        cur.moveToPosition(i);
                        ConnectMSG connectMSG = new ConnectMSG();
                        connectMSG.setUsername(cur.getString(cur.getColumnIndex("username")));
                        connectMSG.setPassword(cur.getString(cur.getColumnIndex("password")));
                        dataList.add(connectMSG);
                    }
                }
                cur.close();
                db.close();
                return dataList;
            case 2:
                return null;
        }
        return null;
    }

    public Boolean updateData(MsgObject msgObject,String newpwd){
        int mType = msgObject.getType();
        Boolean result = false;
        ContentValues values = new ContentValues();
        switch (mType){
            //修改用户密码
            case 1:
               break;
            case 2:
                break;
            default:
                break;
        }
        return result;
    }

    public boolean deleteData(MsgObject msgObject){
        int mType = msgObject.getType();
        switch (mType){
            //删除联系人
            case 1:
                break;
            //删除我的收藏记录
            case 2:
                break;
        }
        return false;
    }


    /**
     * 更新时使用
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table account if exists");
        db.execSQL("drop table device if exists");
        db.execSQL("drop table alarm if exists");
        db.execSQL("drop table document if exists");
        db.execSQL("drop table relater if exists");
        db.execSQL("drop table chat if exists");
        db.execSQL("drop table notice if exists");
        db.execSQL("drop table info if exists");
        db.execSQL("drop table contact if exists");
        db.execSQL("drop table setting if exists");

        onCreate(db);
    }
}
