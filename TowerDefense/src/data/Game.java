package data;

public class Game {

	private TileGrid grid;
	private Player player;
	private WaveManager waveManager;
	private Dashboard dashboard;
	
	public Game(TileGrid grid) {
		this.grid = grid;
		dashboard = new Dashboard();
		waveManager = new WaveManager(grid);
		player = new Player(grid, waveManager);
		player.setup(100, 10);
	}

	public void update() {
		grid.draw();
		waveManager.update();
		player.update();
		dashboard.update();
	}
}
