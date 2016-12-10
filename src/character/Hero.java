package character;

import javafx.scene.canvas.GraphicsContext;
import render.Animation;
import render.Renderable;
import render.RenderableHolder;

public class Hero implements Renderable {
	
	public int HP;
	public int speed;
	public int damage;
	
	public Animation idle = null;
	public Animation walk = null;
	private boolean visible;
	private int x = 0, y = 0;
	private int offsetX = 80;
	private int offsetY = 90;
	
	private Animation currentState;
	
	public Hero() {
		x = 0;
		y = 0;
		HP = 5;
		speed = 3;
		damage = 2;
		loadAnimation();
		currentState = idle;
		currentState.play();
		RenderableHolder.getInstance().add(this);
	}
	
	public Hero(int x, int y) {
		this.x = x;
		this.y = y;
		HP = 5;
		speed = 3;
		damage = 2;
		loadAnimation();
		currentState = idle;
		currentState.play();
		RenderableHolder.getInstance().add(this);
	}
	
	public Hero(int x, int y, int hp, int speed, int damage) {
		this.x = x;
		this.y = y;
		this.HP = hp;
		this.speed = speed;
		this.damage = damage;
		loadAnimation();
		currentState = idle;
		currentState.play();
		RenderableHolder.getInstance().add(this);
	}
	
	private void loadAnimation() {
		idle = new Animation(RenderableHolder.getInstance().heroSprite, 256, 256, 4, 2, 0);
		idle.setOffset(offsetX, offsetY);
		idle.setPosition(x, y);
		
		walk = new Animation(RenderableHolder.getInstance().heroSprite, 256, 256, 8, 2, 1);
		walk.setOffset(offsetX, offsetY);
		walk.setPosition(x, y);
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
		currentState.setPosition(x, y);
	}
	
	
}
