package carsharing;

public class Company {
    private String name;
    private int rollNo;

    private static int counter;

    Company(String name){
        counter++;
        this.name = name;
        this.rollNo = counter;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }
}
