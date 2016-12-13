package render;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;

public class RenderableHolder {

	private static final RenderableHolder instance = new RenderableHolder();

	private String heroSpriteSrc = "consumed-knight-spriteSheet.png";
	private String testStageFarSrc = "level/test-stage/arena-background.png";
	private String testStageNearSrc = "level/test-stage/arena-ground.png";
	private String fireballSpriteSrc = "monster/fireball-spriteSheet.png";
	private String cuteMonsterSpriteSrc = "monster/cute-monster.png";
	private String skullMonsterSpriteSrc = "monster/skull-monster.png";
	private String tongueMonsterSpriteSrc = "monster/tongue-monster.png";
	private String titleSrc = "menu/darker.png";
	private String rtmNomalSrc = "menu/rtm-nomal.png";
	private String rtmEnterSrc = "menu/rtm-enter.png";
	public String gameOverSrc = "menu/gameover.png";
	public Image heroSprite = null;
	public Image testStageFar = null;
	public Image testStageNear = null;
	public Image fireballSprite = null;
	public Image cuteMonsterSprite = null;
	public Image skullMonsterSprite = null;
	public Image tongueMonsterSprite = null;
	public Image titlemenu = null;
	public Image rtmNomal = null;
	public Image rtmEnter = null;
	public Image gameOver = null;

	private List<Renderable> entities;
	private Comparator<Renderable> comparator;

	public RenderableHolder() {
		entities = new ArrayList<Renderable>();
		comparator = new Comparator<Renderable>() {
			@Override
			public int compare(Renderable o1, Renderable o2) {
				// TODO Auto-generated method stub
				if (o1.getz() > o2.getz())
					return 1;
				if (o1.getz() < o2.getz())
					return -1;
				return 0;
			}
		};

	}

	public void loadResource() {
		entities.clear();
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		try {
			heroSprite = new Image(loader.getResourceAsStream(heroSpriteSrc));
			testStageFar = new Image(loader.getResourceAsStream(testStageFarSrc));
			testStageNear = new Image(loader.getResourceAsStream(testStageNearSrc));
			fireballSprite = new Image(loader.getResourceAsStream(fireballSpriteSrc));
			cuteMonsterSprite = new Image(loader.getResourceAsStream(cuteMonsterSpriteSrc));
			skullMonsterSprite = new Image(loader.getResourceAsStream(skullMonsterSpriteSrc));
			tongueMonsterSprite = new Image(loader.getResourceAsStream(tongueMonsterSpriteSrc));
			titlemenu = new Image(loader.getResourceAsStream(titleSrc));
			rtmEnter = new Image(loader.getResourceAsStream(rtmEnterSrc));
			rtmNomal = new Image(loader.getResourceAsStream(rtmNomalSrc));
			gameOver = new Image(loader.getResourceAsStream(gameOverSrc));
		} catch (NullPointerException e) {
			System.out.println("can not find resource : " + e);
		} catch (Exception e) {
			System.out.println("error load resource : " + e);
		}
	}

	public List<Renderable> getEntity() {
		return entities;
	}

	public synchronized void add(Renderable e) {
		entities.add(e);
		Collections.sort(entities, comparator);
	}
	
	public synchronized void remove(Renderable e) {
		entities.remove(e);
	}
	
	public synchronized void remove(int e) {
		entities.remove(e);
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

}
