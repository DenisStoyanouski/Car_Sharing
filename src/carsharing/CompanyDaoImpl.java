package carsharing;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {

        //list is working as a database
        List<Company> Companies;

        public CompanyDaoImpl(){
            Companies = new ArrayList<Company>();
        }
        @Override
        public void deleteCompany(Company Company) {
            Companies.remove(Company.getRollNo());
            System.out.println("Company: Roll No " + Company.getRollNo() + ", deleted from database");
        }

        //retrive list of Companies from the database
        @Override
        public void getAllCompanies() {
            if (Companies.isEmpty()) {
                System.out.println("The company list is empty!");
            } else {
                System.out.println("Company list:");
                for (Company company : Companies) {
                    System.out.println(company.getRollNo() + ". " + company.getName());
                }
            }
        }

        @Override
        public Company getCompany(int rollNo) {
            return Companies.get(rollNo);
        }

        @Override
        public void updateCompany(Company Company) {
            Companies.get(Company.getRollNo()).setName(Company.getName());
            System.out.println("Company: Roll No " + Company.getRollNo() + ", updated in the database");
        }

        @Override
        public void addCompany(String name) {
            Companies.add(new Company(name));
    }
}
