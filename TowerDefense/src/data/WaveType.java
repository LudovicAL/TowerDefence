package data;

public enum WaveType {

	Wave1(EnemyType.EnemyFire, 3, 1.0f),
	Wave2(EnemyType.EnemyWater, 4, 1.5f),
	Wave3(EnemyType.EnemyAir, 5, 1.0f),
	Wave4(EnemyType.EnemyEarth, 6, 0.5f);
	
	EnemyType type;
	int enemiesPerWave;
	float spawnTime;
	
	WaveType(EnemyType type, int enemiesPerWave, float spawnTime) {
		this.type = type;
		this.enemiesPerWave = enemiesPerWave;
		this.spawnTime = spawnTime;
	}
}