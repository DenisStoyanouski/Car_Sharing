package carsharing;

import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {

        //list is working as a database
        List<Company> Companies;

        public CompanyDaoImpl(){
            Companies = new ArrayList<Company>();
            Company Company1 = new Company("Company1",0);
            Company Company2 = new Company("John",1);
            Companies.add(Company1);
            Companies.add(Company2);
        }
        @Override
        public void deleteCompany(Company Company) {
            Companies.remove(Company.getRollNo());
            System.out.println("Company: Roll No " + Company.getRollNo() + ", deleted from database");
        }

        //retrive list of Companies from the database
        @Override
        public List<Company> getAllCompanies() {
            return Companies;
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
}
