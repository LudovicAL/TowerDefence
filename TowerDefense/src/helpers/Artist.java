package helpers;

import java.io.IOException;
import java.io.InputStream;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import static org.lwjgl.opengl.GL11.*;


public class Artist {

	public static final int SCREENWIDTH = 1024;
	public static final int SCREENHEIGHT = 640;
	public static final int GAMEWIDTH = 896;
	public static final int DASHBOARDWIDTH = 128;
	public static final int TILE_SIZE = 64;

	public static void initiateDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(SCREENWIDTH, SCREENHEIGHT));
			Display.setTitle("MiniTour");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, SCREENWIDTH, SCREENHEIGHT, 0, 1, -1);
		Display.setResizable(false);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glDisable(GL_DEPTH_TEST);
		glClearColor(0f, 0f, 0f, 0f);
		glColorMaterial(GL_FRONT, GL_DIFFUSE);
		glEnable(GL_COLOR_MATERIAL);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}

	public static boolean checkCollision(float x1, float y1, float width1, float height1, float x2, float y2, float width2, float height2) {
		if (x1 + width1 > x2 && x1 < x2 + width2 && y1 + height1 > y2 && y1 < y2 + height2) {
			return true;
		}
		return false;
	}

	public static boolean checkOnGameScreen(float x, float y, float width, float height) {
		if ((y + height < 0) || (y > SCREENHEIGHT) || (x + width < 0) || (x > GAMEWIDTH)) { //Si l'objet est trop à droite en x
			return false;
		} else {
			return true;
		}
	}

	public static void drawQuad(float x, float y, float width, float height) {
		glPushMatrix();
		glBegin(GL_QUADS);
		glVertex2f(x, y); //Coin supérieur gauche
		glVertex2f(x + width, y); //Coin supérieur droit
		glVertex2f(x + width, y + height); //Coin inférieur droit
		glVertex2f(x, y + height); //Coin inférieur gauche
		glEnd();
		glPopMatrix();
	}

	public static void drawQuadTex(Texture tex, float x, float y, float width, float height) {
		glPushMatrix();
		tex.bind();
		glTranslatef(x, y, 0);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0, 0); //Coin supérieur gauche
		glTexCoord2f(1, 0);
		glVertex2f(width, 0); //Coin supérieur droit
		glTexCoord2f(1, 1);
		glVertex2f(width, height); //Coin inférieur droit
		glTexCoord2f(0, 1);
		glVertex2f(0, height); //Coin inférieur gauche
		glEnd();
		glPopMatrix();
		glLoadIdentity();
	}
	
	public static void drawQuadTexScale(Texture tex, float x, float y, float width, float height, float range) {
		float multiplier = range / width;
		glPushMatrix();
		tex.bind();
		glTranslatef(x - ((range / 2) - (TILE_SIZE / 2)), y - ((range / 2) - (TILE_SIZE / 2)), 0);
		glScalef(multiplier, multiplier, 0);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0, 0); //Coin supérieur gauche
		glTexCoord2f(1, 0);
		glVertex2f(width, 0); //Coin supérieur droit
		glTexCoord2f(1, 1);
		glVertex2f(width, height); //Coin inférieur droit
		glTexCoord2f(0, 1);
		glVertex2f(0, height); //Coin inférieur gauche
		glEnd();
		glPopMatrix();
		glLoadIdentity();
	}

	public static void drawQuadTexRotate(Texture tex, float x, float y, float width, float height, float angle) {
		glPushMatrix();
		tex.bind();
		glTranslatef(x + width / 2, y + height / 2, 0);
		glRotatef(angle, 0, 0, 1);
		glTranslatef(-width / 2, -height / 2, 0);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0, 0); //Coin supérieur gauche
		glTexCoord2f(1, 0);
		glVertex2f(width, 0); //Coin supérieur droit
		glTexCoord2f(1, 1);
		glVertex2f(width, height); //Coin inférieur droit
		glTexCoord2f(0, 1);
		glVertex2f(0, height); //Coin inférieur gauche
		glEnd();
		glPopMatrix();
		glLoadIdentity();
	}
	
	/*
	public static void drawFilledCircle(float x, float y, float radius) {
		int i, triangleAmount = 30;	//Nombre de triangles utilisés pour construire le cercle
		float twicePi = (float) (2.0f * Math.PI);	//radius = 0.8f;
		glPushAttrib(GL_CURRENT_BIT | GL_ONE_MINUS_SRC_ALPHA | GL_COLOR_BUFFER_BIT | GL_COLOR_MATERIAL);
		glPushMatrix();
		glColor4f(0.0f, 0.0f, 0.0f, 0.2f);
		glBegin(GL_TRIANGLE_FAN);
			glVertex2f(x, y);
			for(i = 0; i <= triangleAmount;i++) { 
				glVertex2f(
					x + (float)(radius * Math.cos(i *  twicePi / triangleAmount)),
					y + (float)(radius * Math.sin(i * twicePi / triangleAmount))
				);
			}
		glEnd();
		glPopMatrix();
		glPopAttrib();
		glLoadIdentity();
		glFlush();
	}
	
	public static void drawHollowCircle(float x, float y, float radius){
		int i, lineAmount = 100; //Nombre de triangles utilisés pour construire le cercle
		float twicePi = (float) (2.0f * Math.PI);	//radius = 0.8f;
		glPushAttrib(GL_CURRENT_BIT | GL_ONE_MINUS_SRC_ALPHA | GL_COLOR_BUFFER_BIT | GL_COLOR_MATERIAL);
		glPushMatrix();
		glLineWidth(5.0F);
		glColor3f(0.5f, 0.5f, 1.0f);
		glBegin(GL_LINE_LOOP);
			for(i = 0; i <= lineAmount;i++) { 
				glVertex2f(
					x + (float)(radius * Math.cos(i *  twicePi / lineAmount)), 
					y + (float)(radius * Math.sin(i * twicePi / lineAmount))
				);
			}
		glEnd();
		glPopMatrix();
		glPopAttrib();
		glLoadIdentity();
		glFlush();
	}
	*/

	public static Texture loadTexture(String path, String fileType) {
		Texture tex = null;
		InputStream in = ResourceLoader.getResourceAsStream(path);
		try {
			tex = TextureLoader.getTexture(fileType, in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tex;
	}

	public static Texture quickLoad(String name) {
		Texture tex = null;
		tex = loadTexture("res/" + name + ".png", "PNG");
		return tex;
	}
}
