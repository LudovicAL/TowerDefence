package data;

import helpers.StateManager;
import helpers.StateManager.GameState;

public class WaveManager {
	private float timeSinceLastWave;
	private int waveNumber;
	private Wave currentWave;
	private TileGrid grid;

	public WaveManager(TileGrid grid) {
		this.timeSinceLastWave = 0;
		this.waveNumber = 0;
		this.currentWave = null;
		this.grid = grid;
		newWave();
	}

	public void update() {
		if (!currentWave.isCompleted()) {
			currentWave.update();
		} else if (waveNumber < WaveType.values().length) {
			newWave();
		} else {
			victory();
		}
	}

	private void newWave() {
		currentWave = new Wave(WaveType.values()[waveNumber], grid, 2, 2);
		waveNumber++;
		System.out.println("Begining wave " + waveNumber);
	}

	public Wave getCurrentWave() {
		return currentWave;
	}
	
	private void victory() {
		StateManager.setState(GameState.MAINMENU);
	}

}
