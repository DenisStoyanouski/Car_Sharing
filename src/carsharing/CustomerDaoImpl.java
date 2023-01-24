package carsharing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerDaoImpl implements CustomerDao{

    Connection conn;
    
    ArrayList<Customer> customers;
    
    CustomerDaoImpl(Connection conn) {
        this.conn = conn;
        this.customers = new ArrayList<>();
        createTable();
    }

    private void createTable() {
        try (Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(true);
            String createCompanyTable =  "CREATE TABLE IF NOT EXISTS CUSTOMER  " +
                    "(id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    " name VARCHAR(255) UNIQUE NOT NULL," +
                    " rented_car_id INTEGER DEFAULT NULL," +
                    " FOREIGN KEY (rented_car_id) REFERENCES CAR(id) " +
                    "ON DELETE SET NULL " +
                    "ON UPDATE CASCADE)";
            if (stmt.executeUpdate(createCompanyTable) == 0) {
                conn.commit();
            } else {
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Customer> getAllCustomer() {
        customers.clear();
        System.out.println();
        String query = "SELECT * FROM CUSTOMER";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("name");
                Customer customer = new Customer(name);
                customer.setRollNo(rs.getInt("id"));
                customer.setRentedCarId(rs.getInt("rented_car_id"));
                customers.add(customer);
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customers;
    }

    @Override
    public Customer getCustomer(int rollNo) {
        Customer customer = null;
        String query = String.format("SELECT * FROM CUSTOMER where id = %d", rollNo);
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("name");
                customer = new Customer(name);
                customer.setRollNo(rs.getInt("id"));
                customer.setRentedCarId(rs.getInt("rented_car_id"));
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customer;
    }

    @Override
    public void updateCustomer(Customer customer) {
        String query = null;
        if (customer.getRentedCarId() == 0) {
            query= String.format("UPDATE CUSTOMER " +
                    "SET rented_car_id = NULL where id = %d", customer.getRollNo());
        } else {
            query= String.format("UPDATE CUSTOMER " +
                    "SET rented_car_id = %d where id = %d", customer.getRentedCarId(), customer.getRollNo());
        }
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteCustomer(Customer customer) {

    }
    @Override
    public void addCustomer(String nameCustomer) {
        try (Statement stmt = conn.createStatement()) {
            String query = String.format("INSERT INTO CUSTOMER (name) " +
                    "VALUES ('%s')", nameCustomer);
            if (stmt.executeUpdate(query) != 0) {
                System.out.printf("The %s was created!%n%n", "customer");
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void showCustomers() {
        System.out.println("Customer list:");
        customers.forEach(x-> System.out.println((customers.indexOf(x) + 1) + ". " + x.getName()));
        System.out.println("0. Back");
        System.out.println();
    }
}
