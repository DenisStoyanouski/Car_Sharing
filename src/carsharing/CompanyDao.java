package carsharing;

import java.util.ArrayList;;

public interface CompanyDao {
    public ArrayList<Company> getAllCompanies();

    public Company getCompany(int rollNo);

    public void updateCompany(Company company);

    public void deleteCompany(Company Company);

    public void addCompany(String name);
}
