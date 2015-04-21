package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlInstance {
	
	private Connection connect;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	  
	SqlInstance() {
		connect = null;
		statement = null;
		preparedStatement = null;
		resultSet = null;
		
		try {
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
		      
/*		      // PreparedStatements can use variables and are more efficient
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
		      writeMetaData(resultSet);*/

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
}