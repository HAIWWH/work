package app.cddic.com.smarter.db.database;


import static app.cddic.com.smarter.db.database.SettingDatabase.SettingTable.*;


/**
 * Created by Hai on 2017/8/8.
 */

public class SettingDatabase {
    public static final String TABLE_NAME = "setting_msg";

    public static String getSql(){
        return " CREATE TABLE " + TABLE_NAME + "(" +
                USER_NAME + " VARCHAR(20) NOT NULL,"+
                ID + " INTEGER ,"+
                SOUNT + " BYTE)";

    }

    public static final class SettingTable{
        public static final String USER_NAME = "uaerName";
        public static final String ID = "id";
        public static final String SOUNT = "sount";

    }
}
