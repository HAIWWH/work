package app.cddic.com.smarter.db.database;

import static app.cddic.com.smarter.db.database.DeviceDatabase.DeviceTable.*;

/**
 * Created by pantiy on 2017/8/4.
 * Copyright © 2016 All rights Reserved by Pantiy
 */

public class DeviceDatabase {

    public static final String TABLE_NAME = "device_msg";

    public static String getSql(){
        return "CREATE TABLE " + TABLE_NAME + "(" +
                UID + "," +
                DEVICE_NAME + " NOT NULL," +
                SERIAL + " VARCHAR(30)," +
                CODE + " VARCHAR(16)," +
                IP + " INTEGER," +
                ALIAS + " VARCHAR(20)," +
                USER + " VARCHAR(20)," +
                PASSWORD + " VARCHAR(20)," +
                POWER + " BYTE," +
                TYPE + " BYTE," +
                PLUGIN + " VARCHAR(20)," +
                DID + " INTEGER," +
                VERSION + " VARCHAR(10)," +
                TIME + " VARCHAR(30)," +
                LONGITUDE + " FLOAT," +
                LATITUDE + " FLOAT," +
                STATE + " BYTE)";
    }

    public static final class DeviceTable {

        public static final String UID = "uid";
        public static final String DEVICE_NAME = "deviceName";
        public static final String SERIAL = "serial";
        public static final String CODE = "code";
        public static final String IP = "ip";
        public static final String ALIAS = "alias";
        public static final String USER = "user";
        public static final String PASSWORD = "password";
        public static final String POWER = "power";
        public static final String TYPE = "type";
        public static final String PLUGIN = "plugin";
        public static final String DID = "did";
        public static final String VERSION = "version";
        public static final String TIME = "time";
        public static final String LONGITUDE = "longitude";
        public static final String LATITUDE = "latitude";
        public static final String STATE = "state";
    }
}
