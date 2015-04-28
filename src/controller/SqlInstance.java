package controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
		} catch (SQLException sqle) {
			System.out.println("SQLException in SqlInstance.getUser: " + sqle.getMessage());
		}
		
		return null;
	}
	
	public boolean findUser(String u) {
		if (getUser(u) != null) return true;
		else return false;
	}
	
	public boolean validateLogin(String u, String pw) {
		ResultSet rs = getUser(u);
		
		if (rs == null) return false;
		
		try {
			if (rs.getString("password").equals(pw)) {
				return true;
			}
		} catch (SQLException sqle) {
			System.out.println("SQLException in SqlInstance.validateLogin: " + sqle.getMessage());
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
			ps.setObject(5,  img);
			ps.setString(6, desc);
			ps.executeUpdate();
			
		} catch (SQLException sqle) {
			System.out.println("SQL Exception in SqlInstance.register: " + sqle.getMessage());
		}
	}
	
	public void setAchievement(String u, model.Achievement ach) {
		if (checkAchievement(u, ach.getName())) return;
		
		ResultSet u_rs = getUser(u);
		
		try {
			int userID = u_rs.getInt("userID");
			ps = c.prepareStatement("INSERT INTO achievement_data (achievement, userID, achievementName) VALUE (?, ?, ?)");
			ps.setObject(1, ach);
			ps.setInt(2, userID);
			ps.setString(3,  ach.getName());
			ps.executeUpdate();
			
		} catch (SQLException sqle) {
			System.out.println("SQLException in SqlInstance.setAchievement: " + sqle.getMessage());
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
		} catch (SQLException sqle) {
			System.out.println("SQLException in SqlInstance.getAchievements: " + sqle.getMessage());
		} 
		catch (IOException e) { 
			System.out.println("IOException in SqlInstance.getAchievements: " + e.getMessage());
		} 
		catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException in SqlInstance.getAchievements: " + e.getMessage());
		}
		return achieve;
	}
	
	public boolean checkAchievement(String u, String ach) {
		ResultSet u_rs = getUser(u);
		
		try {
			int userID = u_rs.getInt("userID");
			Statement st = c.createStatement();
			ResultSet a_rs = st.executeQuery("SELECT achievement FROM achievement_data WHERE userID = " + userID + " AND achievementName = '" + ach + "'");
			if (a_rs.next()) return true;
		} catch (SQLException sqle) {
			System.out.println("SQLException in SqlInstance.checkAchievement: " + sqle.getMessage());
		}
		
		return false;
	}
	
	public void updateStats(String u, boolean wl) {
		//if wl is true, user won
		//if wl is false, user lost
		
		ResultSet u_rs = getUser(u);
		
		try {
			int userID = u_rs.getInt("userID");
			int numgames = u_rs.getInt("player_games") + 1;
			int numwin = u_rs.getInt("player_won");
			int numloss = u_rs.getInt("player_loss");
			long ratio = u_rs.getLong("player_ratio");
			int gamesLost = u_rs.getInt("gamesLost");
			
			if (wl) {
				numwin++;
				gamesLost = 0;
			}
			else {
				numloss++;
				gamesLost++;
			}
			if (numloss > 0) {
				ratio = (long) numwin/ (long) numloss;
			} else ratio = 0;
			
			ps = c.prepareStatement("UPDATE account_data SET player_games = ?, player_won = ?, player_loss = ?,"
					+ " player_ratio = ?, gamesLost = ? WHERE userID = ?");
			ps.setInt(1,  numgames);
			ps.setInt(2,  numwin);
			ps.setInt(3,  numloss);
			ps.setLong(4, ratio);
			ps.setInt(5,  gamesLost);
			ps.setInt(6,  userID);
			ps.executeUpdate();
			
		} catch (SQLException sqle) {
			System.out.println("SQLException in SqlInstance.updateStats: " + sqle.getMessage());
		}
	}
	
	public String getName(String u) {
		ResultSet u_rs = getUser(u);
		String name = "";
		try {
			name = u_rs.getString("player_firstName") + " " + u_rs.getString("player_lastName");
		} catch (SQLException sqle) {
			System.out.println("SQLException in SqlInstance.getName: " + sqle.getMessage());
		} 
		return name;
	}
	
	public String getDesc(String u) {
		ResultSet u_rs = getUser(u);
		String desc = "";
		try {
			desc = u_rs.getString("player_description");
		} catch (SQLException sqle) {
			System.out.println("SQLException in SqlInstance.getDesc: " + sqle.getMessage());
		} 
		return desc;
	}
	
	public int getImage(String u) {
		ResultSet u_rs = getUser(u);
		int img = -1;
		try {
			img = u_rs.getInt("player_avatar");
		} catch (SQLException sqle) {
			System.out.println("SQLException in SqlInstance.getImage: " + sqle.getMessage());
		} 
		return img;
	}
	
	public long getRatio(String u) {
		ResultSet u_rs = getUser(u);
		long ratio = 0;
		try {
			ratio = u_rs.getLong("player_ratio");
		} catch (SQLException sqle) {
			System.out.println("SQLException in SqlInstance.getRatio: " + sqle.getMessage());
		} 
		return ratio;
	}
	
	public int getGames(String u) {
		ResultSet u_rs = getUser(u);
		int games = -1;
		try {
			games = u_rs.getInt("player_games");
		} catch (SQLException sqle) {
			System.out.println("SQLException in SqlInstance.getGames: " + sqle.getMessage());
		} 
		return games;
	}
	
	public int getGamesLostInARow(String u) {
		ResultSet u_rs = getUser(u);
		int row = -1;
		try {
			row = u_rs.getInt("gamesLost");
		} catch (SQLException sqle) {
			System.out.println("SQLException in SqlInstance.getGamesLostInARow: " + sqle.getMessage());
		} 
		return row;
	}
	
	public int getWins(String u) {
		ResultSet u_rs = getUser(u);
		int wins = -1;
		try {
			wins = u_rs.getInt("player_won");
		} catch (SQLException sqle) {
			System.out.println("SQLException in SqlInstance.getWins: " + sqle.getMessage());
		} 
		return wins;
	}
	
	public int getLosses(String u) {
		ResultSet u_rs = getUser(u);
		int loss = -1;
		try {
			loss = u_rs.getInt("player_loss");
		} catch (SQLException sqle) {
			System.out.println("SQLException in SqlInstance.getLosses: " + sqle.getMessage());
		} 
		return loss;
	}
	
	public void changePassword(String u, String pw) {
		ResultSet u_rs = getUser(u);
		
		try {
			int userID = u_rs.getInt("userID");
			ps = c.prepareStatement("UPDATE account_data SET password = ? WHERE userID = ?");
			ps.setString(1, pw);
			ps.setInt(2, userID);
			ps.executeUpdate();
			
		} catch (SQLException sqle) {
			System.out.println("SQLException in SqlInstance.changePassword: " + sqle.getMessage());
		}
	}
	
	public void setFriends(String u, String fu) {
		if (findFriend(u, fu)) return;
		
		ResultSet u_rs = getUser(u);
		ResultSet fu_rs = getUser(fu);
		
		try {
			int fuserID = fu_rs.getInt("userID");
			int userID = u_rs.getInt("userID");
			ps = c.prepareStatement("INSERT INTO friends (userID, friendID, friendName) VALUE (?, ?, ?)");
			ps.setInt(1, userID);
			ps.setInt(2, fuserID);
			ps.setString(3,  fu);
			ps.executeUpdate();
			
		} catch (SQLException sqle) {
			System.out.println("SQLException in SqlInstance.setFriends: " + sqle.getMessage());
		}
	}
	
	public ArrayList<String> getFriends(String u) {
		ResultSet u_rs = getUser(u);
		
		ArrayList<String> franz = new ArrayList<String>();
		try {
			int userID = u_rs.getInt("userID");
			Statement st = c.createStatement();
			ResultSet f_rs = st.executeQuery("SELECT friendName FROM friends WHERE userID = " + userID);
			while (f_rs.next()) {
				franz.add(f_rs.getString("friendName"));
			}
		} catch (SQLException sqle) {
			System.out.println("SQLException in SqlInstance.getFriends: " + sqle.getMessage());
		} 
		return franz;
	}
	
	public boolean findFriend(String u, String fu) {
		ResultSet u_rs = getUser(u);
		
		try {
			int userID = u_rs.getInt("userID");
			Statement st = c.createStatement();
			ResultSet f_rs = st.executeQuery("SELECT friendID FROM friends WHERE userID = " + userID + " AND friendName = '" + fu + "'");
			if (f_rs.next()) return true;
		} catch (SQLException sqle) {
			System.out.println("SQLException in SqlInstance.findFriend: " + sqle.getMessage());
		} 
		return false;
	}
	
	public void changeAvatar(String u, int ava) {
		ResultSet u_rs = getUser(u);
		
		try {
			int userID = u_rs.getInt("userID");
			ps = c.prepareStatement("UPDATE account_data SET player_avatar = ? WHERE userID = ?");
			ps.setInt(1, ava);
			ps.setInt(2, userID);
			ps.executeUpdate();
			
		} catch (SQLException sqle) {
			System.out.println("SQLException in SqlInstance.changePassword: " + sqle.getMessage());
		}
	}
	
	public boolean checkLoggedIn (String u) {
		ResultSet u_rs = getUser(u);
		boolean log = false;
		
		try {
			log = u_rs.getBoolean("player_loggedOn");
		} catch (SQLException sqle) {
			System.out.println("SQLException in SqlInstance.checkLoggedIn: " + sqle.getMessage());
		}
		return log;
	}
	
	public void toggleLog (String u) {
		ResultSet u_rs = getUser(u);
		
		try {
			boolean log = u_rs.getBoolean("player_loggedOn");
			ps = c.prepareStatement("UPDATE account_data SET player_loggedOn = ? WHERE username = ?");
			ps.setBoolean(1, !log);
			ps.setString(2,  u);
			ps.executeUpdate();
			
		} catch (SQLException sqle) {
			System.out.println("SQLException in SqlInstance.logout: " + sqle.getMessage());
		}
	}
}