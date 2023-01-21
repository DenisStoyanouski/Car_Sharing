package carsharing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompanyDaoImpl implements CompanyDao {

        //list is working as a database
        Connection conn;

        public CompanyDaoImpl(Connection conn){
            this.conn = conn;
        }
        @Override
        public void deleteCompany(Company Company) {

            System.out.println("Company: Roll No " + Company.getRollNo() + ", deleted from database");
        }

        //retrieve list of Companies from the database
        @Override
        public void getAllCompanies() {
            System.out.println();
            try (Statement stmt = conn.createStatement()) {
                String query = "SELECT * FROM COMPANY";
                ResultSet rs = stmt.executeQuery(query);
                if (!rs.isBeforeFirst()) {
                    System.out.println("The company list is empty!");
                } else {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        System.out.println( id + ". " + name);
                    }
                    conn.commit();
                }
                System.out.println();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void getCompany(int rollNo) {
            System.out.println();
        }

        @Override
        public void updateCompany(Company Company) {

            System.out.println("Company: Roll No " + Company.getRollNo() + ", updated in the database");
        }

        @Override
        public void addCompany(String name) {
            try (Statement stmt = conn.createStatement()) {
                String query = String.format("INSERT INTO COMPANY (name) " +
                        "VALUES ('%s')",name);
                if (stmt.executeUpdate(query) != 0) {
                    System.out.println("The company was created!");
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
