package model;

import java.awt.image.BufferedImage;

public class SlimeSuper extends Slime {
	public SlimeSuper(int x, int y, int player, BufferedImage slimeImage, Game game) {
		super(x, y, player, slimeImage, game);
	}

	public void useSpecialPower() {
		acceleration = 3;
		maxSpeed = 1000;
	}
	
	public void retractSpecialPower() {
		acceleration = finalAcceleration;
		maxSpeed = finalMaxSpeed;
	}

}
