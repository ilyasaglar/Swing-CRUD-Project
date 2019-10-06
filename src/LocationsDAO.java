import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LocationsDAO implements CustomDAO {

	private Locations location;

	public LocationsDAO() {
		super();
	}
	
	public Locations setLocation(Locations l) {
		return location = l;
	}
	
	public Locations getLocation(Integer id) {

		Connection connection = DbConnection.getConnection();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM locations WHERE location_id=" + id);
			if (rs.next()) {
				Locations l = new Locations();
				
				l.setLocation_id(rs.getInt(1));
				l.setStreet_address(rs.getString(2));
				l.setPostal_code(rs.getString(3));
				l.setCity(rs.getString(4));
				l.setState_province(rs.getString(4));
				l.setCountry_id(rs.getString(6));
				
				connection.close();
				return l;
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

			String inserting = "INSERT INTO locations "
					+ "(location_id, street_address, postal_code, city, state_province, country_id)"
					+ " values(?,?,?,?,?,?)";

			System.out.println("insert " + inserting);//
			PreparedStatement ps = connection.prepareStatement(inserting);

			ps.setInt(1, location.getLocation_id());
			ps.setString(2, location.getStreet_address());
			ps.setString(3, location.getPostal_code());
			ps.setString(4, location.getCity());
			ps.setString(5, location.getState_province());
			ps.setString(6, location.getCountry_id());
			
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
			String sql = "UPDATE locations "
					+ "SET street_address=?, postal_code=?, city=?, state_province=?, country_id=? "
					+ "WHERE location_id=" + location.getLocation_id();

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, location.getStreet_address());
			ps.setString(2, location.getPostal_code());
			ps.setString(3, location.getCity());
			ps.setString(4, location.getState_province());
			ps.setString(5, location.getCountry_id());
			
			int i = ps.executeUpdate();
			if (i == 1) {
				System.out.println(location.getLocation_id() + " güncellendi");
			}

			connection.close();
			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean delete(int location_id) {
		Connection connection = DbConnection.getConnection();

		try {
			Statement stmt = connection.createStatement();
			int i = stmt.executeUpdate("DELETE FROM locations WHERE location_id=" + location_id);
			if (i == 1) {
				System.out.println(location_id + " silindi");
			}
			connection.close();
			return true;
		} catch (SQLException ex) {
			System.out.println(ex);
			ex.printStackTrace();
			return false;
		}
	}
	
	public List<Locations> getAllData() {

		Connection conn = DbConnection.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Locations");
			List<Locations> locationList = new ArrayList<>();
			while (rs.next()) {
				Locations location = extractUserFromResultSet(rs);
				locationList.add(location);
			}
			conn.close();
			return locationList;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static Locations extractUserFromResultSet(ResultSet rs) throws SQLException {
		Locations l = new Locations();
		
		l.setLocation_id(rs.getInt("location_id"));
		l.setStreet_address(rs.getString("street_address"));
		l.setPostal_code(rs.getString("postal_code"));
		l.setCity(rs.getString("city"));
		l.setState_province(rs.getString("state_province"));
		l.setCountry_id(rs.getString("country_id"));
		
		return l;
	}
	


}
