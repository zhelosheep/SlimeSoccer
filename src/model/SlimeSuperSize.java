package model;

import java.awt.image.BufferedImage;

public class SlimeSuperSize extends Slime {
	int maxSize = 268;
	
	public SlimeSuperSize(int x, int y, int player, BufferedImage slimeImage) {
		super(x, y, player, slimeImage);
	}

	public void useSpecialPower() {
		specialPowerBeingUsed = true;
		if (this.width * 1.2 <= maxSize) {
			this.width *= 1.2;
			this.height *= 1.2;
			this.radius *= 1.2;
		}
		SlimeSuperSizeShrink thread = new SlimeSuperSizeShrink(this); // creating this thread multiple times may cause problems later
		thread.start();
		specialPowerBeingUsed = false;
	}
	
}

class SlimeSuperSizeShrink extends Thread {
	SlimeSuperSize slime;
	SlimeSuperSizeShrink(SlimeSuperSize slime) {
		this.slime = slime;
	}
	public void run() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			System.out.println("Sleep thread in SlimeSuperSizeShrink class: " + e.getMessage());
		}
		if (!slime.specialPowerBeingUsed) {
			while (slime.width * 0.8 >= 54) {
				slime.width *= 0.8;
				slime.height *= 0.8;
				slime.radius *= 0.8;
				if (slime.specialPowerBeingUsed) {
					return;
				}
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {
					System.out.println("Sleep thread in SlimeSuperSizeShrink class: " + e.getMessage());
				}
			}
			slime.width = slime.finalWidth;
			slime.height = slime.finalHeight;
			slime.radius = slime.finalRadius;
		}
	}
}