package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Variables {
	// try 
	private static boolean[] p1_keyboardState = new boolean[535];
	private static boolean[] p2_keyboardState = new boolean[535];
	
	// image resources
	public BufferedImage imgBall;
	public BufferedImage imgBackground;
	public BufferedImage imgGoal;
	public BufferedImage imgSlime1, imgSlime2;
	public BufferedImage imgSlimeBombSpecial, imgSlimeBowAndArrowSpecial, imgSlimeCosmicSpecial, imgFisherSpecial;
	public BufferedImage imgGameoverPlayer1, imgGameoverPlayer2;
	
	// game objects
	public Slime slime1, slime2;
	public Ball ball;
	public Goal goal1, goal2;
	public int groundLevel = 400, topLevel = 0; // y value of where ground level and top of screen are in game
	public int leftBoundary = 0, rightBoundary = 600; 
	
	// game stats
	public String background;
	public String player1_slimeType, player2_slimeType;
	public String player1_username, player2_username;
	public double player1_manaCurrent, player2_manaCurrent; // live data
	public int player1_manaMax, player2_manaMax;
	public Integer player1_score, player2_score; // live data
	public boolean player1scored, player2scored;
	public int manaRegenerationRate;
	public String specialMode;
	public boolean gameOver;
	public int playerThatWon;
	public boolean slimeHasMoved_1 = false;
	public boolean slimeHasMoved_2 = false;
	
	public Variables() {
		try {
			imgBall = ImageIO.read(new File("./resources/game/others/SoccerBall.png"));
			imgGoal = ImageIO.read(new File("./resources/game/others/Goal.png"));
			imgGameoverPlayer1 = ImageIO.read(new File("./resources/game/others/gameover1.png"));
			imgGameoverPlayer2 = ImageIO.read(new File("./resources/game/others/gameover2.png"));
		} catch (IOException ioe) {
			System.out.println("IOException in Variables(): " + ioe.getMessage());
		}
	}
	
	public void setBackground(String background) {
		this.background = background;
		try {
			if (background.equals("soccerfield")) {
				imgBackground = ImageIO.read(new File("./resources/game/backgrounds/soccerfield.png"));
			} else if (background.equals("desk")) {
				imgBackground = ImageIO.read(new File("./resources/game/backgrounds/desk.jpg"));
				groundLevel = 238;
			} else if (background.equals("laptop")) {
				imgBackground = ImageIO.read(new File("./resources/game/backgrounds/laptop.png"));
				topLevel = 90;
				groundLevel = 352;
				leftBoundary = 88;
				rightBoundary = 511;
			} else if (background.equals("outerspace")) {
				imgBackground = ImageIO.read(new File("./resources/game/backgrounds/outerspace.jpg"));
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
					imgSlime1 = ImageIO.read(new File("./resources/game/slimes/BombSlime.png"));
					imgSlimeBombSpecial = ImageIO.read(new File("./resources/game/slimes/SlimeSpecialEffects/Bomb.png"));
				} else if (player1_slimeType.equals("SlimeBowAndArrow")) {
					imgSlime1 = ImageIO.read(new File("./resources/game/slimes/BowAndArrowSlime.png"));
					imgSlimeBowAndArrowSpecial = ImageIO.read(new File("./resources/game/slimes/SlimeSpecialEffects/BowAndArrow.png"));
				} else if (player1_slimeType.equals("SlimeClone")) {
					imgSlime1 = ImageIO.read(new File("./resources/game/slimes/CloneSlime.png"));
				} else if (player1_slimeType.equals("SlimeCosmic")) {
					imgSlime1 = ImageIO.read(new File("./resources/game/slimes/CosmicSlime.png"));
					imgSlimeCosmicSpecial = ImageIO.read(new File("./resources/game/slimes/SlimeSpecialEffects/Cosmic.png"));
				} else if (player1_slimeType.equals("SlimeFireball")) {
					imgSlime1 = ImageIO.read(new File("./resources/game/slimes/FireballSlime.png"));
				} else if (player1_slimeType.equals("SlimeFisher")) {
					imgSlime1 = ImageIO.read(new File("./resources/game/slimes/FisherSlime.png"));
					imgFisherSpecial = ImageIO.read(new File("./resources/game/slimes/SlimeSpecialEffects/Fisher.png"));
				} else if (player1_slimeType.equals("SlimeGeyser")) {
					imgSlime1 = ImageIO.read(new File("./resources/game/slimes/GeyserSlime.png"));
				} else if (player1_slimeType.equals("SlimeMagnet")) {
					imgSlime1 = ImageIO.read(new File("./resources/game/slimes/MagnetSlime.png"));
				} else if (player1_slimeType.equals("SlimeSuperSize")) {
					imgSlime1 = ImageIO.read(new File("./resources/game/slimes/SuperSizeSlime.png"));
				} else if (player1_slimeType.equals("SlimeSuper")) {
					imgSlime1 = ImageIO.read(new File("./resources/game/slimes/SuperSlime.png"));
				}
			} else if (slimeNum == 2) {
				player2_slimeType = slimeType;
				if (player2_slimeType.equals("SlimeBomb")) {
					imgSlime2 = ImageIO.read(new File("./resources/game/slimes/BombSlime.png"));
					imgSlimeBombSpecial = ImageIO.read(new File("./resources/game/slimes/SlimeSpecialEffects/Bomb.png"));
				} else if (player2_slimeType.equals("SlimeBowAndArrow")) {
					imgSlime2 = ImageIO.read(new File("./resources/game/slimes/BowAndArrowSlime.png"));
					imgSlimeBowAndArrowSpecial = ImageIO.read(new File("./resources/game/slimes/SlimeSpecialEffects/BowAndArrow.png"));
				} else if (player2_slimeType.equals("SlimeClone")) {
					imgSlime2 = ImageIO.read(new File("./resources/game/slimes/CloneSlime.png"));
				} else if (player2_slimeType.equals("SlimeCosmic")) {
					imgSlime2 = ImageIO.read(new File("./resources/game/slimes/CosmicSlime.png"));
					imgSlimeCosmicSpecial = ImageIO.read(new File("./resources/game/slimes/SlimeSpecialEffects/Cosmic.png"));
				} else if (player2_slimeType.equals("SlimeFireball")) {
					imgSlime2 = ImageIO.read(new File("./resources/game/slimes/FireballSlime.png"));
				} else if (player2_slimeType.equals("SlimeFisher")) {
					imgSlime2 = ImageIO.read(new File("./resources/game/slimes/FisherSlime.png"));
					imgFisherSpecial = ImageIO.read(new File("./resources/game/slimes/SlimeSpecialEffects/Fisher.png"));
				} else if (player2_slimeType.equals("SlimeGeyser")) {
					imgSlime2 = ImageIO.read(new File("./resources/game/slimes/GeyserSlime.png"));
				} else if (player2_slimeType.equals("SlimeMagnet")) {
					imgSlime2 = ImageIO.read(new File("./resources/game/slimes/MagnetSlime.png"));
				} else if (player2_slimeType.equals("SlimeSuperSize")) {
					imgSlime2 = ImageIO.read(new File("./resources/game/slimes/SuperSizeSlime.png"));
				} else if (player2_slimeType.equals("SlimeSuper")) {
					imgSlime2 = ImageIO.read(new File("./resources/game/slimes/SuperSlime.png"));
				}
			}
		} catch (IOException ioe) {
			System.out.println("IOException in Variables.setSlimeImages(): " + ioe.getMessage());
		}
	}
}
