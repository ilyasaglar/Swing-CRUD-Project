import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    public static final String USER = "hr";
    public static final String PASS = "hr";
    /**
     * Get a connection to database
     * @return Connection object
     */
    public static Connection getConnection()
    {
      try {
    	  Connection myConn = DriverManager.getConnection(URL, USER, PASS);
         // DriverManager.registerDriver(new Driver());
          return myConn;
      } catch (SQLException ex) {
          throw new RuntimeException("Error connecting to the database", ex);
      }
    }
    /**
     * Test Connection
     */
    public static void main(String[] args) {
        Connection connection = MyConnection.getConnection();
    }
}

