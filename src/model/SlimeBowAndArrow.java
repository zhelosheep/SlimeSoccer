package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SlimeBowAndArrow extends Slime {
	BufferedImage arrow = variables.imgSlimeBowAndArrowSpecial;
	boolean arrowFacingLeft, arrowHasHitBall = false;
	int arrowX, arrowY, arrowVelocityX, arrowVelocityY, arrowWidth = 30, arrowHeight = 7;
	
	public SlimeBowAndArrow(int x, int y, int player, BufferedImage slimeImage, Variables variables) {
		super(x, y, player, slimeImage, variables);
	}

	public void useSpecialPower() {
		specialPowerBeingUsed = true;
		// determine direction of arrow
		if (x > variables.ball.x) {
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
					if (arrowX > variables.ball.x) {
						arrowVelocityX = -10;
						arrowX -= 10;
					} else if (arrowX < variables.ball.x){
						arrowVelocityX = 10;
						arrowX += 10;
					}
					if (arrowY > variables.ball.y) {
						arrowVelocityY = -10;
						arrowY -= 10;
					} else if (arrowY < variables.ball.y){
						arrowVelocityY = 10;
						arrowY += 10;
					}
					// arrow has hit ball
					if ( (((arrowX > variables.ball.x && arrowX + arrowVelocityX <= variables.ball.x) || (arrowX < variables.ball.x && arrowX + arrowVelocityX >= variables.ball.x)) 
							&& 
						 ((arrowY > variables.ball.y && arrowY + arrowVelocityY <= variables.ball.y) || (arrowY < variables.ball.y && arrowY + arrowVelocityY >= variables.ball.y)))
						 	||
						 (arrowX > variables.ball.x - variables.ball.radius && arrowX < variables.ball.x + variables.ball.radius && 
						  arrowY < variables.ball.y + variables.ball.radius && arrowY > variables.ball.y - variables.ball.radius)) {
						arrowHasHitBall = true;
						variables.ball.velocityX = arrowVelocityX;
						variables.ball.velocityY = arrowVelocityY;
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
