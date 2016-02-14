package data;

public class ProjectileCircle extends Projectile {

	public ProjectileCircle(ProjectileType type, Enemy target, float x, float y) {
		super(type, target, x, y, type.width, type.height);
	}
}