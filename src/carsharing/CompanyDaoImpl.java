package carsharing;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class CompanyDaoImpl implements CompanyDao {

    Connection conn;

    public CompanyDaoImpl(Connection conn){
        this.conn = conn;
    }
    @Override
    public void delete(String tableName) {

        System.out.println("Company: Roll No " + ", deleted from database");
    }

    //retrieve list of Companies from the database
    @Override
    public Map<Integer, String> getAll(String tableName) {
        Map<Integer, String> select = new HashMap<>();
        System.out.println();
        String query = String.format("SELECT * FROM %s", tableName);
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                select.put(id, name);
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return select;
    }

    public Map<Integer, String> getAll(String tableName, int company_id) {
        Map<Integer, String> select = new HashMap<>();
        System.out.println();
        String query = String.format("SELECT * FROM %s where company_id = %d", tableName, company_id);
        try (Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(true);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                select.put(id, name);
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return select;
    }

    @Override
    public void getById(String tableName, int id) {
        System.out.println();
    }

    @Override
    public void update(String tableName) {

        System.out.println("Company: Roll No " + ", updated in the database");
    }

    @Override
    public void add(String name) {
        try (Statement stmt = conn.createStatement()) {
            String query = String.format("INSERT INTO Company (name) " +
                    "VALUES ('%s')", name);
            if (stmt.executeUpdate(query) != 0) {
                System.out.printf("The %s was created!%n%n", "company");
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void add(String carName, int company_id) {
        try (Statement stmt = conn.createStatement()) {
            String query = String.format("INSERT INTO car (name, company_id) " +
                    "VALUES ('%s', %d)", carName, company_id);
            if (stmt.executeUpdate(query) != 0) {
                System.out.printf("The car was added!%n%n");
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
