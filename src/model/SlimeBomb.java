package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SlimeBomb extends Slime {
	int bombRadius = radius + 40;
	double resultingVelocity = 30;
	
	public SlimeBomb(int x, int y, int player, BufferedImage slimeImage, Game game) {
		super(x, y, player, slimeImage, game);
	}

	public void useSpecialPower() {
		// animation
		new Thread() {
			public void run() {
				slimeImage = game.imgSlimeBombSpecial;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println("InterruptedException in SlimeBomb: " + e.getMessage());
				}
				if (player == 1) {
					slimeImage = game.imgSlime1;
				} else if (player == 2) {
					slimeImage = game.imgSlime2;
				}
			}
		}.start();
		// if ball is hit by explosion, change ball velocity
		if (game.ball.x > this.x - bombRadius && game.ball.x < this.x + bombRadius && game.ball.y > this.y - bombRadius && game.ball.y < this.y + bombRadius) {
			// calculate angle at which ball will be propelled from slime
			int distanceX = this.x - game.ball.x;
			int distanceY = this.y - game.ball.y;
			double angleOfBallToSlimeOrigin = Math.atan(distanceY/distanceX);
			// change resulting velocity of ball
			game.ball.velocityX = resultingVelocity * Math.cos(angleOfBallToSlimeOrigin);
			game.ball.velocityY = resultingVelocity * Math.sin(angleOfBallToSlimeOrigin);
			// end animation
		}
	}
	
}
