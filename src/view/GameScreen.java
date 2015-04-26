package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;
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
import network.GameThread;
import controller.Controller;

public class GameScreen extends JFrame{
	private static final long serialVersionUID = 1L;
	public JTextArea chatArea;
	private JTextField chatField;
	private JButton sendButton, logoutButton, settingsButton;
	private JLabel slimeSoccerLabel;
	private ImageIcon avatar;
	private String username;
	public BufferedReader sReader;
	PrintWriter sWriter;
	public static Canvas primary;
	public static Controller controller;
	public static GameThread gt;
	
	public GameScreen(String username)
	{
		setSize(800, 600);
		setLocation(300, 100);
		this.username = username;
		instantiateVariables();
		addComponents();
		addListeners();
		setResizable(false);
	}

	private void instantiateVariables()
	{
		sendButton = new JButton("Send");
		logoutButton = new JButton("Logout");
		settingsButton = new JButton("Settings");
		chatArea = new JTextArea();
		chatField = new JTextField(10);
		slimeSoccerLabel = new JLabel("Slime Soccer");
		slimeSoccerLabel.setFont(new Font("Arial", Font.BOLD, 20));
		slimeSoccerLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
		
		gt = new GameThread("Outer Space", "SlimeBowAndArrow", "SlimeSuperSize", "shawnren", "josemama", 100, 100, 1, "");
		// set up controller
		controller = new Controller();

		primary = new Canvas();
	}
	
	private void addComponents()
	{
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
		leftPanel.setPreferredSize(new Dimension(610, 460));
		leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 15, 0));
		leftPanel.add(primary);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		JLabel chatLabel = new JLabel("Chat");
		chatArea.setLineWrap(true);
		chatArea.setWrapStyleWord(true);
		chatArea.setEditable(false);
		((DefaultCaret)chatArea.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		JScrollPane jsp = new JScrollPane(chatArea);
		jsp.setPreferredSize(new Dimension(40, 420));
		JPanel jp5 = new JPanel();
		jp5.add(chatField);
		jp5.add(sendButton);
		JPanel jp6 = new JPanel();
		jp6.add(chatLabel);
		rightPanel.add(jp6);
		rightPanel.add(jsp);
		rightPanel.add(jp5);
		rightPanel.setPreferredSize(new Dimension(180, 420));
		
		centerPanel.add(leftPanel);
		centerPanel.add(rightPanel);
		add(centerPanel, BorderLayout.CENTER);
	}
	
	private void addListeners()
	{
		//If a player clicks the quit/back button, and confirms the other player auto wins
		
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sWriter.println("C" + username + ": " + chatField.getText());
				sWriter.flush();
				chatArea.setText(chatArea.getText() + "\n" + username + ": " + chatField.getText());
				chatField.setText("");
			}
		});
		
		chatField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				sWriter.println("C" + username + ": " + chatField.getText());
				sWriter.flush();
				chatArea.setText(chatArea.getText() + "\n" + username + ": " + chatField.getText());
				chatField.setText("");
			}
		});
	}
	
	public void checkAchievements() {
		// N00b – Play first game
		if (LoginPage.sqli.getGames(username) == 1 && !LoginPage.sqli.checkAchievement(username, LoginPage.noob_a.getName())) {
			LoginPage.sqli.setAchievement(username, LoginPage.noob_a);
		}
		
		// No Life Award – Play 1000 games
		if (LoginPage.sqli.getGames(username) == 1000 && !LoginPage.sqli.checkAchievement(username, LoginPage.nolife_a.getName())) {
			LoginPage.sqli.setAchievement(username, LoginPage.nolife_a);
		}
	
		// Victorious – Win 10 games
		if (LoginPage.sqli.getWins(username) == 10 && !LoginPage.sqli.checkAchievement(username, LoginPage.vict_a.getName())) {
			LoginPage.sqli.setAchievement(username, LoginPage.vict_a);
		}
		
		// Loser – Lose 5 games in a row
		if (LoginPage.sqli.getGamesLostInARow(username) == 5 && !LoginPage.sqli.checkAchievement(username, LoginPage.loser_a.getName())) {
			LoginPage.sqli.setAchievement(username, LoginPage.loser_a);
		}
		
		// Cristiano Ronaldo – Have a 2:1 win lose ratio or greater
		if (LoginPage.sqli.getRatio(username) >= 2 && !LoginPage.sqli.checkAchievement(username, LoginPage.chris_a.getName())) {
			LoginPage.sqli.setAchievement(username, LoginPage.chris_a);
		}

		// Unathletic Athlete – Have a 1:10 win/loss ratio or less
		if (LoginPage.sqli.getRatio(username) <= 1/10 && LoginPage.sqli.getGames(username) >= 10 && !LoginPage.sqli.checkAchievement(username, LoginPage.unath_a.getName())) {
			LoginPage.sqli.setAchievement(username, LoginPage.unath_a);
		}
		
		// Packing on the Pounds – Don’t move your slime at all during a game
		if (!primary.variables.slimeHasMoved_1 && !LoginPage.sqli.checkAchievement(username, LoginPage.pack_a.getName())) {
			LoginPage.sqli.setAchievement(username, LoginPage.pack_a);
		}

	}
	
	public static void main (String [] args)
	{
		GameScreen gamescreen = new GameScreen("techguychen");
		primary.variables = gt.game.variables;
		gamescreen.setVisible(true);
		gt.start();
		primary.start();
	}
}
