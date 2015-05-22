package model;

public class ArtificialIntelligence {
	Game game;
	Slime slimeAI, slimeHuman;
	Ball ball;
	int controllerKey; 
	
	int hotzone = 50;
	ArtificialIntelligence(Game game, Slime slimeAI, Slime slimeHuman) {
		this.game = game;
		this.slimeAI = slimeAI;
		this.slimeHuman = slimeHuman;
		this.ball = game.variables.ball;
	}
	
	public void update() {
		followBall();
		
	}
	
	private void followBall() {
		// move towards ball if it is between AI and Human's goal post
		if (slimeAI.x < ball.x - ball.radius) {
//			if ( (ball.x - slimeAI.x) <= (slimeAI.x - ball.x) ) { // if ball is closer to slime than human
				moveRight();
//			}
		}

		// try to get on left side of ball if it is between AI and AI's goal post
		if (ball.x - ball.radius < slimeAI.x + slimeAI.radius) {
			moveLeft();
			if (ball.velocityY < 2 && ball.y + ball.radius > slimeAI.y - slimeAI.radius && ball.x + ball.radius - slimeAI.x - slimeAI.radius < 10) {
				moveUp();
			}
		}

		// however, if ball is about to hit slime, always push it towards the Human's goal post
		if (ball.y + ball.radius >= slimeAI.y - slimeAI.radius - hotzone && ball.y - ball.radius <= slimeAI.y && ball.x + ball.radius > slimeAI.x - slimeAI.radius) {
			moveRight();
		}
	}
	
	void moveLeft() {
		game.variables.p1_keyboardState[2] = true;
		game.variables.p1_keyboardState[3] = false;
//		Controller.changeKeyboardKeyState(KeyEvent.VK_A, true);
//		Controller.changeKeyboardKeyState(KeyEvent.VK_D, false);
	}
	void moveRight() {
		game.variables.p1_keyboardState[3] = true;
		game.variables.p1_keyboardState[2] = false;
//		Controller.changeKeyboardKeyState(KeyEvent.VK_D, true);
//		Controller.changeKeyboardKeyState(KeyEvent.VK_A, false);
	}
	void moveUp() {
		new Thread() {
			public void run() {
				game.variables.p1_keyboardState[0] = true;
				game.variables.p1_keyboardState[1] = false;
//				Controller.changeKeyboardKeyState(KeyEvent.VK_W, true);
//				Controller.changeKeyboardKeyState(KeyEvent.VK_S, false);
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					System.out.println("Thread sleep InterruptedException in moveUp() in Artificial Intelligence: " + e.getMessage());
				}
				game.variables.p1_keyboardState[0] = false;
//				Controller.changeKeyboardKeyState(KeyEvent.VK_W, false);
			}
		}.start();
	}
	void moveDown() {
		game.variables.p1_keyboardState[1] = true;
		game.variables.p1_keyboardState[0] = false;
//		Controller.changeKeyboardKeyState(KeyEvent.VK_S, true);
//		Controller.changeKeyboardKeyState(KeyEvent.VK_D, false);
	}
}
