package data;

import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.drawQuadTex;
import static helpers.Artist.drawQuadTexScale;
import static helpers.Artist.drawQuadTexRotate;
import static helpers.Artist.quickLoad;
import static helpers.Clock.delta;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Tower implements Entity {

	private float x, y, timeSinceLastShot, firingSpeed, angle;
	private int width, height, range;
	public Enemy target;
	private Texture[] texture;
	private CopyOnWriteArrayList<Enemy> enemies;
	private boolean targeted, selected;
	public ArrayList<Projectile> projectilesList;
	public TowerType type;
	
	public Tower(TowerType type, Tile startTile, CopyOnWriteArrayList<Enemy> enemies) {
		this.type = type;
		this.texture = type.texture;
		this.range = type.range;
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = startTile.getWidth();
		this.height = startTile.getHeight();
		this.enemies = enemies;
		this.targeted = false;
		this.selected = false;
		this.timeSinceLastShot = 0f;
		this.projectilesList = new ArrayList<Projectile>();
		this.firingSpeed = type.firingSpeed;
		this.angle = 0f;
	}
	
	private Enemy acquireTarget() {
		//POUR TROUVER L'ENEMI LE PLUS PRÈS DE LA TOUR
		Enemy closest = null;
		float closestDistance = Float.MAX_VALUE;
		for (Enemy e: enemies) {
			if (findDistance(e) < closestDistance && e.isAlive()) {
				closestDistance = findDistance(e);
				closest = e;
			}
		}
		if (closest != null) {
			targeted = true;
		}
		return closest;
		//POUR TROUVER LE PREMIER ENEMI DE LA WAVE
		//return enemies.get(0);
	}
	
	private boolean isInRange(Enemy e) {
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);
		if (xDistance < range && yDistance < range) {
			return true;
		}
		return false;
	}
	
	private float findDistance(Enemy e) {
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);
		return xDistance + yDistance;
	}

	private float calculateAngle() {
		double angleTemp = Math.atan2(target.getY() - y, target.getX() - x);
		float desiredAngle = (float) Math.toDegrees(angleTemp) - 90;
		if (angle > desiredAngle + 4) {
			return angle - 3f;
		} else if (angle < desiredAngle - 4) {
			return angle + 3f;
		} else {
			return desiredAngle;
		}
	}
	
	public abstract void shoot(Enemy target);
	
	public void updateEnemyList(CopyOnWriteArrayList<Enemy> newList) {
		enemies = newList;
	}
	
	public void update() {
		if (!targeted) {
			target = acquireTarget();
		} else {
			angle = calculateAngle();
			if (isInRange(target)) {
				if (timeSinceLastShot > firingSpeed) {
					shoot(target);
					timeSinceLastShot = 0;
				}
			} else {
				targeted = false;
			}
		}
		if (target == null || target.isAlive() == false) {
			targeted = false;
		}
		
		timeSinceLastShot += delta();
		
		for (int i = 0; i < projectilesList.size(); i++) {
			if (projectilesList.get(i).getAlive()) {
				projectilesList.get(i).update();
			} else {
				projectilesList.remove(i);
			}
		}
		draw();
	}

	public void draw() {
		drawQuadTex(texture[0], x, y, width, height);
		if (texture.length > 1) {
			for (int i = 1; i < texture.length; i++) {
				drawQuadTexRotate(texture[i], x, y, width, height, angle);
			}			
		}
		if (selected) {
			drawQuadTexScale(quickLoad("cercle"), x, y, 128, 128, (float)range);
		}
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public Enemy getTarget() {
		return target;
	}

}
