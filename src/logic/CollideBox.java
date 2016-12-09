package logic;

public class CollideBox {
	
	private int startX, startY; // top left of the box
	private int endX, endY; // bottom right of the box
	
	public CollideBox(int startX, int startY, int endX, int endY) {
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
	}
	
	public int getTop() {
		return startY;
	}
	
	public int getBottom() {
		return endY;
	}
	
	public int getLeft() {
		return startX;
	}
	
	public int getRight() {
		return endX;
	}
}
