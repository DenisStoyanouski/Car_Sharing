package carsharing;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

class DataBase {

    static Connection conn = null;
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private final String databaseFileName;
    private static final String DB_URL = "jdbc:h2:." + File.separator + "src" + File.separator + "carsharing" + File.separator + "db" + File.separator;


    //  Database credentials
    static final String USER = "";
    static final String PASS = "";

    DataBase(String databaseFileName) {
        this.databaseFileName = databaseFileName;
    }

    public Connection getConnection() {

        // STEP 1: Register JDBC driver
        try {
            Class.forName(JDBC_DRIVER);
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        //STEP 2: Open a connection
        System.out.println("Connecting to database...");
        try {
            conn = DriverManager.getConnection(DB_URL + databaseFileName);
            System.out.println("Connected to database!");

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        createTable();
        return conn;
    }

    public void createTable() {
        try (Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(true);
            String createCompanyTable =  "CREATE TABLE IF NOT EXISTS COMPANY  " +
                    "(id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    " name VARCHAR(255) UNIQUE NOT NULL)";
            if (stmt.executeUpdate(createCompanyTable) == 0) {
                conn.commit();
            } else {
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(true);
            String createTableCar =  "CREATE TABLE IF NOT EXISTS CAR  " +
                    "(id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(255) UNIQUE NOT NULL," +
                    "company_id INTEGER NOT NULL," +
                    "FOREIGN KEY (company_id) REFERENCES COMPANY(id))";
            if (stmt.executeUpdate(createTableCar) == 0) {
                conn.commit();
            } else {
                conn.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
