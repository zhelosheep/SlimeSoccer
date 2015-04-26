package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
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

import model.Game;
import controller.Controller;

public class GameScreen extends JFrame{
	public JTextArea chatArea;
	private JTextField chatField;
	private JButton sendButton, logoutButton, settingsButton;
	private JLabel slimeSoccerLabel;
	private ImageIcon avatar;
	private String username;
	public BufferedReader sReader;
	PrintWriter sWriter;
	public static Canvas primary;
	public static Game model;
	public static Controller controller;
	
	public GameScreen(String username)
	{
		setSize(800, 600);
		setLocation(300, 100);
		this.username = username;
		instantiateVariables();
		addComponents();
		addListeners();
		setResizable(false);
		setVisible(true);
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
		
		primary = new Canvas();
		// set up model
		model = new Game("outerspace", "SlimeFireball", "SlimeSuperSize", "shawnren", "josemama", 100, 100, 1, null);
		// set up controller
		controller = new Controller();
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
		leftPanel.setPreferredSize(new Dimension(610, 450));
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
		rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		
		centerPanel.add(Box.createGlue());
		centerPanel.add(leftPanel);
		centerPanel.add(Box.createGlue());
		centerPanel.add(rightPanel);
		add(centerPanel, BorderLayout.CENTER);
	}
	
	private void addListeners()
	{
		//If a player clicks the quit/back button, and confirms the other player auto wins
	}
	
	public static void main (String [] args)
	{
		GameScreen gamescreen = new GameScreen("techguychen");
	}
}
