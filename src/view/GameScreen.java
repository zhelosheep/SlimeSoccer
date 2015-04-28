package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
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
	public Canvas primary;
	public Controller controller;
	GameThread gt; // delete later
	public MainMenuUserPlaySlime prevScreen;
	public boolean isPvCGame;
	public JLabel gameIDLabel;
	
	public GameScreen(String username, MainMenuUserPlaySlime prevScreen, boolean isPvCGame)
	{
		setSize(800, 600);
		setLocation(300, 100);
		this.username = username;
		this.prevScreen = prevScreen;
		this.isPvCGame = isPvCGame;
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
		
		gt = new GameThread("Outer Space", "SlimeBowAndArrow", "SlimeSuperSize", "shawnren", "josemama", 100, 100, 1, "", null, this.isPvCGame);
		// set up controller
		controller = new Controller(this);

		primary = new Canvas();

		gameIDLabel = new JLabel("Game ID: ");
		gameIDLabel.setFont(new Font("Arial", Font.BOLD, 20));
		gameIDLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
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
		northPanel.add(gameIDLabel);
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
		AbstractAction upAction = new AbstractAction()
	    {   
	        public void actionPerformed(ActionEvent ae)
	        {
	        	if (prevScreen.isPlayer1) {
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("K" + "up");
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();					        		
	        	} else {
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("L" + "up");
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();					        		
	        	}
	        }
	    };
	    AbstractAction downAction = new AbstractAction()
	    {   
	        public void actionPerformed(ActionEvent ae)
	        {
	        	if (prevScreen.isPlayer1) {
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("K" + "down");
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();					        		
	        	} else {
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("L" + "down");
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();				
	        	}
	        }
	    };
	    AbstractAction leftAction = new AbstractAction()
	    {   
	        public void actionPerformed(ActionEvent ae)
	        {
	        	if (prevScreen.isPlayer1) {
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("K" + "left");
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();				
	        	} else {
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("L" + "left");
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();				
	        	}
	        }
	    };
	    AbstractAction rightAction = new AbstractAction()
	    {   
	        public void actionPerformed(ActionEvent ae)
	        {
	        	if (prevScreen.isPlayer1) {
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("K" + "right");
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();					        		
	        	} else {
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("L" + "right");
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();				
	        	}
	        }
	    };
	    AbstractAction spaceAction = new AbstractAction()
	    {   
	        public void actionPerformed(ActionEvent ae)
	        {
	        	if (prevScreen.isPlayer1) {
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("K" + "space");
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();					        		
	        	} else {
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("L" + "space");
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();				
	        	}
	        }
	    };
		AbstractAction rupAction = new AbstractAction()
	    {   
	        public void actionPerformed(ActionEvent ae)
	        {
	        	if (prevScreen.isPlayer1) {
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("K" + "upno");
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();					        		
	        	} else {
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("L" + "upno");
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();				
	        	}
	        }
	    };
	    AbstractAction rdownAction = new AbstractAction()
	    {   
	        public void actionPerformed(ActionEvent ae)
	        {
	        	if (prevScreen.isPlayer1) {
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("K" + "downno");
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();					        		
	        	} else {
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("L" + "downno");
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();				
	        	}
	        }
	    };
	    AbstractAction rleftAction = new AbstractAction()
	    {   
	        public void actionPerformed(ActionEvent ae)
	        {
	        	if (prevScreen.isPlayer1) {
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("K" + "leftno");
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();					        		
	        	} else {
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("L" + "leftno");
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();				
	        	}
	        }
	    };
	    AbstractAction rrightAction = new AbstractAction()
	    {   
	        public void actionPerformed(ActionEvent ae)
	        {
	        	if (prevScreen.isPlayer1) {
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("K" + "rightno");
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();					        		
	        	} else {
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("L" + "rightno");
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();				
	        	}
	        }
	    };
	    AbstractAction rspaceAction = new AbstractAction()
	    {   
	        public void actionPerformed(ActionEvent ae)
	        {
	        	if (prevScreen.isPlayer1) {
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("K" + "spaceno");
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();	        		
	        	} else {
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("L" + "spaceno");
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();				
	        	}
	        }
	    };
	    
	    primary.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "UP");
	    primary.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "DOWN");
	    primary.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "LEFT");
	    primary.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "RIGHT");
	    primary.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("P"), "SPACE");
	    primary.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released UP"), "rUP");
	    primary.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released DOWN"), "rDOWN");
	    primary.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released LEFT"), "rLEFT");
	    primary.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released RIGHT"), "rRIGHT");
	    primary.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released P"), "rSPACE");
	    primary.getActionMap().put("UP", upAction);
	    primary.getActionMap().put("DOWN", downAction);
	    primary.getActionMap().put("LEFT", leftAction);
	    primary.getActionMap().put("RIGHT", rightAction);
	    primary.getActionMap().put("SPACE", spaceAction);
	    primary.getActionMap().put("rUP", rupAction);
	    primary.getActionMap().put("rDOWN", rdownAction);
	    primary.getActionMap().put("rLEFT", rleftAction);
	    primary.getActionMap().put("rRIGHT", rrightAction);
	    primary.getActionMap().put("rSPACE", rspaceAction);
	    
//	    primary.registerKeyboardAction(upAction, "UP", KeyStroke.getKeyStroke("UP"), JComponent.WHEN_IN_FOCUSED_WINDOW);
//	    primary.registerKeyboardAction(downAction, "DOWN", KeyStroke.getKeyStroke("DOWN"), JComponent.WHEN_IN_FOCUSED_WINDOW);
//	    primary.registerKeyboardAction(leftAction, "LEFT", KeyStroke.getKeyStroke("LEFT"), JComponent.WHEN_IN_FOCUSED_WINDOW);
//	    primary.registerKeyboardAction(rightAction, "RIGHT", KeyStroke.getKeyStroke("RIGHT"), JComponent.WHEN_IN_FOCUSED_WINDOW);
//	    primary.registerKeyboardAction(spaceAction, "SPACE", KeyStroke.getKeyStroke("SPACE"), JComponent.WHEN_IN_FOCUSED_WINDOW);
	    
	    sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isPvCGame) {
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("D" + username + ": " + chatField.getText());
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();					
				}
				chatArea.setText(chatArea.getText() + "\n" + username + ": " + chatField.getText());
				chatField.setText("");
				primary.requestFocusInWindow();
			}
		});
		
		chatField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (!isPvCGame) {
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("D" + username + ": " + chatField.getText());
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();					
				}
				chatArea.setText(chatArea.getText() + "\n" + username + ": " + chatField.getText());
				chatField.setText("");
				primary.requestFocusInWindow();
			}
		});
		
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				prevScreen.prevScreen.setVisible(true);
				LoginPage.sqli.toggleLog(MainMenuUser.username);
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
	
	public static void main(String[] args) {

	}
}
