package data;

import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;
import static helpers.Clock.*;

import java.util.ArrayList;

public class Enemy implements Entity {
	private int width, height, currentCheckpoint;
	private float speed, x, y, health, maxHealth;
	private Texture texture, healthBackground, healthForeground, healthBorder;
	private Tile startTile;
	private boolean first, alive;
	private TileGrid grid;
	private ArrayList<Checkpoint> checkpoints;
	private int[] directions;

	public Enemy(EnemyType type, Tile startTile, TileGrid grid) {
		this.texture = type.texture;
		this.healthBackground = quickLoad("healthbackground");
		this.healthForeground = quickLoad("healthforeground");
		this.healthBorder = quickLoad("healthborder");
		this.startTile = startTile;
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = type.width;
		this.height = type.height;
		this.speed = type.speed;
		this.health = type.health;
		this.maxHealth = health;
		this.grid = grid;
		this.first = true;
		this.alive = true;
		this.checkpoints = new ArrayList<Checkpoint>();
		this.directions = new int[2];
		this.directions[0] = 0; //X direction
		this.directions[1] = 0; //Y direction
		this.directions = findNextD(startTile);
		this.currentCheckpoint = 0;
		populateCheckpointList();
	}

	public void update() {
		if (first) { //Permet d'éviter le premier passage à travers cette méthode
			first = false;
		} else {
			if (checkpointReached()) { //Parcourt tous les Checkpoints jusqu'au dernier
				if (currentCheckpoint + 1 == checkpoints.size()) {
					endOfMazeReached();
				} else {
					currentCheckpoint++;
				}
			} else { //Si l'enemi n'est pas à un Checkpoint, il continu sa course sans changer de direction
				x += delta() * checkpoints.get(currentCheckpoint).getXDirection() * speed;
				y += delta() * checkpoints.get(currentCheckpoint).getYDirection() * speed;
			}
		}
	}

	private void endOfMazeReached() { //Est appelé lorsqu'un enemi atteint le dernier Checkpoint
		Player.modifyLives(-1);
		die();
	}
	
	private boolean checkpointReached() {
		boolean reached = false;
		Tile t = checkpoints.get(currentCheckpoint).getTile();
		//On vérifie si la position a atteint la case (à trois pixel près)
		if (x > t.getX() - 10 && x < t.getX() + 10 && y > t.getY() - 10 && y < t.getY() + 10) {
			reached = true;
			x = t.getX();
			y = t.getY();
		}
		return reached;
	}

	private void populateCheckpointList() {
		checkpoints.add(findNextC(startTile, directions = findNextD(startTile))); //Le premier Checkpoint est ajouté manuellement, basé sur la StartTile
		int counter = 0;
		boolean cont = true;

		while (cont) {
			int[] currentD = findNextD(checkpoints.get(counter).getTile());
			//On vérifie si 
			if (currentD[0] == 2 || counter == 20) {
				cont = false;
			} else {
				checkpoints.add(findNextC(checkpoints.get(counter).getTile(), directions = findNextD(checkpoints.get(counter).getTile())));
			}
			counter++;
		}
	}

	private Checkpoint findNextC(Tile s, int[] dir) { //Méthode retournant le prochain Checkpoint (c)
		Tile next = null;
		Checkpoint c = null;

		boolean found = false; //Si oui, le prochain checkpoint est trouvé, si non, le prochain checkpoint n'est pas encore trouvé
		int counter = 1;

		while (!found) {
			if (s.getXPlace() + dir[0] * counter == grid.getTilesWide() || s.getYPlace() + dir[1] * counter == grid.getTilesHigh() || s.getType() != grid.getTile(s.getXPlace() + dir[0] * counter, s.getYPlace() + dir[1] * counter).getType()) {
				found = true;
				counter -= 1; //Le counter est ramené de 1 pour retourner une case en arrière
				next = grid.getTile(s.getXPlace() + dir[0] * counter, s.getYPlace() + dir[1] * counter);
			}
			counter++;
		}
		c = new Checkpoint(next, dir[0], dir[1]);
		return c;
	}

	private int[] findNextD(Tile s) { //Méthode retournant le type de la prochaine case vers laquelle s'avance enemi(s)
		int[] dir = new int[2];
		Tile u = grid.getTile(s.getXPlace(), s.getYPlace() - 1);//L'enemi peut-il bouger vers le haut?
		Tile r = grid.getTile(s.getXPlace() + 1, s.getYPlace());//L'enemi peut-il bouger vers la droite?
		Tile d = grid.getTile(s.getXPlace(), s.getYPlace() + 1);//L'enemi peut-il bouger vers le bas?
		Tile l = grid.getTile(s.getXPlace() - 1, s.getYPlace());//L'enemi peut-il bouger vers la gauche?
		if (s.getType() == u.getType() && directions[1] != 1) {
			dir[0] = 0;
			dir[1] = -1;
		} else if (s.getType() == r.getType() && directions[0] != -1) {
			dir[0] = 1;
			dir[1] = 0;
		} else if (s.getType() == d.getType() && directions[1] != -1) {
			dir[0] = 0;
			dir[1] = 1;
		} else if (s.getType() == l.getType() && directions[0] != 1) {
			dir[0] = -1;
			dir[1] = 0;
		} else {
			dir[0] = 2;
			dir[1] = 2;
		}
		return dir;
	}

	public void damage(int amount) { //Appelé lorsque l'enemi reçoit des dommages
		health  -= amount;
		if (health <= 0) {
			die();
			Player.modifyCash(5);
		}
	}
	
	private void die() { //Appelé lorsque l'enemi meurt
		alive = false;
	}

	public void draw() {
		float healthPercentage = health / maxHealth;
		//Enemy texture:
		drawQuadTex(texture, x, y, width, height);
		//Healthbar textures:
		drawQuadTex(healthBackground, x, y - 16, width, 8);
		drawQuadTex(healthForeground, x, y - 16, TILE_SIZE * healthPercentage, 8);
		drawQuadTex(healthBorder, x, y - 16, width, 8);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Tile getStartTile() {
		return startTile;
	}

	public void setStartTile(Tile startTile) {
		this.startTile = startTile;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public TileGrid getTileGrid() {
		return grid;
	}

	public boolean isAlive() {
		return alive;
	}

}
