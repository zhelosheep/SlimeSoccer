package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Game {
	// image resources
	public static BufferedImage imgBackgroundSoccerfield; 
	public static BufferedImage imgSlimeBomb, imgSlimeBowAndArrow, imgSlimeClone, imgSlimeCosmic, imgSlimeFireball, imgSlimeFisher, imgSlimeGeyser, imgSlimeMagnet, imgSlimeSuperSize, imgSlimeSuper; 
	public static BufferedImage imgBall;

	// game objects
	public static Slime slime1, slime2;
	public static Ball ball;
	final static public int groundLevel = 400; // y value of where ground level is in game
	final static public int leftBoundary = 0, rightBoundary = 600; // y value of where ground level is in game
	
	public Game() {
		// load resources
		try {
			imgBackgroundSoccerfield = ImageIO.read(new File("./resources/game/backgrounds/soccerfield.png"));
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

		// initialize objects
		slime1 = new Slime(100, groundLevel, 1, "SlimeGeyser");
		slime2 = new Slime(500, groundLevel, 2, "SlimeFireball");
		ball = new Ball(300, groundLevel - 12 - 20);
	}
	
	public static void update() {
		// update positions
		slime1.update();
		slime2.update();
		ball.update();
		
		// check for collisions
		Double [] newVelocities = slime1.detectCollision(ball);
		Double [] newVelocities2 = slime2.detectCollision(ball);
		if (newVelocities[0] != null) {
			ball.velocityX = newVelocities[0].intValue();
			ball.velocityY = newVelocities[1].intValue();
			ball.update();
		}
		if (newVelocities2[0] != null) {
			ball.velocityX = newVelocities2[0].intValue();
			ball.velocityY = newVelocities2[1].intValue();
			ball.update();
		}
		

	}
	
}
