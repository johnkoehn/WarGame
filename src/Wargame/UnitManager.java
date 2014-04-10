package Wargame;

import java.io.IOException;
import java.util.ArrayList;

public class UnitManager {
	// Function:
	// Tracks units for each player
	// Puts all units for given player in an array list
	// Need to be able to add unit
	private ArrayList<ActiveUnit> player1;

	public UnitManager() {
	}

	/**
	 * Create a new active unit and add it to the player's army.
	 * @param player player to add unit to
	 * @param unit unit to create an active unit out of
	 * @param xpos X position to add unit to
	 * @param ypos Y position to add unit to
	 * @param texture The texture for the unit
	 * @throws IOException 
	 */
	public void addUnit(int player, Unit unit, float xpos, float ypos, String texture) throws IOException
	{ActiveUnit a = new ActiveUnit(unit,xpos,ypos,texture);
		player1.add(a);}
	/*
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
	public void addUnit(int player, Unit unit, float xpos, float ypos,
			String texture) throws IOException {
		ActiveUnit a = new ActiveUnit(unit, xpos, ypos, texture);
		player1.add(a);
	}

	/**
	 * Remove unit from player's army.
	 * 
	 * @param player
	 *            player to remove unit from
	 * @param a
	 *            unit to be removed
	 */
	public void deleteUnit(int player, ActiveUnit a) {
		player1.remove(a);
	}

	/**
	 * Todo: Add a function that checks every player's team for units with 0 HP
	 * and removes them. This should be called at the end of every action that
	 * deducts units' health.
	 */
	public void removeDead() {
	}

}
