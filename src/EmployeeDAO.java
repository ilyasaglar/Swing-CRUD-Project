import java.util.List;

public interface EmployeeDAO {
	
	public List<Employee> getAllEmployees();
	public Employee getEmployee(int employee_id);
	boolean insertEmployee(Employee employee);
	boolean updateEmployee(Employee employee);
	boolean deleteEmployee(Employee employee);

}
