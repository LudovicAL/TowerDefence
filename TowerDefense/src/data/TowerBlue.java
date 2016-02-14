package data;

import java.util.concurrent.CopyOnWriteArrayList;

public class TowerBlue extends Tower {

	public TowerBlue(TowerType type, Tile startTile, CopyOnWriteArrayList<Enemy> enemies) {
		super(type, startTile, enemies);
	}
	
	@Override
	public void shoot(Enemy target) {
		super.projectilesList.add(new ProjectileBlue(super.type.projectileType, super.target, super.getX(), super.getY(), 32, 32));
		SoundType.playSound(SoundType.Pew);
	}
	
}
