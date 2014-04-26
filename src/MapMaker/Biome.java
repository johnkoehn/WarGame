package MapMaker;

import java.awt.Point;
import java.util.ArrayList;

/**
 * A Class to contain information about a biome for a 2D map
 * 
 * @author Alex
 * 
 */
public class Biome {

	/**
	 * the area the biome will take up
	 */
	public final int AREA = 30;
	/**
	 * the list of points in the biome
	 */
	private ArrayList<Point> node;
	/**
	 * the starting location of the biome
	 */
	private Point seed;
	/**
	 * the type of biome that is produced
	 */
	private int type;

	/**
	 * Constructs a Biome
	 * 
	 * @param start
	 *            the starting location for the biome
	 * @param givenType
	 *            the type of biome
	 */
	public Biome(Point start, int givenType) {
		node = new ArrayList<Point>();
		node.add(start);
		seed = start;
		type = givenType;
	}

	/**
	 * Adds a point to the node
	 * 
	 * @param p
	 *            the point to add
	 */
	public void addPoint(Point p) {
		node.add(p);
	}

	/**
	 * 
	 * @return the stating location
	 */
	public Point getSeed() {
		return seed;
	}

	/**
	 * 
	 * @return the requested node
	 */
	public ArrayList<Point> getNodes() {
		return node;
	}

	/**
	 * 
	 * @return the type of biome
	 */
	public int getType() {
		return type;
	}

	/**
	 * 
	 * @return number of nodes in the biome
	 */
	public int getCurrentSize() {
		return node.size();
	}
}
