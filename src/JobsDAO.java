import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobsDAO implements CustomDAO {
	
	private Jobs job;

	public JobsDAO() {
		super();
	}
	
	public Jobs setJob(Jobs j) {
		return job = j;
	}

	public Jobs getJob(String id) {

		Connection connection = DbConnection.getConnection();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM jobs WHERE job_id=" + id);
			if (rs.next()) {
				Jobs j = new Jobs();
				
				j.setJob_id(rs.getString(1));
				j.setJob_title(rs.getString(2));
				j.setMin_salary(rs.getInt(3));
				j.setMax_salary(rs.getInt(4));
				
				connection.close();
				return j;
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

			String inserting = "INSERT INTO jobs (job_id, job_title, min_salary, max_salary)"
					+ " values(?,?,?,?)";

			System.out.println("insert " + inserting);//
			PreparedStatement ps = connection.prepareStatement(inserting);

			ps.setString(1, job.getJob_id());
			ps.setString(2, job.getJob_title());
			ps.setInt(3, job.getMin_salary());
			ps.setInt(4, job.getMax_salary());
			
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

			String sql = "UPDATE jobs "
					+ "SET job_title=?, min_salary=?, max_salary=? "
					+ "WHERE job_id=" + job.getJob_id();

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, job.getJob_title());
			ps.setInt(2, job.getMin_salary());
			ps.setInt(3, job.getMax_salary());
			
			int i = ps.executeUpdate();
			if (i == 1) {
				System.out.println(job.getJob_id() + " güncellendi");
			}

			connection.close();
			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}
	

	
	public List<Jobs> getAllData() {

		Connection conn = DbConnection.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Jobs");
			List<Jobs> jobList = new ArrayList<>();
			while (rs.next()) {
				Jobs job = extractUserFromResultSet(rs);
				jobList.add(job);
			}
			conn.close();
			return jobList ;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static Jobs extractUserFromResultSet(ResultSet rs) throws SQLException {
		Jobs j = new Jobs();
		
		j.setJob_id(rs.getString("job_id"));
		j.setJob_title(rs.getString("job_title"));
		j.setMin_salary(rs.getInt("min_salary"));
		j.setMax_salary(rs.getInt("max_salary"));
		
		return j;
	}

	@Override
	public boolean delete(int job_id) {
		Connection connection = DbConnection.getConnection();

		try {
			Statement stmt = connection.createStatement();
			int i = stmt.executeUpdate("DELETE FROM jobs WHERE job_id='" + job_id + "'");
			if (i == 1) {
				System.out.println(job_id + " silindi");
			}
			connection.close();
			return true;
		} catch (SQLException ex) {
			System.out.println(ex);
			ex.printStackTrace();
			return false;
		}
	}


}
