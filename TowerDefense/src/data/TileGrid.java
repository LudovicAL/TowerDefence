package data;

import helpers.Artist;

public class TileGrid {

	public Tile[][] map;
	private int tilesWide, tilesHigh;

	public TileGrid() {
		this.tilesWide = Artist.GAMEWIDTH / Artist.TILE_SIZE;
		this.tilesHigh = Artist.SCREENHEIGHT / Artist.TILE_SIZE;
		map = new Tile[tilesWide][tilesHigh];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = new Tile(i * Artist.TILE_SIZE, j * Artist.TILE_SIZE, Artist.TILE_SIZE, Artist.TILE_SIZE, TileType.Grass);
			}
		}
	}

	public TileGrid(int[][] newMap) {
		this.tilesWide = newMap[0].length;
		this.tilesHigh = newMap[1].length;
		map = new Tile[15][10];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				switch (newMap[j][i]) {
					case 0:
						map[i][j] = new Tile(i * Artist.TILE_SIZE, j * Artist.TILE_SIZE, Artist.TILE_SIZE, Artist.TILE_SIZE, TileType.Grass);
						break;
					case 1:
						map[i][j] = new Tile(i * Artist.TILE_SIZE, j * Artist.TILE_SIZE, Artist.TILE_SIZE, Artist.TILE_SIZE, TileType.Dirt);
						break;
					case 2:
						map[i][j] = new Tile(i * Artist.TILE_SIZE, j * Artist.TILE_SIZE, Artist.TILE_SIZE, Artist.TILE_SIZE, TileType.Water);
						break;
				}
			}
		}
	}

	public void setTile(int xCoord, int yCoord, TileType type) {
		map[xCoord][yCoord] = new Tile(xCoord * Artist.TILE_SIZE, yCoord * Artist.TILE_SIZE, Artist.TILE_SIZE, Artist.TILE_SIZE, type);
	}

	public Tile getTile(int xPlace, int yPlace) {
		if (xPlace < tilesWide && yPlace < tilesHigh && xPlace > -1 && yPlace > -1) {
			return map[xPlace][yPlace];
		} else {
			return new Tile(0, 0, 0, 0, TileType.NULL);
		}
	}

	public void draw() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j].draw();
			}
		}
	}

	public int getTilesWide() {
		return tilesWide;
	}

	public void setTilesWide(int tilesWide) {
		this.tilesWide = tilesWide;
	}

	public int getTilesHigh() {
		return tilesHigh;
	}

	public void setTilesHigh(int tilesHigh) {
		this.tilesHigh = tilesHigh;
	}

}
