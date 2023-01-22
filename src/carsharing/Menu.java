package carsharing;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private Connection connection;

    private CompanyDaoImpl tableCompanies;

    private CarDaoImpl tableCars;



    public Menu(Connection connection) {
        this.connection = connection;
        tableCompanies = new CompanyDaoImpl(connection);
        tableCars = new CarDaoImpl(connection);
    }
    public void start() {

        String item;
        while (true) {
            System.out.println("1. Log in as a manager");
            System.out.println("0. Exit");
            item = input();
            switch (item.trim()) {
                case "1" : loginManager();
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
        String item;
        while (true) {
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

    private void chooseCompany() {
        ArrayList<Company> select = tableCompanies.companies;
        System.out.print("Choose the company:");

        if (select.isEmpty()) {
            System.out.printf("The companies list is empty!%n");
            return;
        } else {
            select.forEach((x) -> System.out.println(x.getRollNo() + ". " + x.getName()));
            System.out.println("0. Back");
        }
        int choice = -1;
        while(choice != 0) {
            try {
                choice = Integer.parseInt(input().trim());
                if (choice == 0) {
                    return;
                } else if (select.get(choice - 1) == null) {
                    System.out.println("Unknown item");
                } else {
                    System.out.printf("'%s' company%n", select.get(choice - 1));
                    useTableCar(choice);
                    return;
                }
            } catch (NumberFormatException e) {
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
                case "1" : ArrayList<Car> select = tableCars.getAllCars(choice);
                            if (!select.isEmpty()) {
                                System.out.println("Car list:");
                                select.forEach((x) -> System.out.println((x.getRollNo()) + ". " + x.getName()));
                                System.out.println();
                            } else {
                                System.out.printf("The cars list is empty!%n");
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
