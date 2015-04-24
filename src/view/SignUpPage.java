package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUpPage extends JFrame{
	private static final long serialVersionUID = 1L;
	private JButton submitButton, backButton;
	private JTextField fNameField, lNameField, usernameField, descField;
	private JPasswordField passwordField;
	private JButton[] avatarButtons;

	public SignUpPage() {
		setSize(800, 600);
		setLocation(300,100);
		instantiateVariables();
		addComponents();
		addListeners();
		setResizable(false);
	}
	
	private void instantiateVariables() {
		submitButton = new JButton("Submit");
		backButton = new JButton("Back");
		fNameField = new JTextField(15);
		lNameField = new JTextField(15);
		usernameField = new JTextField(15);
		passwordField = new JPasswordField(15);
		descField = new JTextField(15);
		avatarButtons = new JButton[10];
		for (int i = 0; i < 10; i++) avatarButtons[i] = new JButton(new ImageIcon("resources/SoccerBall.png"));
	}
	
	private void addComponents() {
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
		JLabel slimeSoccerLabel = new JLabel("Slime Soccer");
		slimeSoccerLabel.setFont(new Font("Arial", Font.BOLD, 20));
		slimeSoccerLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
		backButton.setFont(new Font("Arial", Font.BOLD, 16));
		backButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 15));
		northPanel.add(slimeSoccerLabel);
		northPanel.add(Box.createGlue());
		northPanel.add(backButton);
		add(northPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		JPanel jp1 = new JPanel();
		jp1.add(new JLabel("Sign Up"));
		JPanel jp2 = new JPanel();
		jp2.add(new JLabel("First Name: "));
		jp2.add(fNameField);
		jp2.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		JPanel jp3 = new JPanel();
		jp3.add(new JLabel("Last Name: "));
		jp3.add(lNameField);
		JPanel jp4 = new JPanel();
		jp4.add(new JLabel("Username: "));
		jp4.add(usernameField);
		JPanel jp5 = new JPanel();
		jp5.add(new JLabel("Password: "));
		jp5.add(passwordField);
		JPanel jp6 = new JPanel();
		jp6.setLayout(new BoxLayout(jp6, BoxLayout.Y_AXIS));
		JPanel avatarPanel = new JPanel();
		avatarPanel.setLayout(new GridLayout(2, 5));
		for (int i = 0; i < 10; i++) avatarPanel.add(avatarButtons[i]);
		avatarPanel.setBorder(BorderFactory.createEmptyBorder(0, 230, 0, 230));
		JPanel c1 = new JPanel();
		c1.add(new JLabel("<html><div style=\"text-align: center;\">Choose an avatar: "));
		jp6.add(c1);
		jp6.add(avatarPanel);
		JPanel jp7 = new JPanel();
		jp7.add(new JLabel("Description: "));
		jp7.add(descField);
		JPanel jp8 = new JPanel();
		jp8.add(submitButton);
		jp8.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
		centerPanel.add(Box.createGlue());
		centerPanel.add(jp1);
		centerPanel.add(jp2);
		centerPanel.add(jp3);
		centerPanel.add(jp4);
		centerPanel.add(jp5);
		centerPanel.add(jp6);
		centerPanel.add(jp7);
		centerPanel.add(jp8);
		add(centerPanel, BorderLayout.CENTER);
	}
	
	private void addListeners() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginPage();
				dispose();
			}
		});
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainMenuUser(usernameField.getText());
				dispose();
			}
		});
	}
	
	public static void main(String[] args) {
		new SignUpPage();
	}
}
