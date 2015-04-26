package controller;

import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlInstance {
	
	private Connection c;
	private PreparedStatement ps;
	  
	private String connection = "jdbc:mysql://slimesoccerdbinstance.cbx5rxifld4q.us-west-2.rds.amazonaws.com/";
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String db = "slime_soccer_db";
	private static final String user = "ttrojan";
	private static final String password = "thinmints";
	
	
	
	public SqlInstance() {
		c = null;
		
		try {
			//load driver
			Class.forName(driver);
			
			//establish connection
			c = DriverManager.getConnection(connection + db, user, password);
			
			
		} catch (ClassNotFoundException cnfe) {
			System.out.println("CNFE in SqlInstance constructor: " + cnfe.getMessage());
		} catch (SQLException sqle) {
			System.out.println("SQLException in SqlInstance constructor: " + sqle.getMessage());
		}
	}
	
	public boolean findUser(String u) {
		try {
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM account_data WHERE username = '" + u + "'");
			
			if (rs.next()) return true;
		} catch (SQLException sqle) {
			System.out.println("SQLException in SqlInstance.login: " + sqle.getMessage());
		}
		
		return false;
	}
	
	public boolean validateLogin(String u, String pw) {
		if (!findUser(u)) return false;
		
		try {
			Statement st = c.createStatement();
			ResultSet rs_temp = st.executeQuery("SELECT * FROM account_data WHERE username = '" + u + "'");
			
			if (rs_temp.next()) {
				String pass = rs_temp.getString("password");
				if (pass.equals(pw)) {
					return true;
				}
			}
		} catch (SQLException e) {
			System.out.println("SQLException in SqlInstance.validateLogin: " + e.getMessage());
		}
		
		return false;
	}
	
	public void register(String fn, String ln, String u, String pw, int img, String desc) {
		try {
			ps = c.prepareStatement("INSERT INTO account_data (player_firstName, player_lastName, username, password, player_avatar, player_description) VALUES (?, ?, ?, ?, ?, ?)");
			ps.setString(1, fn);
			ps.setString(2,  ln);
			ps.setString(3,  u);
			ps.setString(4,  pw);
			ps.setInt(5,  img);
			ps.setString(6, desc);
			ps.executeUpdate();
			
		} catch (SQLException sqle) {
			System.out.println("SQL Exception in SqlInstance.register: " + sqle.getMessage());
		}
	}
	
}
	
/*		try {
			// This will load the MySQL driver, each DB has its own driver
		      Class.forName("com.mysql.jdbc.Driver");
		      // Setup the connection with the DB
		      connect = DriverManager.getConnection("jdbc:mysql://localhost/users?user=root&password=rootpassword", "root", "");
		      
		      // Statements allow to issue SQL queries to the database
		      statement = connect.createStatement();
		      // Result set get the result of the SQL query
		      resultSet = statement.executeQuery("SELECT * FROM ball_data");
		      writeResultSet(resultSet);

		      
		      preparedStatement = connect.prepareStatement("INSERT INTO ball_data VALUES (default, ?, ?, ?, ?, ?)");
		      //populates the table
		      preparedStatement.setString(1, "5");
		      preparedStatement.setString(2, "Testing");
		      preparedStatement.setString(3, "TestWebpage");
		      preparedStatement.setString(4, "No");
		      preparedStatement.executeUpdate();
		      
		      preparedStatement = connect.prepareStatement("SELECT * FROM ball_data");
		      resultSet = preparedStatement.executeQuery();
		      writeResultSet(resultSet);
		      
		      resultSet = statement.executeQuery("SELECT * FROM ball_data");
		      writeMetaData(resultSet);
		      
		      // PreparedStatements can use variables and are more efficient
		      preparedStatement = connect
		          .prepareStatement("insert into  feedback.comments values (default, ?, ?, ?, ?, ?, ?)");
		      // "myuser, webpage, datum, summery, COMMENTS from feedback.comments");
		      // Parameters start with 1
		      preparedStatement.setString(1, "Test");
		      preparedStatement.setString(2, "TestEmail");
		      preparedStatement.setString(3, "TestWebpage");
		      preparedStatement.setDate(4, new Date(2009, 12, 11));
		      preparedStatement.setString(5, "TestSummary");
		      preparedStatement.setString(6, "TestComment");
		      preparedStatement.executeUpdate();

		      preparedStatement = connect
		          .prepareStatement("SELECT myuser, webpage, datum, summery, COMMENTS from feedback.comments");
		      resultSet = preparedStatement.executeQuery();
		      writeResultSet(resultSet);
				
		      // Remove again the insert comment
		      preparedStatement = connect
		      .prepareStatement("delete from feedback.comments where myuser= ? ; ");
		      preparedStatement.setString(1, "Test");
		      preparedStatement.executeUpdate();
		      
		      resultSet = statement
		      .executeQuery("select * from feedback.comments");
		      writeMetaData(resultSet);

		} catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Class not found exception: " + cnfe.getMessage());
		} finally {
			close();
		}
	}
	
	private void writeResultSet(ResultSet resultSet) throws SQLException {
	    // ResultSet is initially before the first data set
	    while (resultSet.next()) {
	      // It is possible to get the columns via name
	      // also possible to get the columns via the column number
	      // which starts at 1
	      // e.g. resultSet.getSTring(2);
	      String user = resultSet.getString("username");
	      String firstname = resultSet.getString("firstname");
	      String lastname = resultSet.getString("lastname");
	      String password = resultSet.getString("password");
	      System.out.println("User: " + user);
	      System.out.println("First name: " + firstname);
	      System.out.println("Last name: " + lastname);
	      System.out.println("Password: " + password);
	    }
	  }
	
	private void writeMetaData(ResultSet resultSet) throws SQLException {
	    //   Now get some metadata from the database
	    // Result set get the result of the SQL query
	    
	    System.out.println("The columns in the table are: ");
	    
	    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
	    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
	      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
	    }
	  }
	
	private void close() {
	    try {
	      if (resultSet != null) {
	        resultSet.close();
	      }

	      if (statement != null) {
	        statement.close();
	      }

	      if (connect != null) {
	        connect.close();
	      }
	    } catch (Exception e) {

	    }
	  }
	
	public static void main (String [] args) {
		new SqlInstance();
	}
}*/