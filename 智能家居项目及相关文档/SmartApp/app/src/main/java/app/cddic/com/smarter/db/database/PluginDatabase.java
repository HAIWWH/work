package app.cddic.com.smarter.db.database;


import static app.cddic.com.smarter.db.database.PluginDatabase.PlaginTable.DEVICENAME;
import static app.cddic.com.smarter.db.database.PluginDatabase.PlaginTable.PLUGINNAME;

/**
 * Created by asus on 2017/9/14.
 */

public class PluginDatabase {

    public static final String TABLE_NAME = "plugin_msg";

    public static String getSql(){
        return "CREATE TABLE " + TABLE_NAME + "(" +
                DEVICENAME + " VARCHAR(20)," +
                PLUGINNAME + " VARCHAR(20))";
    }

    public static final class PlaginTable{

        public static final String DEVICENAME = "devicename";
        public static final String PLUGINNAME = "pluginname";

    }
}
