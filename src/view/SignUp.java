package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignUp extends JFrame {
	
	private JTextField firstnameField, lastnameField, usernameField, passwordField;
	private JButton submit, back;

	SignUp(){
		setSize(800, 600);
		setLocation(300,100);
		
		instantiateVariables();
		addComponents();
		addListeners();
		setResizable(false);
		setVisible(true);
	}
	
	private void instantiateVariables(){
		firstnameField = new JTextField(15);
		lastnameField = new JTextField(15);
		usernameField = new JTextField(15);
		passwordField = new JTextField(15);
		
		submit = new JButton("Submit");
		back = new JButton("Back");	
	}
	
	private void addComponents(){
		JLabel firstName = new JLabel("First Name: ");
		JLabel lastName = new JLabel("Last Name: ");
		JLabel username = new JLabel("Username: ");
		JLabel password = new JLabel("Password: ");
		JPanel firstNameLine = new JPanel();
		JPanel lastNameLine = new JPanel();
		JPanel usernameLine = new JPanel();
		JPanel passwordLine = new JPanel();
		JPanel buttonLine = new JPanel();

		firstName.setAlignmentX(Component.CENTER_ALIGNMENT);
		lastName.setAlignmentX(Component.CENTER_ALIGNMENT);
		usernameLine.setAlignmentX(Component.CENTER_ALIGNMENT);
		passwordLine.setAlignmentX(Component.CENTER_ALIGNMENT);
		JPanel jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		firstNameLine.add(firstName);
		firstNameLine.add(firstnameField);
		jp.add(Box.createVerticalGlue());
		lastNameLine.add(lastName);
		lastNameLine.add(lastnameField);
		jp.add(Box.createVerticalGlue());
		usernameLine.add(username);
		usernameLine.add(usernameField);
		jp.add(Box.createVerticalGlue());
		passwordLine.add(password);
		passwordLine.add(passwordField);
		jp.add(Box.createVerticalGlue());
		jp.add(firstNameLine);
		jp.add(lastNameLine);
		jp.add(usernameLine);
		jp.add(passwordLine);
		buttonLine.add(submit);
		buttonLine.add(back);
		jp.add(buttonLine);
		
		this.add(jp, BorderLayout.CENTER);
	}
	
	private void addListeners(){
		
	}
	
	public static void main(String [] args) {
		new SignUp();
	}
	
}
