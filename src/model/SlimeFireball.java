package model;

import java.awt.image.BufferedImage;

public class SlimeFireball extends Slime {
	public SlimeFireball(int x, int y, int player, BufferedImage slimeType) {
		super(x, y, player, slimeType);
	}

	public void useSpecialPower() {
//		if (Math.sqrt(Math.pow(Game.ball.velocityX,2) + Math.pow(Game.ball.velocityY,2)) < 20) { // limits speed of ball
			Game.ball.velocityX *= 1.2;
			Game.ball.velocityY *= 1.2;
//		}
	}

}
