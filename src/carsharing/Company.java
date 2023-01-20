package carsharing;

public class Company {
    private String name;
    private int rollNo = 1;

    Company(String name){
        this.name = name;
        rollNo++;
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
