package render;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SequenceAnimation extends Animation {

	public boolean finished;

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
			finished = true;
		} else return;
		
	}
	
	@Override
	public void play() {
		visible = true;
		playing = true;
		finished = false;
		currentFrame = 0;
		frameDelayCount = frameDelay;
	}

	@Override
	public void play(int x, int y, GraphicsContext gc) {
		this.x = x;
		this.y = y;
		visible = true;
		playing = true;
		finished = false;
		currentFrame = 0;
		frameDelayCount = frameDelay;
		render(gc);
	}
	
	public boolean isFinished() {
		return finished;
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
