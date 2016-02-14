package ui;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;

import static helpers.Artist.*;

import java.awt.Font;
import org.newdawn.slick.TrueTypeFont;

import data.SoundType;

public class UI {

	public ArrayList<Button> buttonList;
	private ArrayList<Label> labelList;
	private TrueTypeFont font;
	private Font awtFont;
	
	public UI() {
		buttonList = new ArrayList<Button>();
		labelList = new ArrayList<Label>();
		this.awtFont = new Font("Times New Roman", Font.BOLD, 28);
        this.font = new TrueTypeFont(awtFont, true);
	}

	public void update() {
		for (Button b: buttonList) {
			if (isButtonHovered(b.getName()) == true) {
				if (b.isHovered() == false) {
					SoundType.playSound(SoundType.Vloup);
				}
				b.setHovered(true);
			} else {
				b.setHovered(false);
			}
		}
		drawButtons();
	}
	
	public void addButton(String name, String text, ButtonType type, int x, int y) {
		buttonList.add(new Button(name, text, type, x, y));
	}
	
	public void addButton(String name, ButtonType type, int x, int y) {
		buttonList.add(new Button(name, "", type, x, y));
	}
	
	public void addLabel(String text, int x, int y) {
		labelList.add(new Label(text, x, y));
	}
	
	public void setLabel(int index, String text) {
		labelList.get(index).setText(text);
	}

	public boolean isButtonHovered(String buttonName) {
		Button b = getButton(buttonName);
		float mouseY = SCREENHEIGHT - Mouse.getY() - 1;
		if (Mouse.getX() > b.getButtonX() && Mouse.getX() < (b.getButtonX() + b.getWidth()) && mouseY > b.getButtonY() && mouseY < b.getButtonY() + b.getHeight()) {
			return true;
		}
		return false;
	}
	
	public String isAButtonHovered() {
		float mouseY = SCREENHEIGHT - Mouse.getY() - 1;
		for (Button b : buttonList) {
			if (Mouse.getX() > b.getButtonX() && Mouse.getX() < (b.getButtonX() + b.getWidth()) && mouseY > b.getButtonY() && mouseY < b.getButtonY() + b.getHeight()) {
				return b.getName();
			}
		}
		return null;
	}

	private Button getButton(String buttonName) {
		for (Button b : buttonList) {
			if (b.getName().equals(buttonName)) {
				return b;
			}
		}
		return null;
	}
	
	public void drawButtons() {
		for (Button b : buttonList) {
			if (b.isHovered() == true) {
				drawQuadTex(b.getTextureOn(), b.getButtonX(), b.getButtonY(), b.getWidth(), b.getHeight());
			} else {
				drawQuadTex(b.getTextureOff(), b.getButtonX(), b.getButtonY(), b.getWidth(), b.getHeight());
			}
			font.drawString(b.getLabelX(), b.getLabelY(), b.getLabel());
		}			
	}
	
	public void drawLabels() {
		for (Label l : labelList) {
			font.drawString(l.getX(), l.getY(), l.getText());
		}			
	}
}
