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

	List<Employees> employees;
	private Employees employee;

	public EmployeesDAO() {
		employees = new ArrayList<Employees>();

	}

	public void getEmployee() { // ÝD ye göre ad ve soyadý getir

		Connection connection = DbConnection.getConnection();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM employees WHERE employee_id=103");
			if (rs.next()) {
				Employees user = new Employees();
				user.setEmployee_id(rs.getInt(1));
				user.setFirst_name(rs.getString(2));
				user.setLast_name(rs.getString(3));
				System.out.println(user);

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public boolean insert() { // Employees employee eklenmeli

		try {
			Connection connection = DbConnection.getConnection();

			Employees employee = new Employees(5003, "Alper", "Ersayin", "alper12@gmail", 123456789,
					Date.valueOf("2015-10-02"), "SA_REP", 5000, 0.3, 108, 50);

			String inserting = "INSERT INTO employees (employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id)"
					+ " values(?,?,?,?,?,?,?,?,?,?,?)";

			System.out.println("insert " + inserting);//
			PreparedStatement ps = connection.prepareStatement(inserting);

			ps.setInt(1, employee.getEmployee_id());
			ps.setString(2, employee.getFirst_name());
			ps.setString(3, employee.getLast_name());
			ps.setString(4, employee.getEmail());
			ps.setInt(5, employee.getPhone_number());
			ps.setDate(6, employee.getHire_date());
			ps.setString(7, employee.getJob_id());
			ps.setInt(8, employee.getSalary());
			ps.setDouble(9, employee.getCommission_pct());
			ps.setInt(10, employee.getManager_id());
			ps.setInt(11, employee.getDepartment_id());
			ps.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	public boolean update() { // Employees employee eklenmeli
		Connection connection = DbConnection.getConnection();
		Employees e = new Employees(206, "Bengisu", "Özmelleþ", "bengsu@gmail", 123456789, Date.valueOf("2010-10-10"),
				"AD_VP", 5000, 0.3, 108, 50);

		try {
			String sql = "UPDATE employees SET first_name=?, last_name=?, email=?, phone_number=?, hire_date=?, job_id=?, salary=?, commission_pct=?, manager_id=?, department_id=?"
					+ " WHERE employee_id='" + e.getEmployee_id() + "'";

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, e.getFirst_name());
			ps.setString(2, e.getLast_name());
			ps.setString(3, e.getEmail());
			ps.setInt(4, e.getPhone_number());
			ps.setDate(5, e.getHire_date());
			ps.setString(6, e.getJob_id());
			ps.setInt(7, e.getSalary());
			ps.setDouble(8, e.getCommission_pct());
			ps.setInt(9, e.getManager_id());
			ps.setInt(10, e.getDepartment_id());

			int i = ps.executeUpdate();
			if (i == 1) {
				System.out.println(e.getEmployee_id() + " güncellendi");
				return true;

			}
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



}
