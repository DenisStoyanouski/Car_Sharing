package carsharing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {

        //list is working as a database
        List<Company> Companies;

        public CompanyDaoImpl(){
            Companies = new ArrayList<Company>();
        }
        @Override
        public void deleteCompany(Connection conn, Company Company) {
            Companies.remove(Company.getRollNo());
            System.out.println("Company: Roll No " + Company.getRollNo() + ", deleted from database");
        }

        //retrieve list of Companies from the database
        @Override
        public void getAllCompanies(Connection conn) {
            try (Statement stmt = conn.createStatement()) {
                String query = "SELECT * FROM COMPANY";
                ResultSet rs = stmt.executeQuery(query);
                if (!rs.wasNull()) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        System.out.println(id + ". " + name);
                    }
                } else {
                    System.out.println("The company list is empty!");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public Company getCompany(Connection conn, int rollNo) {
            return Companies.get(rollNo);
        }

        @Override
        public void updateCompany(Connection conn, Company Company) {
            Companies.get(Company.getRollNo()).setName(Company.getName());
            System.out.println("Company: Roll No " + Company.getRollNo() + ", updated in the database");
        }

        @Override
        public void addCompany(Connection conn, String name) {
            Companies.add(new Company(name));
            System.out.println("The company was created!");
    }
}
