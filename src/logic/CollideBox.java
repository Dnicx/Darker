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
	
	public void setStartX(int x) {
		this.startX = x;
	}
	public void setStartY(int y) {
		this.startY = y;
	}
	
	public void setEndX(int x) {
		this.endX = x;
	}
	public void setEndY(int y) {
		this.endY = y;
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
	
	public boolean isCollide(CollideBox c1) {
//		check if any part of c1 is in THIS
		if (this.startX <= c1.startX && c1.startX <= this.endX) {
			if (this.startY <= c1.startY && c1.startY <= this.endY) return true;
			if (this.startY <= c1.endY && c1.endY <= this.endY) return true;
		} else if (this.startX <= c1.endX && c1.endX <= this.endX) {
			if (this.startY <= c1.startY && c1.startY <= this.endY) return true;
			if (this.startY <= c1.endY && c1.endY <= this.endY) return true;
		} else 
//		check if any part of THIS is in c1
		if (c1.startX <= this.startX && this.startX <= c1.endX) {
			if (c1.startY <= this.startY && this.startY <= c1.endY) return true;
			if (c1.startY <= this.endY && this.endY <= c1.endY) return true;
		} else if (c1.startX <= this.endX && this.endX <= c1.endX) {
			if (c1.startY <= this.startY && this.startY <= c1.endY) return true;
			if (c1.startY <= this.endY && this.endY <= c1.endY) return true;
		}
		return false;
	}
}
