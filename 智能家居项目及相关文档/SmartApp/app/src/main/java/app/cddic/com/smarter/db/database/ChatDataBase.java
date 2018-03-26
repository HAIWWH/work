package app.cddic.com.smarter.db.database;

import static app.cddic.com.smarter.db.database.ChatDataBase.ChatTable.CColor;
import static app.cddic.com.smarter.db.database.ChatDataBase.ChatTable.CContent;
import static app.cddic.com.smarter.db.database.ChatDataBase.ChatTable.CId;
import static app.cddic.com.smarter.db.database.ChatDataBase.ChatTable.CMid;
import static app.cddic.com.smarter.db.database.ChatDataBase.ChatTable.CNum;
import static app.cddic.com.smarter.db.database.ChatDataBase.ChatTable.CState;
import static app.cddic.com.smarter.db.database.ChatDataBase.ChatTable.CTime;
import static app.cddic.com.smarter.db.database.ChatDataBase.ChatTable.CType;

/**
 * Created by VileWind on 2017/8/8 0008.
 */

public class ChatDataBase {
    public static final String TABLE_NAME="chat_msg";


    public static String  getSql(){
        return " CREATE TABLE " + TABLE_NAME + "(" +
                CNum + " INTEGER," +
                CMid + " INTEGER," +
                CId + " INTEGER ," +
                CType + " BYTE," +
                CTime + " VARCHAR(20)," +
                CContent + " VARCHAR(100) NOT NULL," +
                CColor + " INTEGER," +
                CState + " BYTE)";
    }

    public static final class ChatTable{
        public static final String CNum="number";

        public static final String CMid="mid";

        public static final String CId="id";

        public static final String CType="type";

        public static final String CTime="time";

        public static final String CColor="color";

        public static final String CState="state";

        public static final String CContent = "content";


    }
}
