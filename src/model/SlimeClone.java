package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SlimeClone extends Slime {
	Slime clone;
	
public SlimeClone(int x, int y, int player, BufferedImage slimeImage, Game game) {
	super(x, y, player, slimeImage, game);
	}

	public void useSpecialPower() {
		new Thread() {
			public void run() {
				clone = new Slime(x, y, player, slimeImage, game);
				specialPowerBeingUsed = true;
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					System.out.println("InterruptedException in SlimeClone: " + e.getMessage());
				}
				clone = null;
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
			// paint stuff
			g.setColor(Color.GREEN);
			g.fillArc(clone.getXforPaint(), clone.getYforPaint(), clone.width, clone.height, 0, 180);
			if (clone.facingLeft) {
				g.drawImage(clone.slimeImage, clone.getXforPaintImage() + clone.width, clone.getYforPaintImage(), -clone.width, clone.height, null);
			} else {
				g.drawImage(clone.slimeImage, clone.getXforPaintImage(), clone.getYforPaintImage(), clone.width, clone.height, null);
			}
			
			
			// not good programming practice but I put it in here anyways (model functionality in paint component)
			Double [] cloneCollideNewVelocities = clone.detectCollision(game.ball);
			if (cloneCollideNewVelocities[0] != null) {
				game.ball.velocityX = cloneCollideNewVelocities[0].intValue();
				game.ball.velocityY = cloneCollideNewVelocities[1].intValue();
				game.ball.callMove();
			}
		}
		
	}

}
