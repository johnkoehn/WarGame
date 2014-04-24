package Wargame;

import java.io.IOException;

import org.jsfml.graphics.RenderWindow;

public class Reticule {
	private Actor r;

	public Reticule(Point p) throws IOException {

		r = new Actor(p, "./Reti.png");
	}

	public void draw(RenderWindow window) {

		r.updatePoint(MouseMonitor.getMousePosition(window));
		window.draw(r.getActor());

	}

}
