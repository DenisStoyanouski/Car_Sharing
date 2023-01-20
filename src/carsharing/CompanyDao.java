package carsharing;

import java.util.List;

public interface CompanyDao {
    public List<Company> getAllCompanies();
    public Company getCompany(int rollNo);
    public void updateCompany(Company Company);
    public void deleteCompany(Company Company);
}
