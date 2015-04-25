package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import view.Frame;

public class Slime {
	public int x, y; // displacement
	private double velocityX = 0, velocityY = 0; // velocity
	boolean facingLeft; // true if slime is facing left, right if slime is facing right
	private String slimeType;
	private BufferedImage slimeImage;

	// constants
	final private int maxSpeed = 6;
	final private int acceleration = 1, decceleration = 1, jumpAcceleration = 12; // acceleration — Note: these values are scalar, unlike velocity
	//temporary: faster constants
//	final private int maxSpeed = 13; 
//	final private int acceleration = 3, decceleration = 1, jumpAcceleration = 12; // acceleration — Note: these values are scalar, unlike velocity
	//temporary: faster constants
	
//	final private int width = 70, height = 70, radius = 35;
	final private int width = 54, height = 54, radius = 27;
	public final int mass = 1;
	private int upKey, leftKey, rightKey; // KeyEvent codes
	
	public Slime(int x, int y, int player, String slimeType) {
		this.x = x;
		this.y = y;
		this.slimeType = slimeType;
		if (player == 1) {
			upKey = KeyEvent.VK_W;
			leftKey = KeyEvent.VK_A;
			rightKey = KeyEvent.VK_D;
			facingLeft = false;
		} else if (player == 2) {
			upKey = KeyEvent.VK_UP;
			leftKey = KeyEvent.VK_LEFT;
			rightKey = KeyEvent.VK_RIGHT;
			facingLeft = true;
		}
		if (slimeType.equals("SlimeBomb")) {
			slimeImage = Game.imgSlimeBomb;
		} else if (slimeType.equals("SlimeBowAndArrow")) {
			slimeImage = Game.imgSlimeBowAndArrow;
		} else if (slimeType.equals("SlimeClone")) {
			slimeImage = Game.imgSlimeClone;
		} else if (slimeType.equals("SlimeCosmic")) {
			slimeImage = Game.imgSlimeCosmic;
		} else if (slimeType.equals("SlimeFireball")) {
			slimeImage = Game.imgSlimeFireball;
		} else if (slimeType.equals("SlimeFisher")) {
			slimeImage = Game.imgSlimeFisher;
		} else if (slimeType.equals("SlimeGeyser")) {
			slimeImage = Game.imgSlimeGeyser;
		} else if (slimeType.equals("SlimeMagnet")) {
			slimeImage = Game.imgSlimeMagnet;
		} else if (slimeType.equals("SlimeSuperSize")) {
			slimeImage = Game.imgSlimeSuperSize;
		} else if (slimeType.equals("SlimeSuper")) {
			slimeImage = Game.imgSlimeSuper;
		}
	}
	
	public void update() {
        // Calculating velocity for moving up or down
        if(Frame.controller.keyboardKeyState(upKey) && y == Game.groundLevel) {
            velocityY -= jumpAcceleration;
        } else {
        	if (y >= Game.groundLevel) {
        		velocityY = 0;
        	} else {
        		velocityY += decceleration;
        	}
        }
        
        // Calculating velocity for moving or stopping to the left
        if(Frame.controller.keyboardKeyState(leftKey)) {
        	facingLeft = true;
        	if (velocityX >= -maxSpeed) {
                velocityX -= acceleration;
        	}
        } else if (velocityX < 0) {
            velocityX += decceleration;
        }
        
        // Calculating velocity for moving or stopping to the right
        if(Frame.controller.keyboardKeyState(rightKey)) {
        	facingLeft = false;
        	if (velocityX <= maxSpeed) {
        		velocityX += acceleration;
        	}
        } else if (velocityX > Game.leftBoundary) {
            velocityX -= decceleration;
        }
        
        if (x < width/2) { // if slime is outside of left boundary, put slime back in boundary and stop movement
        	velocityX = 0; 
        	x = width/2;
        }
        if (x > Game.rightBoundary - width/2) { // if slime is outside of right boundary, put slime back in boundary and stop movement
        	velocityX = 0;
        	x = Game.rightBoundary - width/2;
        }
        // Moves the slime
        x += velocityX;
        y += velocityY;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillArc(getXforPaint(), getYforPaint(), width, height, 0, 180);
		if (facingLeft) {
			g.drawImage(this.slimeImage, getXforPaintImage() + this.width, getYforPaintImage(), -this.width, this.height, null);
		} else {
			g.drawImage(this.slimeImage, getXforPaintImage(), getYforPaintImage(), null);
		}
	}
	
	// below getXfor or getYfor functions return the origin position of slime semicircle (as opposed to top-left of slime)
	private int getXforPaint() {
		return (this.x - (width/2));
	}
	private int getYforPaint() {
		return (this.y - (height/2));
	}
	private int getXforPaintImage() {
		return (this.x - (width/2) + 1); // slightly adjusted values for returning x value of image because images aren't exactly 70x70
	}
	private int getYforPaintImage() {
		return (this.y - ((3*height)/4) - 3); // slightly adjusted values for returning y value of image because images aren't exactly 70x70
	}


	public Double[] detectCollision(Ball ball) {
		Double [] newVelocities = new Double[2];
		
		// 1) AABB check (axis-aligned bounding box). This is a non-intensive way to initially determine if there is a chance that the slime collided with the ball.
		boolean AABBcheck = false;
		if (this.x + this.radius + ball.radius > ball.x && this.x < ball.x + this.radius + ball.radius
		&& this.y + ball.radius > ball.y && this.y < ball.y + this.radius + ball.radius) {
			AABBcheck = true;
		} else {
			AABBcheck = false;
			return newVelocities;
		}
		
		// 2) if AABB check says there is a chance the slime has collided with a ball, find out for certain by checking if the radiuses intersect.
		boolean collision = false;
		if (AABBcheck) {
			double distance = Math.sqrt(((this.x - ball.x) * (this.x - ball.x)) + ((this.y - ball.y) * (this.y - ball.y)));
			if (distance < this.radius + ball.radius) {
				collision = true;
				System.out.println("collision!");
			} else {
				collision = false;
				return newVelocities;
			}
		}
		
		// 3) if slime has collided with a ball, determine resulting velocity of ball (slime will not bounce away from ball — just like in the online version —
		// even though it technically should according to the rules of momentum 
		if (collision) {
//			double newVelocityX = (ball.mass * ball.velocityX + this.mass * this.velocityX) / ball.mass;
//			double newVelocityY = (ball.mass * ball.velocityY + this.mass * this.velocityY) / ball.mass;
			
			Double newVelocityX = null, newVelocityY = null;
			
			// Find x velocity resulting from collision
			// if ball and slime are going in opposite directions (or one object is stationary while other hits it)
			if ( (ball.velocityX >= 0 && this.velocityX <= 0) || (ball.velocityX <= 0 && this.velocityX >= 0) ) {
				newVelocityX = (Math.abs(ball.velocityX) + Math.abs(this.velocityX));
				if (this.velocityX < 0 || (ball.velocityX > 0 && this.velocityX == 0)) {
					newVelocityX = -newVelocityX;
				}
			}
			// if ball and slime are going in same direction
			if ( (ball.velocityX > 0 && this.velocityX > 0) || ball.velocityX < 0 && this.velocityX < 0) {
				if (ball.velocityX > this.velocityX) {
					newVelocityX = ball.velocityX + 0.2*this.velocityX;
				} else {
					newVelocityX = this.velocityX + 0.2*ball.velocityX;
				}
			}
			
			// Find y velocity resulting from collision
			// if ball and slime are going in opposite directions (or one object is stationary while other hits it)
			if ( (ball.velocityY >= 0 && this.velocityY <= 0) || (ball.velocityY <= 0 && this.velocityY >= 0) ) {
				newVelocityY = 0.7*(Math.abs(ball.velocityY) + Math.abs(this.velocityY));
				if (this.velocityY < 0 || (ball.velocityY > 0 && this.velocityY == 0) ) {
					newVelocityY = -newVelocityY;
				}
			}
			// if ball and slime are going in same direction
			if ( (ball.velocityY > 0 && this.velocityY > 0) || ball.velocityY < 0 && this.velocityY < 0) {
				if (ball.velocityY > this.velocityY) {
					newVelocityY = ball.velocityY + 0.2*this.velocityY;
				} else {
					newVelocityY = this.velocityY + 0.2*ball.velocityY;
				}
			} else if (ball.velocityY < 15){
				newVelocityY -= 8; // this isn't right according to physics, but the online slime game does this and it makes the ball bouncier
			}

			newVelocities[0] = newVelocityX;
			newVelocities[1] = newVelocityY;
		}
		return newVelocities;
	}

}
