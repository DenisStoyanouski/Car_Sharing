package carsharing;

public interface CompanyDao {
    public void getAllCompanies();
    public void getCompany(int rollNo);
    public void updateCompany(Company Company);
    public void deleteCompany(Company Company);
    public void addCompany(String name);
}
