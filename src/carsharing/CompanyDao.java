package carsharing;

import java.util.Map;

public interface CompanyDao {
    public Map<Integer, String> getAll(String tableName);
    public void getById(String tableName, int id);
    public void update(String tableName);
    public void delete(String tableName);
    public void add(String tableName, String name);
}
