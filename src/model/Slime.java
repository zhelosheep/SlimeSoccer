package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import controller.Controller;

public class Slime {
	// numbers for painting slime in right position, direction, etc
	public int x, y; 
	public double velocityX = 0, velocityY = 0; 
	boolean facingLeft; // true if slime is facing left, right if slime is facing right
	protected BufferedImage slimeImage;
	public boolean specialPowerBeingUsed;
	protected Variables variables;

	// constants
	final int finalMaxSpeed = 6;
	protected int maxSpeed = finalMaxSpeed;
	final int finalAcceleration = 1;
	protected int acceleration = finalAcceleration;
	final int decceleration = 1, jumpAcceleration = 12; 
	final int finalWidth = 54, finalHeight = 54, finalRadius = 27;
	protected int width = finalWidth, height = finalHeight, radius = finalRadius;
	final public int mass = 1;
	protected int player;
	final protected int manaUsageRate = 5; // determines how fast mana is consumed when slime uses special power
	private int upKey, leftKey, rightKey, downKey, powerKey; // KeyEvent codes
	
	public Slime(int x, int y, int player, BufferedImage slimeImage, Variables variables) {
		this.x = x;
		this.y = y;
		this.variables = variables;
		specialPowerBeingUsed = false;
		this.slimeImage = slimeImage;
		this.player = player;
		if (player == 1) {
			upKey = KeyEvent.VK_W;
			downKey = KeyEvent.VK_S;
			leftKey = KeyEvent.VK_A;
			rightKey = KeyEvent.VK_D;
			powerKey = KeyEvent.VK_1;
			facingLeft = false;
		} else if (player == 2) {
			upKey = KeyEvent.VK_UP;
			downKey = KeyEvent.VK_DOWN;
			leftKey = KeyEvent.VK_LEFT;
			rightKey = KeyEvent.VK_RIGHT;
			powerKey = KeyEvent.VK_SPACE;
			facingLeft = true;
		}
		this.slimeImage = slimeImage;
		if (variables.specialMode.equals("Mini Slime")) {
			this.width = 30;
			this.height = 30;
			this.radius = 15;
		}
	}
	
	public void useSpecialPower() {}; // is implemented in all slime subclasses
	public void retractSpecialPower() {}; // is implemented in some slime subclasses

	public void update() {
		// normal instances
		if (!variables.specialMode.equals("Anti-Gravity")) {

			// Calculating velocity for moving up or down
			if(Controller.keyboardKeyState(upKey) && y == variables.groundLevel) {
	            velocityY -= jumpAcceleration;
	            if (player == 1) { variables.slimeHasMoved_1 = true; } 
	            else if (player == 2) { variables.slimeHasMoved_2 = true; }
	        } else {
	        	if (y >= variables.groundLevel) {
	        		velocityY = 0;
	        	} else {
	        		velocityY += decceleration;
	        	}
	        }
	        
	        // Calculating velocity for moving or stopping to the left
	        if(Controller.keyboardKeyState(leftKey)) {
	        	facingLeft = true;
	            if (player == 1) { variables.slimeHasMoved_1 = true; } 
	            else if (player == 2) { variables.slimeHasMoved_2 = true; }
	        	if (velocityX >= -maxSpeed) {
	                velocityX -= acceleration;
	        	}
	        } else if (velocityX < 0) {
	            velocityX += decceleration;
	        }
	        
	        // Calculating velocity for moving or stopping to the right
	        if(Controller.keyboardKeyState(rightKey)) {
	        	facingLeft = false;
	            if (player == 1) { variables.slimeHasMoved_1 = true; } 
	            else if (player == 2) { variables.slimeHasMoved_2 = true; }
	        	if (velocityX <= maxSpeed) {
	        		velocityX += acceleration;
	        	}
	        } else if (velocityX > 0) {
	            velocityX -= decceleration;
	        }
	        
		} 
		// special case: only for antigravity mode
		else {
	        // Calculating velocity for moving up 
	        if(Controller.keyboardKeyState(upKey)) {
	            if (player == 1) { variables.slimeHasMoved_1 = true; } 
	            else if (player == 2) { variables.slimeHasMoved_2 = true; }
	        	if (velocityY >= -maxSpeed) {
	        		velocityY -= acceleration;
	        	}
	        }
	        // Calculating velocity for moving down
	        if(Controller.keyboardKeyState(downKey)) {
	            if (player == 1) { variables.slimeHasMoved_1 = true; } 
	            else if (player == 2) { variables.slimeHasMoved_2 = true; }
	        	if (velocityY <= maxSpeed) {
	        		velocityY += acceleration;
	        	}
	        }
	        // Calculating velocity for moving or stopping to the left
	        if(Controller.keyboardKeyState(leftKey)) {
	        	facingLeft = true;
	            if (player == 1) { variables.slimeHasMoved_1 = true; } 
	            else if (player == 2) { variables.slimeHasMoved_2 = true; }
	        	if (velocityX >= -maxSpeed) {
	                velocityX -= acceleration;
	        	}
	        }
	        // Calculating velocity for moving or stopping to the right
	        if(Controller.keyboardKeyState(rightKey)) {
	        	facingLeft = false;
	            if (player == 1) { variables.slimeHasMoved_1 = true; } 
	            else if (player == 2) { variables.slimeHasMoved_2 = true; }
	        	if (velocityX <= maxSpeed) {
	        		velocityX += acceleration;
	        	}
	        }
		}
        

        // User special power
        if(Controller.keyboardKeyState(powerKey) && !specialPowerBeingUsed) {
        	if (this.player == 1) {
        		if (variables.player1_manaCurrent > 0) {
                	this.useSpecialPower();
                	variables.player1_manaCurrent -= manaUsageRate;
        		} else {
                	retractSpecialPower();
        		}
        	}
        	else if (this.player == 2) {
        		if (variables.player2_manaCurrent > 0) {
                	this.useSpecialPower();
                	variables.player2_manaCurrent -= manaUsageRate;
        		} else {
                	retractSpecialPower();
        		}
        	}
        }
        // call retractSpecialPower() and also regenerate mana
        if(!Controller.keyboardKeyState(powerKey)) {
        	retractSpecialPower();
			if (variables.player1_manaCurrent < variables.player1_manaMax) {
	        	variables.player1_manaCurrent += variables.manaRegenerationRate;
			}
			if (variables.player2_manaCurrent < variables.player2_manaMax) {
	        	variables.player2_manaCurrent += variables.manaRegenerationRate;
			}
        }

        
        if (x - width/2 < variables.leftBoundary) { // if slime is outside of left boundary, put slime back in boundary and stop movement
        	velocityX = 0; 
        	x = width/2 + variables.leftBoundary;
        }
        if (x > variables.rightBoundary - width/2) { // if slime is outside of right boundary, put slime back in boundary and stop movement
        	velocityX = 0;
        	x = variables.rightBoundary - width/2;
        }
        // Moves the slime
        x += velocityX;
        y += velocityY;
	}
	
	// update x y width height facingleft
	public void paint(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillArc(getXforPaint(), getYforPaint(), width, height, 0, 180);
		if (facingLeft) {
			g.drawImage(this.slimeImage, getXforPaintImage() + this.width, getYforPaintImage(), -this.width, this.height, null);
		} else {
			g.drawImage(this.slimeImage, getXforPaintImage(), getYforPaintImage(), this.width, this.height, null);
		}
	}
	
	// below getXfor or getYfor functions return the origin position of slime semicircle (as opposed to top-left of slime)
	protected int getXforPaint() {
		return (this.x - (width/2));
	}
	protected int getYforPaint() {
		return (this.y - (height/2));
	}
	protected int getXforPaintImage() {
		return (this.x - (width/2) + 1); // slightly adjusted values for returning x value of image because images aren't exactly 70x70
	}
	protected int getYforPaintImage() {
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
			} else {
				collision = false;
				return newVelocities;
			}
		}
		
		// 3) if slime has collided with a ball, determine resulting velocity of ball (slime will not bounce away from ball — just like in the online version —
		// even though it technically should according to the rules of momentum 
		if (collision) {
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
