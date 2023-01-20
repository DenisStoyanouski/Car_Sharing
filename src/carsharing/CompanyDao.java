package carsharing;

import java.util.List;

public interface CompanyDao {
    public void getAllCompanies();
    public Company getCompany(int rollNo);
    public void updateCompany(Company Company);
    public void deleteCompany(Company Company);
    public void addCompany(String name);
}
