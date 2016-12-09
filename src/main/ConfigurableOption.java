package main;

public class ConfigurableOption {
	
	private ConfigurableOption instance = new ConfigurableOption();
	
	private int scale;
	private int screenWidth = 768, screenHeight = 512;
	private int brightness = 0;
	
	public ConfigurableOption() {
		scale = 1;
	}
	
	public void setScale(int scale) {
		this.scale = scale;
	}
	
	public int getScale() {
		return scale;
	}
	
	public int getScreenWidth() {
		return screenWidth;
	}
	
	public void setScreenWidht(int width) {
		this.screenWidth = width;
	}
	
	public int getScreenHeight() {
		return screenHeight;
	}
	
	public void setScreenHeight(int height) {
		this.screenHeight = height;
	}
	
	
}
