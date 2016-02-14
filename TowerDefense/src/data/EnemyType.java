package data;

import static helpers.Artist.quickLoad;

import org.newdawn.slick.opengl.Texture;

public enum EnemyType {

	EnemyFire(quickLoad("enemy1"), 64, 64, 200, 15),
	EnemyWater(quickLoad("enemy2"), 64, 64, 210, 20),
	EnemyAir(quickLoad("enemy3"), 64, 64, 220, 25),
	EnemyEarth(quickLoad("enemy4"), 64, 64, 230, 30);
	
	Texture texture;
	int width, height;
	float speed, health, maxHealth;
	
	EnemyType(Texture texture, int width, int height, float Speed, float health) {
		this.texture = texture;
		this.speed = Speed;
		this.health = health;
		this.width = width;
		this.height = height;
	}
}