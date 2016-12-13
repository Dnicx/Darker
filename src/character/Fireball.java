package character;

import logic.EnemyPattern;
import render.Animation;
import render.RenderableHolder;
import render.SequenceAnimation;

public class Fireball extends Enemy {

	private static final int offsetX = 40;
	private static final int offsetY = 80;
	public static final int width = 40;
	public static final int height = 40;
	public static final int patternDelay = 40;

	private static final int frameWidth = 128;
	private static final int frameHeight = 128;

	private static final int walkFrameCount = 4;
	private static final int idleFrameCount = 4;
	private static final int attackFrameCount = 8;

	private static final int attackRange = 40;
	private static final int attackFrame = 4;

	/**
	 * create fireball with default status position = 0, 0 hp = 5 speed = 5
	 * damage = 2
	 */
	public Fireball() {
		super();
		super.setWidth(width);
		super.setheight(height);
		loadPattern();
		currentPattern = walkPattern;
	}

	/**
	 * create fireball with default status but specific position hp = 5 speed =
	 * 5 damage = 2
	 * 
	 * @param positionX
	 *            : fireball init x position
	 * @param positionY
	 *            : fireball init y position
	 */
	public Fireball(int positionX, int positionY) {
		super(positionX, positionY);
		super.setWidth(width);
		super.setheight(height);
		loadPattern();
		currentPattern = walkPattern;
	}

	/**
	 * create fireball with custom status
	 * 
	 * @param positionX
	 *            : position x
	 * @param positionY
	 *            : position y
	 * @param hp
	 * @param speed
	 * @param damage
	 */
	public Fireball(int positionX, int positionY, int hp, int speed, int damage) {
		super(positionX, positionY, hp, speed, damage);
		super.setWidth(width);
		super.setheight(height);
		loadPattern();
		currentPattern = walkPattern;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public Animation getCurrentStat() {
		return this.getCurrentStat();
	}

	public EnemyPattern getCurrentPattern() {
		return currentPattern;
	}

	public int getAttackRange() {
		return attackRange;
	}

	@Override
	protected void loadIdleLeft() {
		// TODO Auto-generated method stub
		this.idleLeft = new Animation(RenderableHolder.getInstance().fireballSprite, frameWidth, frameHeight,
				idleFrameCount, 2, 0);
		this.idleLeft.setOffset(offsetX, offsetY);
		this.idleLeft.setPosition(this.logicalX, this.logicalY);

	}

	@Override
	protected void loadIdleRight() {
		// TODO Auto-generated method stub
		this.idleRight = new Animation(RenderableHolder.getInstance().fireballSprite, frameWidth, frameHeight,
				idleFrameCount, 2, 0);
		this.idleRight.setOffset(offsetX, offsetY);
		this.idleRight.setPosition(this.logicalX, this.logicalY);

	}

	@Override
	protected void loadWalkLeft() {
		// TODO Auto-generated method stub
		this.walkLeft = new Animation(RenderableHolder.getInstance().fireballSprite, frameWidth, frameHeight,
				walkFrameCount, 2, 1);
		this.walkLeft.setOffset(offsetX, offsetY);
		this.walkLeft.setPosition(this.logicalX, this.logicalY);

	}

	@Override
	protected void loadWalkRight() {
		// TODO Auto-generated method stub
		this.walkRight = new Animation(RenderableHolder.getInstance().fireballSprite, frameWidth, frameHeight,
				walkFrameCount, 2, 1);
		this.walkRight.setOffset(offsetX, offsetY);
		this.walkRight.setPosition(this.logicalX, this.logicalY);

	}

	@Override
	protected void loadAttackLeft() {
		// TODO Auto-generated method stub
		this.attackLeft = new SequenceAnimation(RenderableHolder.getInstance().fireballSprite, frameWidth, frameHeight,
				attackFrameCount, 2, 2);
		this.attackLeft.setOffset(offsetX, offsetY);
		this.attackLeft.setPosition(this.logicalX, this.logicalY);

	}

	@Override
	protected void loadAttackRight() {
		// TODO Auto-generated method stub
		this.attackRight = new SequenceAnimation(RenderableHolder.getInstance().fireballSprite, frameWidth, frameHeight,
				attackFrameCount, 2, 2);
		this.attackRight.setOffset(offsetX, offsetY);
		this.attackRight.setPosition(this.logicalX, this.logicalY);

	}

	@Override
	protected void loadWalkPattern() {
		// TODO Auto-generated method stub
		this.walkPattern = new EnemyPattern(30);
		this.walkPattern.add(walkLeft);
		this.walkPattern.add(idleLeft);
		this.walkPattern.add(walkRight);
		this.walkPattern.add(idleRight);
	}

	@Override
	protected void loadAttackRightPattern() {
		// TODO Auto-generated method stub
		this.attackRightPattern = new EnemyPattern(25);
		this.attackRightPattern.add(idleRight);
		this.attackRightPattern.add(attackRight);
		this.attackRightPattern.add(idleRight);
	}

	@Override
	protected void loadAttackLeftPattern() {
		this.attackLeftPattern = new EnemyPattern(25);
		this.attackLeftPattern.add(idleLeft);
		this.attackLeftPattern.add(attackLeft);
		this.attackLeftPattern.add(idleLeft);
	}

	@Override
	public int getAtttackFrame() {
		// TODO Auto-generated method stub
		return attackFrame;
	}

}
