package carsharing;

import java.util.ArrayList;

public interface CustomerDao {
    public ArrayList<Customer> getAllCustomer();

    public Customer getCustomer(int rollNo);

    public void updateCustomer(Customer customer);

    public void deleteCustomer(Customer customer);

    public void addCustomer(String name);
}
