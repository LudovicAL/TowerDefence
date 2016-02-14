package data;

import static helpers.Artist.SCREENHEIGHT;
import static helpers.Leveler.*;

import javax.swing.JOptionPane;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import helpers.Artist;
import helpers.StateManager;
import helpers.StateManager.GameState;

public class Editor {
	private TileGrid grid;
	private int index;
	private TileType[] types;
	
	public Editor() {
		this.grid = new TileGrid();
		this.index = 0;
		this.types = new TileType[3];
		this.types[0] = TileType.Grass;
		this.types[1] = TileType.Dirt;
		this.types[2] = TileType.Water;
	}

	public void update() {
		grid.draw();
		
		//Handle mouse inputs
		if (Mouse.isButtonDown(0)) {
			setTile();
		}

		//Handle keyboard inputs
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
				moveIndex();
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_S && Keyboard.getEventKeyState()) {
				int mc = JOptionPane.WARNING_MESSAGE;
				String mapName = JOptionPane.showInputDialog (null, "Map name:", "Name your map", mc);
				if (mapName != null) {
					saveMap(mapName, grid);
				}
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE && Keyboard.getEventKeyState()) {
				StateManager.setState(GameState.MAINMENU);
			}
		}
	}
	
	private void setTile() {
		grid.setTile((int) Math.floor(Mouse.getX() / Artist.TILE_SIZE), (int) Math.floor((SCREENHEIGHT - Mouse.getY() - 1) / Artist.TILE_SIZE), types[index]);
	}
	
	private void moveIndex() {
		index++;
		if (index > types.length - 1) {
			index = 0;
		}
	}
}
