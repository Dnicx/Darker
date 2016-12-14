package render;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class RenderableHolder {

	private static final RenderableHolder instance = new RenderableHolder();

	public static final String levelDir = "level/stage";
	
	public static final Font font = Font.font("Tahoma", FontWeight.BOLD, 20);

	private String heroSpriteSrc = "consumed-knight-spriteSheet.png";
	private String StageFarSrc = "level/stage0/arena-background.png";
	private String StageNearSrc = "level/stage0/arena-ground.png";
	private String fireballSpriteSrc = "monster/fireball-spriteSheet.png";
	private String cuteMonsterSpriteSrc = "monster/cute-monster.png";
	private String skullMonsterSpriteSrc = "monster/skull-monster.png";
	private String tongueMonsterSpriteSrc = "monster/tongue-monster.png";
	private String titleSrc = "menu/darker.png";
	private String rtmNomalSrc = "menu/rtm-nomal.png";
	private String rtmEnterSrc = "menu/rtm-enter.png";
	private String rtmClickSrc = "menu/rtm-click.png";
	private String rtmV2ClickSrc = "menu/rtm-v2-click.png";
	private String gameOverSrc = "menu/gameover.png";
	private String victorySrc = "menu/victory.png";
	private String startBtnSrc = "menu/startBtn-v2.png";
	private String startBtnEnterSrc = "menu/startBtn-v2-enter.png";
	private String startBtnClickSrc = "menu/startBtn-v2-click.png";
	private String exitBtnSrc = "menu/exitBtn.png";
	private String exitBtnEnterSrc = "menu/exitBtn-enter.png";
	private String exitBtnClickSrc = "menu/exitBtn-click.png";
	private String exitBtnV2ClickSrc = "menu/exitBtn-v2-click.png";
	private String exitBtnV2EnterSrc = "menu/exitBtn-v2-enter.png";
	private String exitBtnV2Src = "menu/exitBtn-v2.png";
	public Image heroSprite = null;
	public Image StageFar = null;
	public Image StageNear = null;
	public Image fireballSprite = null;
	public Image cuteMonsterSprite = null;
	public Image skullMonsterSprite = null;
	public Image tongueMonsterSprite = null;
	public Image titlemenu = null;
	public Image rtmNomal = null;
	public Image rtmEnter = null;
	public Image rtmClick = null;
	public Image rtmV2Click = null;
	public Image gameOver = null;
	public Image victory = null;
	public Image startBtn = null;
	public Image startBtnEnter = null;
	public Image startBtnClick = null;
	public Image exitBtn = null;
	public Image exitBtnEnter = null;
	public Image exitBtnClick = null;
	public Image exitBtnV2Click = null;
	public Image exitBtnV2 = null;
	public Image exitBtnV2Enter = null;

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
			StageFar = new Image(loader.getResourceAsStream(StageFarSrc));
			StageNear = new Image(loader.getResourceAsStream(StageNearSrc));
			fireballSprite = new Image(loader.getResourceAsStream(fireballSpriteSrc));
			cuteMonsterSprite = new Image(loader.getResourceAsStream(cuteMonsterSpriteSrc));
			skullMonsterSprite = new Image(loader.getResourceAsStream(skullMonsterSpriteSrc));
			tongueMonsterSprite = new Image(loader.getResourceAsStream(tongueMonsterSpriteSrc));
			titlemenu = new Image(loader.getResourceAsStream(titleSrc));
			rtmEnter = new Image(loader.getResourceAsStream(rtmEnterSrc));
			rtmNomal = new Image(loader.getResourceAsStream(rtmNomalSrc));
			rtmClick = new Image(loader.getResourceAsStream(rtmClickSrc));
			rtmV2Click = new Image(loader.getResourceAsStream(rtmV2ClickSrc));
			gameOver = new Image(loader.getResourceAsStream(gameOverSrc));
			victory = new Image(loader.getResourceAsStream(victorySrc));
			startBtn = new Image(loader.getResourceAsStream(startBtnSrc));
			startBtnClick = new Image(loader.getResourceAsStream(startBtnClickSrc));
			startBtnEnter = new Image(loader.getResourceAsStream(startBtnEnterSrc));
			exitBtn = new Image(loader.getResourceAsStream(exitBtnSrc));
			exitBtnClick = new Image(loader.getResourceAsStream(exitBtnClickSrc));
			exitBtnEnter = new Image(loader.getResourceAsStream(exitBtnEnterSrc));
			exitBtnV2 = new Image(loader.getResourceAsStream(exitBtnV2Src));
			exitBtnV2Click = new Image(loader.getResourceAsStream(exitBtnV2ClickSrc));
			exitBtnV2Enter = new Image(loader.getResourceAsStream(exitBtnV2EnterSrc));

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

	public void setFarBackgroundSrc(String src) {
		StageFarSrc = src;
	}

	public void setNearBackgroundSrc(String src) {
		StageNearSrc = src;
	}

}
