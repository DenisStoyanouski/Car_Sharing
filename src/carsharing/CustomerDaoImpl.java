package carsharing;

import java.sql.Connection;
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
                    " rented_car_id INTEGER DEFAULT NULL" +
                    " FOREIGN KEY (rented_car_id) REFERENCES CAR(id)))";
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
    public ArrayList<Customer> getAllCustomer(int rentedCarId) {
        return null;
    }

    @Override
    public Customer getCustomer(int rollNo) {
        return null;
    }

    @Override
    public void updateCustomer(Customer customer) {

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
}
