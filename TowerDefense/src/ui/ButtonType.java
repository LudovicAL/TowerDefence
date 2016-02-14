package ui;

import static helpers.Artist.quickLoad;

import org.newdawn.slick.opengl.Texture;

public enum ButtonType {
	ButtonPlay(quickLoad("buttonplayon"), quickLoad("buttonplayoff")),
	ButtonEditor(quickLoad("buttoneditoron"), quickLoad("buttoneditoroff")),
	ButtonQuit(quickLoad("buttonquiton"), quickLoad("buttonquitoff")),
	ButtonEmpty(quickLoad("buttonemptyon"), quickLoad("buttonemptyoff"));
	
	Texture textureOn;
	Texture textureOff;
	
	ButtonType(Texture textureOn, Texture textureOff) {
		this.textureOn = textureOn;
		this.textureOff = textureOff;
	}
}
