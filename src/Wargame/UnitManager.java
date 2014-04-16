package Wargame;

import java.io.IOException;
import java.util.ArrayList;

import org.jsfml.graphics.RenderWindow;

public class UnitManager {
	// Function:
	// Tracks units for each player
	// Puts all units for given player in an array list
	// Need to be able to add unit
	private ArrayList<ActiveUnit> player;

	public UnitManager() {
		player = new ArrayList<ActiveUnit>();
	}

	/**
	 * Create a new active unit and add it to the player's army.
	 * 
	 * @param player
	 *            player to add unit to
	 * @param unit
	 *            unit to create an active unit out of
	 * @param xpos
	 *            X position to add unit to
	 * @param ypos
	 *            Y position to add unit to
	 * @param texture
	 *            The texture for the unit
	 * @throws IOException
	 */
	public void addUnit(int playerPosition, Unit unit, float xpos, float ypos,
			String texture) throws IOException {
		
		ActiveUnit temp = new ActiveUnit(unit, xpos, ypos, texture);
//		player.get(0);
		player.add(temp);
	}

	/**
	 * Remove unit from player's army.
	 * 
	 * @param player
	 *            player to remove unit from
	 * @param a
	 *            unit to be removed
	 */
//	public void deleteUnit(int player, ActiveUnit a) {
//		player.remove(a);
//	}

	/**
	 * Todo: Add a function that checks every player's team for units with 0 HP
	 * and removes them. This should be called at the end of every action that
	 * deducts units' health.
	 */
	public void removeDead() {
	}

	public void draw(RenderWindow window) {
		// draw all the active units
		for (int i = 0; i < player.size(); i++) {
			window.draw(player.get(i).getActor());
		}
	}
	
	public ActiveUnit getUnit(int index)
	{
		if (index > player.size())
		{
			System.out.println("Out of bounds for getUnit index, returning first unit");
			return player.get(0);
		}
		
		return player.get(index);
	}
	
	public int getLength()
	{
		return player.size();
	}

}
