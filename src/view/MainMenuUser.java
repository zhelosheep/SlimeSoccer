package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

import chat.ChatClient;
import chat.ChatServer;

public class MainMenuUser extends JFrame{
	private static final long serialVersionUID = 1L;
	private ImageIcon avatar;
	private JButton sendButton, playCompButton, playPlayerButton, spectateButton, logoutButton, settingsButton;
	private String username, hostIP;
	private JTextArea chatArea;
	private JTextField chatField;
	private SettingsPage settingsPage;
	private MainMenuUserPlayPlayer mainMenuUserPlayPlayer;
	private MainMenuUserSpectate mainMenuUserSpectate;
	private MainMenuUserPlaySlime mainMenuUserPlaySlime;
	
	public MainMenuUser(String username, String hostIP) {
		setSize(800, 600);
		setLocation(300,100);
		this.username = username;
		this.hostIP = hostIP;
		instantiateVariables();
		addComponents();
		addListeners();
		setResizable(false);
	}
	
	private void instantiateVariables() {
		avatar = new ImageIcon(new ImageIcon("resources/SoccerBall.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
		playCompButton = new JButton("Play Computer");
		playPlayerButton = new JButton("Play Player");
		spectateButton = new JButton("Spectate Battle");
		sendButton = new JButton("Send");
		chatArea = new JTextArea();
		chatField = new JTextField(10);
		logoutButton = new JButton("Logout");
		settingsButton = new JButton(new ImageIcon(new ImageIcon("resources/SoccerBall.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
		settingsPage = new SettingsPage();
		mainMenuUserPlayPlayer = new MainMenuUserPlayPlayer(this);
		mainMenuUserSpectate = new MainMenuUserSpectate(this);
		mainMenuUserPlaySlime = new MainMenuUserPlaySlime(this, true, true);
	}
	
	private void addComponents() {
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
		JLabel slimeSoccerLabel = new JLabel("Slime Soccer");
		slimeSoccerLabel.setFont(new Font("Arial", Font.BOLD, 20));
		slimeSoccerLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
		JLabel avatarLabel = new JLabel(avatar);
		avatarLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		settingsButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		logoutButton.setFont(new Font("Arial", Font.BOLD, 16));
		logoutButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 15));
		northPanel.add(slimeSoccerLabel);
		northPanel.add(Box.createGlue());
		northPanel.add(avatarLabel);
		northPanel.add(settingsButton);
		northPanel.add(logoutButton);
		add(northPanel, BorderLayout.NORTH);
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		JLabel helloLabel = new JLabel("Hello, " + username + "!");
		helloLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		JPanel jp1 = new JPanel();
		jp1.add(playCompButton);
		JPanel jp2 = new JPanel();
		jp2.add(playPlayerButton);		
		JPanel jp3 = new JPanel();
		jp3.add(spectateButton);
		leftPanel.add(helloLabel);
		leftPanel.add(Box.createGlue());
		leftPanel.add(jp1);
		leftPanel.add(jp2);
		leftPanel.add(jp3);
		leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		JLabel chatLabel = new JLabel("Chat");
		chatArea.setLineWrap(true);
		chatArea.setWrapStyleWord(true);
		chatArea.setEditable(false);
		((DefaultCaret)chatArea.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		JScrollPane jsp = new JScrollPane(chatArea);
		jsp.setPreferredSize(new Dimension(40, 420));
		JPanel jp4 = new JPanel();
		jp4.add(chatField);
		jp4.add(sendButton);
		JPanel jp5 = new JPanel();
		jp5.add(chatLabel);
		rightPanel.add(jp5);
		rightPanel.add(jsp);
		rightPanel.add(jp4);
		rightPanel.setPreferredSize(new Dimension(130, 400));
		rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		centerPanel.add(leftPanel);
		centerPanel.add(Box.createGlue());
		centerPanel.add(rightPanel);
		add(centerPanel, BorderLayout.CENTER);
	}
	
	private void addListeners() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new LoginPage()).setVisible(true);
				dispose();
			}
		});
		settingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsPage.setPrevScreen(MainMenuUser.this);
				settingsPage.setVisible(true);
				setVisible(false);
			}
		});
		playCompButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenuUserPlaySlime.setVisible(true);
				setVisible(false);
			}
		});
		playPlayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenuUserPlayPlayer.setVisible(true);
				setVisible(false);
				//should pass chat data forward to mainMenuUserPlayPlayer class/frame
			}
		});
		spectateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenuUserSpectate.setVisible(true);
				setVisible(false);
				//should pass chat data forward to mainMenuUserSpectate class/frame
			}
		});
	}
	
	SettingsPage getSettingsPage() {
		return settingsPage;
	}
	
	String getUsername() {
		return username;
	}
	
	public static void main(String[] args) {
		(new MainMenuUser("faketechguy", "localhost")).setVisible(true);
	}
}
