package model;

import java.awt.image.BufferedImage;

public class SlimeMagnet extends Slime {
	int gravitationalPull = 1;
	
public SlimeMagnet(int x, int y, int player, BufferedImage slimeImage, Game game) {
	super(x, y, player, slimeImage, game);
	}

	public void useSpecialPower() {
		if (game.ball.x > x) {
			game.ball.velocityX -= gravitationalPull;
		} else if (game.ball.x < x) {
			game.ball.velocityX += gravitationalPull;
		}
		if (game.ball.y > y) {
			game.ball.velocityY -= gravitationalPull;
		} else if (game.ball.y < y) {
			game.ball.velocityY += gravitationalPull;
		}

	}
}
