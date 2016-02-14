package data;

import static helpers.Artist.SCREENHEIGHT;
import static helpers.Artist.SCREENWIDTH;
import static helpers.Artist.drawQuadTex;
import static helpers.Artist.quickLoad;
import static helpers.Leveler.mapFolderPath;

import java.io.File;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import helpers.Artist;
import helpers.StateManager;
import helpers.UserInput;
import helpers.StateManager.GameState;
import ui.ButtonType;
import ui.UI;

public class MapSelector {
	private Texture background;
	public static UI menuUI;
	private File[] mapNamesList;
	
	public MapSelector() {
		background = quickLoad("mapselector");
		menuUI = new UI();
		getAvailableMaps();
		showAvailableMaps();
	}
	
	private void showAvailableMaps() {
		for (int i = 0; i < mapNamesList.length; i++) {
			menuUI.addButton(mapNamesList[i].getName(), mapNamesList[i].getName(), ButtonType.ButtonEmpty, SCREENWIDTH / 2 - 128, (int) (SCREENHEIGHT * (0.45f + i * 0.15f)));
		}
	}
	
	private void getAvailableMaps() {
		try {
			File folder = new File(mapFolderPath);
			mapNamesList = folder.listFiles();
		} catch  (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void updateButtons() { //Vérifie si un bouton est cliqué par l'usager
		if (!UserInput.leftMouseButtonDown && Mouse.isButtonDown(0)) {
			if (menuUI.isAButtonHovered() != null) {
				StateManager.setState(GameState.GAME);
			}
		}
		menuUI.update();
	}
	
	public void update() {
		drawQuadTex(background, 0, 0, Artist.SCREENWIDTH, Artist.SCREENHEIGHT);
		updateButtons();
		
		//Handle keyboard inputs
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE && Keyboard.getEventKeyState()) {
				StateManager.setState(GameState.MAINMENU);
			}
		}
	}
}
