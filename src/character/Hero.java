package character;

import javafx.scene.canvas.GraphicsContext;
import logic.CollideBox;
import render.Animation;
import render.Renderable;
import render.RenderableHolder;
import render.SequenceAnimation;

public class Hero implements Renderable {

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
	public Animation fallRight = null;
	public Animation fallLeft = null;
	private boolean visible;
	private int logicalX = 0, logicalY = 0;
	private int onScreenX = 0, onScreenY = 0;
	public int offsetX = 80;
	public int offsetY = 90;
	public int width = 80;
	public int height = 140;
	private int direction;
	private boolean alive;
	private boolean attackState;

	private Animation currentState;

	public Hero() {
		logicalX = 0;
		logicalY = 0;
		HP = 5;
		speed = 10;
		damage = 2;
		visible = true;
		alive = true;
		direction = FACE_RIGHT;
		attackState = false;
		loadAnimation();
		currentState = idleRight;
		currentState.play();
		RenderableHolder.getInstance().add(this);
	}

	public Hero(int x, int y) {
		this.logicalX = x;
		this.logicalY = y;
		HP = 5;
		speed = 10;
		damage = 2;
		visible = true;
		alive = true;
		direction = FACE_RIGHT;
		attackState = false;
		loadAnimation();
		currentState = idleRight;
		currentState.play();
		RenderableHolder.getInstance().add(this);
	}

	public Hero(int x, int y, int hp, int speed, int damage) {
		this.logicalX = x;
		this.logicalY = y;
		this.HP = hp;
		this.speed = speed;
		this.damage = damage;
		visible = true;
		alive = true;
		direction = FACE_RIGHT;
		attackState = false;
		loadAnimation();
		currentState = idleRight;
		currentState.play();
		RenderableHolder.getInstance().add(this);
	}

	private void loadAnimation() {
		idleLeft = new Animation(RenderableHolder.getInstance().heroSprite, 256, 256, 4, 2, 0);
		idleLeft.setOffset(offsetX, offsetY);
		idleLeft.setPosition(onScreenX, onScreenY);

		walkLeft = new Animation(RenderableHolder.getInstance().heroSprite, 256, 256, 8, 2, 2);
		walkLeft.setOffset(offsetX, offsetY);
		walkLeft.setPosition(onScreenX, onScreenY);

		idleRight = new Animation(RenderableHolder.getInstance().heroSprite, 256, 256, 4, 2, 1);
		idleRight.setOffset(offsetX, offsetY);
		idleRight.setPosition(onScreenX, onScreenY);

		walkRight = new Animation(RenderableHolder.getInstance().heroSprite, 256, 256, 8, 2, 3);
		walkRight.setOffset(offsetX, offsetY);
		walkRight.setPosition(onScreenX, onScreenY);

		attackLeft = new SequenceAnimation(RenderableHolder.getInstance().heroSprite, 256, 256, 6, 2, 4);
		attackLeft.setOffset(offsetX, offsetY);
		attackLeft.setPosition(onScreenX, onScreenY);

		attackRight = new SequenceAnimation(RenderableHolder.getInstance().heroSprite, 256, 256, 6, 2, 5);
		attackRight.setOffset(offsetX, offsetY);
		attackRight.setPosition(onScreenX, onScreenY);
		
		fallLeft = new Animation(RenderableHolder.getInstance().heroSprite, 256, 256, 4, 2, 6);
		fallLeft.setOffset(offsetX, offsetY);
		fallLeft.setPosition(onScreenX, onScreenY);
		
		fallRight = new Animation(RenderableHolder.getInstance().heroSprite, 256, 256, 4, 2, 7);
		fallRight.setOffset(offsetX, offsetY);
		fallRight.setPosition(onScreenX, onScreenY);
	}

	/**
	 * 
	 * @param state
	 *            : (animation) current animation state
	 */
	public void setState(Animation state) {
		currentState.stop();
		this.currentState = state;
		currentState.play();
	}

	public Animation getCurrentState() {
		return currentState;
	}
	
	public boolean isAttacking() {
		return attackState;
	}
	public void setAttackState(boolean attack) {
		attackState = attack;
	}


	public int getDirection() {
		return direction;
	}

	public void setDirection(int dir) {
		this.direction = dir;
	}

	public void setLogicalPosition(int x, int y) {
		this.logicalX = x;
		this.logicalY = y;
	}
	
	public void setOnScreenPosition(int x, int y) {
		this.onScreenX = x;
		this.onScreenY = y;
	}

	public int getLogicalX() {
		return logicalX;
	}

	public int getlogicalY() {
		return logicalY;
	}
	
	public int getOnScreenX() {
		return onScreenX;
	}
	
	public int getOnScreenY() {
		return onScreenY;
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
		currentState.setPosition(onScreenX, onScreenY);
		currentState.render(gc);
		currentState.updateAnimation();

	}

}
