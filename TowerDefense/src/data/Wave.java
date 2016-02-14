package data;

import java.util.concurrent.CopyOnWriteArrayList;

import static helpers.Clock.*;

public class Wave {
	private float timeSinceLastSpawn, spawnTime;
	private EnemyType enemyType;
	private CopyOnWriteArrayList<Enemy> enemyList;
	private int enemiesPerWave, enemiesSpawned, spawnXCoordinate, spawnYCoordinate;
	private boolean waveCompleted;
	private TileGrid grid;

	public Wave(WaveType type, TileGrid grid, int spawnXCoordinate, int spawnYCoordinate) {
		this.enemyType = type.type;
		this.spawnTime = type.spawnTime;
		this.enemiesPerWave = type.enemiesPerWave;
		this.enemiesSpawned = 0;
		this.timeSinceLastSpawn = type.spawnTime;
		this.enemyList = new CopyOnWriteArrayList<Enemy>();
		this.waveCompleted = false;
		this.grid = grid;
		this.spawnXCoordinate = spawnXCoordinate;
		this.spawnYCoordinate = spawnYCoordinate;
	}

	public void update() {
		boolean allEnemiesDead = true; //Suppose que tous les enemis sont morts jusqu'à ce qu'un enemi vivant soit trouvé
		if (enemiesSpawned < enemiesPerWave) {
			timeSinceLastSpawn += delta();
			if (timeSinceLastSpawn > spawnTime) {
				spawn();
				timeSinceLastSpawn = 0;
			}
		}
		for (Enemy e : enemyList) {
			if (e.isAlive()) {
				allEnemiesDead = false;
				e.update();
				e.draw();
			} else {
				enemyList.remove(e);
			}
		}
		if (allEnemiesDead) {
			waveCompleted = true;
		}
	}

	private void spawn() {
		enemyList.add(new Enemy(enemyType, grid.getTile(spawnXCoordinate, spawnYCoordinate), grid));
		enemiesSpawned++;
	}

	public boolean isCompleted() {
		return waveCompleted;
	}

	public CopyOnWriteArrayList<Enemy> getEnemyList() {
		return enemyList;
	}

}
