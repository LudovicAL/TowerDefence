package data;

import static helpers.Artist.initiateDisplay;

import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.openal.SoundStore;

import helpers.Clock;
import helpers.StateManager;
import helpers.UserInput;


public class Boot {

	public Boot() {
		initiateDisplay(); //Appel la fonction d'initialisation des méthodes de display d'OpenGL
		SoundType.initializeSound();
		while (!Display.isCloseRequested()) { //Game loop
			Clock.update();
			StateManager.update();
			UserInput.updateMouse();
			Display.update();
			Display.sync(60);
			SoundStore.get().poll(0);
		}
		Display.destroy();
		AL.destroy();
	}

	public static void main(String[] args) {
		new Boot();
	}
}
