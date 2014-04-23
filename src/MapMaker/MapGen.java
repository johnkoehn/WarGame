package MapMaker;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class MapGen {
	private static int map[][];
	private static int ySize;
	private static int xSize;
	private static Random rand = new Random();
	private static final int NUM_BIOS = 80;
	private static final int NUM_MOUNTAINS = 10;
	private static final int MOUNTAIN_THICK = 1;

	public static ArrayList<Integer> makeMap(int givenxSize, int givenySize) {
		ArrayList<Biome> list = new ArrayList<Biome>();
		ySize = givenySize;
		xSize = givenxSize;
		map = new int[ySize][xSize];
		ArrayList<Point> randPoints = genPoints(NUM_BIOS);
		int typeID;

		negMap();

		for (int i = 0; i < NUM_BIOS; i++) {
			int temp = rand.nextInt(20);
			if (temp <= 12) {
				typeID = 0; // grass
			} else if (temp <= 15) {
				typeID = 1; // sand
			} else {
				typeID = 2; // water
			}

			list.add(new Biome(((i * -1) - 2), randPoints.get(i), typeID));
			// biomeReserv(list.get(i));
		}

		// expands biomes
		for (int i = 0; i < list.size(); i++) {
			expandBiome(list.get(i));
			// fillReserve(list.get(i));
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
		return getMap();
	}

	private static void negMap() {
		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				map[y][x] = -1;
			}
		}
	}

	private static ArrayList<Point> genPoints(int amt) {
		ArrayList<Integer> x = new ArrayList<Integer>();
		ArrayList<Integer> y = new ArrayList<Integer>();
		ArrayList<Point> ps = new ArrayList<Point>();

		// create inital point
		int tempX = rand.nextInt(xSize);
		int tempY = rand.nextInt(ySize);
		x.add(tempX);
		y.add(tempY);
		ps.add(new Point(tempX, tempY));

		boolean notClose = true;
		int counter = 0;

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

			// adds new point if the point is not close to old one
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

		// incase of unable to place all biomes, overlap will then be allowed
		while (ps.size() < amt) {
			// System.out.println("Filled");
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

	private static boolean closeTo(int a, int b, int dist) {

		return Math.abs(a - b) < dist;
	}

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

	private static void expandBiome(Biome b) {
		ArrayList<Point> list;
		Point focus;
		Point expand;

		// initit seed point on map
		map[b.getSeed().y][b.getSeed().x] = b.getType();

		while (b.getCurrentSize() < b.AREA) {
			// list is now list of perimeter points
			list = getPerimeter(b);
			// System.out.println(list.size());
			if (list.size() == 0) {
				break;
			} else {
				focus = list.get(rand.nextInt(list.size()));
			}

			// list is now list of neighboring points
			list = getUseableNeighboor(focus, b.getID());
			expand = list.get(rand.nextInt(list.size()));

			map[expand.y][expand.x] = b.getType();
			b.addPoint(expand);
		}
	}

	private static ArrayList<Point> getPerimeter(Biome b) {
		ArrayList<Point> list = b.getNodes();

		for (int i = b.getNodes().size() - 1; i >= 0; i--) {
			if (!hasNeighboor(b.getNodes().get(i), b.getID())) {
				list.remove(i);
			}
		}
		return list;
	}

	private static boolean hasNeighboor(Point p, int altID) {
		// check up
		if (map[Math.max(0, p.y - 1)][p.x] == -1
				|| map[Math.max(0, p.y - 1)][p.x] == altID) {
			return true;
		}

		// check down
		if (map[Math.min(ySize - 1, p.y + 1)][p.x] == -1
				|| map[Math.min(ySize - 1, p.y + 1)][p.x] == altID) {
			return true;
		}

		// check left
		if (map[p.y][Math.max(0, p.x - 1)] == -1
				|| map[p.y][Math.max(0, p.x - 1)] == altID) {
			return true;
		}

		// check right
		if (map[p.y][Math.min(xSize - 1, p.x + 1)] == -1
				|| map[p.y][Math.min(xSize - 1, p.x + 1)] == altID) {
			return true;
		}

		return false;
	}

	private static ArrayList<Point> getUseableNeighboor(Point p, int altID) {
		ArrayList<Point> list = new ArrayList<Point>();

		// check up
		if (map[Math.max(0, p.y - 1)][p.x] == -1
				|| map[Math.max(0, p.y - 1)][p.x] == altID) {
			list.add(new Point(p.x, Math.max(0, p.y - 1)));
		}

		// check down
		if (map[Math.min(ySize - 1, p.y + 1)][p.x] == -1
				|| map[Math.min(ySize - 1, p.y + 1)][p.x] == altID) {
			list.add(new Point(p.x, Math.min(ySize - 1, p.y + 1)));
		}

		// check left
		if (map[p.y][Math.max(0, p.x - 1)] == -1
				|| map[p.y][Math.max(0, p.x - 1)] == altID) {
			list.add(new Point(Math.max(0, p.x - 1), p.y));
		}

		// check right
		if (map[p.y][Math.min(xSize - 1, p.x + 1)] == -1
				|| map[p.y][Math.min(xSize - 1, p.x + 1)] == altID) {
			list.add(new Point(Math.min(xSize - 1, p.x + 1), p.y));
		}

		return list;
	}

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

	private static void islandRemove(Point p) {
		// check up
		// if (map[Math.max(0, p.y - 1)][p.x] != map[p.y][p.x]) {
		// map[p.y][p.x] = map[Math.max(0, p.y - 1)][p.x];
		// } else {
		// map[p.y][p.x] = map[Math.max(0, p.y - 1)][p.x];
		// }

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

	private static void mapExpand() {
		// Random rand = new Random();
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

	private static void printMap() {
		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				System.out.print(map[y][x] + " ");
			}
			System.out.println();
		}
	}

	private static ArrayList<Integer> getMap() {
		ArrayList<Integer> mapList = new ArrayList<Integer>();

		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				mapList.add(map[y][x]);
			}
		}
		return mapList;
	}

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
}
