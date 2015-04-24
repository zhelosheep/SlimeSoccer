package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Ball {
	public int x, y;
	public int speedX = 0, speedY = 0; 
	BufferedImage ballImage;
	
	// constants
	public final int width = 24, height = 24, radius = 12;
	public final int mass = 1;
	
	Ball(int x, int y) {
		this.x = x;
		this.y = y;
		ballImage = Game.imgBall;
	}
	
	public void update() {

	}
	
	public void paint(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillArc(getXforPaint(), getYforPaint(), this.width, this.height, 0, 360);
		g.drawImage(ballImage, getXforPaint(), getYforPaint(), null);
	}

	private int getXforPaint() {
		return (this.x - (width/2));
	}
	private int getYforPaint() {
		return (this.y - (height/2));
	}

	
}
