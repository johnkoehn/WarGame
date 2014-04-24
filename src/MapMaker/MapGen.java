package MapMaker;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * MapGen is used in the creation of 2D Maps.
 * 
 * @author Alex
 * 
 */
public class MapGen {
	/**
	 * The map that is stored and returned
	 */
	private static int map[][];
	/**
	 * the maximum y
	 */
	private static int ySize;
	/**
	 * the maximum x
	 */
	private static int xSize;
	/**
	 * The Random variable used in the creation
	 */
	private static Random rand = new Random();
	/**
	 * The number of biome seeds to be placed initally
	 */
	private static final int NUM_BIOS = 120;
	/**
	 * The number of mountains to be placed
	 */
	private static final int NUM_MOUNTAINS = 10;

	/**
	 * Creates a Integer Array that is filled with the values for the types of
	 * terrain
	 * 
	 * @param givenxSize
	 * @param givenySize
	 * @return the map in Integer ArrayList
	 */
	public static ArrayList<Integer> makeMap(int givenxSize, int givenySize) {
		// the list of biomes
		ArrayList<Biome> list = new ArrayList<Biome>();

		// Initialize the sizes
		ySize = givenySize;
		xSize = givenxSize;
		// Initialize the map
		map = new int[ySize][xSize];
		// Generates a list of random points to place the biome seeds
		ArrayList<Point> randPoints = genPoints(NUM_BIOS);
		// The placeholder for the type ID with will be set to each biome
		int typeID;

		// Initilize map to -1 for defualt (0 is a type ID)
		negMap();

		// seeds the biomes and their types
		for (int i = 0; i < NUM_BIOS; i++) {
			// Randomly picks a Biome type ID
			int temp = rand.nextInt(20);
			if (temp <= 12) {
				typeID = 0; // grass
			} else if (temp <= 15) {
				typeID = 1; // sand
			} else {
				typeID = 2; // water
			}

			// adds biome to array
			list.add(new Biome(randPoints.get(i), typeID));
		}

		// expands biomes
		for (int i = 0; i < list.size(); i++) {
			expandBiome(list.get(i));
			;
		}

		// generate mountains
		for (int i = 0; i < NUM_MOUNTAINS; i++) {
			mountainLay();
		}

		// final Fill in
		while (!isMapFull()) {
			mapExpand();
		}

		// remove single tiles
		for (int i = 0; i < 5; i++) {
			for (int y = 0; y < ySize; y++) {
				for (int x = 0; x < xSize; x++) {
					Point p = new Point(x, y);
					if (islandCheck(p)) {
						islandRemove(p);
					}
				}
			}
		}

		// printMap();

		// return map
		return getMap();
	}

	/**
	 * Sets all values in map to -1 since 0 is used as a type ID
	 */
	private static void negMap() {
		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				map[y][x] = -1;
			}
		}
	}

	/**
	 * Generates a certain amount of points. Each point is no closer than 12
	 * units unless the point system cannot find a place that is that far away
	 * 
	 * @param amt
	 *            The number of points to generate
	 * @return the list of points
	 */
	private static ArrayList<Point> genPoints(int amt) {
		ArrayList<Integer> x = new ArrayList<Integer>();
		ArrayList<Integer> y = new ArrayList<Integer>();
		ArrayList<Point> ps = new ArrayList<Point>();

		// create initial point
		int tempX = rand.nextInt(xSize);
		int tempY = rand.nextInt(ySize);
		x.add(tempX);
		y.add(tempY);
		ps.add(new Point(tempX, tempY));

		boolean notClose = true;
		int counter = 0;

		// generate new point & check for closeness
		while (ps.size() < amt && counter < 100) {
			tempX = rand.nextInt(xSize);
			tempY = rand.nextInt(ySize);

			// checks if new values are close to the used values
			for (int i = 0; i < x.size(); i++) {
				if (closeTo(x.get(i), tempX, 12)
						&& closeTo(y.get(i), tempY, 12)) {
					notClose = false;
					break;
				}
			}

			// adds new point if the point is not close to any other one
			if (notClose) {
				x.add(tempX);
				y.add(tempY);

				ps.add(new Point(tempX, tempY));

				// reset counter for new try
				counter = 0;
			}

			// reset and update
			notClose = true;
			counter++;
		}

		// in case of unable to place all biomes, distance check is disabled
		// (but does not place them on same line)
		while (ps.size() < amt) {
			tempX = rand.nextInt(xSize);
			tempY = rand.nextInt(ySize);
			if (x.contains(tempX) && y.contains(tempY)) {
				x.add(tempX);
				y.add(tempY);

				ps.add(new Point(tempX, tempY));
			}
		}

		return ps;
	}

	/**
	 * Checks if the first parameter is near the second parameter in 1
	 * dimension, the distance determines if it is near or not
	 * 
	 * @param a
	 * @param b
	 * @param dist
	 * @return true if the values are with the given distance from each other
	 */
	private static boolean closeTo(int a, int b, int dist) {

		return Math.abs(a - b) < dist;
	}

	/**
	 * Checks if the map has been filled
	 * 
	 * @return true if the map is filled
	 */
	private static boolean isMapFull() {
		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				if (map[y][x] == -1) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Expands the Biome a number of units based on the the area the biome has.
	 * 
	 * @param b
	 */
	private static void expandBiome(Biome b) {
		ArrayList<Point> list;
		Point focus;
		Point expand;

		// Initialize seed point on map
		map[b.getSeed().y][b.getSeed().x] = b.getType();

		while (b.getCurrentSize() < b.AREA) {
			// list is now list of perimeter points
			list = getPerimeter(b);
			if (list.size() == 0) {
				break;
			} else {
				focus = list.get(rand.nextInt(list.size()));
			}

			// list is now list of neighboring points
			list = getUseableNeighboor(focus);
			expand = list.get(rand.nextInt(list.size()));

			map[expand.y][expand.x] = b.getType();
			b.addPoint(expand);
		}
	}

	/**
	 * Return the perimeter points of a Biome
	 * 
	 * @param b
	 *            the biome to check
	 * @return the list of points that are on the outside and near usable values
	 */
	private static ArrayList<Point> getPerimeter(Biome b) {
		ArrayList<Point> list = b.getNodes();

		for (int i = b.getNodes().size() - 1; i >= 0; i--) {
			if (!hasNeighboor(b.getNodes().get(i))) {
				list.remove(i);
			}
		}
		return list;
	}

	/**
	 * Checks to see if point has usable neighbor points
	 * 
	 * @param p
	 *            the point to check
	 * @param altID
	 *            the ID to include in the usable value
	 * @return true if there is a usable Neighbor
	 */
	private static boolean hasNeighboor(Point p) {
		// check up
		if (map[Math.max(0, p.y - 1)][p.x] == -1) {
			return true;
		}

		// check down
		if (map[Math.min(ySize - 1, p.y + 1)][p.x] == -1) {
			return true;
		}

		// check left
		if (map[p.y][Math.max(0, p.x - 1)] == -1) {
			return true;
		}

		// check right
		if (map[p.y][Math.min(xSize - 1, p.x + 1)] == -1) {
			return true;
		}

		return false;
	}

	/**
	 * Find the usable neighbors that surround the given point
	 * 
	 * @param p
	 *            the point to check
	 * @return the list of usable neighbors
	 */
	private static ArrayList<Point> getUseableNeighboor(Point p) {
		ArrayList<Point> list = new ArrayList<Point>();

		// check up
		if (map[Math.max(0, p.y - 1)][p.x] == -1) {
			list.add(new Point(p.x, Math.max(0, p.y - 1)));
		}

		// check down
		if (map[Math.min(ySize - 1, p.y + 1)][p.x] == -1) {
			list.add(new Point(p.x, Math.min(ySize - 1, p.y + 1)));
		}

		// check left
		if (map[p.y][Math.max(0, p.x - 1)] == -1) {
			list.add(new Point(Math.max(0, p.x - 1), p.y));
		}

		// check right
		if (map[p.y][Math.min(xSize - 1, p.x + 1)] == -1) {
			list.add(new Point(Math.min(xSize - 1, p.x + 1), p.y));
		}

		return list;
	}

	/**
	 * Checks to see if the point is not surrounded by others of the same type
	 * 
	 * @param p
	 *            the point to check
	 * @return true if it is an island, otherwise it is false
	 */
	private static boolean islandCheck(Point p) {

		// check up
		if (p.y - 1 >= 0) {
			if (map[p.y - 1][p.x] == map[p.y][p.x]) {
				return false;
			}
		}

		// check down
		if (p.y + 1 < ySize) {
			if (map[p.y + 1][p.x] == map[p.y][p.x]) {
				return false;
			}
		}

		// check left
		if (p.x - 1 >= 0) {
			if (map[p.y][p.x - 1] == map[p.y][p.x]) {
				return false;
			}
		}

		// check right
		if (p.x + 1 < xSize) {
			if (map[p.y][p.x + 1] == map[p.y][p.x]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Removes a tile by replacing it with the tile from above (or below if no
	 * tile above)
	 * 
	 * @param p
	 */
	private static void islandRemove(Point p) {
		// check up
		if (p.y - 1 >= 0) {
			if (map[p.y - 1][p.x] != map[p.y][p.x]) {
				map[p.y][p.x] = map[p.y - 1][p.x];
			}
		} else {// check down
			if (p.y + 1 < ySize) {
				if (map[p.y + 1][p.x] != map[p.y][p.x]) {
					map[p.y][p.x] = map[p.y + 1][p.x];
				}
			}
		}

	}

	/**
	 * creates Mountains. Number of mountains is determined by a global. The
	 * size of mountain is determined randomly
	 */
	private static void mountainLay() {
		// generate directions
		int xL = rand.nextInt(10) + rand.nextInt(10);
		rand.setSeed(rand.nextLong());
		int yL = rand.nextInt(10) + rand.nextInt(10);

		if (rand.nextBoolean()) {
			xL = xL * -1;
		}
		if (rand.nextBoolean()) {
			yL = yL * -1;
		}
		int xStart = rand.nextInt(xSize);
		int yStart = rand.nextInt(ySize);
		int xEnd;
		int yEnd;

		// determines the end of the x
		xEnd = xStart + xL;

		// determines the end of the y
		yEnd = yStart + yL;

		ArrayList<Point> list = Line.makeLine(new Point(xStart, yStart),
				new Point(xEnd, yEnd));

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).x < xSize && list.get(i).x >= 0
					&& list.get(i).y < ySize && list.get(i).y >= 0) {
				map[list.get(i).y][list.get(i).x] = 3;
			}
		}
	}

	/**
	 * Expands a tile of the map in all directions that there is a blank. Only
	 * expands 50% of the time to ensure the tile does not expand too far or
	 * consume other tiles
	 */
	private static void mapExpand() {
		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {

				if (map[y][x] != -1 && rand.nextBoolean()) {

					// check up
					if (map[Math.max(0, y - 1)][x] == -1) {
						map[Math.max(0, y - 1)][x] = map[y][x];
					}

					// check down
					if (map[Math.min(ySize - 1, y + 1)][x] == -1) {
						map[Math.min(ySize - 1, y + 1)][x] = map[y][x];
					}

					// check left
					if (map[y][Math.max(0, x - 1)] == -1) {
						map[y][Math.max(0, x - 1)] = map[y][x];
					}

					// check right
					if (map[y][Math.min(xSize - 1, x + 1)] == -1) {
						map[y][Math.min(xSize - 1, x + 1)] = map[y][x];
					}
				}

			}
		}
	}

	/**
	 * prints the map in easy to read colums
	 */
	@SuppressWarnings("unused")
	private static void printMap() {
		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				System.out.print(map[y][x] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Converts the 2D array into an arrayList
	 * 
	 * @return
	 */
	private static ArrayList<Integer> getMap() {
		ArrayList<Integer> mapList = new ArrayList<Integer>();

		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				mapList.add(map[y][x]);
			}
		}
		return mapList;
	}
}
