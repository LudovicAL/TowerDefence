package data;

import static helpers.Artist.SCREENHEIGHT;
import static helpers.Artist.SCREENWIDTH;
import static helpers.Artist.drawQuadTex;
import static helpers.Artist.quickLoad;

import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import helpers.Artist;
import helpers.StateManager;
import helpers.StateManager.GameState;
import ui.ButtonType;
import ui.UI;

public class MainMenu {
	private Texture background;
	private UI menuUI;

	public MainMenu() {
		background = quickLoad("mainmenu");
		menuUI = new UI();
		menuUI.addButton("Selector", ButtonType.ButtonPlay, SCREENWIDTH / 2 - 128, (int) (SCREENHEIGHT * 0.45f));
		menuUI.addButton("Editor", ButtonType.ButtonEditor, SCREENWIDTH / 2 - 128, (int) (SCREENHEIGHT * 0.60f));
		menuUI.addButton("Quit", ButtonType.ButtonQuit, SCREENWIDTH / 2 - 128, (int) (SCREENHEIGHT * 0.75f));
	}

	private void updateButtons() { //Vérifie si un bouton est cliqué par l'usager
		String hoveredButton = menuUI.isAButtonHovered();
		if (hoveredButton != null) {
			if (Mouse.isButtonDown(0)) {
				switch (hoveredButton) {
					case "Selector":
						StateManager.setState(GameState.MAPSELECTOR);
						break;
					case "Editor":
						StateManager.setState(GameState.EDITOR);
						break;
					case "Quit":
						Display.destroy();
						AL.destroy();
						System.exit(0);
						break;
					default:
						
				}
			}
		}
		menuUI.update();
	}

	public void update() {
		drawQuadTex(background, 0, 0, Artist.SCREENWIDTH, Artist.SCREENHEIGHT);
		updateButtons();
	}
}
