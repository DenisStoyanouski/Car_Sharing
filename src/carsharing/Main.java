package carsharing;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    private static String databaseFileName = "carsharing";

    public static void main(String[] args) throws SQLException {
        if (args.length == 2) {
            databaseFileName = args[1];
        }
        DataBase db = new DataBase(databaseFileName);
        Connection conn = db.getConnection();
        new Menu(conn).start();
    }
}