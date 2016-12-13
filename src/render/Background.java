package render;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import main.ConfigurableOption;

public class Background implements Renderable {

	private Image farBackground;
	private Image nearBackground;
	private boolean visible;
	private int farPositionX, farPositionY; // far background top left position
	private int nearPositionX, nearPositionY; // near background top left
												// position

	public Background(Image farBackground, Image nearBackground) {
		farPositionX = 0;
		farPositionY = 0;
		nearPositionX = 0;
		nearPositionY = 0;
		this.farBackground = farBackground;
		this.nearBackground = nearBackground;
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

	public double getWidth() {
		return nearBackground.getWidth();
	}

	public double getHeight() {
		return nearBackground.getHeight();
	}

	/**
	 * this method is to check if the screen can move any further.
	 * 
	 * @param x
	 *            : x position of screen
	 * @param y
	 *            : y position of screen
	 * @return true if screen can't move anymore
	 */
	public boolean isBorderX(int x) {
		int width = ConfigurableOption.getInstance().getScreenWidth();
		if (x <= 0 || x + width >= nearBackground.getWidth())
			return true;
		return false;
	}

	public boolean isBoderY(int y) {
		int height = ConfigurableOption.getInstance().getScreenHeight();
		if (y <= 0 || y + height >= nearBackground.getHeight())
			return true;
		return false;
	}

	@Override
	public int getz() {
		// TODO Auto-generated method stub
		return Integer.MIN_VALUE;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		ConfigurableOption option = ConfigurableOption.getInstance();
		int scale = option.getScale();
		if (visible && farBackground != null && nearBackground != null) {
			WritableImage fbg = null, nbg = null;
			/*
			 * if (farPositionX < 0) farPositionX = 0; if (farPositionY < 0)
			 * farPositionY = 0; if (farPositionX + option.getScreenWidth() >
			 * farBackground.getWidth()) farPositionX =
			 * (int)farBackground.getWidth()- option.getScreenWidth(); if
			 * (farPositionY + option.getScreenHeight() >
			 * farBackground.getHeight()) farPositionY =
			 * (int)farBackground.getHeight()-option.getScreenHeight();
			 */
			if (nearPositionX < 0) {
				nearPositionX = 0;
				farPositionX = 0;
			}
			if (nearPositionY < 0) {
				nearPositionY = 0;
				farPositionY = 0;
			}
			if (nearPositionX + option.getScreenWidth() > nearBackground.getWidth()) {
				nearPositionX = (int) nearBackground.getWidth() - option.getScreenWidth();
				farPositionX = nearPositionX / 2;
			}
			if (nearPositionY + option.getScreenHeight() > nearBackground.getHeight()) {
				nearPositionY = (int) nearBackground.getHeight() - option.getScreenHeight();
				farPositionY = nearPositionY / 2;
			}

			try {
				fbg = new WritableImage(farBackground.getPixelReader(), farPositionX, farPositionY,
						option.getScreenWidth() / scale, option.getScreenHeight() / scale);
			} catch (IndexOutOfBoundsException e) {
				fbg = new WritableImage(farBackground.getPixelReader(),
						(int) farBackground.getWidth() - option.getScreenWidth(),
						(int) farBackground.getHeight() - option.getScreenHeight(), option.getScreenWidth() / scale,
						option.getScreenHeight() / scale);
			}
			try {
				nbg = new WritableImage(nearBackground.getPixelReader(), nearPositionX, nearPositionY,
						option.getScreenWidth() / scale, option.getScreenHeight() / scale);
			} catch (IndexOutOfBoundsException e) {
				nbg = new WritableImage(nearBackground.getPixelReader(),
						(int) nearBackground.getWidth() - option.getScreenWidth(),
						(int) nearBackground.getHeight() - option.getScreenHeight(), option.getScreenWidth() / scale,
						option.getScreenHeight() / scale);
			}
			gc.drawImage(fbg, 0, 0, option.getScreenWidth(), option.getScreenHeight());
			gc.drawImage(nbg, 0, 0, option.getScreenWidth(), option.getScreenHeight());
		}
	}

}
