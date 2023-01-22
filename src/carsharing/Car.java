package carsharing;

public class Car {
    private String name;
    private int rollNo;
    private int companyId;

    Car(String name, int rollNo, int companyId){
        this.name = name;
        this.rollNo = rollNo;
        this.companyId = companyId;
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

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
