package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SlimeBomb extends Slime {
	int bombRadius = radius + 40;
	double resultingVelocity = 30;
	
	public SlimeBomb(int x, int y, int player, BufferedImage slimeImage) {
		super(x, y, player, slimeImage);
	}

	public void useSpecialPower() {
		// animation
		new Thread() {
			public void run() {
				slimeImage = Game.imgSlimeBombSpecial;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println("InterruptedException in SlimeBomb: " + e.getMessage());
				}
				if (player == 1) {
					slimeImage = Game.imgSlime1;
				} else if (player == 2) {
					slimeImage = Game.imgSlime2;
				}
			}
		}.start();
		// if ball is hit by explosion, change ball velocity
		if (Game.ball.x > this.x - bombRadius && Game.ball.x < this.x + bombRadius && Game.ball.y > this.y - bombRadius && Game.ball.y < this.y + bombRadius) {
			// calculate angle at which ball will be propelled from slime
			int distanceX = this.x - Game.ball.x;
			int distanceY = this.y - Game.ball.y;
			double angleOfBallToSlimeOrigin = Math.atan(distanceY/distanceX);
			// change resulting velocity of ball
			Game.ball.velocityX = resultingVelocity * Math.cos(angleOfBallToSlimeOrigin);
			Game.ball.velocityY = resultingVelocity * Math.sin(angleOfBallToSlimeOrigin);
			// end animation
		}
	}
	
}
