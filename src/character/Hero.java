package character;

import javafx.scene.canvas.GraphicsContext;
import render.Animation;
import render.Renderable;
import render.RenderableHolder;

public class Hero implements Renderable {
	
	public Animation idle;
	public Animation walk;
	private boolean visible;
	private int x = 0, y = 0;
	private int offsetX = 80;
	private int offsetY = 90;
	private Animation currentState;
	
	public Hero() {
		x = 0;
		y = 0;
		loadAnimation();
		currentState = walk;
		currentState.play();
	}
	
	public Hero(int x, int y) {
		this.x = x;
		this.y = y;
		loadAnimation();
		currentState = walk;
		currentState.play();
	}
	
	private void loadAnimation() {
		idle = new Animation(RenderableHolder.getInstance().heroSprite, 256, 256, 4, 3, 0);
		idle.setOffset(offsetX, offsetY);
		walk = new Animation(RenderableHolder.getInstance().heroSprite, 256, 256, 8, 3, 1);
		walk.setOffset(offsetX, offsetY);
	}

	@Override
	public int getz() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return visible;
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		currentState.render(gc);		
		currentState.updateAnimation();
	}
	
	
}
