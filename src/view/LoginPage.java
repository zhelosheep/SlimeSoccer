package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import network.ClientThread;

public class LoginPage extends JFrame{
	private static final long serialVersionUID = 1;
	private JTextField usernameField, hostField;
	private JPasswordField passwordField;
	private JButton login, signup, guest;
	
	public LoginPage() {
		setSize(800, 600);
		setLocation(300,100);
		
		instantiateVariables();
		addComponents();
		addListeners();
		setResizable(false);
	}
	
	private void instantiateVariables() {
		hostField = new JTextField(15);
		usernameField = new JTextField(15);
		passwordField = new JPasswordField(15);
		login = new JButton("Login");
		signup = new JButton("Sign up");
		guest = new JButton("Continue as guest");
		
		login.setAlignmentX(Component.CENTER_ALIGNMENT);
		signup.setAlignmentX(Component.CENTER_ALIGNMENT);
		guest.setAlignmentX(Component.CENTER_ALIGNMENT);
	}
	
	private void addComponents() {
		JLabel welcome = new JLabel("Slime Soccer!");
		JLabel host = new JLabel("   Host: ");
		JLabel username = new JLabel("Username: ");
		JLabel password = new JLabel("Password: ");
		JPanel hostLine = new JPanel();
		JPanel usernameLine = new JPanel();
		JPanel passwordLine = new JPanel();

		welcome.setFont(new Font("Arial", Font.BOLD, 20));
		welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
		hostLine.setAlignmentX((Component.CENTER_ALIGNMENT));
		usernameLine.setAlignmentX(Component.CENTER_ALIGNMENT);
		passwordLine.setAlignmentX(Component.CENTER_ALIGNMENT);
		hostField.setText("localhost");
		hostLine.add(host);
		hostLine.add(hostField);
		usernameLine.add(username);
		usernameLine.add(usernameField);
		passwordLine.add(password);
		passwordLine.add(passwordField);
		JPanel jp = new JPanel();
		jp.add(login);
		jp.add(signup);
		
		BufferedImage backgroundImage = null;
		try {
			backgroundImage = ImageIO.read(new File("./resources/SlimeSoccerLogin.png"));
		} catch (IOException e) {
			System.out.println("IOException in addComponents (trying to load image): " + e.getMessage());
		}
		setContentPane(new JLabel(new ImageIcon(backgroundImage)));

		setLayout(null);
		Dimension d = hostLine.getPreferredSize();
		hostLine.setBounds(495, 200, d.width, d.height);
		d = usernameLine.getPreferredSize();
		usernameLine.setBounds(495, 240, d.width, d.height);
		d = passwordLine.getPreferredSize();
		passwordLine.setBounds(495, 280, d.width, d.height);
		d = jp.getPreferredSize();
		jp.setBounds(545, 330, d.width, d.height);
		d = guest.getPreferredSize();
		guest.setBounds(560, 370, d.width, d.height);
		hostLine.setBackground(new Color(253, 255, 215));
		usernameLine.setBackground(new Color(253, 255, 215));
		passwordLine.setBackground(new Color(253, 255, 215));
		jp.setBackground(new Color(253, 255, 215));
		add(hostLine);
		add(usernameLine);
		add(passwordLine);
		add(jp);
		add(guest);
	}
	
	private void addListeners() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuUser mmu = new MainMenuUser(usernameField.getText());
				try {
					mmu.s = new Socket(hostField.getText(), 6789);
					mmu.sReader = new BufferedReader(new InputStreamReader(mmu.s.getInputStream()));
					mmu.sWriter = new PrintWriter(mmu.s.getOutputStream());
					(new ClientThread(mmu, false)).start();
				} catch (UnknownHostException uhe) {
					System.out.println("UnknownHostException: " + uhe.getMessage());
				} catch (IOException ioe) {
					System.out.println("IOException in login listener: " + ioe.getMessage());
				}
				mmu.setVisible(true);
				dispose();
			}
		});
		signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new SignUpPage()).setVisible(true);
				dispose();
			}			
		});
		guest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuGuest mmg = new MainMenuGuest();
				try {
					mmg.s = new Socket(hostField.getText(), 6789);
					mmg.sReader = new BufferedReader(new InputStreamReader(mmg.s.getInputStream()));
					mmg.sWriter = new PrintWriter(mmg.s.getOutputStream());
					(new ClientThread(mmg, true)).start();
				} catch (UnknownHostException uhe) {
					System.out.println("UnknownHostException: " + uhe.getMessage());
				} catch (IOException ioe) {
					System.out.println("IOException in login listener: " + ioe.getMessage());
				}
				mmg.setVisible(true);
				dispose();
			}
		});
	}
	
	public static void main(String [] args) {
		(new LoginPage()).setVisible(true);
	}
}
