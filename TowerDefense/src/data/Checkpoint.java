package data;

public class Checkpoint {
	private Tile tile;
	private int xDirection, yDirection;

	public Checkpoint(Tile tile, int xDirection, int yDirection) {
		this.tile = tile;
		this.xDirection = xDirection;
		this.yDirection = yDirection;
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public int getXDirection() {
		return xDirection;
	}

	public void setXDirection(int xDirection) {
		this.xDirection = xDirection;
	}

	public int getYDirection() {
		return yDirection;
	}

	public void setYDirection(int yDirection) {
		this.yDirection = yDirection;
	}

}
