package model;

import java.awt.image.BufferedImage;

public class SlimeMagnet extends Slime {
	int gravitationalPull = 1;
	
	public SlimeMagnet(int x, int y, int player, BufferedImage slimeImage) {
		super(x, y, player, slimeImage);
	}

	public void useSpecialPower() {
		if (Game.ball.x > x) {
			Game.ball.velocityX -= gravitationalPull;
		} else if (Game.ball.x < x) {
			Game.ball.velocityX += gravitationalPull;
		}
		if (Game.ball.y > y) {
			Game.ball.velocityY -= gravitationalPull;
		} else if (Game.ball.y < y) {
			Game.ball.velocityY += gravitationalPull;
		}

	}
}
