package data;

import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;

public enum SoundType {

	Victory("res/victory.wav"),
	Defeat("res/defeat.wav"),
	Pew("res/pew.wav"),
	Shoot("res/shoot.wav"),
	Vloup("res/vloup.wav");
	
	private String fileName;
	public static ArrayList<Audio> soundList;
	
	SoundType(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileName() {
		return fileName;
	}

	public static void initializeSound () {
		soundList = new ArrayList<Audio>();
		try {
			for (int i = 0; i < SoundType.values().length; ++i) {
				Audio sound = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(SoundType.values()[i].getFileName()));
				soundList.add(sound);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		playSound(Victory);
	}
	
	public static Audio getSound(SoundType type) {
		return soundList.get(type.ordinal());
	}
	
	public static void playSound(SoundType type) {
		getSound(type).playAsSoundEffect(1.0f, 1.0f, false);
		SoundStore.get().poll(0);
	}
}
