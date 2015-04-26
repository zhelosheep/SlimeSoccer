package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SlimeCosmic extends Slime {
	BufferedImage blackhole = variables.imgSlimeCosmicSpecial;
	int blackholeX, blackholeY, blackholeRadius = 25, gravitationalPull = 1, lengthOfEffect = 3000; // lengthOfEffect is time in milliseconds that blackhole lasts
	long timeCalled;
	
	public SlimeCosmic(int x, int y, int player, BufferedImage slimeImage, Variables variables) {
		super(x, y, player, slimeImage, variables);
	}

	public void useSpecialPower() {
		specialPowerBeingUsed = true;
		blackholeX = x - blackholeRadius;
		blackholeY = y - radius - blackholeRadius;
		
		new Thread() {
			public void run() {
				timeCalled = System.currentTimeMillis();
				while (!variables.player1scored && !variables.player2scored) {
					if (System.currentTimeMillis() - timeCalled > lengthOfEffect) {
						break;
					}
					// pull ball towards black hole
					if (variables.ball.x > blackholeX) {
						variables.ball.velocityX -= gravitationalPull;
					} else if (variables.ball.x < blackholeX) {
						variables.ball.velocityX += gravitationalPull;
					}
					if (variables.ball.y > blackholeY) {
						variables.ball.velocityY -= gravitationalPull;
					} else if (variables.ball.y < blackholeY) {
						variables.ball.velocityY += gravitationalPull;
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
