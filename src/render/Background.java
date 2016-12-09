package render;

import java.util.Scanner;

import javafx.scene.image.Image;
import logic.Ground;

public class Background extends Ground{

	private Image farBackground = null;
	private Image nearBackGround = null; 
	
	public Background(String groundFile) {
		try {
			Scanner file = new Scanner(groundFile);
		} catch (Exception e) {
			
		}
		
	}
	
}
