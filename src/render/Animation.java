package render;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import main.ConfigurableOption;

public class Animation {

	private Image spriteSheet = null;
	protected boolean visible = false;
	protected boolean playing = false;
	protected int x = 0;
	protected int y = 0;
	private int z = 0;
	private int frameWidth, frameHeight;
	protected int frameDelay;
	protected int frameDelayCount;
	protected int currentFrame = 0;
	protected int numberOfFrame;
	private int offsetX = 0, offsetY = 0;
	private int spriteLine; // specify the column in sprite sheet
	private int scale; // size of animation. get from setting

	/**
	 * 
	 * @param spriteSheet
	 *            : Animation's sprite sheet
	 * @param frameWidth
	 *            : Animation's frame width
	 * @param frameHeight
	 *            : Animation's frame height
	 * @param numberOfFrame
	 *            : number of animation in frame
	 * @param frameDelay
	 * @param spriteLine
	 */
	public Animation(Image spriteSheet, int frameWidth, int frameHeight, int numberOfFrame, int frameDelay,
			int spriteLine) {
		this.spriteSheet = spriteSheet;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.numberOfFrame = numberOfFrame;
		this.frameDelay = frameDelay;
		visible = false;
		playing = false;
		this.spriteLine = spriteLine;
		scale = ConfigurableOption.getInstance().getScale();
	}

	public void setOffset(int offsetX, int offsetY) {
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}

	public void setTopleftPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setPosition(int x, int y) {
		setCenterPosition(x, y);
	}

	public void setCenterPosition(int x, int y) {
		setTopleftPosition(x - offsetX, y - offsetY);
	}

	public void play() {
		visible = true;
		playing = true;
		currentFrame = 0;
		frameDelayCount = frameDelay;
	}

	public void play(int x, int y, GraphicsContext gc) {
		this.x = x;
		this.y = y;
		visible = true;
		playing = true;
		currentFrame = 0;
		frameDelayCount = frameDelay;
		render(gc);
	}

	public void stop() {
		visible = false;
		playing = false;
	}

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
			currentFrame = 0;
		}
	}
	
	public int getNumberOfFrame() {
		return numberOfFrame;
	}
	
	public int getCurrentFrame() {
		return currentFrame;
	}
	
	public int getAnimationDelay() {
		return frameDelay;
	}

	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		// System.out.println("before " + visible);
		if (visible && spriteSheet != null) {
			// System.out.println("render : " + this);
			WritableImage frame = new WritableImage(spriteSheet.getPixelReader(), currentFrame * frameWidth,
					spriteLine * frameHeight, frameWidth, frameHeight);
			gc.drawImage(frame, x, y, frameWidth * scale, frameHeight * scale);
		}
	}

}
