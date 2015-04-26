package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.Achievement;
import network.ClientThread;

public class LoginPage extends JFrame{
	private static final long serialVersionUID = 1;
	private JTextField usernameField, hostField;
	private JPasswordField passwordField;
	private JButton login, signup, guest;
	private String u, pw, host;
	
	//starts up static mysql instance
	public static final controller.SqlInstance sqli= new controller.SqlInstance();
	
	//achievements
	//this array of Achievement objects
	public static final Achievement chris_a, loser_a, nolife_a, noob_a, pack_a, soc_a, unath_a, vict_a;
	//imageicons for each of the 8 achievements
	private static final ImageIcon chrisronaldo, loser, nolife, noob, packingpounds, socbutterfly, unathathlete, victorious;
	
	static {
		chrisronaldo = new ImageIcon("./resources/achievements/ChrisRonaldo.png");
		loser = new ImageIcon("./resources/achievements/Loser.png");
		nolife = new ImageIcon("./resources/achievements/NoLife.png");
		noob = new ImageIcon("./resources/achievements/Noob.png");
		packingpounds = new ImageIcon("./resources/achievements/PackingPounds.png");
		socbutterfly = new ImageIcon("./resources/achievements/SocButterfly.png");
		unathathlete = new ImageIcon("./resources/achievements/UnathAthlete.png");
		victorious = new ImageIcon("./resources/achievements/Victorious.png");
		
		chris_a = new Achievement("Cristiano Ronaldo", "Have a 2:1 win lose ratio or greater", chrisronaldo);
		loser_a = new Achievement("Loser", "Lose 5 games in a row", loser);
		nolife_a = new Achievement("No Life", "Play 1,000 games", nolife);
		noob_a = new Achievement("Noob", "Play first game", noob);
		pack_a = new Achievement("Packing on the Pounds", "Don't move your slime at all during a game", packingpounds);
		soc_a = new Achievement("Social Butterfly", "Have over 25 friends", socbutterfly);
		unath_a = new Achievement("Unathletic Athlete", "Have a 1:10 win lose ratio or less", unathathlete);
		vict_a = new Achievement("Victorious", "Win 10 games", victorious);
	}
	
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
		
		login.setEnabled(false);
	}
	
	private void addComponents() {
		//JLabel welcome = new JLabel("Slime Soccer!");
		JLabel host = new JLabel("   Host: ");
		JLabel username = new JLabel("Username: ");
		JLabel password = new JLabel("Password: ");
		JPanel hostLine = new JPanel();
		JPanel usernameLine = new JPanel();
		JPanel passwordLine = new JPanel();

		//welcome.setFont(new Font("Arial", Font.BOLD, 20));
		//welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
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
				if (sqli.validateLogin(u, pw)) {
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
				} else {
					JOptionPane.showMessageDialog(LoginPage.this, "Invalid username/password", "Invalid Login", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		login.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e)
			{
				if ((e.getKeyCode()==KeyEvent.VK_ENTER) && (login.isFocusPainted()))
				{
					login.doClick();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		passwordField.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					login.doClick();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
				
		signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new SignUpPage()).setVisible(true);
				dispose();
			}
		});
		
		signup.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e)
			{
				if ((e.getKeyCode()==KeyEvent.VK_ENTER) && (signup.isFocusPainted()))
				{
					signup.doClick();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		guest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuGuest mmg = new MainMenuGuest();
				try {
					mmg.s = new Socket(host, 6789);
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
		
		guest.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e)
			{
				if ((e.getKeyCode()==KeyEvent.VK_ENTER) && (guest.isFocusPainted()))
				{
					guest.doClick();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		
		hostField.getDocument().addDocumentListener(new checkFields());
		usernameField.getDocument().addDocumentListener(new checkFields());
		passwordField.getDocument().addDocumentListener(new checkFields());
	}
	
	private void changed() {
		u = usernameField.getText();
		pw = String.valueOf(passwordField.getPassword());
		host = hostField.getText();
		
		if (host.equals("")) {
			login.setEnabled(false);
			guest.setEnabled(false);
		}
		else if (u.equals("") || pw.equals("")) {
			login.setEnabled(false);
		}
		else {
			login.setEnabled(true);
			guest.setEnabled(true);
		}
	}
	
	private class checkFields implements DocumentListener {
		//checks to see if all textfields are filled to enable submit button
		public void insertUpdate(DocumentEvent e) {
			changed();
		}
		
		public void removeUpdate(DocumentEvent e) {
			changed();
		}

		public void changedUpdate(DocumentEvent e) {
			changed();
		}
	}
	
	public static void main(String [] args) {
		(new LoginPage()).setVisible(true);
	}
}
