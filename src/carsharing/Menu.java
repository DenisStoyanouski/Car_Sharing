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
            System.out.println(String.format("1. %s list", name));
            System.out.println(String.format("2. Create a %s", name));
            System.out.println("0. Back");
            item = input();
            switch(item.trim()) {
                case "1" :
                    chooseCompany(name);
                    break;
                case "2" :
                    System.out.println(String.format("Enter the %s name:", name));
                    companies.add(name, input());
                    break;
                case "0" : return;
                default :
                    System.out.println("Unknown query");
            }
        }
    }

    private void chooseCompany(String name) {
        Map<Integer,String> select = companies.getAll(name);
        if (select.isEmpty()) {
            System.out.println(String.format("The %s list is empty!", name));
        } else {
            if ("company".equals(name)) {
                System.out.println("Choose the company:");
                select.forEach((key, val) -> System.out.println(key + ". " + val));
                System.out.println("0. back");
                String number = input();
                if ("0".equals(number)) {
                    return;
                } else {
                    String nextName = select.getOrDefault(Integer.parseInt(number), "0");
                    System.out.println(String.format("'%s' company", nextName));
                    queryMenu(nextName);
                }
            } else {
                select.forEach((key, val) -> System.out.println(key + ". " + val));
            }
        }
    }
}
