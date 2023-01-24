package carsharing;

import java.sql.Connection;

public class Main {

    private static String databaseFileName = "carsharing";

    public static void main(String[] args) {
        if (args.length == 2) {
            databaseFileName = args[1];
        }
        //Create database with table name databaseFileName
        DataBase db = new DataBase(databaseFileName);
        //Create connection with db
        Connection conn = db.getConnection();
        //Start the main menu
        new Menu(conn).startMainMenu();
    }
}