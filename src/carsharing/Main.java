package carsharing;

public class Main {

    private static String databaseFileName = "carsharing";

    public static void main(String[] args) {
        if (args.length == 2) {
            databaseFileName = args[1];
        }
        DataBase.setName(databaseFileName);
        DataBase.create();
        Menu.start();
    }
}