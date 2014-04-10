package Wargame;

import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;

public class MouseMonitor {
public MouseMonitor()
{}

public static Point getMousePosition()
{Vector2i position = Mouse.getPosition();
return new Point(position.x, position.y);
}
}
