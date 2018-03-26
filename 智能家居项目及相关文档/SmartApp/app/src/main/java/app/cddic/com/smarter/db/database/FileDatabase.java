package app.cddic.com.smarter.db.database;


public class FileDatabase {
    public static final String TABLE_NAME = "document_msg";

    public static String getSql() {
        return "CREATE TABLE "+TABLE_NAME
                + "(ID INTEGER NOT NULL,"
                + " SERIAL VARCHAR(30) NOT NULL,"
                + " SORT INT NOT NULL,"
                + " TIME VARCHAR(30) NOT NULL,"
                + " URL VARCHAR(20))";

    }

    public static final class fileTable {
        public static final String ID = "id";
        public static final String SORT = "sort";
        public static final String TIME = "time";
        public static final String URL = "url";


}
}
