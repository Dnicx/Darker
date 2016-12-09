package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ground {
	
	private List<CollideBox> box;
	
	public Ground(Scanner file) {
		box = new ArrayList<>();
		try{ 
			Scanner boxs = file;
			while (boxs.hasNextInt()) {
				box.add(new CollideBox(boxs.nextInt(), boxs.nextInt(), boxs.nextInt(), boxs.nextInt()));
			}
		} catch (Exception e) {
			System.out.println("error read file");
		}
	}
	
	
	/**
	 * (slow)
	 * find the top box that character can stand on
	 * @param character's top x position
	 * @param character's top y position
	 * @param width of character
	 * @param height of character
	 * @return y position of floor(ground).
	 */
	public int getGround(int x, int y, int width, int height) {
		int ground = Integer.MAX_VALUE;
		for (CollideBox b : box) {
			if (b.getLeft() < x+width && b.getRight() > x && b.getTop() > y+height) {
				if (ground > b.getTop()) {
					ground = b.getTop();
				}
			}
		}
		return ground;
	}
	
}
