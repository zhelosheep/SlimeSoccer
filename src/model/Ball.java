package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ball {
	public int x, y;
	public double velocityX = 0, velocityY = 0; 
	BufferedImage ballImage;
	private Integer bounceAccelerationY, bounceAccelerationX;
	private Integer numTimesUpdateWasCalledSinceLastXDecceleration;
	private Game game;
	
	// constants
	public int width = 24, height = 24, radius = 12;
	public final int mass = 1;
	final private int decceleration = 1; 
	final private double bounceMultiplier = 0.8;

	Ball(int x, int y, Game game) {
		this.x = x;
		this.y = y;
		this.game = game;
		bounceAccelerationY = null;
		bounceAccelerationX = null;
		numTimesUpdateWasCalledSinceLastXDecceleration = 0;
		ballImage = game.imgBall;
		if (game.specialMode.equals("Giant Ball")) {
			try {
				this.ballImage = ImageIO.read(new File("./resources/game/others/SoccerBallGiant.png"));
			} catch (IOException e) {
				System.out.println("Image IOException in Ball constructor: " + e.getMessage());
			}
			this.width = 60;
			this.height = 60;
			this.radius = 30;
		}
	}
	
	public void update() {
		// gravity
		if (!game.specialMode.equals("Anti-Gravity")) {
			velocityY += decceleration;
		}

		// Keep ball within top boundary
		int yTop = y - (height/2);
		// 1) If ball is about to hit top of screen, make sure it doesn't go past it
		if ( (yTop + velocityY <= game.topLevel) && bounceAccelerationY == null) {
			bounceAccelerationY = (int)((double)velocityY * bounceMultiplier);
			velocityY = game.topLevel - yTop;
    	}
		// 2) If ball just hit top, make it bounce
		if (yTop <= game.topLevel && bounceAccelerationY != null) {
			velocityY = -bounceAccelerationY;
			bounceAccelerationY = null;
		}

		// Keep ball within bottom boundary
		int yBottom = y + (height/2);
		// 1) If ball is about to hit ground, make sure it doesn't go beneath ground level
		if ( (yBottom + velocityY >= game.groundLevel) && bounceAccelerationY == null) {
			bounceAccelerationY = (int)((double)velocityY * bounceMultiplier);
			velocityY = game.groundLevel - yBottom;
    	}
		// 2) If ball just hit ground, make it bounce
		if (yBottom >= game.groundLevel && bounceAccelerationY != null) {
			velocityY = -bounceAccelerationY;
			bounceAccelerationY = null;
		}

		// Keep ball within left boundary
		// 1) If ball is about to hit left boundary, make sure it doesn't go past it
		if ( velocityX < 0 && (x - width/2 + velocityX <= game.leftBoundary) && bounceAccelerationX == null) {
			bounceAccelerationX = (int)((double)-velocityX * bounceMultiplier);
			velocityX = game.leftBoundary - (x - width/2);
    	}
		// 2) If ball just hit ground, make it bounce
		if (x - width/2 <= game.leftBoundary && bounceAccelerationX != null) {
			velocityX = bounceAccelerationX;
			bounceAccelerationX = null;
		}

		// Keep ball within right boundary
		// 1) If ball is about to hit right boundary, make sure it doesn't go past it
		if ( velocityX > 0 && (x + width/2 + velocityX >= game.rightBoundary) && bounceAccelerationX == null) {
			bounceAccelerationX = (int)((double)-velocityX * bounceMultiplier);
			velocityX = (game.rightBoundary - x - width/2);
    	}
		// 2) If ball just hit ground, make it bounce
		if (x + width/2 >= game.rightBoundary && bounceAccelerationX != null) {
			velocityX = bounceAccelerationX;
			bounceAccelerationX = null;
		}
		
		// Decelerate x movement of ball so it won't roll sideways forever
		if (numTimesUpdateWasCalledSinceLastXDecceleration > 25) {
			if (velocityX > 0) {
				velocityX -= decceleration;
			} else if (velocityX < 0) {
				velocityX += decceleration;
			}
			numTimesUpdateWasCalledSinceLastXDecceleration = 0;
		}

        // Moves the ball
        x += velocityX;
        y += velocityY;
        
        numTimesUpdateWasCalledSinceLastXDecceleration++;
	}
	
	public void callMove() {
        x += velocityX;
        y += velocityY;
	}

	public void paint(Graphics g) {
//		g.setColor(Color.GREEN);
//		g.fillArc(getXforPaint(), getYforPaint(), this.width, this.height, 0, 360);
		g.drawImage(ballImage, getXforPaint(), getYforPaint(), width, height, null);
	}

	private int getXforPaint() {
		return (this.x - (width/2));
	}
	private int getYforPaint() {
		return (this.y - (height/2));
	}

}
