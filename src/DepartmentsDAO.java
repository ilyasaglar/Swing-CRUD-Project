import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DepartmentsDAO implements CustomDAO {

	// List<Departments> departments;
	private Departments department;

	public DepartmentsDAO() {
		// departments = new ArrayList<Departments>();
	}

	public Departments setDepartment(Departments d) {
		return department = d;
	}

	public Departments getDepartment(Integer id) {

		Connection connection = DbConnection.getConnection();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM departments WHERE department_id=" + id);
			if (rs.next()) {
				Departments d = new Departments();
				d.setDepartment_id(rs.getInt(1));
				d.setDepartment_name(rs.getString(2));
				d.setManager_id(rs.getInt(3));
				d.setLocation_id(rs.getInt(4));
				System.out.println(d);
				connection.close();
				return d;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insert() {
		try {
			Connection connection = DbConnection.getConnection();

			String inserting = "INSERT INTO departments (department_id, department_name, manager_id, location_id)"
					+ " values(?,?,?,?)";

			System.out.println("insert " + inserting);//
			PreparedStatement ps = connection.prepareStatement(inserting);

			ps.setInt(1, department.getDepartment_id());
			ps.setString(2, department.getDepartment_name());
			ps.setInt(3, department.getManager_id());
			ps.setInt(4, department.getLocation_id());
			ps.executeUpdate();

			connection.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update() {
		Connection connection = DbConnection.getConnection();

		try {

			String sql = "UPDATE departments " + "SET department_name=?, manager_id=?, location_id=? "
					+ "WHERE employee_id=" + department.getDepartment_id();

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, department.getDepartment_name());
			ps.setInt(2, department.getManager_id());
			ps.setInt(3, department.getLocation_id());

			int i = ps.executeUpdate();
			if (i == 1) {
				System.out.println(department.getDepartment_id() + " güncellendi");
			}

			connection.close();
			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean delete(int department_id) {

		Connection connection = DbConnection.getConnection();

		try {
			Statement stmt = connection.createStatement();

			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(null,
					"Department Bilgilerini Silmek Ýstediðinize emin misiniz?", "Onay Ekraný", dialogButton);
			if (dialogResult == JOptionPane.YES_OPTION) {

				int i = stmt.executeUpdate("DELETE FROM departments WHERE department_id=" + department_id);
				if (i == 1) {
					System.out.println(department_id + " silindi");
					connection.close();

				}
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

	public List<Departments> getAllData() {

		Connection conn = DbConnection.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Departments");
			List<Departments> depList = new ArrayList<>();
			while (rs.next()) {
				Departments department = extractUserFromResultSet(rs);

				depList.add(department);
			}
			conn.close();
			return depList;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static Departments extractUserFromResultSet(ResultSet rs) throws SQLException {
		Departments d = new Departments();

		d.setDepartment_id(rs.getInt("department_id"));
		d.setDepartment_name(rs.getString("department_name"));
		d.setLocation_id(rs.getInt("location_id"));
		d.setManager_id(rs.getInt("manager_id"));

		return d;
	}

}
