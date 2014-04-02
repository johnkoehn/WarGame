package Wargame;

public class Terrain {
	/**
	 * damage reduction from being on terrain
	 */
	private int defense;
	/**
	 * movement penalty for moving across terrain
	 */
	private int moveCost;
	/**
	 * number representing the type of terrain
	 */
	private int id;
	/**
	 * number representing the subtype of terrain (1 land or air,2 water or
	 * air,3 air only)
	 */

	private int color;

	private int subid;
	/**
	 * boolean representing the occupation of space
	 */
	private boolean occupied;

	/**
	 * Constructor
	 */
	public Terrain(int id, int subid, int color, int defense, int moveCost) {
		this.id = id;
		this.subid = subid;
		this.color = color;
		this.defense = defense;
		this.moveCost = moveCost;
		occupied = false;
	}

	/**
	 * returns the defense bonus for terrain
	 * 
	 * @return defense bonus
	 */
	public int getDefense() {
		return defense;
	}

	/**
	 * returns the move penalty for the terrain
	 * 
	 * @return move penalty
	 */
	public int getmoveCost() {
		return moveCost;
	}

	/**
	 * evaluates whether or not the space is occupied
	 * 
	 * @return whether or not unit is occupied
	 */
	public boolean isOccupied() {
		return occupied;
	}

	/**
	 * toggles the occupied boolean to true
	 */
	public void makeOccupied() {
		occupied = true;
	}

	/**
	 * returns the ID of the terrain
	 * 
	 * @return ID
	 */
	public int getID() {
		return id;
	}

	/**
	 * returns the sub-ID of the terrain
	 * 
	 * @return sub-ID
	 */
	public int getsubID() {
		return subid;
	}

	/**
	 * toggles the occupied boolean to false
	 */

	public void makeUnoccupied() {
		occupied = false;
	}

	public int getColor() {
		return color;
	}
}
