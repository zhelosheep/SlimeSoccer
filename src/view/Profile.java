package view;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public abstract class Profile extends JFrame{
	private String u;
	private JFrame prevScreen;
	
	private JTextField searchField;
	private JButton searchButton, backButton;
	
	Profile (String username, JFrame prev) {
		u = username;
		prevScreen = prev;
		
		setSize(800, 600);
		setLocation(300,100);
		instantiateVariables();
		addComponents();
		addListeners();
		setResizable(false);
	}
	
	private void instantiateVariables() {
		searchField = new JTextField(200);
		searchButton = new JButton("Search for a user: ");
		backButton = new JButton("Back");
		
	}
	
	private void addComponents() {
		String name = LoginPage.sqli.getName(u);
		ImageIcon avatar = SignUpPage.avatarImages[LoginPage.sqli.getImage(u)];
		String desc = LoginPage.sqli.getDesc(u);
		int wins = LoginPage.sqli.getWins(u);
		int loss = LoginPage.sqli.getLosses(u);
		ArrayList<model.Achievement> ach = LoginPage.sqli.getAchievements(u);
		
		JLabel slimeSoccerLabel = new JLabel("Slime Soccer");
		slimeSoccerLabel.setFont(new Font("Arial", Font.BOLD, 20));
		slimeSoccerLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
		backButton.setFont(new Font("Arial", Font.BOLD, 16));
		backButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 15));
		
		
	}
	
	private void addListeners() {
		
	}
	
	/*public static void main (String [] args) {
		Profile p = new Profile("zhelo", null);
		p.setVisible(true);
	}*/
}
