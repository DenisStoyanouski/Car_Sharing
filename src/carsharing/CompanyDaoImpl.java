package carsharing;

import java.sql.*;
import java.util.ArrayList;


public class CompanyDaoImpl implements CompanyDao {

    Connection conn;
    ArrayList<Company> companies;

    public CompanyDaoImpl(Connection conn) {
        this.conn = conn;
        this.companies = new ArrayList<>();
        createTable();
    }

    private void createTable() {
        try (Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(true);
            String createCompanyTable = "CREATE TABLE IF NOT EXISTS COMPANY  " +
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
    }

    //retrieve list of Companies from the database
    @Override
    public ArrayList<Company> getAllCompanies() {
        companies.clear();
        System.out.println();
        String query = "SELECT * FROM COMPANY";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                companies.add(new Company(name, id));
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return companies;
    }

    @Override
    public Company getCompany(int rollNo) {
        Company company = null;
        String query = String.format("SELECT * FROM COMPANY where id = %s", rollNo);
        try (Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(true);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                company = new Company(name, id);
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return company;
    }

    @Override
    public void updateCompany(Company company) {

    }

    @Override
    public void deleteCompany(Company Company) {

    }

    @Override
    public void addCompany(String nameCompany) {
        try (Statement stmt = conn.createStatement()) {
            String query = String.format("INSERT INTO Company (name) " +
                    "VALUES ('%s')", nameCompany);
            if (stmt.executeUpdate(query) != 0) {
                System.out.printf("The %s was created!%n%n", "company");
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showCompanies() {
        getAllCompanies();
        System.out.println("Choose the company:");
        companies.forEach(x -> System.out.println((companies.indexOf(x) + 1) + ". " + x.getName()));
        System.out.println("0. Back");
        System.out.println();
    }
}
