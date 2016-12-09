package render;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import main.ConfigurableOption;

public class Background implements Renderable{

	private Image farBackground;
	private Image nearBackGround;
	private boolean visible;
	private int farPositionX, farPositionY; // far background top left position
	private int nearPositionX, nearPositionY; // near background top left position
	
	
	public Background(Image farBackground, Image nearBackground) {
		this.farBackground = farBackground;
		this.nearBackGround = nearBackground;
		this.visible = true;
		RenderableHolder.getInstance().add(this);
	}
	
	public void setFarPosition(int x, int y) {
		farPositionX = x;
		farPositionY = y;
	}
	
	public void setNearPosition(int x, int y) {
		nearPositionX = x;
		nearPositionY = y;
	}
	
	public int getFarPositionX() {
		return farPositionX;
	}
	public int getFarPositionY() {
		return farPositionY;
	}
	public int getNearPositionX() {
		return nearPositionX;
	}
	public int getNearPositionY() {
		return nearPositionY;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Override
	public int getz() {
		// TODO Auto-generated method stub
		return Integer.MIN_VALUE;
	}


	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		ConfigurableOption option = ConfigurableOption.getInstance();
		int scale = option.getScale();
		if (visible && farBackground != null && nearBackGround != null) {
			WritableImage fbg = new WritableImage(farBackground.getPixelReader() , farPositionX, farPositionY, option.getScreenWidth()/scale, option.getScreenHeight()/scale);
			WritableImage nbg = new WritableImage(nearBackGround.getPixelReader(), nearPositionX, nearPositionY, option.getScreenWidth()/scale, option.getScreenHeight()/scale);
			gc.drawImage(fbg, farPositionX, farPositionY, option.getScreenWidth(), option.getScreenHeight());
			gc.drawImage(nbg, nearPositionX, nearPositionY, option.getScreenWidth(), option.getScreenHeight());
		}
	}
	
}
