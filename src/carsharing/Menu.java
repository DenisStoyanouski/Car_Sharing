package carsharing;

import java.sql.Connection;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private Connection connection;

    private CompanyDaoImpl companies;


    public Menu(Connection connection) {
        this.connection = connection;
        companies = new CompanyDaoImpl(connection);
    }
    public void start() {

        String item;
        while (true) {
            System.out.println("1. Log in as a manager");
            System.out.println("0. Exit");
            item = input();
            switch (item.trim()) {
                case "1" : queryMenu("company");
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

    private void queryMenu(String name) {
        String item;
        while (true) {
            System.out.printf("1. %s list%n", name);
            System.out.printf("2. Create a %s%n", name);
            System.out.println("0. Back");
            item = input();
            switch(item.trim()) {
                case "1" :
                    chooseCompany(name);
                    break;
                case "2" :
                    System.out.println(String.format("Enter the %s name:", name));
                    companies.add(input());
                    break;
                case "0" : return;
                default :
                    System.out.println("Unknown query");
            }
        }
    }

    private void chooseCompany(String name) {
        System.out.println("Choose the company:");
        Map<Integer,String> select = companies.getAll(name);
        if (select.isEmpty()) {
            System.out.printf("The %s list is empty!%n", name);
            return;
        } else {
            select.forEach((key, val) -> System.out.println(key + ". " + val));
            System.out.println("0. back");
        }
        int choice = -1;
        while(choice != 0) {
            try {
                choice = Integer.parseInt(input().trim());
                if (choice == 0) {
                    return;
                } else if (select.get(choice) == null) {
                    System.out.println("Unknown item");
                } else {
                    System.out.printf("'%s' company%n", select.get(choice));
                    useTableCar(choice, "car");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Unknown item");
            }
        }
    }

    private void useTableCar(int choice, String tableName) {
        String item = null;
        while( !"0".equals(item)) {
            System.out.printf("1. %s list%n", tableName);
            System.out.printf("2. Create a %s%n", tableName);
            System.out.println("0. Back");
            item = input().trim();
            switch(item) {
                case "1" : Map<Integer,String> select = companies.getAll(tableName, choice);
                            if (!select.isEmpty()) {
                                select.forEach((key, val) -> System.out.println(key + ". " + val));
                                System.out.println();
                            } else {
                                System.out.printf("The %s list is empty!%n", tableName);
                            }
                        break;
                case "2" : System.out.println("Enter the car name:");
                            String carName = input().trim();
                            companies.add(carName, choice);
                            break;
                case "0" : return;
                default :
                    System.out.println("Unknown item");
            }
        }
    }
}
