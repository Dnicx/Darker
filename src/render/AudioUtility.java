package render;


import com.sun.scenario.effect.impl.sw.sse.SSEBoxShadowPeer;

import javafx.scene.media.AudioClip;

public class AudioUtility {
	private static final AudioUtility instance=new AudioUtility();
	private String fireSrc = "audio/fire.wav";
	private String swordSlashSrc = "audio/sword-slash.wav";
	private String swordSlashWindSrc = "audio/sword-slash-wind.wav";
	private String woodDestroySrc = "audio/wood-destroy.wav";
	public AudioClip fire = null;
	public AudioClip swordSlash = null;
	public AudioClip swordSlashWind = null;
	public AudioClip woodDestroy = null;
	public static final String fireSound = "fire";
	public static final String swordSlashSound = "swordSlash";
	public static final String swordSlashWindSound = "swordSlashWind";
	public static final String woodDestroySound = "woodDestroy";
	
	public void AutioUtility() {
		
	}

	public void loadResource(){
		try {
			fire = new AudioClip(ClassLoader.getSystemResource(fireSrc).toString());
			swordSlash = new AudioClip(ClassLoader.getSystemResource(swordSlashSrc).toString());
			swordSlashWind = new AudioClip(ClassLoader.getSystemResource(swordSlashWindSrc).toString());
			woodDestroy=new AudioClip(ClassLoader.getSystemResource(woodDestroySrc).toString());
			
			
		} catch (NullPointerException e) {
			System.out.println("can not find resource : " + e);
		} catch (Exception e) {
			System.out.println("error load resource : " + e);
		}
		
	}
	public static AudioUtility getinstance(){
		return instance;
	}
	
	public static void playSound(String sound) {
		if (sound.equals(fireSound)) instance.fire.play();
		if (sound.equals(swordSlashSound)) instance.swordSlash.play();
		if (sound.equals(swordSlashWindSound)) instance.swordSlashWind.play();
		if (sound.equals(woodDestroySound)) instance.woodDestroy.play();
	}

}
