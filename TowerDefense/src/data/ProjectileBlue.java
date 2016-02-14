package data;

public class ProjectileBlue extends Projectile {

	public ProjectileBlue(ProjectileType type, Enemy target, float x, float y, int width, int height) {
		super(type, target, x, y, width, height);
	}
	
	@Override
	public void damage() {
		if (super.getTarget().getSpeed() > 1f) {
			super.getTarget().setSpeed((super.getTarget().getSpeed() / 5) * 4);
			super.damage();
		}
	}
}
