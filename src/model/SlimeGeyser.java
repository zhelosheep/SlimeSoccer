package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SlimeGeyser extends Slime {
	int geyserWidth = 16, geyserStrength = 2;
	boolean geyserOn;
	
	public SlimeGeyser(int x, int y, int player, BufferedImage slimeImage, Variables variables) {
		super(x, y, player, slimeImage, variables);
	}

	public void useSpecialPower() {
		geyserOn = true;
		variables.ball.velocityY -= geyserStrength;
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
			g.fillRect(variables.ball.x - geyserWidth/2, variables.ball.y + variables.ball.radius, geyserWidth, variables.groundLevel - variables.ball.y - variables.ball.radius);
		}
	}


}
