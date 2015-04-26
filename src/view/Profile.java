package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Profile extends JFrame{
	private String u;
	private JFrame prevScreen;
	
	private JTextField searchField;
	private JButton search;
	
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
		search = new JButton("Search for a user: ");
	}
	
	private void addComponents() {
		String name = LoginPage.sqli.getName(u);
		ImageIcon avatar = SignUpPage.avatarImages[LoginPage.sqli.getImage(u)];
		String desc = LoginPage.sqli.getDesc(u);
		
	}
	
	private void addListeners() {
		
	}
	
	public static void main (String [] args) {
		Profile p = new Profile("zhelo", null);
		p.setVisible(true);
	}
}
