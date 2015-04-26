package model;

import java.awt.image.BufferedImage;

public class SlimeBomb extends Slime {
	int bombRadius = radius + 40;
	double resultingVelocity = 30;
	
	public SlimeBomb(int x, int y, int player, BufferedImage slimeImage, Variables variables) {
		super(x, y, player, slimeImage, variables);
	}

	public void useSpecialPower() {
		// animation
		new Thread() {
			public void run() {
				slimeImage = variables.imgSlimeBombSpecial;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println("InterruptedException in SlimeBomb: " + e.getMessage());
				}
				if (player == 1) {
					slimeImage = variables.imgSlime1;
				} else if (player == 2) {
					slimeImage = variables.imgSlime2;
				}
			}
		}.start();
		// if ball is hit by explosion, change ball velocity
		if (variables.ball.x > this.x - bombRadius && variables.ball.x < this.x + bombRadius && variables.ball.y > this.y - bombRadius && variables.ball.y < this.y + bombRadius) {
			// calculate angle at which ball will be propelled from slime
			int distanceX = this.x - variables.ball.x;
			int distanceY = this.y - variables.ball.y;
			double angleOfBallToSlimeOrigin = Math.atan(distanceY/distanceX);
			// change resulting velocity of ball
			variables.ball.velocityX = resultingVelocity * Math.cos(angleOfBallToSlimeOrigin);
			variables.ball.velocityY = resultingVelocity * Math.sin(angleOfBallToSlimeOrigin);
			// end animation
		}
	}
	
}
