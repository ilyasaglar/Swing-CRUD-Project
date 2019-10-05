import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountriesDAO implements CustomDAO {


	List<Countries> countries;
	private Countries country;

	public CountriesDAO() {
		countries = new ArrayList<Countries>();

	}
	
	public void getCountry() {

		Connection connection = DbConnection.getConnection();
		
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM countries WHERE country_id=AR");
			if (rs.next()) {
				Countries user = new Countries();
				user.setCountry_id(rs.getString(1));
				user.setCountry_name(rs.getString(2));
				user.setRegion_id(rs.getInt(3));
				System.out.println(user);

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}
	
	public boolean insert() {
		
		try {
			Connection connection = DbConnection.getConnection();

			Countries c = new Countries("CA", "Canada", 2);
			String inserting = "INSERT INTO countries (country_id, country_name, region_id, job_id)"+ " values(?,?,?)";

			System.out.println("insert " + inserting);
			PreparedStatement ps = connection.prepareStatement(inserting);

			ps.setString(1, c.getCountry_id());
			ps.setString(2, c.getCountry_name());
			ps.setInt(3, c.getRegion_id());
			ps.executeUpdate();


		} catch (SQLException e) {

			e.printStackTrace();
		} 

		return false;
	}

	
	public boolean update() {
		
		Connection connection = DbConnection.getConnection();
		Countries c = new Countries("DE", "Germany", 1);

		try {
			String sql = "UPDATE countries SET country_id=?, country_name=?"
					+ " WHERE Region_id='" + c.getRegion_id() + "'";

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, c.getCountry_id());
			ps.setString(2, c.getCountry_name());
			ps.setInt(3, c.getRegion_id());

			int i = ps.executeUpdate();
			if (i == 1) {
				System.out.println(c.getCountry_id() + " güncellendi");
				return true;

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		
		return false;

	}

	
	@Override
	public boolean delete(int employee_id) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
