package render;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class Animation implements Renderable {
	
	private Image model = null;
	private boolean visible = false, playing = false;
	private int x = 0, y = 0, z = 0;
	private int frameWidth, frameHeight;
	private int frameDelay, frameDelayCount;
	private int currentFrame = 0;
	private int numberOfFrame;
	private int offsetX = 0, offsetY = 0;
	private int spriteLine; // specify the column in sprite sheet
	private int scale; // size of animation. get from setting
	
	public Animation(Image model,int frameWidth, int frameHeight, int numberOfFrame, int frameDelay, int spriteLine) {
		this.model = model;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.numberOfFrame = numberOfFrame;
		this.frameDelay = frameDelay;
		visible = false;
		playing = false;
		this.spriteLine = spriteLine;
		scale = 1;
	}
	
	public void setOffset(int offsetX, int offsetY) {
		this.offsetX  = offsetX;
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
		setTopleftPosition(x-offsetX, y-offsetY);
	}
	
	public void play() {
		visible = true;
		playing = true;
		currentFrame = 0;
		frameDelayCount = frameDelay;
	}
	
	public void play(int x, int y) {
		this.x = x;
		this.y = y;
		visible = true;
		playing = true;
		currentFrame = 0;
		frameDelayCount = frameDelay;
	}
	
	public void stop() {
		visible = false;
		playing = false;
	}
	
	public void updateAnimation() {
		if (!playing)
			return;
		if (frameDelayCount > 0) {
			frameDelayCount-=1;
			return;
		}
		frameDelayCount = frameDelay;
		currentFrame+=1;
		if (currentFrame >= numberOfFrame) {
			currentFrame = 0;
		}
	}
	
	@Override
	public int getz() {
		// TODO Auto-generated method stub
		return z;
	}
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return visible;
	}
	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (visible && model != null) {
			WritableImage frame = new WritableImage(model.getPixelReader(), currentFrame*frameWidth, spriteLine*frameHeight, frameWidth, frameHeight);
			gc.drawImage(frame, x, y, frameWidth*scale, frameHeight*scale);
		}
	}
	
	
	
	
	
}
