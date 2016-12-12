package character;

import javafx.scene.canvas.GraphicsContext;
import render.Animation;
import render.Renderable;
import render.RenderableHolder;

public abstract class Enemy implements Renderable {
	public int HP;
	public int speed;
	public int damage;
	public int fall_speed = 0;
	public int jumpStrength = 25;
	public static final int FACE_RIGHT = 1, FACE_LEFT = -1;
	public Animation idleLeft = null;
	public Animation walkLeft = null;
	public Animation idleRight = null;
	public Animation walkRight = null;
	public Animation attackLeft = null;
	public Animation attackRight = null;
	protected boolean visible;
	protected int x = 0, y = 0;
	public int offsetX = 80;
	public int offsetY = 90;
	public int width = 80;
	public int height  = 140;
	protected int direction;
	private boolean alive;
	
	private Animation currentState;
	
	public Enemy() {
		x = 0;
		y = 0;
		HP = 5;
		speed = 5;
		damage = 2;
		visible = true;
		direction = FACE_RIGHT;
		loadAnimation();
		currentState = idleRight;
		currentState.play();
		EnemyHolder.getInstance().add(this);
		RenderableHolder.getInstance().add(this);
	}
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		HP = 5;
		speed = 5;
		damage = 2;
		visible = true;
		direction = FACE_RIGHT;
		loadAnimation();
		currentState = idleRight;
		currentState.play();
		EnemyHolder.getInstance().add(this);
		RenderableHolder.getInstance().add(this);
	}
	
	public Enemy(int x, int y, int hp, int speed, int damage) {
		this.x = x;
		this.y = y;
		this.HP = hp;
		this.speed = speed;
		this.damage = damage;
		visible = true;
		direction = FACE_RIGHT;
		loadAnimation();
		currentState = idleRight;
		currentState.play();
		EnemyHolder.getInstance().add(this);
		RenderableHolder.getInstance().add(this);
	}
	
	private void loadAnimation() {
		loadIdleLeft();
		loadIdleRight();
		loadWalkLeft();
		loadWalkRight();
		loadAttackLeft();
		loadAttackRight();
	}
	
	protected abstract void loadIdleLeft();
	protected abstract void loadIdleRight();
	protected abstract void loadWalkLeft();
	protected abstract void loadWalkRight();
	protected abstract void loadAttackLeft();
	protected abstract void loadAttackRight();
	
	
	/**
	 * 
	 * @param state : (animation) current animation state
	 */
	public void setState(Animation state) {
		currentState.stop();
		this.currentState = state;
		currentState.play();
	}

	public Animation getCurrentState() {
		return currentState;
	}
	
	public int getDirection() {
		return direction;
	}
	public void setDirection(int dir) {
		this.direction = dir;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public void hitted(int damage) {
		this.HP -= damage;
		if (this.HP <= 0) {
			alive = false;
		}
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
		currentState.setPosition(x, y);
		currentState.render(gc);
		currentState.updateAnimation();

	}
	
}
