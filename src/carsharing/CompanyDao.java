package carsharing;

import java.util.List;
import java.util.Map;

public interface CompanyDao {
    public List<String> getAll(String tableName);
    public int getOne(String tableName, String name);
    public void update(String tableName);
    public void delete(String tableName);
    public void add(String name);
}
