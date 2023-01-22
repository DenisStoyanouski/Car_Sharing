package carsharing;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyDaoImpl implements CompanyDao {

    Connection conn;

    public CompanyDaoImpl(Connection conn){
        this.conn = conn;
    }

    //retrieve list of Companies from the database
    @Override
    public ArrayList<String> getAll(String tableName) {
        ArrayList<String> select = new ArrayList<>();
        System.out.println();
        String query = String.format("SELECT * FROM %s", tableName);
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                select.add(name);
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return select;
    }

    public ArrayList<String> getAll(String tableName, int company_id) {
        ArrayList<String> select = new ArrayList<>();
        System.out.println();
        String query = String.format("SELECT * FROM %s where company_id = %d", tableName, company_id);
        try (Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(true);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                select.add(name);
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return select;
    }

    @Override
    public int getOne(String tableName, String name) {
        int id = 0;
        String query = String.format("SELECT * FROM %s where name = %s", tableName, name);
        try (Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(true);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt(id);
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    @Override
    public void update(String tableName) {

    }

    @Override
    public void delete(String tableName) {

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
