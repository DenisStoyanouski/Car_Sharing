package carsharing;

public class Customer {
    private String name;
    private int rollNo;
    private int rentedCarId;

    Customer(String name, int rollNo, int rentedCarId){
        this.name = name;
        this.rollNo = rollNo;
        this.rentedCarId = rentedCarId;
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

    public int getRentedCarId() {
        return rentedCarId;
    }

    public void setRentedCarId(int companyId) {
        this.rentedCarId = rentedCarId;
    }

}
