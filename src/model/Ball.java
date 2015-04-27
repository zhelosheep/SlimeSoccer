package model;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.Controller;

public class Ball {
	public int x, y;
	public double velocityX = 0, velocityY = 0; 
	BufferedImage ballImage;
	private Integer bounceAccelerationY, bounceAccelerationX;
	private Integer numTimesUpdateWasCalledSinceLastXDecceleration;
	private Variables variables;
	
	// constants
	public int width = 24, height = 24, radius = 12;
	public final int mass = 1;
	final private int decceleration = 1; 
	final private double bounceMultiplier = 0.8;

	public Ball(int x, int y, Variables variables) {
		this.x = x;
		this.y = y;
		this.variables = variables;
		bounceAccelerationY = null;
		bounceAccelerationX = null;
		numTimesUpdateWasCalledSinceLastXDecceleration = 0;
		ballImage = variables.imgBall;
		if (variables.specialMode.equals("Giant Ball")) {
			try {
				this.ballImage = ImageIO.read((getClass().getClassLoader().getResource("game/others/SoccerBallGiant.png")));
			} catch (IOException e) {
				System.out.println("Image IOException in Ball constructor: " + e.getMessage());
			}
			this.width = 60;
			this.height = 60;
			this.radius = 30;
		}
	}
	
	public void update() {
		/***** temporary ******/
//		int velo = 2;
//        // Calculating velocity for moving up 
//        if(Controller.keyboardKeyState(KeyEvent.VK_I)) {
//    		velocityY -= velo;
//        }
//        // Calculating velocity for moving down
//        if(Controller.keyboardKeyState(KeyEvent.VK_K)) {
//    		velocityY += velo;
//        }
//        // Calculating velocity for moving or stopping to the left
//        if(Controller.keyboardKeyState(KeyEvent.VK_J)) {
//    		velocityX -= velo;
//        }
//        // Calculating velocity for moving or stopping to the right
//        if(Controller.keyboardKeyState(KeyEvent.VK_L)) {
//    		velocityX += velo;
//        }
		/***** temporary ******/

		// gravity
		if (!variables.specialMode.equals("Anti-Gravity")) {
			velocityY += decceleration;
		}

		// Keep ball within top boundary
		int yTop = y - (height/2);
		// 1) If ball is about to hit top of screen, make sure it doesn't go past it
		if ( (yTop + velocityY <= variables.topLevel) && bounceAccelerationY == null) {
			bounceAccelerationY = (int)((double)velocityY * bounceMultiplier);
			velocityY = variables.topLevel - yTop;
    	}
		// 2) If ball just hit top, make it bounce
		if (yTop <= variables.topLevel && bounceAccelerationY != null) {
			velocityY = -bounceAccelerationY;
			bounceAccelerationY = null;
		}

		// Keep ball within bottom boundary
		int yBottom = y + (height/2);
		// 1) If ball is about to hit ground, make sure it doesn't go beneath ground level
		if ( (yBottom + velocityY >= variables.groundLevel) && bounceAccelerationY == null) {
			bounceAccelerationY = (int)((double)velocityY * bounceMultiplier);
			velocityY = variables.groundLevel - yBottom;
    	}
		// 2) If ball just hit ground, make it bounce
		if (yBottom >= variables.groundLevel && bounceAccelerationY != null) {
			velocityY = -bounceAccelerationY;
			bounceAccelerationY = null;
		}

		// Keep ball within left boundary
		// 1) If ball is about to hit left boundary, make sure it doesn't go past it
		if ( velocityX < 0 && (x - width/2 + velocityX <= variables.leftBoundary) && bounceAccelerationX == null) {
			bounceAccelerationX = (int)((double)-velocityX * bounceMultiplier);
			velocityX = variables.leftBoundary - (x - width/2);
    	}
		// 2) If ball just hit ground, make it bounce
		if (x - width/2 <= variables.leftBoundary && bounceAccelerationX != null) {
			velocityX = bounceAccelerationX;
			bounceAccelerationX = null;
		}

		// Keep ball within right boundary
		// 1) If ball is about to hit right boundary, make sure it doesn't go past it
		if ( velocityX > 0 && (x + width/2 + velocityX >= variables.rightBoundary) && bounceAccelerationX == null) {
			bounceAccelerationX = (int)((double)-velocityX * bounceMultiplier);
			velocityX = (variables.rightBoundary - x - width/2);
    	}
		// 2) If ball just hit ground, make it bounce
		if (x + width/2 >= variables.rightBoundary && bounceAccelerationX != null) {
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

	// update x y width height
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
