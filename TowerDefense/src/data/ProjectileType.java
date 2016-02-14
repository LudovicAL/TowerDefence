package data;

import static helpers.Artist.quickLoad;

import org.newdawn.slick.opengl.Texture;

public enum ProjectileType {

	ProjectileRed(quickLoad("bulletred"), 10, 600, 32, 32),
	ProjectileBlue(quickLoad("bulletblue"), 5, 800, 32, 32),
	ProjectileCircle(quickLoad("bulletcircle"), 1, 1000, 32, 32);
	
	Texture texture;
	int damage, width, height;
	float speed;
	
	ProjectileType(Texture texture, int damage, float Speed, int width, int height) {
		this.texture = texture;
		this.damage = damage;
		this.speed = Speed;
		this.width = width;
		this.height = height;
	}
}