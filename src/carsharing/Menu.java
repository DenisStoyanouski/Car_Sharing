package carsharing;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private Connection connection;

    private CompanyDaoImpl tableCompanies;

    private CarDaoImpl tableCars;

    private CustomerDaoImpl tableCustomers;



    public Menu(Connection connection) {
        this.connection = connection;
        tableCompanies = new CompanyDaoImpl(connection);
        tableCars = new CarDaoImpl(connection);
        tableCustomers = new CustomerDaoImpl(connection);
    }
    public void startMainMenu() {

        String item;
        while (true) {
            System.out.println("1. Log in as a manager");
            System.out.println("2. Log in as a customer");
            System.out.println("3. Create a customer");
            System.out.println("0. Exit");
            item = input();
            switch (item.trim()) {
                case "1" : loginManager();
                    break;
                case "2" : loginCustomer();
                    break;
                case "3" : createCustomer();
                    break;
                case "0" : exit();
                    break;
                default :
                    System.out.println("Unknown command");
            }
        }
    }

    private static void exit() {
        System.exit(0);
    }

    private static String input() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void loginManager() {
        String item = null;
        while (!"0".equals(item)) {
            System.out.printf("1. Company list%n");
            System.out.printf("2. Create a company%n");
            System.out.println("0. Back");
            item = input();
            switch(item.trim()) {
                case "1" :
                    chooseCompany();
                    break;
                case "2" :
                    System.out.println("Enter the company name:");
                    tableCompanies.addCompany(input());
                    break;
                case "0" : return;
                default :
                    System.out.println("Unknown query");
            }
        }
    }

    private void loginCustomer() {
        ArrayList<Customer> select = tableCustomers.getAllCustomer();
        String customerNumber = null;
        if (select.isEmpty()) {
            System.out.printf("The customers list is empty!%n");
        } else {
            while (!"0".equals(customerNumber)) {
                tableCustomers.showCustomers();
                try {
                    customerNumber = input();
                    int cN = Integer.parseInt(customerNumber);
                    if (cN == 0) {
                        return;
                    } else {
                        getCustomerMenu(select.get(cN - 1));
                        break;
                    }
                } catch (NumberFormatException|IndexOutOfBoundsException e) {
                    System.out.println("Unknown item");
                }
            }
        }
    }

    private void getCustomerMenu(Customer customer) {
        System.out.println("1. Rent a car");
        System.out.println("2. Return a rented car");
        System.out.println("3. My rented car");
        System.out.println("0. Back");
        String item = input();
        switch(item.trim()) {
            case "0" : return;
            case "1" :
                tableCompanies.showCompanies();
                if (customer.getRentedCarId() != 0) {
                    System.out.println("You've already rented a car!");
                } else {
                    tableCars.getFreeCars(Integer.parseInt(input()));
                    tableCars.showCars();
                    customer.setRentedCarId(Integer.parseInt(input()));
                    tableCustomers.updateCustomer(customer);
                }
                break;
            case "2" :
                if (customer.getRentedCarId() == 0) {
                    System.out.println("You didn't rent a car!");
                } else {
                    customer.setRentedCarId(0);
                    tableCustomers.updateCustomer(customer);
                }
                break;
            case "3" :
                if (customer.getRentedCarId() == 0 ) {
                    System.out.println("You didn't rent a car!");
                } else {
                    System.out.println("Your rented car:");
                    Car car = tableCars.getCar(customer.getRentedCarId());
                    System.out.println(car.getName());
                    System.out.println("Company");
                    Company company = tableCompanies.getCompany(car.getCompanyId());
                    System.out.println(company.getName());
                }
                break;
            default:
                System.out.println("Unknown item");
        }
    }

    private void createCustomer() {
        System.out.println("Enter the customer name:");
        tableCustomers.addCustomer(input());
    }

    private void chooseCompany() {
        tableCompanies.getAllCompanies();
        if (tableCompanies.companies.isEmpty()) {
            System.out.println("The customer list is empty!");
            return;
        } else {
            tableCompanies.showCompanies();
        }
        int choice = -1;
        while(choice != 0) {
            try {
                choice = Integer.parseInt(input().trim());
                if (choice != 0) {
                    System.out.printf("'%s' company%n", tableCompanies.companies.get(choice - 1).getName());
                    useTableCar(choice);
                    return;
                }
            } catch (NumberFormatException|IndexOutOfBoundsException e) {
                System.out.println("Unknown item");
            }
        }
    }

    private void useTableCar(int choice) {
        String item = null;
        while( !"0".equals(item)) {
            System.out.printf("1. Car list%n");
            System.out.printf("2. Create a car%n");
            System.out.println("0. Back");
            item = input().trim();
            switch(item) {
                case "1" :
                    tableCars.getAllCars(choice);
                    if (tableCars.cars.isEmpty()) {
                        System.out.println("The car list is empty!");
                    } else {
                        tableCars.showCars();
                    }
                    break;
                case "2" : System.out.println("Enter the car name:");
                            String carName = input().trim();
                            tableCars.addCar(carName, choice);
                            break;
                case "0" : return;
                default :
                    System.out.println("Unknown item");
            }
        }
    }
}
