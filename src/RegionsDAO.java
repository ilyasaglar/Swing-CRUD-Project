import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RegionsDAO implements CustomDAO {
	
	private Regions region;

	public RegionsDAO() {
		super();
	}
	
	public Regions setJob(Regions r) {
		return region = r;
	}
	
	public Regions getJob(Integer id) {

		Connection connection = DbConnection.getConnection();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM regions WHERE job_id=" + id);
			if (rs.next()) {
				Regions r = new Regions();
				
				r.setRegion_id(rs.getInt(1));
				r.setRegion_name(rs.getString(2));
				
				connection.close();
				return r;
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

			String inserting = "INSERT INTO regions "
					+ "(region_id, region_name)"
					+ " values(?,?)";

			System.out.println("insert " + inserting);//
			PreparedStatement ps = connection.prepareStatement(inserting);
			
			ps.setInt(1, region.getRegion_id());
			ps.setString(2, region.getRegion_name());
			
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
			String sql = "UPDATE regions "
					+ "SET region_name=? "
					+ "WHERE region_id=" + region.getRegion_id();

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, region.getRegion_name());
			
			int i = ps.executeUpdate();
			if (i == 1) {
				System.out.println(region.getRegion_id()+ " güncellendi");
			}

			connection.close();
			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean delete(Integer region_id) {
		Connection connection = DbConnection.getConnection();

		try {
			Statement stmt = connection.createStatement();
			int i = stmt.executeUpdate("DELETE FROM regions WHERE region_id=" + region_id);
			if (i == 1) {
				System.out.println(region_id + " silindi");
			}
			connection.close();
			return true;
		} catch (SQLException ex) {
			System.out.println(ex);
			ex.printStackTrace();
			return false;
		}
	}

	public List<Regions> getAllData() {

		Connection conn = DbConnection.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Regions");
			List<Regions> regionList = new ArrayList<>();
			while (rs.next()) {
				Regions region = extractUserFromResultSet(rs);
				regionList.add(region);
			}
			conn.close();
			return regionList;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static Regions extractUserFromResultSet(ResultSet rs) throws SQLException {
		Regions r = new Regions();
		
		r.setRegion_id(rs.getInt("region_id"));
		r.setRegion_name(rs.getString("region_name"));
		
		return r;
	}
	
	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}

}
