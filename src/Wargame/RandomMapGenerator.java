package Wargame;

import java.util.ArrayList;
import java.util.Random;

public class RandomMapGenerator {

	private static int[][] map;
	private static int ySize;
	private static int xSize;

	public static ArrayList<Integer> makeMap(int givenxSize, int givenySize) {

		ySize = givenySize;
		xSize = givenySize;
		map = new int[ySize][xSize];

		negMap();
		seedMap();

		while (!mapFull()) {
			mapExpand();
		}

		for (int i = 0; i < 3; i++) {
			islandFixer();
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
		int seeds = (int) (ySize * xSize * 0.15);
		Random rand = new Random();
		int temp = 0;
		int x = 0;
		int y = 0;

		for (int i = 0; i < seeds; i++) {
			temp = rand.nextInt(20);
			x = rand.nextInt(xSize);
			y = rand.nextInt(ySize);

			if (temp <= 10) {
				map[y][x] = 0; // grass
			} else if (temp <= 14) {
				map[y][x] = 1; // sand
			} else if (temp <= 17) {
				map[y][x] = 2; // water
			} else if (temp <= 19) {
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
		Random rand = new Random();
		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {

				if (map[y][x] != -1
						&& (rand.nextBoolean() && rand.nextBoolean())) {

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
		boolean island = true;
		Random rand = new Random();

		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				if (islandCheck(x, y)) {
					switch (rand.nextInt(4)) {
					case 0:
						map[Math.max(0, y - 1)][x] = map[y][x];
						break;
					case 1:
						map[Math.min(ySize - 1, y + 1)][x] = map[y][x];
						break;
					case 2:
						map[y][Math.max(0, x - 1)] = map[y][x];
						break;
					case 3:
						map[y][Math.min(xSize - 1, x + 1)] = map[y][x];
						break;
					case 4:
						break;
					}
				}
			}
		}
	}

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

	private static void surrCheck() {

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
}
