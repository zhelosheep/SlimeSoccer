package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SlimeGeyser extends Slime {
	int geyserWidth = 16, geyserStrength = 2;
	boolean geyserOn;
	
	public SlimeGeyser(int x, int y, int player, BufferedImage slimeImage) {
		super(x, y, player, slimeImage);
	}

	public void useSpecialPower() {
		geyserOn = true;
		Game.ball.velocityY -= geyserStrength;
	}
	
	public void retractSpecialPower() {
		geyserOn = false;
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
		if (geyserOn) {
			g.setColor(Color.BLUE);
			g.fillRect(Game.ball.x - geyserWidth/2, Game.ball.y + Game.ball.radius, geyserWidth, Game.groundLevel - Game.ball.y - Game.ball.radius);
		}
	}


}
