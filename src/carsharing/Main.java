package carsharing;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    private static String databaseFileName = "carsharing";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        if (args.length == 2) {
            databaseFileName = args[1];
        }
        DataBase.setName(databaseFileName);
        Connection conn = DataBase.getConnection();
        DataBase.createTable();
        Menu.start(conn);
    }
}