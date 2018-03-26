package app.cddic.com.smarter.db.database;

import static app.cddic.com.smarter.db.database.NoticeDatabase.NoticeTable.*;

/**
 * Created by 小帆哥 on 2017/8/8.
 */

public final class NoticeDatabase {

    public static final String TABLE_NAME = "notice_msg";

    public static String getSql(){
        return "CREATE TABLE " + TABLE_NAME + "(" +
                NUMBER + " INTEGER NOT NULL," +
                MID + " INTEGER NOT NULL," +
                SORT + " BYTE," +
                KIND + " BYTE NOT NULL," +
                TIME + " VARCHAR(30) NOT NULL," +
                MESSAGE + " VARCHAR(100)," +
                STATE + " BYTE," +
                REQUEST + " VARCHAR(20)," +
                POWER + " BYTE," +
                SERIAL + " VARCHAR(20) NOT NULL," +
                PASS + " VARCHAR(20))";
    }

    public static final class NoticeTable{

        public static final String NUMBER = "number";
        public static final String MID = "mid";
        public static final String SORT = "sort";
        public static final String KIND = "kind";
        public static final String TIME = "time";
        public static final String MESSAGE = "message";
        public static final String STATE = "state";
        public static final String REQUEST = "request";
        public static final String POWER = "power";
        public static final String SERIAL = "serial";
        public static final String PASS = "pass";
    }
}
