package model;

import java.awt.image.BufferedImage;

public class SlimeSuperSize extends Slime {
	public SlimeSuperSize(int x, int y, int player, BufferedImage slimeImage, Game game) {
		super(x, y, player, slimeImage, game);
	}

	public void useSpecialPower() {
		this.width *= 1.2;
		this.height *= 1.2;
		this.radius *= 1.2;
	}
}
