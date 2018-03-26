package app.cddic.com.smarter.db.database;

import static app.cddic.com.smarter.db.database.AlarmDataBase.AlarmTable.ID;
import static app.cddic.com.smarter.db.database.AlarmDataBase.AlarmTable.LEVEL;
import static app.cddic.com.smarter.db.database.AlarmDataBase.AlarmTable.LURL;
import static app.cddic.com.smarter.db.database.AlarmDataBase.AlarmTable.NUMBER;
import static app.cddic.com.smarter.db.database.AlarmDataBase.AlarmTable.STATE;
import static app.cddic.com.smarter.db.database.AlarmDataBase.AlarmTable.TIME;
import static app.cddic.com.smarter.db.database.AlarmDataBase.AlarmTable.TYPE;
import static app.cddic.com.smarter.db.database.AlarmDataBase.AlarmTable.URL;

/**
 * Created by VileWind on 2017/8/8 0008.
 */

public class AlarmDataBase {
    public static final String TABLE_NAME="alarm_msg";

    public static String getSql(){
        return " CREATE TABLE "+ TABLE_NAME + "("+
                NUMBER + " integer ,"+
                ID +" interger not null,"+
                TYPE +" byte not null,"+
                LEVEL +" byte not null,"+
                TIME +" varchar(30) not null,"+
                URL +" varchar(20),"+
                LURL+" varchar(20),"+
                STATE+" byte)";
    }
    public static final class AlarmTable{
        public static final String Name = "name";
        public static final String NUMBER = "number";
        public static final String ID = "id";
        public static final String TYPE="type";
        public static final String LEVEL = "level";
        public static final String TIME="time";
        public static final String URL="url";
        public static final String LURL="lurl";
        public static final String STATE="state";
    }
}
