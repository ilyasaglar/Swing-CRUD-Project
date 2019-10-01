import java.sql.Connection;
import java.io.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOimp implements EmployeeDAO {

	List<Employee> employees;
	private Employee employee;

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
	 
		
		try {
			Connection	connection = DriverManager.getConnection(MyConnection.URL,"hr","hr");
			PreparedStatement ps = connection.prepareStatement("INSERT INTO user VALUES (NULL, ?, ?, ?)");
		        ps.setString(1, employee.getFirst_name());
		        ps.setString(2, employee.getLast_name());
		        ps.setInt(3, employee.phone_number);
		        int i = ps.executeUpdate();
		      if(i == 1) {
		        return true;
		      }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
