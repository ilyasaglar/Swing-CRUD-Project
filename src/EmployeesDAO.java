import java.sql.Connection;
import java.io.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class EmployeesDAO implements CustomDAO {

//	List<Employees> employees;
	
	private Employees employee;
	
	public EmployeesDAO() {
		//employees = new ArrayList<Employees>();
	}
	
	public Employees setEmployee(Employees e){
		return employee = e;
	}
	
	
	public Employees getEmployee(Integer id) { // ÝD ye göre ad ve soyadý getir

		Connection connection = DbConnection.getConnection();
		try {
			Employees e = new Employees();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM employees WHERE employee_id="+id);
			if (rs.next()) {
				
				e.setEmployee_id(rs.getInt(id));
				e.setFirst_name(rs.getString(2));
				e.setLast_name(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setPhone_number(rs.getString(5));
				e.setHire_date(rs.getDate(6));
				e.setJob_id(rs.getString(7));
				e.setSalary(rs.getInt(8));
				e.setCommission_pct(rs.getInt(9));
				e.setManager_id(rs.getInt(10));
				e.setDepartment_id(rs.getInt(11));
				
				return e;
				
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			
		}
		return null;
		

	}

	@Override
	public boolean insert() { // Employees employee eklenmeli

		try {
			Connection connection = DbConnection.getConnection();
		
			
			String inserting = "INSERT INTO employees (employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id)"
					+ " values(?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement ps = connection.prepareStatement(inserting);

			ps.setInt(1, employee.getEmployee_id());
			ps.setString(2, employee.getFirst_name());
			ps.setString(3, employee.getLast_name());
			ps.setString(4, employee.getEmail());
			ps.setString(5, employee.getPhone_number());
			ps.setDate(6, employee.getHire_date());
			ps.setString(7, employee.getJob_id());
			ps.setInt(8, employee.getSalary());
			ps.setDouble(9, employee.getCommission_pct());
			ps.setInt(10, employee.getManager_id());
			ps.setInt(11, employee.getDepartment_id());
			ps.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public void check() {
		Connection connection = DbConnection.getConnection();
		
	}

	public boolean update() { // Employees employee eklenmeli
		Connection connection = DbConnection.getConnection();
	
		try {
			//Employees employee = new Employees();
			
			
			String sql = "UPDATE employees SET first_name=?, last_name=?, email=?, phone_number=?, hire_date=?, job_id=?, salary=?, commission_pct=?, manager_id=?, department_id=?"
					+ " WHERE employee_id=" + employee.getEmployee_id();

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, employee.getFirst_name());
			ps.setString(2, employee.getLast_name());
			ps.setString(3, employee.getEmail());
			ps.setString(4, employee.getPhone_number());
			ps.setDate(5, employee.getHire_date());
			ps.setString(6, employee.getJob_id());
			ps.setInt(7, employee.getSalary());
			ps.setDouble(8, employee.getCommission_pct());
			ps.setInt(9, employee.getManager_id());
			ps.setInt(10, employee.getDepartment_id());

			int i = ps.executeUpdate();
			if (i == 1) {
				System.out.println(employee.getEmployee_id() + " güncellendi");
				return true;

			}
			
			Employees emp = new Employees();
			emp = getEmployee(employee.getEmployee_id());
			
			
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return false;

	}

	public boolean delete() { // Employees employee eklenmeli
		Connection connection = DbConnection.getConnection();
		int employee_id = 109;
		try {
			Statement stmt = connection.createStatement();
			int i = stmt.executeUpdate("DELETE FROM employees WHERE employee_id='" + employee_id + "'");
			if (i == 1) {
				System.out.println(employee_id + " silindi");
				return true;

			}
		} catch (SQLException ex) {
			System.out.println(ex);
			ex.printStackTrace();

		}
		return false;
	}

	public static List<Employees> getAllData() {

		Connection conn = DbConnection.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Employees");
			List<Employees> empList = new ArrayList<>();
			while (rs.next()) {
				Employees employee = extractUserFromResultSet(rs);
				;

				empList.add(employee);
			}
			return empList;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static Employees extractUserFromResultSet(ResultSet rs) throws SQLException {
		Employees e = new Employees();

		e.setEmployee_id(rs.getInt("employee_id"));
		e.setFirst_name(rs.getString("first_name"));
		e.setLast_name(rs.getString("last_name"));
		e.setEmail(rs.getString("email"));
		e.setPhone_number(rs.getString("phone_number"));
		e.setHire_date(rs.getDate("hire_date"));
		e.setJob_id(rs.getString("job_id"));
		e.setSalary(rs.getInt("salary"));
		e.setCommission_pct(rs.getInt("commission_pct"));
		e.setManager_id(rs.getInt("manager_id"));
		e.setDepartment_id(rs.getInt("department_id"));

	
		return e;
	}

}
