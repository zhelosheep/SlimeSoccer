package model;

import java.awt.image.BufferedImage;

public class SlimeMagnet extends Slime {
	int gravitationalPull = 1;
	
public SlimeMagnet(int x, int y, int player, BufferedImage slimeImage, Variables variables) {
	super(x, y, player, slimeImage, variables);
	}

	public void useSpecialPower() {
		if (variables.ball.x > x) {
			variables.ball.velocityX -= gravitationalPull;
		} else if (variables.ball.x < x) {
			variables.ball.velocityX += gravitationalPull;
		}
		if (variables.ball.y > y) {
			variables.ball.velocityY -= gravitationalPull;
		} else if (variables.ball.y < y) {
			variables.ball.velocityY += gravitationalPull;
		}

	}
}
