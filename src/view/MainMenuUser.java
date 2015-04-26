package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultCaret;

public class MainMenuUser extends JFrame{
	private static final long serialVersionUID = 1L;
	private ImageIcon avatar;
	private JButton sendButton, playCompButton, playPlayerButton, spectateButton, logoutButton, settingsButton;
	private static ImageIcon PvPIcon, PvCIcon, SpectateIcon;
	private String username;
	public JTextArea chatArea;
	private JTextField chatField;
	private SettingsPage settingsPage;
	private MainMenuUserPlayPlayer mainMenuUserPlayPlayer;
	private MainMenuUserSpectate mainMenuUserSpectate;
	private MainMenuUserPlaySlime mainMenuUserPlaySlime;
	Socket s;
	public BufferedReader sReader;
	PrintWriter sWriter;
	
	public MainMenuUser(String username) {
		setSize(800, 600);
		setLocation(300,100);
		this.username = username;
		instantiateVariables();
		addComponents();
		addListeners();
		setResizable(false);
	}
	
	private void instantiateVariables() {
		PvPIcon = new ImageIcon(new ImageIcon("resources/PvP.png").getImage().getScaledInstance(340, 120, java.awt.Image.SCALE_SMOOTH));
		PvCIcon = new ImageIcon(new ImageIcon("resources/PvC.png").getImage().getScaledInstance(340, 120, java.awt.Image.SCALE_SMOOTH));
		SpectateIcon = new ImageIcon(new ImageIcon("resources/Spectate.png").getImage().getScaledInstance(340, 120, java.awt.Image.SCALE_SMOOTH));
		avatar = new ImageIcon(new ImageIcon("resources/SoccerBall.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
		playCompButton = new JButton(PvCIcon);
		playPlayerButton = new JButton(PvPIcon);
		spectateButton = new JButton(SpectateIcon);
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
		JPanel jp1 = new JPanel();
		JLabel helloLabel = new JLabel("<html><div style=\"text-align: center;\">Hello, " + username + "!");
		jp1.add(helloLabel);
		JPanel jp2 = new JPanel();
		jp2.add(playCompButton);
		JPanel jp3 = new JPanel();
		jp3.add(playPlayerButton);		
		JPanel jp4 = new JPanel();
		jp4.add(spectateButton);
		leftPanel.add(jp1);
		leftPanel.add(Box.createGlue());
		leftPanel.add(jp2);
		leftPanel.add(jp3);
		leftPanel.add(jp4);
		leftPanel.setPreferredSize(new Dimension(300, 200));
		leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 15, 0));
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
			}
		});
		spectateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenuUserSpectate.setVisible(true);
				setVisible(false);
			}
		});
		chatArea.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				mainMenuUserPlayPlayer.chatArea.setText(chatArea.getText());
				mainMenuUserSpectate.chatArea.setText(chatArea.getText());
			}

			public void removeUpdate(DocumentEvent e) {
				mainMenuUserPlayPlayer.chatArea.setText(chatArea.getText());
				mainMenuUserSpectate.chatArea.setText(chatArea.getText());
			}

			public void changedUpdate(DocumentEvent e) {
				mainMenuUserPlayPlayer.chatArea.setText(chatArea.getText());
				mainMenuUserSpectate.chatArea.setText(chatArea.getText());
			}
		});
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sWriter.println("C" + username + ": " + chatField.getText());
				sWriter.flush();
				chatArea.setText(chatArea.getText() + "\n" + username + ": " + chatField.getText());
				chatField.setText("");
			}
		});
	}
	
	SettingsPage getSettingsPage() {
		return settingsPage;
	}
	
	String getUsername() {
		return username;
	}
	
	void quit() {
		try {
			if (sReader != null) {
				sReader.close();
				sReader = null;
			}
			if (sWriter != null) {
				sWriter.close();
				sWriter = null;
			}
			if (s != null) {
				s.close();
				s = null;
			}
		} catch (IOException ioe) {
			System.out.println("IOException in MainMenuGuest.quit(): " + ioe.getMessage());
		}
		System.exit(0);
	}
}
