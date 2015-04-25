package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Ball {
	public int x, y, yBottom;
	public double velocityX = 0, velocityY = 0; 
	BufferedImage ballImage;
	private Integer bounceAccelerationY, bounceAccelerationX;
	private Integer numTimesUpdateWasCalledSinceLastXDecceleration;
	
	// constants
//	public final int width = 24, height = 24, radius = 12;
	public final int width = 24, height = 24, radius = 12;
	public final int mass = 1;
	final private int decceleration = 1; 
	final private double bounceMultiplier = 0.8;

	Ball(int x, int y) {
		this.x = x;
		this.y = y;
		bounceAccelerationY = null;
		bounceAccelerationX = null;
		numTimesUpdateWasCalledSinceLastXDecceleration = 0;
		ballImage = Game.imgBall;
	}
	
	public void update() {
		yBottom = y + (height/2);
		
		// Keep ball within bottom boundary
		// 1) If ball is about to hit ground, make sure it doesn't go beneath ground level
		if ( (yBottom + velocityY >= Game.groundLevel) && bounceAccelerationY == null) {
			bounceAccelerationY = (int)((double)velocityY * bounceMultiplier);
			velocityY = Game.groundLevel - yBottom;
    	} else {
    		velocityY += decceleration;
    	}
		// 2) If ball just hit ground, make it bounce
		if (yBottom >= Game.groundLevel && bounceAccelerationY != null) {
			velocityY = -bounceAccelerationY;
			bounceAccelerationY = null;
		}

		// Keep ball within left boundary
		// 1) If ball is about to hit left boundary, make sure it doesn't go past it
		if ( velocityX < Game.leftBoundary && (x - width/2 + velocityX <= 0) && bounceAccelerationX == null) {
			bounceAccelerationX = (int)((double)-velocityX * bounceMultiplier);
			velocityX = - (x - width/2);
    	}
		// 2) If ball just hit ground, make it bounce
		if (x - width/2 <= Game.leftBoundary && bounceAccelerationX != null) {
			velocityX = bounceAccelerationX;
			bounceAccelerationX = null;
		}

		// Keep ball within right boundary
		// 1) If ball is about to hit right boundary, make sure it doesn't go past it
		if ( velocityX > 0 && (x + width/2 + velocityX >= Game.rightBoundary) && bounceAccelerationX == null) {
			bounceAccelerationX = (int)((double)-velocityX * bounceMultiplier);
			velocityX = (Game.rightBoundary - x - width/2);
    	}
		// 2) If ball just hit ground, make it bounce
		if (x + width/2 >= Game.rightBoundary && bounceAccelerationX != null) {
			velocityX = bounceAccelerationX;
			bounceAccelerationX = null;
		}
		
		if (numTimesUpdateWasCalledSinceLastXDecceleration > 25) {
			if (velocityX > 0) {
				velocityX -= decceleration;
			} else if (velocityX < 0) {
				velocityX += decceleration;
			}
			numTimesUpdateWasCalledSinceLastXDecceleration = 0;
			System.out.println(velocityX);
		}

        // Moves the ball
        x += velocityX;
        y += velocityY;
        
        numTimesUpdateWasCalledSinceLastXDecceleration++;
	}

	public void paint(Graphics g) {
//		g.setColor(Color.GREEN);
//		g.fillArc(getXforPaint(), getYforPaint(), this.width, this.height, 0, 360);
		g.drawImage(ballImage, getXforPaint(), getYforPaint(), null);
	}

	private int getXforPaint() {
		return (this.x - (width/2));
	}
	private int getYforPaint() {
		return (this.y - (height/2));
	}

}
