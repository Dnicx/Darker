package render;

import javafx.scene.media.AudioClip;

public class AudioUtility {
	private static final AudioUtility instance = new AudioUtility();
	private String fireSrc = "audio/fire.wav";
	private String swordSlashSrc = "audio/sword-slash.wav";
	private String swordSlashWindSrc = "audio/sword-slash-wind.wav";
	private String woodDestroySrc = "audio/wood-destroy.wav";
	private String backgroundMusicSrc = "audio/background-music.wav";
	public AudioClip fire = null;
	public AudioClip swordSlash = null;
	public AudioClip swordSlashWind = null;
	public AudioClip woodDestroy = null;
	public static AudioClip backgroundMusic = null;
	public static final String fireSound = "fire";
	public static final String swordSlashSound = "swordSlash";
	public static final String swordSlashWindSound = "swordSlashWind";
	public static final String woodDestroySound = "woodDestroy";
	public static final String backgroundMusicSound = "backgroundMusic";
	public AudioClip currentClip = null;

	public void AutioUtility() {

	}

	public void loadResource() {
		try {
			fire = new AudioClip(ClassLoader.getSystemResource(fireSrc).toString());
			swordSlash = new AudioClip(ClassLoader.getSystemResource(swordSlashSrc).toString());
			swordSlashWind = new AudioClip(ClassLoader.getSystemResource(swordSlashWindSrc).toString());
			woodDestroy = new AudioClip(ClassLoader.getSystemResource(woodDestroySrc).toString());
			backgroundMusic = new AudioClip(ClassLoader.getSystemResource(backgroundMusicSrc).toString());

		} catch (NullPointerException e) {
			System.out.println("can not find resource : " + e);
		} catch (Exception e) {
			System.out.println("error load resource : " + e);
		}

	}

	public static AudioUtility getinstance() {
		return instance;
	}

	public static void playSound(String sound) {
		if (sound.equals(fireSound)) {
			instance.fire.play();
			instance.currentClip = instance.fire;
		}
		if (sound.equals(swordSlashSound)) {
			instance.swordSlash.play();
			instance.currentClip = instance.fire;
		}
		if (sound.equals(swordSlashWindSound)) {
			instance.swordSlashWind.play();
			instance.currentClip = instance.fire;
		}
		if (sound.equals(woodDestroySound)) {
			instance.woodDestroy.play();
			instance.currentClip = instance.fire;
		}
		if (sound.equals(backgroundMusicSound)) {
			instance.backgroundMusic.play();
			instance.currentClip = instance.fire;
		}
	}

	public static void stopCurrent() {
		instance.currentClip.stop();
	}

}
