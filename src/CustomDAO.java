import java.sql.SQLException;
import java.util.List;
import java.io.*;

public interface CustomDAO {
	
	public List<Employees> getAllEmployees();
	public Employees getEmployee(int employee_id);
	boolean insertEmployee(Employees employee);
	boolean updateEmployee(Employees employee);
	boolean deleteEmployee(Employees employee);

}
