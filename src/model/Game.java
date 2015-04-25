package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Game {
	// image resources
	public static BufferedImage imgBackgroundSoccerfield, imgBackgroundDesk, imgBackgroundLaptop, imgBackgroundOuterSpace; 
	public static BufferedImage imgSlimeBomb, imgSlimeBowAndArrow, imgSlimeClone, imgSlimeCosmic, imgSlimeFireball, imgSlimeFisher, imgSlimeGeyser, imgSlimeMagnet, imgSlimeSuperSize, imgSlimeSuper; 
	public static BufferedImage imgBall;

	// game objects
	public static Slime slime1, slime2;
	public static Ball ball;
	public static Goal goal1, goal2;
	static public int groundLevel = 400; // y value of where ground level is in game
	static public int leftBoundary = 0, rightBoundary = 600; // y value of where ground level is in game
	
	// game stats
	public static String background;
	public static BufferedImage imgBackground;
//	public static int round, timer;
	public static String player1_slimeType, player2_slimeType;
	public static String player1_username, player2_username;
	public static int player1_manaCurrent, player2_manaCurrent; // live data
	public static int player1_manaMax, player2_manaMax;
	public static Integer player1_score, player2_score; // live data
	public static boolean player1scored, player2scored;
	public static int manaRegenerationRate;
	public static String specialMode;
	
	public Game(String background, String player1_slimeType, String player2_slimeType, String player1_username, String player2_username, int player1_manaMax, int player2_manaMax, int manaRegenerationRate, String specialMode) {
		// load resources
		try {
			imgBackgroundSoccerfield = ImageIO.read(new File("./resources/game/backgrounds/soccerfield.png"));
			imgBackgroundDesk = ImageIO.read(new File("./resources/game/backgrounds/desk.jpg"));
			imgBackgroundLaptop = ImageIO.read(new File("./resources/game/backgrounds/laptop.png"));
			imgBackgroundOuterSpace = ImageIO.read(new File("./resources/game/backgrounds/outerspace.jpg"));
			imgSlimeBomb = ImageIO.read(new File("./resources/game/slimes/BombSlime.png"));
			imgSlimeBowAndArrow = ImageIO.read(new File("./resources/game/slimes/BowAndArrowSlime.png"));
			imgSlimeClone = ImageIO.read(new File("./resources/game/slimes/CloneSlime.png"));
			imgSlimeCosmic = ImageIO.read(new File("./resources/game/slimes/CosmicSlime.png"));
			imgSlimeFireball = ImageIO.read(new File("./resources/game/slimes/FireballSlime.png"));
			imgSlimeFisher = ImageIO.read(new File("./resources/game/slimes/FisherSlime.png"));
			imgSlimeGeyser = ImageIO.read(new File("./resources/game/slimes/GeyserSlime.png"));
			imgSlimeMagnet = ImageIO.read(new File("./resources/game/slimes/MagnetSlime.png"));
			imgSlimeSuperSize = ImageIO.read(new File("./resources/game/slimes/SuperSizeSlime.png"));
			imgSlimeSuper = ImageIO.read(new File("./resources/game/slimes/SuperSlime.png"));
			imgBall = ImageIO.read(new File("./resources/game/slimes/SoccerBall.png"));
		} catch (IOException e) {
			System.out.println("IOException in load resources (Frame): " + e.getMessage());
		}
		
		// set game stats
		// background
		this.background = background;
		if (background.equals("soccerfield")) {
			imgBackground = imgBackgroundSoccerfield;
		} else if (background.equals("desk")) {
			imgBackground = imgBackgroundDesk;
			groundLevel = 238;
		} else if (background.equals("laptop")) {
			imgBackground = imgBackgroundLaptop;
			groundLevel = 352;
			leftBoundary = 88;
			rightBoundary = 511;
		} else if (background.equals("outerspace")) {
			imgBackground = imgBackgroundOuterSpace;
		}
//		round = 0;
//		timer = 0;
		// slimetype
		this.player1_slimeType = player1_slimeType;
		this.player2_slimeType = player2_slimeType;
		this.player1_username = player1_username;
		this.player2_username = player2_username;
		this.player1_manaCurrent = player1_manaMax;
		this.player2_manaCurrent = player2_manaMax;
		this.player1_manaMax = player1_manaMax;
		this.player2_manaMax = player2_manaMax;
		this.player1_score = 0;
		this.player2_score = 0;
		this.manaRegenerationRate = manaRegenerationRate;
		this.specialMode = specialMode;
		this.player1scored = false;
		this.player2scored = false;
		
		
		// initialize objects
		slime1 = new Slime(leftBoundary + 50, groundLevel, 1, player1_slimeType);
		slime2 = new Slime(rightBoundary - 50, groundLevel, 2, player2_slimeType);
		ball = new Ball((leftBoundary + rightBoundary)/2, groundLevel - 12 - 20);
		goal1 = new Goal(leftBoundary);
		goal2 = new Goal(rightBoundary);
	}
	
	public static void update() {
		// update positions
		slime1.update();
		slime2.update();
		ball.update();
		
		// check for ball collision with slimes
		Double [] slime1CollideNewVelocities = slime1.detectCollision(ball);
		Double [] slime2CollideNewVelocities = slime2.detectCollision(ball);
		if (slime1CollideNewVelocities[0] != null) {
			ball.velocityX = slime1CollideNewVelocities[0].intValue();
			ball.velocityY = slime1CollideNewVelocities[1].intValue();
			ball.callMove();
		}
		if (slime2CollideNewVelocities[0] != null) {
			ball.velocityX = slime2CollideNewVelocities[0].intValue();
			ball.velocityY = slime2CollideNewVelocities[1].intValue();
			ball.callMove();
		}
		// check for ball collision with top piece of goalpost
		Double [] goal1CollideNewVelocities = goal1.detectCollision(ball.x, ball.y, ball.velocityX, ball.velocityY);
		Double [] goal2CollideNewVelocities = goal2.detectCollision(ball.x, ball.y, ball.velocityX, ball.velocityY);
		if (goal1CollideNewVelocities != null) {
			ball.velocityX = goal1CollideNewVelocities[0].intValue();
			ball.velocityY = goal1CollideNewVelocities[1].intValue();
			ball.callMove();
		}
		if (goal2CollideNewVelocities != null) {
			ball.velocityX = goal2CollideNewVelocities[0].intValue();
			ball.velocityY = goal2CollideNewVelocities[1].intValue();
			ball.callMove();
		}
		
		
		// check for goals
		if (goal1.checkGoal(ball.x, ball.y)) {
			goalScored(2);
		}
		if (goal2.checkGoal(ball.x, ball.y)) {
			goalScored(1);
		}

	}
	
	private static void goalScored(int playerThatScored) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException in goalScored() function: " + e.getMessage());
		}
		if (playerThatScored == 1) {
			player1_score++;
			player1scored = true;
		}
		else if (playerThatScored == 2) {
			player2_score++;
			player2scored = true;
		}
		if (playerThatScored == 1 || playerThatScored == 2) { // then reset ball position, slime positions, etc
			ball.x = (leftBoundary + rightBoundary)/2;
			ball.y = groundLevel - 12 - 20;
			ball.velocityX = 0;
			ball.velocityY = 0;
			slime1.x = leftBoundary + 50;
			slime1.y = groundLevel;
			slime1.velocityX = 0;
			slime1.velocityY = 0;
			slime2.x = rightBoundary - 50;
			slime2.y = groundLevel;
			slime2.velocityX = 0;
			slime2.velocityY = 0;
		}
	}
	
}
