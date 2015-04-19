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

public class MainMenuUser extends JFrame{
	private static final long serialVersionUID = 1L;
	private JLabel[] soccerBalls;
	private ImageIcon avatar;
	private JButton playCompButton, playPlayerButton, spectateButton, sendButton;
	private String username;
	private JTextArea chatArea;
	private JTextField chatField;

	MainMenuUser() {
		setSize(800, 600);
		setLocation(300,100);
		instantiateVariables();
		addComponents();
		addListeners();
		setResizable(false);
		setVisible(true);		
	}
	
	private void instantiateVariables() {
		soccerBalls = new JLabel[3];
		for (int i = 0; i < 3; i++) soccerBalls[i] = new JLabel(new ImageIcon(new ImageIcon("resources/soccerBall.png").getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
		avatar = new ImageIcon(new ImageIcon("resources/avatar.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
		username = "techguychen";
		playCompButton = new JButton("Play Computer");
		playPlayerButton = new JButton("Play Player");
		spectateButton = new JButton("Spectate Battle");
		sendButton = new JButton("Send");
		chatArea = new JTextArea();
		chatField = new JTextField(10);
	}
	
	private void addComponents() {
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
		JLabel slimeSoccerLabel = new JLabel("Slime Soccer");
		slimeSoccerLabel.setFont(new Font("Arial", Font.BOLD, 20));
		slimeSoccerLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
		JLabel avatarLabel = new JLabel(avatar);
		avatarLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JLabel settingsLabel = new JLabel(new ImageIcon(new ImageIcon("resources/settings.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
		settingsLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JLabel logoutLabel = new JLabel("Logout");
		logoutLabel.setFont(new Font("Arial", Font.BOLD, 16));
		logoutLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 15));
		northPanel.add(slimeSoccerLabel);
		northPanel.add(Box.createGlue());
		northPanel.add(avatarLabel);
		northPanel.add(settingsLabel);
		northPanel.add(logoutLabel);
		add(northPanel, BorderLayout.NORTH);
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		JLabel helloLabel = new JLabel("Hello, " + username + "!");
		helloLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		JPanel jp1 = new JPanel();
		jp1.add(soccerBalls[0]);
		jp1.add(playCompButton);
		JPanel jp2 = new JPanel();
		jp2.add(soccerBalls[1]);
		soccerBalls[1].setVisible(false);
		jp2.add(playPlayerButton);		
		JPanel jp3 = new JPanel();
		jp3.add(soccerBalls[2]);
		soccerBalls[2].setVisible(false);
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
		rightPanel.add(chatLabel);
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
	}
	
	public static void main(String[] args) {
		new MainMenuUser();
	}
}
