package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SlimeBowAndArrow extends Slime {
	BufferedImage arrow = Game.imgSlimeBowAndArrowSpecial;
	boolean arrowFacingLeft, arrowHasHitBall = false;
	int arrowX, arrowY, arrowVelocityX, arrowVelocityY, arrowWidth = 30, arrowHeight = 7;
	
	public SlimeBowAndArrow(int x, int y, int player, BufferedImage slimeImage) {
		super(x, y, player, slimeImage);
	}

	public void useSpecialPower() {
		specialPowerBeingUsed = true;
		// determine direction of arrow
		if (x > Game.ball.x) {
			arrowFacingLeft = true;
		} else {
			arrowFacingLeft = false;
		}
		// animation
		new Thread() {
			public void run() {
				arrowHasHitBall = false;
				arrowX = x;
				arrowY = y - radius;
				while (!arrowHasHitBall) {
					if (arrowX > Game.ball.x) {
						arrowVelocityX = -10;
						arrowX -= 10;
					} else if (arrowX < Game.ball.x){
						arrowVelocityX = 10;
						arrowX += 10;
					}
					if (arrowY > Game.ball.y) {
						arrowVelocityY = -10;
						arrowY -= 10;
					} else if (arrowY < Game.ball.y){
						arrowVelocityY = 10;
						arrowY += 10;
					}
					// arrow has hit ball
					if ( (((arrowX > Game.ball.x && arrowX + arrowVelocityX <= Game.ball.x) || (arrowX < Game.ball.x && arrowX + arrowVelocityX >= Game.ball.x)) 
							&& 
						 ((arrowY > Game.ball.y && arrowY + arrowVelocityY <= Game.ball.y) || (arrowY < Game.ball.y && arrowY + arrowVelocityY >= Game.ball.y)))
						 	||
						 (arrowX > Game.ball.x - Game.ball.radius && arrowX < Game.ball.x + Game.ball.radius && 
						  arrowY < Game.ball.y + Game.ball.radius && arrowY > Game.ball.y - Game.ball.radius)) {
						arrowHasHitBall = true;
						Game.ball.velocityX = arrowVelocityX;
						Game.ball.velocityY = arrowVelocityY;
					}
					try {
						Thread.sleep(25);
					} catch (InterruptedException e) {
						System.out.println("Thread sleep in SlimeBowAndArrow class: " + e.getMessage());
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
			if (arrowFacingLeft) {
				g.drawImage(arrow, arrowX, arrowY, -arrowWidth, arrowHeight, null);
			} else {
				g.drawImage(arrow, arrowX, arrowY, arrowWidth, arrowHeight, null);
			}
		}
	}

}
