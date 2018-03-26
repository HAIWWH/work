package app.cddic.com.smarter.db.database;

import static app.cddic.com.smarter.db.database.AccountDatabase.AccountTable.*;

/**
 * Created by pantiy on 2017/8/4.
 * Copyright © 2016 All rights Reserved by Pantiy
 */

public class AccountDatabase {

    public static final String TABLE_NAME = "account_msg";

    public static String getSql() {
        return "CREATE TABLE " + TABLE_NAME + "(" +
                USER_NAME +" VARCHAR(20) NOT NULL," +
                PASSWORD + " VARCHAR(20) NOT NULL," +
                UID + " INTEGER," +
                PHONENUM +" VARCHAR(20), "+
                SEX + " BYTE," +
                TIME + " VARCHAR(30)," +
                TYPE + " BYTE," +
                EMAIL + " VARCHAR(20)," +
                STATE + " BYTE," +
                DID + " INTEGER)";

    }

    public static final class AccountTable {
        public static final String USER_NAME = "userName";
        public static final String PASSWORD = "password";
        public static final String UID = "uid";
        public static final String PHONENUM = "phonenum";
        public static final String SEX = "sex";
        public static final String TIME = "time";
        public static final String TYPE = "type";
        public static final String EMAIL = "email";
        public static final String STATE = "state";
        public static final String DID = "did";
    }

}
