package data;

public class ProjectileRed extends Projectile {

	public ProjectileRed(ProjectileType type, Enemy target, float x, float y) {
		super(type, target, x, y, type.width, type.height);
	}
}