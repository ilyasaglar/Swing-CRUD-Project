import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CountriesDAO implements CustomDAO {

	private Countries country;

	public CountriesDAO() {
		super();
	}

	public Countries setCountry(Countries c) {
		return country = c;
	}

	public Countries getCountry(String id) {

		Connection connection = DbConnection.getConnection();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM countries WHERE country_id=" + id);
			if (rs.next()) {
				Countries c = new Countries();

				c.setCountry_id(rs.getString(1));
				c.setCountry_name(rs.getString(2));
				c.setRegion_id(rs.getInt(3));

				connection.close();
				return c;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public boolean insert() {

		try {
			Connection connection = DbConnection.getConnection();

			CallableStatement stmt = connection.prepareCall("{call PR_INSERT_COUNTRIES(?,?,?)}");

			stmt.setString(1, country.getCountry_id());
			stmt.setString(2, country.getCountry_name());
			stmt.setInt(3, country.getRegion_id());
			stmt.executeUpdate();
			connection.close();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update() {
		Connection connection = DbConnection.getConnection();

		try {
			String sql = "UPDATE countries " + "SET country_name=?, region_id=?" + " WHERE country_id='"
					+ country.getCountry_id() + "'";

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, country.getCountry_name());
			ps.setInt(2, country.getRegion_id());

			int i = ps.executeUpdate();
			if (i == 1) {
				System.out.println(country.getCountry_id() + " 'ye ait countries bilgileri güncellendi");
			}
			connection.close();
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean delete(int country_id) {

		return true;
	}

	public boolean delete2(String country_id) {
		Connection connection = DbConnection.getConnection();

		try {
			Statement stmt = connection.createStatement();

			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to delete Country Information.", "Warning", dialogButton);
			if (dialogResult == JOptionPane.YES_OPTION) {

				int i = stmt.executeUpdate("DELETE FROM countries WHERE country_id='" + country_id + "'");
				if (i == 1) {
					System.out.println(country_id + " silindi");
				}
				connection.close();
				return true;
			} else {
				connection.close();
				return false;
			}

		} catch (SQLException ex) {
			System.out.println(ex);
			ex.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), "An error occured.", "Error!", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	public List<Countries> getAllData() {

		Connection conn = DbConnection.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Countries");
			List<Countries> countryList = new ArrayList<>();
			while (rs.next()) {
				Countries job = extractUserFromResultSet(rs);
				countryList.add(job);
			}
			conn.close();
			return countryList;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static Countries extractUserFromResultSet(ResultSet rs) throws SQLException {
		Countries c = new Countries();

		c.setCountry_id(rs.getString("country_id"));
		c.setCountry_name(rs.getString("country_name"));
		c.setRegion_id(rs.getInt("region_id"));

		return c;
	}

}
