package ui;

import org.newdawn.slick.opengl.Texture;

public class Button {
	private String name, label;
	private Texture textureOn, textureOff;
	private int buttonX, buttonY, labelX, labelY, width, height;
	private boolean hovered;

	public Button(String name, String label, Texture textureOn, Texture textureOff, int buttonX, int buttonY, int width, int height) {
		this.name = name;
		this.label = label;
		this.textureOn = textureOn;
		this.textureOff = textureOff;
		this.buttonX = buttonX;
		this.buttonY = buttonY;
		this.labelX = buttonX + 6;
		this.labelY = buttonY + 12;
		this.width = width;
		this.height = height;
		this.hovered = false;
	}

	public Button(String name, String label, ButtonType type, int buttonX, int buttonY) {
		this.name = name;
		this.label = label;
		this.textureOn = type.textureOn;
		this.textureOff = type.textureOff;
		this.buttonX = buttonX;
		this.buttonY = buttonY;
		this.labelX = buttonX + 6;
		this.labelY = buttonY + 12;
		this.width = textureOn.getImageWidth();
		this.height = textureOn.getImageHeight();
		this.hovered = false;
	}

	public String getName() {
		return name;
	}

	public boolean isHovered() {
		return hovered;
	}

	public void setHovered(boolean hovered) {
		this.hovered = hovered;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getButtonX() {
		return buttonX;
	}

	public void setButtonX(int buttonX) {
		this.buttonX = buttonX;
	}

	public int getButtonY() {
		return buttonY;
	}

	public void setButtonY(int buttonY) {
		this.buttonY = buttonY;
	}

	public int getLabelX() {
		return labelX;
	}

	public void setLabelX(int labelX) {
		this.labelX = labelX;
	}

	public int getLabelY() {
		return labelY;
	}

	public void setLabelY(int labelY) {
		this.labelY = labelY;
	}

	public Texture getTextureOn() {
		return textureOn;
	}

	public void setTextureOn(Texture textureOn) {
		this.textureOn = textureOn;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Texture getTextureOff() {
		return textureOff;
	}

	public void setTextureOff(Texture textureOff) {
		this.textureOff = textureOff;
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

}
