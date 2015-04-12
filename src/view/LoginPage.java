package view;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginPage extends JFrame{
	JLabel welcome, username, password, or;
	JTextField usernameField, passwordField;
	JButton login, signup, guest;
	JPanel usernameLine, passwordLine;
	
	LoginPage() {
		setSize(300, 300);
		setLocation(300,100);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		
		instantiateVariables();
		addComponents();
		addListeners();
		setResizable(false);
		setVisible(true);
	}
	
	private void instantiateVariables() {
		welcome = new JLabel("Slime Soccer!");
		username = new JLabel("Username: ");
		password = new JLabel("Password: ");
		or = new JLabel("- or -");
		
		welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
		or.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		usernameField = new JTextField(15);
		passwordField = new JTextField(15);
		
		login = new JButton("Login");
		signup = new JButton("Sign up");
		guest = new JButton("Continue as guest");
		
		login.setAlignmentX(Component.CENTER_ALIGNMENT);
		signup.setAlignmentX(Component.CENTER_ALIGNMENT);
		guest.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		usernameLine = new JPanel();
		passwordLine = new JPanel();
		
		usernameLine.setAlignmentX(Component.CENTER_ALIGNMENT);
		passwordLine.setAlignmentX(Component.CENTER_ALIGNMENT);
	}
	
	private void addComponents() {
		usernameLine.add(username);
		usernameLine.add(usernameField);
		
		passwordLine.add(password);
		passwordLine.add(passwordField);
		
		add(welcome);
		add(usernameLine);
		add(passwordLine);
		add(login);
		add(or);
		add(signup);
		add(guest);
	}
	
	private void addListeners() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String [] args) {
		new LoginPage();
	}
}
