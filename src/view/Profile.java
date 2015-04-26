package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Profile extends JFrame{
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
		searchField = new JTextField(45);
		searchButton = new JButton("Search");
		backButton = new JButton("Back");
		
	}
	
	private void addComponents() {
		JLabel name = new JLabel(LoginPage.sqli.getName(u));
		ImageIcon avatar = SignUpPage.avatarImages[LoginPage.sqli.getImage(u)];
		JLabel desc = new JLabel(LoginPage.sqli.getDesc(u));
		int wins = LoginPage.sqli.getWins(u);
		int loss = LoginPage.sqli.getLosses(u);
		ArrayList<model.Achievement> ach = LoginPage.sqli.getAchievements(u);
		
		JPanel northPanel = new JPanel();
		JPanel header = new JPanel();
		JPanel searchBar = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel left = new JPanel();
		JPanel otherSide = new JPanel();
		JPanel bottomRight = new JPanel();
		JPanel middle = new JPanel();
		JPanel right = new JPanel();
		
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
		header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		otherSide.setLayout(new BoxLayout(otherSide, BoxLayout.Y_AXIS));
		middle.setLayout(new BoxLayout(middle, BoxLayout.Y_AXIS));
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		
		JLabel slimeSoccerLabel = new JLabel("Slime Soccer");
		slimeSoccerLabel.setFont(new Font("Arial", Font.BOLD, 20));
		slimeSoccerLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
		
		backButton.setFont(new Font("Arial", Font.BOLD, 16));
		backButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 15));
		
		header.add(slimeSoccerLabel);
		header.add(Box.createGlue());
		header.add(backButton);
		searchBar.add(new JLabel("Search for a user :"));
		searchBar.add(searchField);
		searchBar.add(searchButton);
		left.add(name);
		left.add(new JLabel(u));
		left.add(new JLabel(avatar));
		otherSide.add(desc);
		
		northPanel.add(header);
		northPanel.add(searchBar);
		bottomRight.add(middle);
		bottomRight.add(right);
		otherSide.add(bottomRight);
		centerPanel.add(left);
		centerPanel.add(otherSide);
		
		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		
	}
	
	private void addListeners() {
		
	}
	
	public static void main (String [] args) {
		Profile p = new Profile("zhelo", null);
		p.setVisible(true);
	}
}
