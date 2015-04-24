package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
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

public class MainMenuUserSpectate extends JFrame{
	private static final long serialVersionUID = 1L;
	private JButton searchButton, sendButton, backButton, randomButton, logoutButton;
	private JTextArea chatArea;
	private JTextField chatField, gameIDField;
	private ImageIcon avatar;

	public MainMenuUserSpectate() {
		setSize(800, 600);
		setLocation(300,100);
		instantiateVariables();
		addComponents();
		addListeners();
		setResizable(false);
		setVisible(true);		
	}
	
	private void instantiateVariables() {
		avatar = new ImageIcon(new ImageIcon("resources/SoccerBall.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
		searchButton = new JButton("Search");
		randomButton = new JButton("?? Random ??");
		sendButton = new JButton("Send");
		chatArea = new JTextArea();
		chatField = new JTextField(10);
		backButton = new JButton("Back");
		gameIDField = new JTextField(6);
		logoutButton = new JButton("Log Out");
	}
	
	private void addComponents() {
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
		JLabel slimeSoccerLabel = new JLabel("Slime Soccer");
		slimeSoccerLabel.setFont(new Font("Arial", Font.BOLD, 20));
		slimeSoccerLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
		JLabel avatarLabel = new JLabel(avatar);
		avatarLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JButton settingsButton = new JButton(new ImageIcon(new ImageIcon("resources/SoccerBall.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
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
		JPanel jp1 = new JPanel();
		JLabel helloLabel = new JLabel("Hello, Guest!");
		helloLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		jp1.add(helloLabel);
		JPanel jp2 = new JPanel();
		JLabel spectateLabel = new JLabel("Spectate Battle");
		jp2.add(spectateLabel);
		jp2.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		JPanel jp3 = new JPanel();
		JLabel searchLabel = new JLabel("Search by ID: ");
		jp3.add(searchLabel);
		jp3.add(gameIDField);
		jp3.add(searchButton);
		JPanel orPanel = new JPanel();
		JLabel orLabel = new JLabel("- OR -");
		orPanel.add(orLabel);
		JPanel jp4 = new JPanel();
		jp4.add(randomButton);
		JPanel jp5 = new JPanel();
		jp5.add(backButton);
		leftPanel.add(jp1);
		leftPanel.add(jp2);
		leftPanel.add(Box.createGlue());
		leftPanel.add(jp3);
		leftPanel.add(orPanel);
		leftPanel.add(jp4);
		leftPanel.add(jp5);
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
		JPanel jp6 = new JPanel();
		jp6.add(chatField);
		jp6.add(sendButton);
		JPanel jp7 = new JPanel();
		jp7.add(chatLabel);
		rightPanel.add(jp7);
		rightPanel.add(jsp);
		rightPanel.add(jp6);
		rightPanel.setPreferredSize(new Dimension(150, 400));
		rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		centerPanel.add(leftPanel);
		centerPanel.add(Box.createGlue());
		centerPanel.add(rightPanel);
		add(centerPanel, BorderLayout.CENTER);
	}
	
	private void addListeners() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new MainMenuUserSpectate();
	}
}
