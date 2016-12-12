package character;

import java.util.ArrayList;
import java.util.List;

import character.Enemy;

public class EnemyHolder {
	
	private static final EnemyHolder instance = new EnemyHolder();
	private List<Enemy> enemyPack;
	
	
	public EnemyHolder() {
		enemyPack = new ArrayList<>();
	}
	
	/**
	 * @param e : single enemy
	 */
	public void add(Enemy e) {
		enemyPack.add(e);
	}
	
	
	/**
	 * @param e : list of enemy
	 */
	public void addall(List<Enemy> e) {
		enemyPack.addAll(e);
	}
	
	public void clear() {
		enemyPack.clear();
	}
	
	public List<Enemy> getEnemyPack(){ 
		return enemyPack;
	}
	
	public static EnemyHolder getInstance() {
		return instance;
	}
}