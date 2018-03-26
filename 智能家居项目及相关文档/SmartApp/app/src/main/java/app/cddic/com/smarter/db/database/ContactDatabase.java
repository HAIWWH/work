package app.cddic.com.smarter.db.database;

import static app.cddic.com.smarter.db.database.ContactDatabase.ContactTable.*;

/**
 * Created by Hai on 2017/8/8.
 */

public class ContactDatabase {
    public static final String TABLE_NAME = "contact_msg";

    public static String getSql() {
        return "CREATE TABLE " + TABLE_NAME + "(" +
                USERNAME +" VARCHAR(20) NOT NULL,"+
                ID + " INTEGER NOT NULL," +
                CONTACT + " VARCHAR(20)," +
                TYPE + " VARCHAR(20) NOT NULL," +
                STATE + " BYTE NOT NULL," +
                SEX + " BYTE NOT NULL," +
                ALIAS + " VARCHAR(20)," +
                GROUPS + " VARCHAR(10))";
    }
    public static final class ContactTable{
        public static final String USERNAME = "userName";
        public static final String ID = "id";
        public static final String CONTACT = "contact";
        public static final String TYPE = "type";
        public static final String STATE = "state";
        public static final String SEX = "sex";
        public static final String ALIAS = "alias";
        public static final String GROUPS = "groups";
    }
}
