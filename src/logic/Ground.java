package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sun.swing.internal.plaf.synth.resources.synth;

public class Ground {
	
	private List<CollideBox> box = new ArrayList<>();
	
	public Ground(String file) {
		try{ 
			Scanner boxs = new Scanner(file);
			while (boxs.hasNextLine()) {
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
