package carsharing;

import java.util.List;

public interface CompanyDao {
    public List<Company> getAllStudents();
    public Company getStudent(int rollNo);
    public void updateStudent(Company student);
    public void deleteStudent(Company student);
}
