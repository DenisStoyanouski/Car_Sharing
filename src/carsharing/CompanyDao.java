package carsharing;

import java.sql.Connection;
import java.util.List;

public interface CompanyDao {
    public void getAllCompanies(Connection connection);
    public Company getCompany(Connection connection, int rollNo);
    public void updateCompany(Connection connection, Company Company);
    public void deleteCompany(Connection connection, Company Company);
    public void addCompany(Connection connection, String name);
}
