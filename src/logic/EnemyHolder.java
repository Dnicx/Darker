package logic;

import java.util.ArrayList;
import java.util.List;

import character.Enemy;

public class EnemyHolder {
	
	public EnemyHolder instance = new EnemyHolder();
	private List<Enemy> enemyPack = new ArrayList<>();
	
	
	public EnemyHolder() {
		
	}
}
