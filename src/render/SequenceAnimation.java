package render;

import javafx.scene.image.Image;

public class SequenceAnimation extends Animation {

	public boolean isFinished;

	public SequenceAnimation(Image spriteSheet, int frameWidth, int frameHeight, int numberOfFrame, int frameDelay,
			int spriteLine) {
		super(spriteSheet, frameWidth, frameHeight, numberOfFrame, frameDelay, spriteLine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void stop() {
		if (currentFrame >= numberOfFrame) {
			playing = false;
			visible = false;
		} else return;
		
	}
	
	@Override
	public void updateAnimation() {
		if (!playing)
			return;
		if (frameDelayCount > 0) {
			frameDelayCount -= 1;
			return;
		}
		frameDelayCount = frameDelay;
		currentFrame += 1;
		if (currentFrame >= numberOfFrame) {
			stop();
		}
	}

}
