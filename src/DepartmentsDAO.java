import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentsDAO implements CustomDAO {

	List<Departments> departments;
	private Departments department;

	public DepartmentsDAO() {
		departments = new ArrayList<Departments>();

	}

	public void getEmployee() { // ÝD ye göre ad ve soyadý getir

		Connection connection = DbConnection.getConnection();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM departments WHERE department_id=103");
			if (rs.next()) {
				Departments user = new Departments();
				user.setDepartment_id(rs.getInt(1));
				user.setDepartment_name(rs.getString(2));
				user.setManager_id(rs.getInt(3));
				user.setLocation_id(rs.getInt(4));
				System.out.println(user);

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public boolean insert() {
		try {
			Connection connection = DbConnection.getConnection();

			Departments department = new Departments(290, "Beng Beng", 110, 1500);
			

			String inserting = "INSERT INTO employees (department_id, department_name, manager_id, location_id)"
					+ " values(?,?,?,?)";

			System.out.println("insert " + inserting);//
			PreparedStatement ps = connection.prepareStatement(inserting);

			ps.setInt(1, department.getDepartment_id());
			ps.setString(2, department.getDepartment_name());
			ps.setInt(3, department.getManager_id());
			ps.setInt(4, department.getLocation_id());
			ps.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;

	}

	@Override
	public boolean update() {

		return false;
	}

	@Override
	public boolean delete(int employee_id) {
		// TODO Auto-generated method stub
		return false;
	}




}
