package data;

import java.util.concurrent.CopyOnWriteArrayList;

public class TowerRed extends Tower {

	public TowerRed(TowerType type, Tile startTile, CopyOnWriteArrayList<Enemy> enemies) {
		super(type, startTile, enemies);
	}
	
	@Override
	public void shoot(Enemy target) {
		super.projectilesList.add(new ProjectileRed(super.type.projectileType, super.target, super.getX(), super.getY()));
		SoundType.playSound(SoundType.Shoot);
	}
	
}