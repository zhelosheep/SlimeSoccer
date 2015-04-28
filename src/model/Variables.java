package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import network.ServerThread;

public class Variables {
	// try 
	public boolean[] p1_keyboardState = new boolean[535];
	public boolean[] p2_keyboardState = new boolean[535];
	
	// image resources
	public BufferedImage imgBall;
	public BufferedImage imgBackground;
	public BufferedImage imgGoal;
	public BufferedImage imgSlime1, imgSlime2;
	public BufferedImage imgSlimeBombSpecial, imgSlimeBowAndArrowSpecial, imgSlimeCosmicSpecial, imgFisherSpecial, imgGameoverLose;
	public BufferedImage imgGameoverPlayer1, imgGameoverPlayer2;
	
	// game objects
	public Slime slime1 = null, slime2 = null; // live data
	public Ball ball = null; // live data
	public Goal goal1 = null, goal2 = null;
	public int groundLevel = 400, topLevel = 0; // y value of where ground level and top of screen are in game
	public int leftBoundary = 0, rightBoundary = 600; 
	
	// game stats
	public String background = "Soccer Field";
	public String player1_slimeType = "SlimeBomb", player2_slimeType = "SlimeBomb";
	public String player1_username = "", player2_username = "";
	public double player1_manaCurrent = 0, player2_manaCurrent = 0; // live data
	public int player1_manaMax = 0, player2_manaMax = 0;
	public Integer player1_score = 0, player2_score = 0; // live data
	public boolean player1scored = false, player2scored = false; // live data
	public int manaRegenerationRate = 0;
	public String specialMode = "";
	public boolean gameOver = false; // live data
	public int playerThatWon = -1; // live data
	public boolean slimeHasMoved_1 = false; // live data
	public boolean slimeHasMoved_2 = false; // live data
//	public String gameID = "";
	
	public Variables() {
		try {
			//default
			imgBackground = ImageIO.read((getClass().getClassLoader().getResource("game/backgrounds/soccerfield.png")));
			imgSlime1 = ImageIO.read((getClass().getClassLoader().getResource("game/slimes/BombSlime.png")));
			imgSlime2 = ImageIO.read((getClass().getClassLoader().getResource("game/slimes/BombSlime.png")));
			
			imgBall = ImageIO.read((getClass().getClassLoader().getResource("game/others/SoccerBall.png")));
			imgGoal = ImageIO.read((getClass().getClassLoader().getResource("game/others/Goal.png")));
			imgGameoverPlayer1 = ImageIO.read((getClass().getClassLoader().getResource("game/others/gameover1.png")));
			imgGameoverPlayer2 = ImageIO.read((getClass().getClassLoader().getResource("game/others/gameover2.png")));
			imgGameoverLose = ImageIO.read((getClass().getClassLoader().getResource("game/others/gameoverLose.png")));
			imgSlimeBombSpecial = ImageIO.read((getClass().getClassLoader().getResource("game/slimes/SlimeSpecialEffects/Bomb.png")));
			imgSlimeBowAndArrowSpecial = ImageIO.read((getClass().getClassLoader().getResource("game/slimes/SlimeSpecialEffects/BowAndArrow.png")));
			imgSlimeCosmicSpecial = ImageIO.read((getClass().getClassLoader().getResource("game/slimes/SlimeSpecialEffects/Cosmic.png")));
			imgFisherSpecial = ImageIO.read((getClass().getClassLoader().getResource("game/slimes/SlimeSpecialEffects/Fisher.png")));
		} catch (IOException ioe) {
			System.out.println("IOException in Variables(): " + ioe.getMessage());
		}
//		this.gameID = ServerThread.getGameID().toString();

	}
	
	public void setBackground(String background) {
		this.background = background;
		try {
			if (background.equals("Soccer Field")) {
				imgBackground = ImageIO.read((getClass().getClassLoader().getResource("game/backgrounds/soccerfield.png")));
			} else if (background.equals("Desk")) {
				imgBackground = ImageIO.read((getClass().getClassLoader().getResource("game/backgrounds/desk.jpg")));
				groundLevel = 238;
			} else if (background.equals("Laptop Screen")) {
				imgBackground = ImageIO.read((getClass().getClassLoader().getResource("game/backgrounds/laptop.png")));
				topLevel = 90;
				groundLevel = 352;
				leftBoundary = 88;
				rightBoundary = 511;
			} else if (background.equals("Outer Space")) {
				imgBackground = ImageIO.read((getClass().getClassLoader().getResource("game/backgrounds/outerspace.jpg")));
			}			
		} catch (IOException ioe) {
			System.out.println("IOException in Variables.setBackground(): " + ioe.getMessage());
		}
	}
	
	public void setSlimeImage(int slimeNum, String slimeType) {
		try {
			if (slimeNum == 1) {
				player1_slimeType = slimeType;
				if (player1_slimeType.equals("SlimeBomb")) {
					imgSlime1 = ImageIO.read((getClass().getClassLoader().getResource("game/slimes/BombSlime.png")));
				} else if (player1_slimeType.equals("SlimeBowAndArrow")) {
					imgSlime1 = ImageIO.read((getClass().getClassLoader().getResource("game/slimes/BowAndArrowSlime.png")));
				} else if (player1_slimeType.equals("SlimeClone")) {
					imgSlime1 = ImageIO.read((getClass().getClassLoader().getResource("game/slimes/CloneSlime.png")));
				} else if (player1_slimeType.equals("SlimeCosmic")) {
					imgSlime1 = ImageIO.read((getClass().getClassLoader().getResource("game/slimes/CosmicSlime.png")));
				} else if (player1_slimeType.equals("SlimeFireball")) {
					imgSlime1 = ImageIO.read((getClass().getClassLoader().getResource("game/slimes/FireballSlime.png")));
				} else if (player1_slimeType.equals("SlimeFisher")) {
					imgSlime1 = ImageIO.read((getClass().getClassLoader().getResource("game/slimes/FisherSlime.png")));
				} else if (player1_slimeType.equals("SlimeGeyser")) {
					imgSlime1 = ImageIO.read((getClass().getClassLoader().getResource("game/slimes/GeyserSlime.png")));
				} else if (player1_slimeType.equals("SlimeMagnet")) {
					imgSlime1 = ImageIO.read((getClass().getClassLoader().getResource("game/slimes/MagnetSlime.png")));
				} else if (player1_slimeType.equals("SlimeSuperSize")) {
					imgSlime1 = ImageIO.read((getClass().getClassLoader().getResource("game/slimes/SuperSizeSlime.png")));
				} else if (player1_slimeType.equals("SlimeSuper")) {
					imgSlime1 = ImageIO.read((getClass().getClassLoader().getResource("game/slimes/SuperSlime.png")));
				} else if (player1_slimeType.equals("Slime3D")) {
					imgSlime1 = ImageIO.read((getClass().getClassLoader().getResource("game/specialslimes/3DSlime.png")));
				} else if (player1_slimeType.equals("SlimeButterfly")) {
					imgSlime1 = ImageIO.read((getClass().getClassLoader().getResource("game/specialslimes/Butterfly.png")));
				} else if (player1_slimeType.equals("SlimeRonaldo")) {
					imgSlime1 = ImageIO.read((getClass().getClassLoader().getResource("game/specialslimes/CristianoRonaldoSlime.png")));
				} else if (player1_slimeType.equals("SlimeCrown")) {
					imgSlime1 = ImageIO.read((getClass().getClassLoader().getResource("game/specialslimes/Crown.png")));
				} else if (player1_slimeType.equals("SlimeDunce")) {
					imgSlime1 = ImageIO.read((getClass().getClassLoader().getResource("game/specialslimes/Dunce.png")));
				} else if (player1_slimeType.equals("LSlime")) {
					imgSlime1 = ImageIO.read((getClass().getClassLoader().getResource("game/specialslimes/LSlime.png")));
				} else if (player1_slimeType.equals("SlimePotato")) {
					imgSlime1 = ImageIO.read((getClass().getClassLoader().getResource("game/specialslimes/Potato.png")));
				} else if (player1_slimeType.equals("SlimeSweater")) {
					imgSlime1 = ImageIO.read((getClass().getClassLoader().getResource("game/specialslimes/SweaterSlime.png")));
				}	
				
			} else if (slimeNum == 2) {
				player2_slimeType = slimeType;
				if (player2_slimeType.equals("SlimeBomb")) {
					imgSlime2 = ImageIO.read((getClass().getClassLoader().getResource("game/slimes/BombSlime.png")));
				} else if (player2_slimeType.equals("SlimeBowAndArrow")) {
					imgSlime2 = ImageIO.read((getClass().getClassLoader().getResource("game/slimes/BowAndArrowSlime.png")));
				} else if (player2_slimeType.equals("SlimeClone")) {
					imgSlime2 = ImageIO.read((getClass().getClassLoader().getResource("game/slimes/CloneSlime.png")));
				} else if (player2_slimeType.equals("SlimeCosmic")) {
					imgSlime2 = ImageIO.read((getClass().getClassLoader().getResource("game/slimes/CosmicSlime.png")));
				} else if (player2_slimeType.equals("SlimeFireball")) {
					imgSlime2 = ImageIO.read((getClass().getClassLoader().getResource("game/slimes/FireballSlime.png")));
				} else if (player2_slimeType.equals("SlimeFisher")) {
					imgSlime2 = ImageIO.read((getClass().getClassLoader().getResource("game/slimes/FisherSlime.png")));
				} else if (player2_slimeType.equals("SlimeGeyser")) {
					imgSlime2 = ImageIO.read((getClass().getClassLoader().getResource("game/slimes/GeyserSlime.png")));
				} else if (player2_slimeType.equals("SlimeMagnet")) {
					imgSlime2 = ImageIO.read((getClass().getClassLoader().getResource("game/slimes/MagnetSlime.png")));
				} else if (player2_slimeType.equals("SlimeSuperSize")) {
					imgSlime2 = ImageIO.read((getClass().getClassLoader().getResource("game/slimes/SuperSizeSlime.png")));
				} else if (player2_slimeType.equals("SlimeSuper")) {
					imgSlime2 = ImageIO.read((getClass().getClassLoader().getResource("game/slimes/SuperSlime.png")));
				} else if (player2_slimeType.equals("Slime3D")) {
					imgSlime2 = ImageIO.read((getClass().getClassLoader().getResource("game/specialslimes/3DSlime.png")));
				} else if (player2_slimeType.equals("SlimeButterfly")) {
					imgSlime2 = ImageIO.read((getClass().getClassLoader().getResource("game/specialslimes/Butterfly.png")));
				} else if (player2_slimeType.equals("SlimeRonaldo")) {
					imgSlime2 = ImageIO.read((getClass().getClassLoader().getResource("game/specialslimes/CristianoRonaldoSlime.png")));
				} else if (player2_slimeType.equals("SlimeCrown")) {
					imgSlime2 = ImageIO.read((getClass().getClassLoader().getResource("game/specialslimes/Crown.png")));
				} else if (player2_slimeType.equals("SlimeDunce")) {
					imgSlime2 = ImageIO.read((getClass().getClassLoader().getResource("game/specialslimes/Dunce.png")));
				} else if (player2_slimeType.equals("LSlime")) {
					imgSlime2 = ImageIO.read((getClass().getClassLoader().getResource("game/specialslimes/LSlime.png")));
				} else if (player2_slimeType.equals("SlimePotato")) {
					imgSlime2 = ImageIO.read((getClass().getClassLoader().getResource("game/specialslimes/Potato.png")));
				} else if (player2_slimeType.equals("SlimeSweater")) {
					imgSlime2 = ImageIO.read((getClass().getClassLoader().getResource("game/specialslimes/SweaterSlime.png")));
				}
			}
		} catch (IOException ioe) {
			System.out.println("IOException in Variables.setSlimeImages(): " + ioe.getMessage());
		}
	}
	
	public String stringify() {
		return player1_slimeType + "$" + player2_slimeType + "$" + player1_username + "$" + player2_username + "$" + specialMode + "$" + background + "$" + manaRegenerationRate + "$" + player1_manaMax;
	}
}
