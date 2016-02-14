package helpers;

import data.Editor;
import data.MapSelector;
import data.Game;
import data.MainMenu;
import static helpers.Leveler.*;

import org.lwjgl.opengl.GL11;

public class StateManager {
	public static enum GameState {
		MAINMENU, MAPSELECTOR, GAME, EDITOR
	}

	public static GameState gameState = GameState.MAINMENU;
	public static MainMenu mainMenu;
	public static MapSelector mapSelector;
	public static Game game;
	public static Editor editor;

	public static void update() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		switch (gameState) {
			case MAINMENU:
				if (mainMenu == null) {
					mainMenu = new MainMenu();
				}
				game = null;
				mainMenu.update();
				break;
			case MAPSELECTOR:
				if (mapSelector == null) {
					mapSelector = new MapSelector();
				}
				mapSelector.update();
				break;
			case GAME:
				if (game == null) {
					game = new Game(loadMap(MapSelector.menuUI.isAButtonHovered()));
				}
				game.update();
				break;
			case EDITOR:
				if (editor == null) {
					editor = new Editor();
				}
				editor.update();
				break;
		}
	}

	public static void setState(GameState newState) {
		gameState = newState;
	}
}
