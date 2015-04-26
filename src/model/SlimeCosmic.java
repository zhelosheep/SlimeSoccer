package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SlimeCosmic extends Slime {
	BufferedImage blackhole = game.imgSlimeCosmicSpecial;
	int blackholeX, blackholeY, blackholeRadius = 25, gravitationalPull = 1, lengthOfEffect = 3000; // lengthOfEffect is time in milliseconds that blackhole lasts
	long timeCalled;
	
	public SlimeCosmic(int x, int y, int player, BufferedImage slimeImage, Game game) {
		super(x, y, player, slimeImage, game);
	}

	public void useSpecialPower() {
		specialPowerBeingUsed = true;
		blackholeX = x - blackholeRadius;
		blackholeY = y - radius - blackholeRadius;
		
		new Thread() {
			public void run() {
				timeCalled = System.currentTimeMillis();
				while (!game.player1scored && !game.player2scored) {
					if (System.currentTimeMillis() - timeCalled > lengthOfEffect) {
						break;
					}
					// pull ball towards black hole
					if (game.ball.x > blackholeX) {
						game.ball.velocityX -= gravitationalPull;
					} else if (game.ball.x < blackholeX) {
						game.ball.velocityX += gravitationalPull;
					}
					if (game.ball.y > blackholeY) {
						game.ball.velocityY -= gravitationalPull;
					} else if (game.ball.y < blackholeY) {
						game.ball.velocityY += gravitationalPull;
					}
					try {
						Thread.sleep(35);
					} catch (InterruptedException e) {
						System.out.println("InterruptedException in SlimeCosmic: " + e.getMessage());
					}
				}
				specialPowerBeingUsed = false;
			}
		}.start();
	}
	
	@Override
	public void paint(Graphics g) {
		// stuff that was already here in Slime abstract class
		g.setColor(Color.GRAY);
		g.fillArc(getXforPaint(), getYforPaint(), width, height, 0, 180);
		if (facingLeft) {
			g.drawImage(this.slimeImage, getXforPaintImage() + this.width, getYforPaintImage(), -this.width, this.height, null);
		} else {
			g.drawImage(this.slimeImage, getXforPaintImage(), getYforPaintImage(), this.width, this.height, null);
		}
		
		// stuff added
		if (specialPowerBeingUsed) {
			g.drawImage(blackhole, blackholeX, blackholeY, null);
		}
	}

}
