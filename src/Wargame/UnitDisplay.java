package Wargame;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2i;
import org.jsfml.window.ContextActivationException;
import org.jsfml.window.VideoMode;

public class UnitDisplay {
	private RenderWindow a;
	private RenderWindow main;
	private Font arial;
	public static final Color RED = new Color(255,0,0);
	public static final Color YELLOW = new Color (255,255,0);
	public static final Color GREEN = new Color (0,255,0);
	

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
	public void displayWindow() throws ContextActivationException {
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
		Text ammo = new Text("Ammo: " + b.getCurrentAmmo() + "/" + b.getTotalAmmo(), arial, 18);
		Text fuel = new Text("Fuel: " + b.getCurrentFuel() + "/" + b.getTotalFuel(), arial, 18);
		Text range = new Text("Range: " + b.getRange(), arial, 18);
		Text move = new Text("Move: " + b.getCurrentMovePoints() + "/" + b.getTotalMovePoints(), arial, 18);
		health.setColor(GREEN);
		ammo.setColor(GREEN);
		fuel.setColor(GREEN);
		range.setColor(new Color(255,255,255));
		move.setColor(GREEN);
		
		if (b.getCurrentHealth() <= (b.getTotalHealth()*.5)&&(b.getCurrentHealth() > b.getTotalHealth()*.25))
		{health.setColor(YELLOW);}
		if (b.getCurrentHealth() <= b.getTotalHealth()*.25)
		{health.setColor(RED);}
		
		if (b.getCurrentAmmo() <= (b.getTotalAmmo()*.5)&&(b.getCurrentAmmo() > b.getTotalAmmo()*.25))
		{ammo.setColor(YELLOW);}
		if (b.getCurrentAmmo() <= b.getTotalAmmo()*.25)
		{ammo.setColor(RED);}
		
		if (b.getCurrentFuel() <= (b.getTotalFuel()*.5)&&(b.getCurrentFuel() > b.getTotalFuel()*.25))
		{fuel.setColor(YELLOW);}
		if (b.getCurrentFuel() <= b.getTotalFuel()*.25)
		{fuel.setColor(RED);}
		
		if (b.getCurrentMovePoints() <= (b.getTotalMovePoints()*.5)&&(b.getCurrentMovePoints() > b.getTotalMovePoints()*.25))
		{move.setColor(YELLOW);}
		if (b.getCurrentMovePoints() <= b.getTotalMovePoints()*.25)
		{move.setColor(RED);}
		
		
		
		
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
