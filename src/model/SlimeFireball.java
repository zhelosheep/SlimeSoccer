package model;

import java.awt.image.BufferedImage;

public class SlimeFireball extends Slime {
	public SlimeFireball(int x, int y, int player, BufferedImage slimeType) {
		super(x, y, player, slimeType);
	}

	public void useSpecialPower() {
		Game.ball.velocityX *= 1.2;
		Game.ball.velocityY *= 1.2;
	}

}
