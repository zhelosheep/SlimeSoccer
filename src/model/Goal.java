package model;

import java.awt.Color;
import java.awt.Graphics;

public class Goal {
	int x, y;
	Boolean facingLeft = null;
	private Variables variables;
	
	final int width = 50, height = 100;
	public Goal(int boundary, Variables variables) {
		this.variables = variables;
		if (boundary == variables.leftBoundary) {
			facingLeft = true;
			x = boundary;
		} else if (boundary == variables.rightBoundary) {
			facingLeft = false;
			x = boundary; 
		}
		y = variables.groundLevel - height;
	}
	
	
	public boolean checkGoal(int ballX, int ballY) {
		// left side goal
		if (facingLeft && ballX > x && ballX < x + width && ballY < y + height && ballY > y) {
			return true;
		}
		// right side goal
		else if (!facingLeft && ballX < x && ballX > x - width && ballY < y + height && ballY > y) {
			return true;
		}
		else {
			return false;
		}
	}
	public Double[] detectCollision(int ballX, int ballY, double velocityX, double velocityY) {
		Double[] newVelocities = new Double[2]; // index 0 is velocityX, index 1 is velocityY
		boolean collision = false;
		
		// left side goal
		// ball hits top or bottom of top bar of goal
		if (facingLeft && ballX + velocityX <= x + width && ballY + velocityY >= y) {
			collision = true;
			newVelocities[0] = velocityX; 
			newVelocities[1] = -velocityY; 
		}
		
		// right side goal
		// ball hits top or bottom of top bar of goal
		if (!facingLeft && ballX + velocityX >= x - width && ballY + velocityY >= y) {
			collision = true;
			newVelocities[0] = velocityX; 
			newVelocities[1] = -velocityY; 
		}

		if (collision) {
			return newVelocities;
		} else {
			return null;
		}
	}
	public void paint(Graphics g) {
		if (variables.background.equals("Outer Space")) { 
			g.setColor(Color.WHITE); }
		else {
			g.setColor(Color.BLACK);
		}
		if (x == variables.leftBoundary) {
//			g.drawRect(x, y, width, height);
			g.drawImage(variables.imgGoal, x, y, width, height, null);
		} else if (x == variables.rightBoundary) {
//			g.drawRect(x - 50, y, width, height); //TODO CHANGE THIS WHEN YOU SWITCH TO AN IMAGE
			g.drawImage(variables.imgGoal, x, y, -width, height, null);
		}
	}
}
