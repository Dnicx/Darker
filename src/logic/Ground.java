package logic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ground {
	
	private List<CollideBox> box = null;
	private int[][] block;
	private int width, height;
	private static final int MAX_X = 2500;
	private static final int MAX_Y = 1000;
	
	/**
	 * 
	 * @param file : text file contain ground with format "n width height" follow by n number of blocks 
	 */
	public Ground(File file) {
		block = new int[MAX_Y][MAX_X];
		box = new ArrayList<>();
		try{ 
			Scanner boxs = new Scanner(file);
			int n = 0; 
			n = boxs.nextInt();
			this.width = boxs.nextInt();
			this.height = boxs.nextInt();
			for (int i = 0; i<n && boxs.hasNextInt(); i++) {
				box.add(new CollideBox(boxs.nextInt(), boxs.nextInt(), boxs.nextInt(), boxs.nextInt()));
				//System.out.println(boxs.nextInt() + " " + boxs.nextInt() + " " + boxs.nextInt() + " " + boxs.nextInt() + " ");
			}
		} catch (Exception e) {
			System.out.println("error read file : " + e);
		}
	}
	
	public int[][] getBlock() {
		return block;
	}
	
	/*
	 * (slow)
	 * fill block array with 1 wherever there is collide box
	 */
	public void createGround() {
		if (box != null) {
			for (CollideBox b : box) {
				for (int i = b.getTop(); i < b.getBottom(); i++) {
					for (int j = b.getLeft(); j < b.getRight() ; j++) {
						block[i][j] = 1;
					}
				}
			}
		}
	}
	
	public boolean canGoLeft(int x, int y, int width, int height) {
		if (block[x-1][y+(height/2)] == 1) return false;
		else return true;
	}
	
	public boolean canStand(int x, int y, int widht, int height)  {
		for (int i=0; i<widht; i++) {
			if (block[x+i][y+1] == 1) return true;
		}
		return false;
	}
	
	/**
	 * (slow)
	 * find the top box that character can stand on
	 * @param x : character's top x position
	 * @param y : character's top y position
	 * @param width : width of character
	 * @param height : height of character
	 * @return y position of floor(ground).
	 */
	/*
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
	*/
	
}
