import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String USER = "HR";
	public static final String PASS = "hr";

	/**
	 * Get a connection to database
	 * 
	 * @return Connection object
	 */
	public static Connection getConnection() {
		try {
			Connection myConn = DriverManager.getConnection(URL, USER, PASS);
			return DriverManager.getConnection(URL, USER, PASS);

		} catch (SQLException ex) {
			throw new RuntimeException("Error connecting to the database", ex);
		}
	}

	/**
	 * Test Connection
	 */
	public static void main(String[] args) {

		try {
			Connection connection = DbConnection.getConnection();
			EmployeesDAO e = new EmployeesDAO();
			e.getEmployee();
			System.out.println("------------");

			e.insertEmployee();
			// e.updateEmployee();
			// e.deleteEmployee();

			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
