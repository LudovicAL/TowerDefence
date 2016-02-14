package helpers;

import org.lwjgl.input.Mouse;

public class UserInput {
	public static boolean leftMouseButtonDown;
	public static boolean rightMouseButtonDown;

	public static void updateMouse() {
		leftMouseButtonDown = Mouse.isButtonDown(0);
		rightMouseButtonDown = Mouse.isButtonDown(1);
	}
}
