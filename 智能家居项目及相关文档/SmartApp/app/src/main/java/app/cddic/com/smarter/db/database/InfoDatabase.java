package app.cddic.com.smarter.db.database;

import static app.cddic.com.smarter.db.database.InfoDatabase.InfoTable.*;

/**
 * Created by 小帆哥 on 2017/8/8.
 */

public final class InfoDatabase {

    public static final String TABLE_NAME = "info_msg";

    public static String getSql(){
        return "CREATE TABLE " + TABLE_NAME + "(" +
                NUMBER + " INTEGER NOT NULL," +
                MID + " INTEGER NOT NULL," +
                ID + " INTEGER NOT NULL," +
                URI + " VARCHAR(20)," +
                LURI + " VARCHAR(20)," +
                TIME + " VARCHAR(30) NOT NULL," +
                MESSAGE + " VARCHAR(100) NOT NULL," +
                TITLE  + " VARCHAR(50)," +
                SET + " VARCHAR(20),"+
                STATE + " BYTE)";
    }

    public static final class InfoTable{

        public static final String Name = "name";
        public static final String NUMBER = "number";
        public static final String MID = "mid";
        public static final String ID = "id";
        public static final String URI = "uri";
        public static final String LURI = "luri";
        public static final String TIME = "time";
        public static final String MESSAGE = "message";
        public static final String TITLE = "title";
        public static final String STATE = "state";
        public static final String SET = "set";
    }
}
