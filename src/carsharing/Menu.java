package carsharing;

import java.util.Scanner;

public class Menu {
    public static void start() {
        System.out.println("1. Log in as a manager");
        System.out.println("0. Exit");
        String item;
        while (true) {
            item = input();
            switch (item) {
                case "1" : queryMenu();
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
        return scanner.next();
    }

    private static void queryMenu() {
        System.out.println("1. Company list");
        System.out.println("2. Create a company");
        System.out.println("0. Back");
        String item;
        CompanyDaoImpl companies = new CompanyDaoImpl();
        while (true) {
            item = input();
            switch(item) {
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
