package Wargame;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2i;
import org.jsfml.window.VideoMode;

public class UnitDisplay {
	private RenderWindow a;
	private RenderWindow main;
	private Font arial;

	// constructs a UnitDisplay based on the window passed (pass on the main window)
	public UnitDisplay(RenderWindow main) {
		a = new RenderWindow(new VideoMode(200, 300), "Unit");
		this.main = main;
		a.setPosition(new Vector2i(main.getPosition().x+815,main.getPosition().y));
		arial = new Font();
		try {
			arial.loadFromFile(Paths.get("arial.ttf"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// show the current window
	public void displayWindow() {
		a.display();
	}

	// clear the window of current info, draws the info of ActiveUnit passed in
	// parameter
	// for the love of all that is holy, you should not let me
	// do the graphical design of this
	public void drawUnitInfo(ActiveUnit b) {
		a.clear();
		a.setTitle(b.getGivenName());
		Text health = new Text("Health: " + b.getCurrentHealth() + "/"
				+ b.getTotalHealth(), arial, 18);
		Text ammo = new Text("Ammo: " + b.getCurrentAmmo(), arial, 18);
		Text fuel = new Text("Fuel: " + b.getCurrentFuel(), arial, 18);
		Text range = new Text("Range: " + b.getRange(), arial, 18);
		Text move = new Text("Move: " + b.getCurrentMovePoints(), arial, 18);
		health.setPosition(50, 250);
		ammo.setPosition(50, 200);
		fuel.setPosition(50, 150);
		range.setPosition(50, 100);
		move.setPosition(50, 50);
		a.draw(health);
		a.draw(ammo);
		a.draw(fuel);
		a.draw(range);
		a.draw(move);

	}

}
