package carsharing;

import java.sql.Connection;
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
    public void startMainMenu()  {
        //Print the main menu
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
        //Get input from CLI
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void loginManager() {
        //Print Manager menu
        String itemMenu = null;
        while (!"0".equals(itemMenu)) {
            System.out.printf("1. Company list%n");
            System.out.printf("2. Create a company%n");
            System.out.println("0. Back");
            itemMenu = input().trim();
            switch(itemMenu) {
                case "1" :
                    chooseCompany(); //print company list
                    break;
                case "2" :
                    System.out.println("Enter the company name:");
                    tableCompanies.addCompany(input()); //create new company with name from input
                    break;
                case "0" : return;
                default :
                    System.out.println("Unknown query");
            }
        }
    }

    private void chooseCompany() {
        tableCompanies.getAllCompanies();
        String item = null;
        if (tableCompanies.companies.isEmpty()) {
            System.out.println("The company list is empty!");
        } else {
            while(!"0".equals(item)) {
            tableCompanies.showCompanies();
                try {
                    item = input().trim();
                    if (!"0".equals(item)) {
                        int companyNumb = Integer.parseInt(item) - 1;
                        System.out.printf("'%s' company%n", tableCompanies.companies.get(companyNumb).getName());
                        useTableCar(tableCompanies.companies.get(companyNumb).getRollNo());
                    }
                } catch (NumberFormatException|IndexOutOfBoundsException e) {
                    System.out.println("Unknown item");
                }
            }
        }
    }

    private void useTableCar(int companyId) {
        tableCars.getAllCars(companyId);
        String itemCarMenu = null;
        while( !"0".equals(itemCarMenu)) {
            System.out.printf("1. Car list%n");
            System.out.printf("2. Create a car%n");
            System.out.println("0. Back");
            itemCarMenu = input().trim();
            switch(itemCarMenu) {
                case "1" :  chooseCar();
                            break;
                case "2" : System.out.println("Enter the car name:");
                            String carName = input().trim();
                            tableCars.cars.add(new Car(carName,companyId));
                            tableCars.addCar(carName, companyId);
                            break;
                case "0" : return;
                default :
                    System.out.println("Unknown item");
            }
        }
    }

    private void chooseCar() {
        String item = null;
        if (tableCars.cars.isEmpty()) {
            System.out.println("The car list is empty!");
            return;
        }
        while (!"0".equals(item)) {
            tableCars.showCars();
            item = input().trim();
        }
        loginManager();
    }

    private void loginCustomer() {
        tableCustomers.getAllCustomer();
        String item = null;
        if (tableCustomers.customers.isEmpty()) {
            System.out.printf("The customer list is empty!%n");

        } else {
            while (!"0".equals(item)) {
                tableCustomers.showCustomers();
                item = input();
                try {
                    int customerIndex = Integer.parseInt(item);
                    if (customerIndex != 0) {
                        getCustomerMenu(tableCustomers.customers.get(customerIndex - 1));
                    }
                } catch (NumberFormatException|IndexOutOfBoundsException e) {
                    System.out.println("Unknown item");
                }
            }
        }
    }

    private void getCustomerMenu(Customer customer) {
        String item = null;
        while (!"0".equals(item)) {
            System.out.println("1. Rent a car");
            System.out.println("2. Return a rented car");
            System.out.println("3. My rented car");
            System.out.println("0. Back");
            item = input();
            switch(item.trim()) {
                case "0" : return;
                case "1" :
                    if (customer.getRentedCarId() != 0) {
                        System.out.println("You've already rented a car!");
                    } else {
                        tableCompanies.showCompanies();
                        int companyNum = Integer.parseInt(input());
                        tableCars.getFreeCars(companyNum);
                        tableCars.showCars();
                        int carNum = Integer.parseInt(input());
                        customer.setRentedCarId(tableCars.cars.get(carNum - 1).getRollNo());
                        tableCustomers.updateCustomer(customer);
                        tableCars.getAllCars(companyNum);
                        System.out.printf("You rented '%s'%n", tableCars.getCar(customer.getRentedCarId()).getName());
                    }
                    break;
                case "2" :
                    if (customer.getRentedCarId() == 0) {
                        System.out.println("You didn't rent a car!");
                    } else {
                        customer.setRentedCarId(0);
                        tableCustomers.updateCustomer(customer);
                        System.out.println("You've returned a rented car!");
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

    }

    private void createCustomer() {
        System.out.println("Enter the customer name:");
        tableCustomers.addCustomer(input());
    }
}
