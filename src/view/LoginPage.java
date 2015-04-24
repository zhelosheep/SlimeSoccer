package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginPage extends JFrame{
	private static final long serialVersionUID = 1;
	private JPanel background;
	private JTextField usernameField, passwordField;
	private JButton login, signup, guest;
	private BufferedImage backgroundImage;
	
	public LoginPage() {
		setSize(800, 600);
		setLocation(300,100);
		
		instantiateVariables();
		addComponents();
		addListeners();
		setResizable(false);
		setVisible(true);
	}
	
	private void instantiateVariables() {
		background = new JPanel();
		background.setLayout(null);
		
		usernameField = new JTextField(15);
		passwordField = new JTextField(15);
		
		login = new JButton("Login");
		signup = new JButton("Sign up");
		guest = new JButton("Continue as guest");
		
		login.setAlignmentX(Component.CENTER_ALIGNMENT);
		signup.setAlignmentX(Component.CENTER_ALIGNMENT);
		guest.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		try {
			backgroundImage = ImageIO.read(new File("./resources/SlimeSoccerLogin.png"));
		} catch (IOException e) {
			System.out.println("IOException in instantiateVariables (trying to load image): " + e.getMessage());
		}
	}
	
	private void addComponents() {
		JLabel welcome = new JLabel("Slime Soccer!");
		JLabel username = new JLabel("Username: ");
		JLabel password = new JLabel("Password: ");
		JPanel usernameLine = new JPanel();
		JPanel passwordLine = new JPanel();

		welcome.setFont(new Font("Arial", Font.BOLD, 20));
		welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
		usernameLine.setAlignmentX(Component.CENTER_ALIGNMENT);
		passwordLine.setAlignmentX(Component.CENTER_ALIGNMENT);
		usernameLine.add(username);
		usernameLine.add(usernameField);
		passwordLine.add(password);
		passwordLine.add(passwordField);
		JPanel jp = new JPanel();
		jp.add(login);
		jp.add(signup);
		
		setContentPane(new JLabel(new ImageIcon(backgroundImage)));

		setLayout(null);
		Dimension d = usernameLine.getPreferredSize();
		usernameLine.setBounds(495, 200, d.width, d.height);
		d = passwordLine.getPreferredSize();
		passwordLine.setBounds(495, 240, d.width, d.height);
		d = jp.getPreferredSize();
		jp.setBounds(545, 290, d.width, d.height);
		d = guest.getPreferredSize();
		guest.setBounds(560, 330, d.width, d.height);
		usernameLine.setBackground(new Color(253, 255, 215));
		passwordLine.setBackground(new Color(253, 255, 215));
		jp.setBackground(new Color(253, 255, 215));
		add(usernameLine);
		add(passwordLine);
		add(jp);
		add(guest);
	}
	
//	public void paintComponent(Graphics g) {
//		super.paintComponents(g);
//		g.drawImage(backgroundImage, 0, 0, null);
//		System.out.println("hey");
//	}
//	
	private void addListeners() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String [] args) {
		new LoginPage();
	}
}
