package carsharing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

class CarDaoImpl implements CarDao{

    Connection conn;
    ArrayList<Car> cars;

    public CarDaoImpl(Connection conn){
        this.conn = conn;
        this.cars = new ArrayList<>();
    }

    private void createTable() {
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

    @Override
    public ArrayList<Car> getAllCars(int companyId) {
        System.out.println();
        String query = "SELECT * FROM CAR where company_id = %d".formatted(companyId);
        try (Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(true);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int compId = rs.getInt("company_id");
                cars.add(new Car(name, id, compId));
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cars;
    }

    @Override
    public Car getCar(int rollNo) {
        return null;
    }

    @Override
    public void updateCar(Car company) {

    }

    @Override
    public void deleteCar(Car Company) {

    }

    @Override
    public void addCar(String carName, int company_id) {
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
