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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

import model.Ball;
import model.Goal;
import model.Slime;
import model.SlimeBomb;
import model.SlimeBowAndArrow;
import model.SlimeClone;
import model.SlimeCosmic;
import model.SlimeFireball;
import model.SlimeFisher;
import model.SlimeGeyser;
import model.SlimeMagnet;
import model.SlimeSuper;
import model.SlimeSuperSize;

public class SpectateScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFrame prevScreen;
	private boolean isGuest;
	private JButton backButton, switchButton, randomButton, sendButton;
	private JTextField gameIDField, chatField;
	public JTextArea chatArea;
	public Canvas primary;
	
	SpectateScreen(boolean isGuest, JFrame prevScreen) {
		setSize(800, 600);
		setLocation(300,100);
		this.isGuest = isGuest;
		this.prevScreen = prevScreen;
		instantiateVariables();
		addComponents();
		addListeners();
		setResizable(false);
	}
	private void instantiateVariables() {
		backButton = new JButton("Back");
		switchButton = new JButton("Switch");
		randomButton = new JButton("Random");
		sendButton = new JButton("Send");
		gameIDField = new JTextField(10);
		chatField = new JTextField(10);
		chatArea = new JTextArea();
		primary = new Canvas("guest");
	}
	
	private void addComponents() {
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
		JLabel slimeSoccerLabel = new JLabel("Slime Soccer");
		slimeSoccerLabel.setFont(new Font("Arial", Font.BOLD, 20));
		slimeSoccerLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
		backButton.setFont(new Font("Arial", Font.BOLD, 16));
		backButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 15));
		northPanel.add(slimeSoccerLabel);
		northPanel.add(Box.createGlue());
		northPanel.add(backButton);
		add(northPanel, BorderLayout.NORTH);

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.setPreferredSize(new Dimension(600, 450));
		leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 15, 0));
		leftPanel.add(primary);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		JPanel jp1 = new JPanel();
		JLabel spectateLabel = new JLabel("Spectate Mode");
		jp1.add(spectateLabel);
		JPanel jp2 = new JPanel();
		JLabel switchSpecificLabel = new JLabel("Switch to specific game");
		jp2.add(switchSpecificLabel);
		JPanel jp3 = new JPanel();
		JLabel gameIDLabel = new JLabel("Game ID: ");
		jp3.add(gameIDLabel);
		jp3.add(gameIDField);
		jp3.add(switchButton);
		JPanel jp4 = new JPanel();
		JLabel switchRandomLabel = new JLabel("Switch to a random game");
		jp4.add(switchRandomLabel);
		JPanel jp5 = new JPanel();
		jp5.add(randomButton);
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
		rightPanel.add(jp1);
		rightPanel.add(jp2);
		rightPanel.add(jp3);
		rightPanel.add(jp4);
		rightPanel.add(jp5);
		rightPanel.add(jp7);
		rightPanel.add(jsp);
		rightPanel.add(jp6);
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
				if (!isGuest) {
					LoginPage.sqli.toggleLog(MainMenuUser.username);
				}
				quit();
			}
		});
		switchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isGuest) {
					((MainMenuGuest) prevScreen).sWriter.println("S");
					((MainMenuGuest) prevScreen).sWriter.flush();
					((MainMenuGuest) prevScreen).sWriter.println("NG" + gameIDField.getText());
					((MainMenuGuest) prevScreen).sWriter.flush();
				} else {
					((MainMenuUserSpectate) prevScreen).prevScreen.sWriter.println("S");
					((MainMenuUserSpectate) prevScreen).prevScreen.sWriter.flush();
					((MainMenuUserSpectate) prevScreen).prevScreen.sWriter.println("NU" + gameIDField.getText());
					((MainMenuUserSpectate) prevScreen).prevScreen.sWriter.flush();
				}
			}
		});
		randomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isGuest) {
					((MainMenuGuest) prevScreen).sWriter.println("S");
					((MainMenuGuest) prevScreen).sWriter.flush();
					((MainMenuGuest) prevScreen).sWriter.println("MG");
					((MainMenuGuest) prevScreen).sWriter.flush();
				} else {
					((MainMenuUserSpectate) prevScreen).prevScreen.sWriter.println("S");
					((MainMenuUserSpectate) prevScreen).prevScreen.sWriter.flush();
					((MainMenuUserSpectate) prevScreen).prevScreen.sWriter.println("MU");
					((MainMenuUserSpectate) prevScreen).prevScreen.sWriter.flush();
				}				
			}
		});
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prevScreen.setVisible(true);
				setVisible(false);
				if (isGuest) {
					((MainMenuGuest) prevScreen).sWriter.println("S");
					((MainMenuGuest) prevScreen).sWriter.flush();
				} else {
					((MainMenuUserSpectate) prevScreen).prevScreen.sWriter.println("S");
					((MainMenuUserSpectate) prevScreen).prevScreen.sWriter.flush();
				}				
			}
		});
		
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isGuest){
					((MainMenuGuest) prevScreen).sWriter.println("D" + "Guest: " + chatField.getText());
					((MainMenuGuest) prevScreen).sWriter.flush();
					chatArea.setText(chatArea.getText() + "\n" + "Guest: " + chatField.getText());
				} else{
					((MainMenuUserSpectate) prevScreen).prevScreen.sWriter.println("D" + ((MainMenuUserSpectate) prevScreen).prevScreen.username + ": " + chatField.getText());
					((MainMenuUserSpectate) prevScreen).prevScreen.sWriter.flush();
					chatArea.setText(chatArea.getText() + "\n" + ((MainMenuUserSpectate) prevScreen).prevScreen.username + ": " + chatField.getText());
				}
				chatField.setText("");
			}
		});
		
		chatField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(isGuest){
					((MainMenuGuest) prevScreen).sWriter.println("D" + "Guest: " + chatField.getText());
					((MainMenuGuest) prevScreen).sWriter.flush();
					chatArea.setText(chatArea.getText() + "\n" + "Guest: " + chatField.getText());
				} else{
					((MainMenuUserSpectate) prevScreen).prevScreen.sWriter.println("D" + ((MainMenuUserSpectate) prevScreen).prevScreen.username + ": " + chatField.getText());
					((MainMenuUserSpectate) prevScreen).prevScreen.sWriter.flush();
					chatArea.setText(chatArea.getText() + "\n" + ((MainMenuUserSpectate) prevScreen).prevScreen.username + ": " + chatField.getText());
				}
				chatField.setText("");
			}
		});
	}
	
	void quit() {
		if (isGuest) {
			((MainMenuGuest) prevScreen).quit();
		} else {
			((MainMenuUserSpectate) prevScreen).quit();
		}
	}	
	
	public void setVariables(String background, String player1_slimeType, String player2_slimeType, String player1_username, String player2_username, int player1_manaMax, int player2_manaMax, int manaRegenerationRate, String specialMode) {
		primary.variables.setBackground(background);
		primary.variables.setSlimeImage(1, player1_slimeType);
		primary.variables.setSlimeImage(2, player2_slimeType);
		primary.variables.player1_username = player1_username;
		primary.variables.player2_username = player2_username;
		primary.variables.player1_manaCurrent = player1_manaMax;
		primary.variables.player2_manaCurrent = player2_manaMax;
		primary.variables.player1_manaMax = player1_manaMax;
		primary.variables.player2_manaMax = player2_manaMax;
		primary.variables.player1_score = 0;
		primary.variables.player2_score = 0;
		primary.variables.manaRegenerationRate = manaRegenerationRate;
		primary.variables.specialMode = specialMode;
		primary.variables.player1scored = false;
		primary.variables.player2scored = false;
		
		System.out.println(player1_slimeType + " " + player2_slimeType);
		System.out.println(player1_username + player2_username);
		System.out.println(player1_manaMax + player2_manaMax);

		if (player1_slimeType.equals("SlimeBomb")) {
			primary.variables.slime1 = new SlimeBomb(primary.variables.leftBoundary + 50, primary.variables.groundLevel, 1, primary.variables.imgSlime1, primary.variables);
		} else if (player1_slimeType.equals("SlimeBowAndArrow")) {
			primary.variables.slime1 = new SlimeBowAndArrow(primary.variables.leftBoundary + 50, primary.variables.groundLevel, 1, primary.variables.imgSlime1, primary.variables);
		} else if (player1_slimeType.equals("SlimeClone")) {
			primary.variables.slime1 = new SlimeClone(primary.variables.leftBoundary + 50, primary.variables.groundLevel, 1, primary.variables.imgSlime1, primary.variables);
		} else if (player1_slimeType.equals("SlimeCosmic")) {
			primary.variables.slime1 = new SlimeCosmic(primary.variables.leftBoundary + 50, primary.variables.groundLevel, 1, primary.variables.imgSlime1, primary.variables);
		} else if (player1_slimeType.equals("SlimeFireball")) {
			primary.variables.slime1 = new SlimeFireball(primary.variables.leftBoundary + 50, primary.variables.groundLevel, 1, primary.variables.imgSlime1, primary.variables);
		} else if (player1_slimeType.equals("SlimeFisher")) {
			primary.variables.slime1 = new SlimeFisher(primary.variables.leftBoundary + 50, primary.variables.groundLevel, 1, primary.variables.imgSlime1, primary.variables);
		} else if (player1_slimeType.equals("SlimeGeyser")) {
			primary.variables.slime1 = new SlimeGeyser(primary.variables.leftBoundary + 50, primary.variables.groundLevel, 1, primary.variables.imgSlime1, primary.variables);
		} else if (player1_slimeType.equals("SlimeMagnet")) {
			primary.variables.slime1 = new SlimeMagnet(primary.variables.leftBoundary + 50, primary.variables.groundLevel, 1, primary.variables.imgSlime1, primary.variables);
		} else if (player1_slimeType.equals("SlimeSuperSize")) {
			primary.variables.slime1 = new SlimeSuperSize(primary.variables.leftBoundary + 50, primary.variables.groundLevel, 1, primary.variables.imgSlime1, primary.variables);
		} else if (player1_slimeType.equals("SlimeSuper")) {
			primary.variables.slime1 = new SlimeSuper(primary.variables.leftBoundary + 50, primary.variables.groundLevel, 1, primary.variables.imgSlime1, primary.variables);
		} else if (player1_slimeType.equals("Slime3D")) {
			primary.variables.slime1 = new Slime(primary.variables.leftBoundary + 50, primary.variables.groundLevel, 1, primary.variables.imgSlime1, primary.variables);
		} else if (player1_slimeType.equals("SlimeButterfly")) {
			primary.variables.slime1 = new Slime(primary.variables.leftBoundary + 50, primary.variables.groundLevel, 1, primary.variables.imgSlime1, primary.variables);
		} else if (player1_slimeType.equals("SlimeRonaldo")) {
			primary.variables.slime1 = new Slime(primary.variables.leftBoundary + 50, primary.variables.groundLevel, 1, primary.variables.imgSlime1, primary.variables);
		} else if (player1_slimeType.equals("SlimeCrossEyed")) {
			primary.variables.slime1 = new Slime(primary.variables.leftBoundary + 50, primary.variables.groundLevel, 1, primary.variables.imgSlime1, primary.variables);
		} else if (player1_slimeType.equals("SlimeCrown")) {
			primary.variables.slime1 = new Slime(primary.variables.leftBoundary + 50, primary.variables.groundLevel, 1, primary.variables.imgSlime1, primary.variables);
		} else if (player1_slimeType.equals("SlimeDunce")) {
			primary.variables.slime1 = new Slime(primary.variables.leftBoundary + 50, primary.variables.groundLevel, 1, primary.variables.imgSlime1, primary.variables);
		} else if (player1_slimeType.equals("LSlime")) {
			primary.variables.slime1 = new Slime(primary.variables.leftBoundary + 50, primary.variables.groundLevel, 1, primary.variables.imgSlime1, primary.variables);
		} else if (player1_slimeType.equals("SlimePotato")) {
			primary.variables.slime1 = new Slime(primary.variables.leftBoundary + 50, primary.variables.groundLevel, 1, primary.variables.imgSlime1, primary.variables);
		} else if (player1_slimeType.equals("SlimeSweater")) {
			primary.variables.slime1 = new Slime(primary.variables.leftBoundary + 50, primary.variables.groundLevel, 1, primary.variables.imgSlime1, primary.variables);
		}
		
		if (player2_slimeType.equals("SlimeBomb")) {
			primary.variables.slime2 = new SlimeBomb(primary.variables.rightBoundary - 50, primary.variables.groundLevel, 2, primary.variables.imgSlime2, primary.variables);
		} else if (player2_slimeType.equals("SlimeBowAndArrow")) {
			primary.variables.slime2 = new SlimeBowAndArrow(primary.variables.rightBoundary - 50, primary.variables.groundLevel, 2, primary.variables.imgSlime2, primary.variables);
		} else if (player2_slimeType.equals("SlimeClone")) {
			primary.variables.slime2 = new SlimeClone(primary.variables.rightBoundary - 50, primary.variables.groundLevel, 2, primary.variables.imgSlime2, primary.variables);
		} else if (player2_slimeType.equals("SlimeCosmic")) {
			primary.variables.slime2 = new SlimeCosmic(primary.variables.rightBoundary - 50, primary.variables.groundLevel, 2, primary.variables.imgSlime2, primary.variables);
		} else if (player2_slimeType.equals("SlimeFireball")) {
			primary.variables.slime2 = new SlimeFireball(primary.variables.rightBoundary - 50, primary.variables.groundLevel, 2, primary.variables.imgSlime2, primary.variables);
		} else if (player2_slimeType.equals("SlimeFisher")) {
			primary.variables.slime2 = new SlimeFisher(primary.variables.rightBoundary - 50, primary.variables.groundLevel, 2, primary.variables.imgSlime2, primary.variables);
		} else if (player2_slimeType.equals("SlimeGeyser")) {
			primary.variables.slime2 = new SlimeGeyser(primary.variables.rightBoundary - 50, primary.variables.groundLevel, 2, primary.variables.imgSlime2, primary.variables);
		} else if (player2_slimeType.equals("SlimeMagnet")) {
			primary.variables.slime2 = new SlimeMagnet(primary.variables.rightBoundary - 50, primary.variables.groundLevel, 2, primary.variables.imgSlime2, primary.variables);
		} else if (player2_slimeType.equals("SlimeSuperSize")) {
			primary.variables.slime2 = new SlimeSuperSize(primary.variables.rightBoundary - 50, primary.variables.groundLevel, 2, primary.variables.imgSlime2, primary.variables);
		} else if (player2_slimeType.equals("SlimeSuper")) {
			primary.variables.slime2 = new SlimeSuper(primary.variables.rightBoundary - 50, primary.variables.groundLevel, 2, primary.variables.imgSlime2, primary.variables);
		} else if (player2_slimeType.equals("Slime3D")) {
			primary.variables.slime2 = new Slime(primary.variables.rightBoundary - 50, primary.variables.groundLevel, 2, primary.variables.imgSlime2, primary.variables);
		} else if (player2_slimeType.equals("SlimeButterfly")) {
			primary.variables.slime2 = new Slime(primary.variables.rightBoundary - 50, primary.variables.groundLevel, 2, primary.variables.imgSlime2, primary.variables);
		} else if (player2_slimeType.equals("SlimeRonaldo")) {
			primary.variables.slime2 = new Slime(primary.variables.rightBoundary - 50, primary.variables.groundLevel, 2, primary.variables.imgSlime2, primary.variables);
		} else if (player2_slimeType.equals("SlimeCrossEyed")) {
			primary.variables.slime2 = new Slime(primary.variables.rightBoundary - 50, primary.variables.groundLevel, 2, primary.variables.imgSlime2, primary.variables);
		} else if (player2_slimeType.equals("SlimeCrown")) {
			primary.variables.slime2 = new Slime(primary.variables.rightBoundary - 50, primary.variables.groundLevel, 2, primary.variables.imgSlime2, primary.variables);
		} else if (player2_slimeType.equals("SlimeDunce")) {
			primary.variables.slime2 = new Slime(primary.variables.rightBoundary - 50, primary.variables.groundLevel, 2, primary.variables.imgSlime2, primary.variables);
		} else if (player2_slimeType.equals("LSlime")) {
			primary.variables.slime2 = new Slime(primary.variables.rightBoundary - 50, primary.variables.groundLevel, 2, primary.variables.imgSlime2, primary.variables);
		} else if (player2_slimeType.equals("SlimePotato")) {
			primary.variables.slime2 = new Slime(primary.variables.rightBoundary - 50, primary.variables.groundLevel, 2, primary.variables.imgSlime2, primary.variables);
		} else if (player2_slimeType.equals("SlimeSweater")) {
			primary.variables.slime2 = new Slime(primary.variables.rightBoundary - 50, primary.variables.groundLevel, 2, primary.variables.imgSlime2, primary.variables);
		}
		primary.variables.ball = new Ball((primary.variables.leftBoundary + primary.variables.rightBoundary)/2, primary.variables.groundLevel - 12 - 100, primary.variables);
		primary.variables.goal1 = new Goal(primary.variables.leftBoundary, primary.variables);
		primary.variables.goal2 = new Goal(primary.variables.rightBoundary, primary.variables);
		primary.begin();
	}
	
//	static public void main(String [] args) {
//		SpectateScreen s = new SpectateScreen(false, null);
//		s.setVisible(true);
//	}
}
