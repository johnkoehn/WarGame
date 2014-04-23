package Wargame;

import java.util.ArrayList;
import java.util.Random;

//TODO Biomes

public class RandomMapGenerator {

	private static int[][] map;
	private static int ySize;
	private static int xSize;
	private static Random rand = new Random(2);
	private static final int CHANCE = 3;

	public static ArrayList<Integer> makeMap(int givenxSize, int givenySize) {

		ySize = givenySize;
		xSize = givenySize;
		map = new int[ySize][xSize];

		negMap();
		seedMap();

		while (!mapFull()) {
			mapExpand();
		}

		for (int i = 0; i < 30; i++) {
//			islandFixer();
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

	private static void seedMap() {
		int seeds = (int) (ySize * xSize * 0.3);
		Random rand = new Random();
		int temp = 0;
		int x = 0;
		int y = 0;

		for (int i = 0; i < seeds; i++) {
			temp = rand.nextInt(20);
			x = rand.nextInt(xSize);
			y = rand.nextInt(ySize);

			if (temp <= 7) { // 00-07 -> 8
				map[y][x] = 0; // grass
			} else if (temp <= 12) { // 08-12 -> 5
				map[y][x] = 1; // sand
			} else if (temp <= 16) { // 13-16 -> 4
				map[y][x] = 2; // water
			} else { // 17-19 -> 3
				map[y][x] = 3; // mountain
			}
		}
	}

	private static boolean mapFull() {
		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				if (map[y][x] == -1) {
					return false;
				}
			}
		}
		return true;
	}

	private static void mapExpand() {
		// Random rand = new Random();
		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {

				if (map[y][x] != -1 && rand.nextInt(10) < CHANCE) {

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

	private static void islandFixer() {
		// Random rand = new Random();

		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				// if (islandCheck(x, y)) {
				if (!surroundCheck(2, x, y)) {
					//removes island
					switch (rand.nextInt(4)) {
					case 0:
						map[y][x] = map[Math.max(0, y - 1)][x];
						break;
					case 1:
						map[y][x] = map[Math.min(ySize - 1, y + 1)][x];
						break;
					case 2:
						map[y][x] = map[y][Math.max(0, x - 1)];
						break;
					case 3:
						map[y][x] = map[y][Math.min(xSize - 1, x + 1)];
						break;
					}
				} else {
					if (surroundCheck(1, x, y)) {  
						//expands
						smallExpand(2, 3, x, y);
					}
				}
			}
		}
	}

	private static void smallExpand(int r, int cycles, int xStart, int yStart) {
		// Cycle
		for (int i = 0; i < cycles; i++) {
			// Coordinate
			for (int y = yStart - r; y < yStart + r + 1; y++) {
				for (int x = xStart - r; x < xStart + r + 1; x++) {
					// Bound
					if (x < xSize && x >= 0 && y < ySize && y >= 0
							&& x != xStart && y != yStart) {
						// Point Check
						if (map[y][x] != map[yStart][xStart]
								&& rand.nextInt(10) < CHANCE) {
							map[y][x] = map[yStart][xStart];
						}
					}
				}
			}
		}

	}

	@SuppressWarnings("unused")
	private static boolean islandCheck(int x, int y) {
		boolean island = true;

		// check up
		if (map[Math.max(0, y - 1)][x] == map[y][x]) {
			island = false;
		}

		// check down
		if (map[Math.min(ySize - 1, y + 1)][x] == map[y][x]) {
			island = false;
		}

		// check left
		if (map[y][Math.max(0, x - 1)] == map[y][x]) {
			island = false;
		}

		// check right
		if (map[y][Math.min(xSize - 1, x + 1)] == map[y][x]) {
			island = false;
		}
		return island;
	}

	private static boolean surroundCheck(int r, int xStart, int yStart) {
		boolean sameID = false;

		for (int y = yStart - r; y < yStart + r; y++) {
			for (int x = xStart - r; x < xStart + r + 1; x++) {
				// checks bounds
				if (x < xSize && x >= 0 && y < ySize && y >= 0 && x != xStart
						&& y != yStart) {
					// checks if same as start point
					if (map[y][x] == map[yStart][xStart]) {
						sameID = true;
					}
				}
			}
		}

		return sameID;

	}

	@SuppressWarnings("unused")
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
}
