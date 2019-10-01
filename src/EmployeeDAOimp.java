import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOimp implements EmployeeDAO {

	List<Employee> employees;

	   public EmployeeDAOimp(){
	      employees = new ArrayList<Employee>();
	      	
	   }
	@Override
	public List<Employee> getAllEmployees() {
		
		return null;
	}

	@Override
	public Employee getEmployee(int employee_id) {
		
		return null;
	}

	@Override
	public boolean insertEmployee(Employee employee) {
		Connector connector = new Connector();
	    //Connection connection = connector.getConnection();
		//Connection myConn = DriverManager.getConnection(null);
		    try {
		        PreparedStatement ps = connection.prepareStatement("INSERT INTO user VALUES (NULL, ?, ?, ?)");
		        ps.setString(1, employee.getFirst_name());
		        ps.setString(2, employee.getLast_name());
		        ps.setInt(3, employee.getEmployee_id());
		        int i = ps.executeUpdate();
		      if(i == 1) {
		        return true;
		      }
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		    }
		    return false;
		}
		
	

	@Override
	public boolean updateEmployee(Employee employee) {
		return false;
		
		
	}

	@Override
	public boolean deleteEmployee(Employee employee) {
		return false;
		
		
	}

	
}
