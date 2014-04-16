package Wargame;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;

public class MouseMonitor {
	public MouseMonitor() {
	}

	public static Point getMousePosition(RenderWindow window) {
		// Vector2i position = Mouse.getPosition();
		Vector2i position = Mouse.getPosition(window);
		Vector2f gridPoint = window.mapPixelToCoords(position);

		return new Point(gridPoint.x, gridPoint.y);
	}
}
