package model;

import java.awt.image.BufferedImage;

public class SlimeFireball extends Slime {
	public SlimeFireball(int x, int y, int player, BufferedImage slimeType, Game game) {
		super(x, y, player, slimeType, game);
	}

	public void useSpecialPower() {
		game.ball.velocityX *= 1.2;
		game.ball.velocityY *= 1.2;
	}

}
