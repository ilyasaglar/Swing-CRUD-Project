import java.sql.Connection;
import java.io.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	public boolean insertEmployee() { //Employees employee eklenmeli

		try {
			Connection connection = DbConnection.getConnection();

			String tr = "aa";
			String tr2 = "bbb";
			int id = 3;

			Employees e = new Employees(4, "TR", "Turkey");

			String inserting = "INSERT INTO countries(country_id,country_name,region_id) values(?,?,?)";
			System.out.println("insert " + inserting);//
			PreparedStatement ps = connection.prepareStatement(inserting);
			ps.setString(1, e.getFirst_name()); // <----- this
			ps.setString(2, e.getLast_name());// <---- and this
			ps.setInt(3, e.getEmployee_id());
			ps.executeUpdate();

			/*
			 * Statement stmt = connection.createStatement(); int rowsInserted =
			 * stmt.executeUpdate("insert into countries values (,'Turkey',4)");
			 * System.out.println(rowsInserted + " rows inserted");
			 */

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	
	public boolean updateEmployee() { //Employees employee eklenmeli
		Connection connection = DbConnection.getConnection();
		Employees e = new Employees(4,"TR", "tr");
		
		try {
			String sql ="UPDATE countries SET country_name=?, region_id=? WHERE country_id='" + e.getFirst_name() + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, e.getLast_name());
			ps.setInt(2, e.getEmployee_id());
			int i = ps.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;

	}

	@Override
	public boolean deleteEmployee(Employees employee) {
		return false;

	}

}
