package model;

import java.awt.image.BufferedImage;

public class SlimeFireball extends Slime {
	public SlimeFireball(int x, int y, int player, BufferedImage slimeType, Variables variables) {
		super(x, y, player, slimeType, variables);
	}

	public void useSpecialPower() {
//		if (Math.sqrt(Math.pow(Game.ball.velocityX,2) + Math.pow(Game.ball.velocityY,2)) < 20) { // limits speed of ball
			variables.ball.velocityX *= 1.2;
			variables.ball.velocityY *= 1.2;
//		}
	}

}
