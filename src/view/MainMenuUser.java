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

import javax.imageio.ImageIO;
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
	private JButton sendButton, playCompButton, playPlayerButton, spectateButton, logoutButton, settingsButton, avatarButton;
	private static ImageIcon PvPIcon, PvCIcon, SpectateIcon;
	public static String username;
	public static ImageIcon avatar;
	public JTextArea chatArea;
	private JTextField chatField;
	private SettingsPage settingsPage;
	public MainMenuUserPlayPlayer mainMenuUserPlayPlayer;
	public MainMenuUserSpectate mainMenuUserSpectate;
	private MainMenuUserPlaySlime mainMenuUserPlaySlime;
	Socket s;
	public BufferedReader sReader;
	public PrintWriter sWriter;
	//public int avatarNum = LoginPage.sqli.getImage(username);
	
	public MainMenuUser(String username) {
		setSize(800, 600);
		setLocation(300,100);
		MainMenuUser.username = username;
		instantiateVariables();
		addComponents();
		addListeners();
		setResizable(false);
	}
	
	private void instantiateVariables() {
		try {
			PvPIcon = new ImageIcon(new ImageIcon((ImageIO.read((getClass().getClassLoader().getResource("PvP.png"))))).getImage().getScaledInstance(340, 120, java.awt.Image.SCALE_SMOOTH));
			PvCIcon = new ImageIcon(new ImageIcon((ImageIO.read((getClass().getClassLoader().getResource("PvC.png"))))).getImage().getScaledInstance(340, 120, java.awt.Image.SCALE_SMOOTH));
			SpectateIcon = new ImageIcon(new ImageIcon((ImageIO.read((getClass().getClassLoader().getResource("Spectate.png"))))).getImage().getScaledInstance(340, 120, java.awt.Image.SCALE_SMOOTH));
			settingsButton = new JButton(new ImageIcon(new ImageIcon((ImageIO.read((getClass().getClassLoader().getResource("OptionsButton.png"))))).getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
		} catch (IOException ioe) {
			System.out.println("IOException in MainMenuUser.instantiateVariables (read images): " + ioe.getMessage());
		}
		
		//System.out.println(LoginPage.sqli.getImage(username));
		avatar = LoginPage.avatarImages[LoginPage.sqli.getImage(username)];
		playCompButton = new JButton(PvCIcon);
		playPlayerButton = new JButton(PvPIcon);
		spectateButton = new JButton(SpectateIcon);
		sendButton = new JButton("Send");
		chatArea = new JTextArea();
		chatField = new JTextField(10);
		logoutButton = new JButton("Logout");
		settingsPage = new SettingsPage();
		mainMenuUserPlayPlayer = new MainMenuUserPlayPlayer(this);
		mainMenuUserSpectate = new MainMenuUserSpectate(this);
		mainMenuUserPlaySlime = new MainMenuUserPlaySlime(this, true);
	}
	
	private void addComponents() {
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
		JLabel slimeSoccerLabel = new JLabel("Slime Soccer");
		slimeSoccerLabel.setFont(new Font("Arial", Font.BOLD, 20));
		slimeSoccerLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
		avatarButton = new JButton(avatar);
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
		jsp.setMinimumSize(new Dimension(200, 350));
		jsp.setPreferredSize(new Dimension(200, 350));
		jsp.setMaximumSize(new Dimension(200, 350));
		JPanel jp5 = new JPanel();
		jp5.add(chatField);
		jp5.add(sendButton);
		JPanel jp6 = new JPanel();
		jp6.add(chatLabel);
		rightPanel.add(jp6);
		rightPanel.add(jsp);
		rightPanel.add(jp5);
		rightPanel.setMinimumSize(new Dimension(300, 420));
		rightPanel.setPreferredSize(new Dimension(300, 420));
		rightPanel.setMaximumSize(new Dimension(300, 420));
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
		
		chatField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				sWriter.println("C" + username + ": " + chatField.getText());
				sWriter.flush();
				chatArea.setText(chatArea.getText() + "\n" + username + ": " + chatField.getText());
				chatField.setText("");
			}
		});
		
		avatarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Profile pro = new Profile(username, MainMenuUser.this);
				pro.setVisible(true);
			}
		});
	}
	
	SettingsPage getSettingsPage() {
		return settingsPage;
	}
	
	public String getUsername() {
		return username;
	}
	
	void quit() {
		try {
			sWriter.println("Z");
			sWriter.flush();
			if (s != null) {
				s.shutdownInput();
				s.shutdownOutput();
				s.close();
				s = null;
			}
		} catch (IOException ioe) {
			System.out.println("IOException in MainMenuGuest.quit(): " + ioe.getMessage());
		}
		System.exit(0);
	}
}
