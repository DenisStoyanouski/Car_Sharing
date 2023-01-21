package carsharing;

import java.sql.Connection;
import java.util.Scanner;

public class Menu {
    public static void start(Connection connection) {

        String item;
        while (true) {
            System.out.println("1. Log in as a manager");
            System.out.println("0. Exit");
            item = input();
            switch (item.trim()) {
                case "1" : queryMenu(connection);
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

    private static void queryMenu(Connection connection) {

        String item;
        CompanyDaoImpl companies = new CompanyDaoImpl(connection);
        while (true) {
            System.out.println("1. Company list");
            System.out.println("2. Create a company");
            System.out.println("0. Back");
            item = input();
            switch(item.trim()) {
                case "1" :
                    companies.getAllCompanies();
                    break;
                case "2" :
                    System.out.println("Enter the company name:");
                    companies.addCompany(input());
                    break;
                case "0" : return;
                default :
                    System.out.println("Unknown query");
            }
        }
    }
}
