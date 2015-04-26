package controller;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



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
	
	private ResultSet getUser(String u) {
		try {
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM account_data WHERE username = '" + u + "'");
			
			if (rs.next()) return rs;
		} catch (SQLException sqle) {}
		
		return null;
	}
	
	public boolean findUser(String u) {
		if (getUser(u) != null) return true;
		else return false;
	}
	
	public boolean validateLogin(String u, String pw) {
		ResultSet rs = getUser(u);
		
		if (rs == null) return false;
		
		System.out.println(rs == null);
		
		try {
			if (rs.getString("password").equals(pw)) {
				return true;
			}
		} catch (SQLException sqle) {}
			
		return false;
	}
	
	public void register(String fn, String ln, String u, String pw, int img, String desc) {
		try {
			ps = c.prepareStatement("INSERT INTO account_data (player_firstName, player_lastName, username, password, player_avatar, player_description) VALUES (?, ?, ?, ?, ?, ?)");
			ps.setString(1, fn);
			ps.setString(2,  ln);
			ps.setString(3,  u);
			ps.setString(4,  pw);
			ps.setObject(5,  img);
			ps.setString(6, desc);
			ps.executeUpdate();
			
		} catch (SQLException sqle) {
			System.out.println("SQL Exception in SqlInstance.register: " + sqle.getMessage());
		}
	}
	
	public void setAchievement(String u, model.Achievement ach) {
		ResultSet u_rs = getUser(u);
		
		try {
			int userID = u_rs.getInt("userID");
			ps = c.prepareStatement("INSERT INTO achievement_data (achievement, userID) VALUE (?, ?)");
			ps.setObject(1, ach);
			ps.setInt(2, userID);
			ps.executeUpdate();
			
		} catch (SQLException sqle) {
			System.out.println("ERROR: " + sqle.getMessage());
		}
	}
	
	public ArrayList<model.Achievement> getAchievements(String u) {
		ResultSet u_rs = getUser(u);
		
		ArrayList<model.Achievement> achieve = new ArrayList<model.Achievement>();
		
		try {
			int userID = u_rs.getInt("userID");
			Statement st = c.createStatement();
			ResultSet a_rs = st.executeQuery("SELECT achievement FROM achievement_data WHERE userID = " + userID);
			while (a_rs.next()) {
				byte[] obj = (byte[])a_rs.getObject("achievement");
				
				//convert byte array into object!!!
				ByteArrayInputStream in = new ByteArrayInputStream(obj);
			    ObjectInputStream is = new ObjectInputStream(in);
				achieve.add((model.Achievement)(is.readObject()));
			}
		} catch (SQLException sqle) {} 
		catch (IOException e) { System.out.println("ioe error");} 
		catch (ClassNotFoundException e) {System.out.println("cnfe error");}
		return achieve;
	}
	
	public static void main (String [] args) {
		SqlInstance sql = new SqlInstance();
		sql.setAchievement("zhelo", view.LoginPage.ach[6]);
		ArrayList<model.Achievement> a = sql.getAchievements("zhelo");
		for (int i = 0; i < a.size(); i++) {
			System.out.println(a.get(i).getName());
			System.out.println(a.get(i).getDescription());
		}
	}
}