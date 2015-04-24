package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import view.Frame;

public class Slime {
	public int x, y; // displacement
	private double speedX = 0, speedY = 0; // velocity
	boolean facingLeft; // true if slime is facing left, right if slime is facing right
	private String slimeType;
	private BufferedImage slimeImage;

	// constants
//	final private int maxSpeed = 6;
//	final private int acceleration = 1, decceleration = 1, jumpAcceleration = 12; // acceleration 
	
	//fast
	final private int maxSpeed = 13; 
	final private int acceleration = 6, decceleration = 1, jumpAcceleration = 18; // acceleration 
	//temp: fast
	
	final private int width = 70, height = 70, radius = 35;
	public final int mass = 5;
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
        // Calculating speed for moving up or down
        if(Frame.controller.keyboardKeyState(upKey) && y == Game.groundLevel) {
            speedY -= jumpAcceleration;
        } else {
        	if (y >= Game.groundLevel) {
        		speedY = 0;
        	} else {
        		speedY += decceleration;
        	}
        }
        
        // Calculating speed for moving or stopping to the left
        if(Frame.controller.keyboardKeyState(leftKey)) {
        	facingLeft = true;
        	if (speedX >= -maxSpeed) {
                speedX -= acceleration;
        	}
        } else if (speedX < 0) {
            speedX += decceleration;
        }
        
        // Calculating speed for moving or stopping to the right
        if(Frame.controller.keyboardKeyState(rightKey)) {
        	facingLeft = false;
        	if (speedX <= maxSpeed) {
        		speedX += acceleration;
        	}
        } else if (speedX > 0) {
            speedX -= decceleration;
        }
        
        if (x < width/2) { // if slime is outside of left boundary, put slime back in boundary and stop movement
        	speedX = 0; 
        	x = width/2;
        }
        if (x > 800 - width/2) { // if slime is outside of right boundary, put slime back in boundary and stop movement
        	speedX = 0;
        	x = 800 - width/2;
        }
        
        // Moves the rocket
        x += speedX;
        y += speedY;
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
		Double [] newVelocities = new Double[4];
		
		// 1) AABB check (axis-aligned bounding box). This is a non-intensive way to initially determine if there is a chance that the slime collided with the ball.
		boolean AABBcheck = false;
		if (this.x + this.radius + ball.radius > ball.x && this.x < ball.x + this.radius + ball.radius
		&& this.y + /*this.radius +*/ ball.radius > ball.y && this.y < ball.y + this.radius + ball.radius) {
//			System.out.println("collision!");
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
			} else {
				collision = false;
				return newVelocities;
			}
		}
		
		// 3) if slime has collided with a ball, determine new velocities of each object
		if (collision) {
			double newVelX1 = (this.speedX * (this.mass - ball.mass) + (2 * ball.mass * ball.speedX)) / (this.mass + ball.mass);
			double newVelY1 = (this.speedY * (this.mass - ball.mass) + (2 * ball.mass * ball.speedY)) / (this.mass + ball.mass);
//			double newVelX2 = (ball.speedX * (ball.mass - this.mass) + (2 * this.mass * this.speedX)) / (this.mass + ball.mass);
//			double newVelY2 = (ball.speedY * (ball.mass - this.mass) + (2 * this.mass * this.speedY)) / (this.mass + ball.mass);
			double newVelX2 = (ball.mass * ball.speedX + this.mass * this.speedX) / ball.mass;
			double newVelY2 = (ball.mass * ball.speedY + this.mass * this.speedY) / ball.mass;
			newVelocities[0] = newVelX1;
			newVelocities[1] = newVelY1;
			newVelocities[2] = newVelX2;
			newVelocities[3] = newVelY2;
		}
		return newVelocities;
	}

}
