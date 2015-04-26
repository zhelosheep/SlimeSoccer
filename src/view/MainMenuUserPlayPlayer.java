package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

public class MainMenuUserPlayPlayer extends JFrame{
	private static final long serialVersionUID = 1L;
	private JButton searchButton, sendButton, backButton, randomButton, logoutButton, settingsButton;
	JTextArea chatArea;
	private JTextField chatField, usernameField;
	private ImageIcon avatar;
	private MainMenuUser prevScreen;
	private static JLabel playPlayerLabel;
	public MainMenuUserWaiting mainMenuUserWaiting;

	public MainMenuUserPlayPlayer(MainMenuUser prevScreen) {
		setSize(800, 600);
		setLocation(300,100);
		this.prevScreen = prevScreen;
		instantiateVariables();
		addComponents();
		addListeners();
		setResizable(false);
	}
	
	private void instantiateVariables() {
		avatar = SignUpPage.avatarImages[LoginPage.sqli.getImage(getUsername())];
		searchButton = new JButton("Search");
		randomButton = new JButton("?? Random ??");
		sendButton = new JButton("Send");
		chatArea = new JTextArea();
		chatField = new JTextField(10);
		backButton = new JButton("Back");
		logoutButton = new JButton("Log Out");
		usernameField = new JTextField(6);
		settingsButton = new JButton(new ImageIcon(new ImageIcon("resources/OptionsButton.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
		playPlayerLabel = new JLabel(new ImageIcon("resources/PvP.png"));
		mainMenuUserWaiting = new MainMenuUserWaiting(this);
	}
	
	private void addComponents() {
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
		JLabel slimeSoccerLabel = new JLabel("Slime Soccer");
		slimeSoccerLabel.setFont(new Font("Arial", Font.BOLD, 20));
		slimeSoccerLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
		JButton avatarButton = new JButton(avatar);
		avatarButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		settingsButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		logoutButton.setFont(new Font("Arial", Font.BOLD, 16));
		logoutButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 15));
		northPanel.add(slimeSoccerLabel);
		northPanel.add(Box.createGlue());
		northPanel.add(avatarButton);
		northPanel.add(settingsButton);
		northPanel.add(logoutButton);
		add(northPanel, BorderLayout.NORTH);
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		JPanel jp1 = new JPanel();
		JLabel helloLabel = new JLabel("Hello, " + prevScreen.getUsername() + "!");
		helloLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		jp1.add(helloLabel);
		JPanel jp2 = new JPanel();
		jp2.add(playPlayerLabel);
		jp2.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		JPanel jp3 = new JPanel();
		JLabel searchLabel = new JLabel("<html><div style=\"text-align: center;\">Search by<br>Username: ");
		jp3.add(searchLabel);
		jp3.add(usernameField);
		jp3.add(searchButton);
		JPanel orPanel = new JPanel();
		JLabel orLabel = new JLabel("- OR -");
		orPanel.add(orLabel);
		JPanel jp4 = new JPanel();
		jp4.add(randomButton);
		jp4.setBorder(BorderFactory.createEmptyBorder(0, 0, 60, 0));;
		JPanel jp5 = new JPanel();
		jp5.add(backButton);
		leftPanel.add(jp1);
		leftPanel.add(jp2);
		leftPanel.add(Box.createGlue());
		leftPanel.add(jp3);
		leftPanel.add(orPanel);
		leftPanel.add(jp4);
		leftPanel.add(jp5);
		leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 70, 0, 0));
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
		rightPanel.setPreferredSize(new Dimension(180, 600));
		rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		centerPanel.add(leftPanel);
		centerPanel.add(Box.createGlue());
		centerPanel.add(rightPanel);
		add(centerPanel, BorderLayout.CENTER);
	}
	
	private void addListeners() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				quit();
			}
		});
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new LoginPage()).setVisible(true);
				dispose();
			}
		});
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				prevScreen.setVisible(true);
			}
		});
		settingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prevScreen.getSettingsPage().setPrevScreen(MainMenuUserPlayPlayer.this);
				prevScreen.getSettingsPage().setVisible(true);
				setVisible(false);
			}
		});
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prevScreen.sWriter.println("C" + prevScreen.getUsername() + ": " + chatField.getText());
				prevScreen.sWriter.flush();
				prevScreen.chatArea.setText(prevScreen.chatArea.getText() + "\n" + prevScreen.getUsername() + ": " + chatField.getText());
				chatField.setText("");
			}
		});
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prevScreen.sWriter.println("Q" + usernameField.getText());
				prevScreen.sWriter.flush();
				mainMenuUserWaiting.setVisible(true);
				setVisible(false);
			}
		});
		randomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prevScreen.sWriter.println("P");
				prevScreen.sWriter.flush();
				mainMenuUserWaiting.setVisible(true);
				setVisible(false);
			}
		});
	}
	
	String getUsername() {
		return prevScreen.getUsername();
	}
	
	void quit() {
		prevScreen.quit();
	}
}
