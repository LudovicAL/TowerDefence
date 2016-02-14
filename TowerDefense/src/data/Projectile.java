package data;

import org.newdawn.slick.opengl.Texture;

import helpers.Artist;

import static helpers.Clock.*;
import static helpers.Artist.*;

public abstract class Projectile implements Entity {

	private Texture texture;
	private float x, y, speed, xVelocity, yVelocity;
	private int damage, width, height;
	private Enemy target;
	private boolean alive;

	public Projectile(ProjectileType type, Enemy target, float x, float y, int width, int height) {
		this.texture = type.texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = type.speed;
		this.damage = type.damage;
		this.target = target;
		this.alive = true;
		this.xVelocity = 0f;
		this.yVelocity = 0f;
		calculateDirection();			
	}

	private void calculateDirection() {
		float totalAllowedMovement = 1.0f;
		float xDistanceFromTarget = Math.abs(target.getX() - x + (Artist.TILE_SIZE / 4));
		float yDistanceFromTarget = Math.abs(target.getY() - y + (Artist.TILE_SIZE / 4));
		float totalDistanceFromTarget = xDistanceFromTarget + yDistanceFromTarget;
		float xPercentOfMovement = xDistanceFromTarget / totalDistanceFromTarget;
		xVelocity = xPercentOfMovement;
		yVelocity = totalAllowedMovement - xPercentOfMovement;
		if (target.getX() < x) { //Défini la direction du projectile en fonction de la position de l'enemi relativement à la tour
			xVelocity *= -1;
		}
		if (target.getY() < y) {
			yVelocity *= -1;
		}
	}

	public void damage() { //Inflige des dégats aux ennemis
		target.damage(damage);
		die();
	}
	
	public void die() {
		alive = false;
	}
	
	public void update() {
		x += xVelocity * speed * delta();
		y += yVelocity * speed * delta();
		if (checkCollision(x, y, width, height, target.getX(), target.getY(), target.getWidth(), target.getHeight())) {
			damage();
		}
		if (!checkOnGameScreen(x, y, width, height)) {
			die();
		}
		draw();
	}

	public void draw() {
		drawQuadTex(texture, x, y, 32, 32);
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
	
	public void setAlive(boolean status) {
		alive = status;
	}
	
	public boolean getAlive() {
		return alive;
	}
	
}
