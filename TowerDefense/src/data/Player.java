package data;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import helpers.Artist;
import helpers.Clock;
import helpers.StateManager;
import helpers.StateManager.GameState;
import helpers.UserInput;

import static helpers.Artist.*;

import java.util.ArrayList;
import ui.UI;

public class Player {
	private TileGrid grid;
	private TileType[] types;
	private WaveManager waveManager;
	private ArrayList<Tower> towerList;
	public static int cash, lives;
	private UI menuUI;

	public Player(TileGrid grid, WaveManager waveManager) {
		this.grid = grid;
		this.types = new TileType[3];
		this.types[0] = TileType.Grass;
		this.types[1] = TileType.Dirt;
		this.types[2] = TileType.Water;
		this.waveManager = waveManager;
		this.towerList = new ArrayList<Tower>();
		Player.cash = 0;
		Player.lives = 0;
		menuUI = new UI();
		menuUI.addLabel("0 live", 5, 5);
		menuUI.addLabel("$0", 5, 30);
	}
	
	public void setup(int cash, int lives) { //Initialise l'argent et les vies du joueur
		Player.cash = cash;
		Player.lives = lives;
	}
	
	public static boolean modifyCash(int amount) {
		if (cash + amount >= 0) {
			cash += amount;
			return true;
		}
		return false;
	}
	
	public static void modifyLives(int amount) {
		lives += amount;
	}
	
	public boolean isBuildable(int x, int y) {
		if (grid.getTile((x / Artist.TILE_SIZE), ((SCREENHEIGHT - y - 1) / Artist.TILE_SIZE)).getType().buildable == true) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean tileHasTower(int x, int y) {
		x = x / Artist.TILE_SIZE;
		y = (SCREENHEIGHT - y - 1) / Artist.TILE_SIZE;
		for (Tower t : towerList) {
			if ((t.getX() / Artist.TILE_SIZE)  == x && (t.getY() / Artist.TILE_SIZE) == y) {
				return true;
			}
		}
		return false;
	}
	
	public void cashAndLivesUpdate() {
		menuUI.setLabel(0, lives + " live(s)");
		menuUI.setLabel(1, "$" + cash);
	}
	
	public void update() {
		for (Tower t : towerList) { //Actualise toutes les tours
			t.update();
			t.updateEnemyList(waveManager.getCurrentWave().getEnemyList());
		}
		cashAndLivesUpdate();
		menuUI.drawLabels();

		//Handle mouse inputs
		if (Mouse.isButtonDown(0) && !UserInput.leftMouseButtonDown) {
			if (isBuildable(Mouse.getX(), Mouse.getY()) && !tileHasTower(Mouse.getX(), Mouse.getY())) {
				towerList.add(new TowerBlue(TowerType.TowerBlue, grid.getTile((Mouse.getX() / Artist.TILE_SIZE), ((SCREENHEIGHT - Mouse.getY() - 1) / Artist.TILE_SIZE)), waveManager.getCurrentWave().getEnemyList()));
			}
		}
		
		if (Mouse.isButtonDown(1) && !UserInput.rightMouseButtonDown) {
			if (isBuildable(Mouse.getX(), Mouse.getY()) && !tileHasTower(Mouse.getX(), Mouse.getY())) {
				towerList.add(new TowerBlue(TowerType.TowerRed, grid.getTile((Mouse.getX() / Artist.TILE_SIZE), ((SCREENHEIGHT - Mouse.getY() - 1) / Artist.TILE_SIZE)), waveManager.getCurrentWave().getEnemyList()));
			}
		}

		//Handle keyboard inputs
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_T && Keyboard.getEventKeyState()) {
				if (isBuildable(Mouse.getX(), Mouse.getY()) && !tileHasTower(Mouse.getX(), Mouse.getY())) {
					towerList.add(new TowerCircle(TowerType.TowerCircle, grid.getTile((Mouse.getX() / Artist.TILE_SIZE), ((SCREENHEIGHT - Mouse.getY() - 1) / Artist.TILE_SIZE)), waveManager.getCurrentWave().getEnemyList()));
				}
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
				Clock.changeMultiplier(0.2f);
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState()) {
				Clock.changeMultiplier(-0.2f);
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE && Keyboard.getEventKeyState()) {
				StateManager.setState(GameState.MAINMENU);
			}
		}
	}
}
