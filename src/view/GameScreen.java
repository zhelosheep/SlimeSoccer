package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JButton sendButton, backButton, settingsButton;
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
		backButton = new JButton("Back");
		settingsButton = new JButton("Settings");
		chatArea = new JTextArea();
		chatField = new JTextField(10);
		slimeSoccerLabel = new JLabel("Slime Soccer");
		slimeSoccerLabel.setFont(new Font("Arial", Font.BOLD, 20));
		slimeSoccerLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
		
		primary = new Canvas(username);

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
		backButton.setFont(new Font("Arial", Font.BOLD, 16));
		backButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 15));
		northPanel.add(slimeSoccerLabel);
		northPanel.add(gameIDLabel);
		northPanel.add(Box.createGlue());
		northPanel.add(avatarLabel);
		northPanel.add(settingsButton);
		northPanel.add(backButton);
		add(northPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.setPreferredSize(new Dimension(610, 460));
		leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
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
		if (!isPvCGame) {
			AbstractAction upAction = new AbstractAction() {
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent ae) {
		        	if (prevScreen.isPlayer1) {
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("K" + "up");
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();					        		
		        	} else {
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("L" + "up");
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();					        		
		        	}
		        }
		    };
		    AbstractAction downAction = new AbstractAction() {
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent ae) {
		        	if (prevScreen.isPlayer1) {
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("K" + "down");
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();					        		
		        	} else {
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("L" + "down");
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();				
		        	}
		        }
		    };
		    AbstractAction leftAction = new AbstractAction() {
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent ae) {
		        	if (prevScreen.isPlayer1) {
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("K" + "left");
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();				
		        	} else {
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("L" + "left");
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();				
		        	}
		        }
		    };
		    AbstractAction rightAction = new AbstractAction() {
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent ae) {
		        	if (prevScreen.isPlayer1) {
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("K" + "right");
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();					        		
		        	} else {
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("L" + "right");
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();				
		        	}
		        }
		    };
		    AbstractAction spaceAction = new AbstractAction() {
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent ae) {
		        	if (prevScreen.isPlayer1) {
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("K" + "space");
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();					        		
		        	} else {
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("L" + "space");
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();				
		        	}
		        }
		    };
			AbstractAction rupAction = new AbstractAction() {
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent ae) {
		        	if (prevScreen.isPlayer1) {
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("K" + "upno");
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();					        		
		        	} else {
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("L" + "upno");
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();				
		        	}
		        }
		    };
		    AbstractAction rdownAction = new AbstractAction() {
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent ae) {
		        	if (prevScreen.isPlayer1) {
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("K" + "downno");
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();					        		
		        	} else {
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("L" + "downno");
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();				
		        	}
		        }
		    };
		    AbstractAction rleftAction = new AbstractAction() {
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent ae) {
		        	if (prevScreen.isPlayer1) {
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("K" + "leftno");
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();					        		
		        	} else {
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("L" + "leftno");
						((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();				
		        	}
		        }
		    };
		    AbstractAction rrightAction = new AbstractAction() {
				private static final long serialVersionUID = 1L;

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
		    AbstractAction rspaceAction = new AbstractAction() {
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent ae) {
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
		} else {
			AbstractAction upAction = new AbstractAction() {
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent ae) {
		        	gt.game.variables.p2_keyboardState[0] = true;
		        }
		    };
		    AbstractAction downAction = new AbstractAction() {
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent ae) {
		        	gt.game.variables.p2_keyboardState[1] = true;
		        }
		    };
		    AbstractAction leftAction = new AbstractAction() {
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent ae) {
		        	gt.game.variables.p2_keyboardState[2] = true;
		        }
		    };
		    AbstractAction rightAction = new AbstractAction() {
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent ae) {
		        	gt.game.variables.p2_keyboardState[3] = true;
		        }
		    };
		    AbstractAction spaceAction = new AbstractAction() {
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent ae) {
		        	gt.game.variables.p2_keyboardState[4] = true;
		        }
		    };
			AbstractAction rupAction = new AbstractAction() {
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent ae) {
		        	gt.game.variables.p2_keyboardState[0] = false;
		        }
		    };
		    AbstractAction rdownAction = new AbstractAction() {
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent ae) {
		        	gt.game.variables.p2_keyboardState[1] = false;
		        }
		    };
		    AbstractAction rleftAction = new AbstractAction() {
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent ae)
		        {
		        	gt.game.variables.p2_keyboardState[2] = false;
		        }
		    };
		    AbstractAction rrightAction = new AbstractAction() {
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent ae)
		        {
		        	gt.game.variables.p2_keyboardState[3] = false;
		        }
		    };
		    AbstractAction rspaceAction = new AbstractAction() {
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent ae)
		        {
		        	gt.game.variables.p2_keyboardState[4] = false;
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
		}
	    
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
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				prevScreen.prevScreen.setVisible(true);
				LoginPage.sqli.toggleLog(MainMenuUser.username);
				if (!isPvCGame) {
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.println("S");
					((MainMenuUserPlayPlayer) prevScreen.prevScreen).prevScreen.sWriter.flush();					
				}
			}
		});
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
		primary.variables.playerThatWon = -1;
		primary.variables.slimeHasMoved_1 = false;
		primary.variables.slimeHasMoved_2 = false;
		primary.variables.gameOver = false;
		
		for (int k = 0; k < 5; k++) {
			primary.variables.p1_keyboardState[k] = false;
			primary.variables.p2_keyboardState[k] = false;
		}
		
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
}
