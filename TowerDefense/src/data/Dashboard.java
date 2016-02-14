package data;

import static helpers.Artist.drawQuadTex;
import static helpers.Artist.quickLoad;

import org.newdawn.slick.opengl.Texture;

import helpers.Artist;

public class Dashboard {
	private Texture background;
	
	public Dashboard() {
		background = quickLoad("dashboard");
	}
	
	public void update() {
		drawQuadTex(background, Artist.SCREENWIDTH - Artist.DASHBOARDWIDTH, 0, Artist.DASHBOARDWIDTH, Artist.SCREENHEIGHT);
	}
}
