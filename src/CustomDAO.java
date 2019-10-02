import java.sql.SQLException;
import java.util.List;
import java.io.*;

public interface CustomDAO {
	
	
	boolean insertEmployee();
	boolean updateEmployee();
	boolean deleteEmployee(Employees employee);
	
	
	

}
