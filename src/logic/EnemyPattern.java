package logic;

import java.util.ArrayList;
import java.util.List;
import render.Animation;

public class EnemyPattern {
	
	private List<Animation> pattern;
	private int patternDelay;
	private int patternDelayCount;
	private int currentStateNumber;
	private boolean end;
	
	public EnemyPattern() {
		patternDelay = 10;
		pattern = new ArrayList<>();
		currentStateNumber = 0;
		end = true;
	}
	
	public EnemyPattern(int patternDelay) {
		pattern = new ArrayList<>();
		this.patternDelay = patternDelay;
		currentStateNumber = 0;
		end = true;
	}
	
	public EnemyPattern(List<Animation> animation) {
		patternDelay = 10;
		pattern = new ArrayList<>();
		pattern.addAll(animation);
		currentStateNumber = 0;
		end = true;
	}
	
	public EnemyPattern(List<Animation> animation, int patternDelay) {
		this.patternDelay = patternDelay;
		pattern = new ArrayList<>();
		pattern.addAll(animation);
		currentStateNumber = 0;
		end = true;
	}
	
	public void add(Animation state) {
		pattern.add(state);
	}
	
	public void setDelay(int patternDelay) {
		this.patternDelay = patternDelay;
	}
	
	public boolean isEnd() {
		return end;
	}
	
	public void playPattern() {
		end = false;
	}
	
	public Animation getCurrentState() {
		return pattern.get(currentStateNumber);
	}
	
	public void skipState() {
		patternDelayCount = patternDelay;
		currentStateNumber += 1;
		pattern.get(currentStateNumber).play();
	}
	
	public void patternUpdate() {
		if (end) return;
		patternDelayCount -= 1;
		if (patternDelayCount >= 0) {
			return;
		}
		patternDelayCount = patternDelay;
		currentStateNumber += 1;
		if (currentStateNumber >= pattern.size()) {
			currentStateNumber = 0;
			end = true;
		}
	}
	
}
