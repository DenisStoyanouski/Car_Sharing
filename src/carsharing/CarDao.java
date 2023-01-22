package carsharing;

import java.util.ArrayList;

public interface CarDao {
    public ArrayList<Car> getAllCars(int companyId);
    public Car getCar(int rollNo);
    public void updateCar(Car company);
    public void deleteCar(Car Company);
    public void addCar(String name, int companyId);
}
