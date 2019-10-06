import java.sql.CallableStatement;
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

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.sql.Date;

public class EmployeesDAO implements CustomDAO {

	// List<Employees> employees;

	public Employees employee;

	public EmployeesDAO() {
		// employees = new ArrayList<Employees>();
	}

	public Employees setEmployee(Employees e) {
		return employee = e;
	}

	public Employees getEmployee(Integer id) { // ÝD ye göre ad ve soyadý getir

		Connection connection = DbConnection.getConnection();
		try {
			Employees e = new Employees();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM employees WHERE employee_id=" + id);
			if (rs.next()) {

				e.setEmployee_id(rs.getInt(id));
				e.setFirst_name(rs.getString(2));
				e.setLast_name(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setPhone_number(rs.getString(5));
				e.setHire_date(rs.getDate(6));
				e.setJob_id(rs.getString(7));
				e.setSalary(rs.getInt(8));
				e.setCommission_pct(rs.getDouble(9));
				e.setManager_id(rs.getInt(10));
				e.setDepartment_id(rs.getInt(11));
				
				connection.close();
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

			CallableStatement stmt = connection.prepareCall("{call PR_INSERT_EMPLOYEE(?,?,?,?,?,?,?,?,?,?,?)}");

			System.out.println(employee);

			stmt.setInt(1, employee.getEmployee_id());
			stmt.setString(2, employee.getFirst_name());
			stmt.setString(3, employee.getLast_name());
			stmt.setString(4, employee.getEmail());
			stmt.setString(5, employee.getPhone_number());
			stmt.setDate(6, employee.getHire_date());
			stmt.setString(7, employee.getJob_id());
			stmt.setInt(8, employee.getSalary());
			stmt.setDouble(9, employee.getCommission_pct());
			stmt.setInt(10, employee.getManager_id());
			stmt.setInt(11, employee.getDepartment_id());

			stmt.execute();
			connection.close();
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
			// Employees employee = new Employees();
			/*
			String sql = "UPDATE employees SET first_name=?, last_name=?, email=?, phone_number=?, hire_date=?, job_id=?, salary=?, commission_pct=?, manager_id=?, department_id=?"
					+ " WHERE employee_id=" + employee.getEmployee_id();*/
			
			CallableStatement stmt = connection.prepareCall("{call PR_UPDATE_EMPLOYEE(?,?,?,?,?,?,?,?,?,?,?)}");

			//PreparedStatement ps = connection.prepareStatement(sql);
			stmt.setInt(1, employee.getEmployee_id());
			stmt.setString(2, employee.getFirst_name());
			stmt.setString(3, employee.getLast_name());
			stmt.setString(4, employee.getEmail());
			stmt.setString(5, employee.getPhone_number());
			stmt.setDate(6, employee.getHire_date());
			stmt.setString(7, employee.getJob_id());
			stmt.setInt(8, employee.getSalary());
			stmt.setDouble(9, employee.getCommission_pct());
			stmt.setInt(10, employee.getManager_id());
			stmt.setInt(11, employee.getDepartment_id());
			
			stmt.execute();
			
			System.out.println(employee.getEmployee_id() + "güncellendi");
			/*
			int i = ps.executeUpdate();
			if (i == 1) {
				System.out.println(employee.getEmployee_id() + " güncellendi");

			}
			 */
			connection.close();
			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}

	}


	public boolean delete(int employee_id) { // Employees employee eklenmeli
		Connection connection = DbConnection.getConnection();

		try {
			Statement stmt = connection.createStatement();

			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(null, "Kiþi Bilgilerini Silmek Ýstediðinize emin misiniz?",
					"Onay Ekraný", dialogButton);
			if (dialogResult == JOptionPane.YES_OPTION) {

				int i = stmt.executeUpdate("DELETE FROM employees WHERE employee_id='" + employee_id + "'");
				if (i == 1) {
					System.out.println(employee_id + " silindi");

				}
				connection.close();
				return true;
			} else {

				return false;
			}

		} catch (SQLException ex) {
			System.out.println(ex);
			ex.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), "Bir hata oluþtu.", "Hata!", JOptionPane.ERROR_MESSAGE);
			return false;
		}

	}

	public List<Employees> getAllData() {

		Connection conn = DbConnection.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Employees");
			List<Employees> empList = new ArrayList<>();
			while (rs.next()) {
				Employees employee = extractUserFromResultSet(rs);

				empList.add(employee);
			}
			conn.close();
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
		e.setCommission_pct(rs.getDouble("commission_pct"));
		e.setManager_id(rs.getInt("manager_id"));
		e.setDepartment_id(rs.getInt("department_id"));

		return e;
	}


}
