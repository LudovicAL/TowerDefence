package data;

import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;

public enum TowerType {

	TowerRed(new Texture[]{quickLoad("canonbasered"), quickLoad("canonheadred")}, ProjectileType.ProjectileRed, 7, 200, 3),
	TowerBlue(new Texture[]{quickLoad("canonbaseblue"), quickLoad("canonheadblue")}, ProjectileType.ProjectileBlue, 5, 300, 2),
	TowerCircle(new Texture[]{quickLoad("canonbasecircle"), quickLoad("canonheadcircle")}, ProjectileType.ProjectileCircle, 2, 400, 1);
	
	Texture[] texture;
	ProjectileType projectileType;
	int damage, range;
	float firingSpeed;
	
	TowerType(Texture[] texture, ProjectileType projectileType, int damage, int range, float firingSpeed) {
		this.texture = texture;
		this.projectileType = projectileType;
		this.damage = damage;
		this.range = range;
		this.firingSpeed = firingSpeed;
	}
}