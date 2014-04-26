package MapMaker;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Line {

	private static final int UP = 0;
	private static final int DOWN = 1;
	private static final int RIGHT = 2;
	private static final int LEFT = 3;

	private static Random rand = new Random();

	private static Point b;

	public static ArrayList<Point> makeLine(Point c, Point d) {
		b = d;
		ArrayList<Point> list = new ArrayList<Point>();
		Point focus = c;
		list.add(focus);

		while (distance(focus, b) > 0) {
			// System.out.println(focus);
			focus = nextPoint(focus);
			list.add(focus);
//			list.addAll(surroundPoints(focus));
		}

//		return removeDuplicates(list);
		return list;
	}

	public static double distance(Point c, Point d) {
		return Math.sqrt(Math.pow(c.x - d.x, 2) + Math.pow(c.y - d.y, 2));
	}

	public static Point nextPoint(Point p) {
		int index;

		ArrayList<Point> possiblePoints = new ArrayList<Point>();
		possiblePoints.add(UP, new Point(p.x, p.y + 1));
		possiblePoints.add(DOWN, new Point(p.x, p.y - 1));
		possiblePoints.add(RIGHT, new Point(p.x + 1, p.y));
		possiblePoints.add(LEFT, new Point(p.x - 1, p.y));

		// adds randomness to mountain creation
		if (rand.nextInt(10) < 6) {
			index = rand.nextInt(4);
		} else {

			ArrayList<Double> dist = new ArrayList<Double>();

			// calc up dist
			dist.add(distance(possiblePoints.get(UP), b));

			// calc down dist
			dist.add(distance(possiblePoints.get(DOWN), b));

			// calc right dist
			dist.add(distance(possiblePoints.get(RIGHT), b));

			// calc left dist
			dist.add(distance(possiblePoints.get(LEFT), b));

			index = getSmallestIndex(dist);
		}

		return possiblePoints.get(index);
	}

	private static int getSmallestIndex(ArrayList<Double> d) {
		int smallIndex = 0;
		double smallNum = d.get(0);
		for (int i = 1; i < d.size(); i++) {
			if (d.get(i) < smallNum) {
				smallNum = d.get(i);
				smallIndex = i;
			}
		}

		return smallIndex;
	}

	private static ArrayList<Point> surroundPoints(Point p) {
		ArrayList<Point> list = new ArrayList<Point>();

		// add up
		list.add(new Point(p.x, p.y - 1));
		// add down
		list.add(new Point(p.x, p.y + 1));
		// add right
		list.add(new Point(p.x + 1, p.y));
		// add left
		list.add(new Point(p.x - 1, p.y));

		return list;
	}

	private static ArrayList<Point> removeDuplicates(ArrayList<Point> list) {
		ArrayList<Point> temp = new ArrayList<Point>();

		for (int i = 0; i < list.size(); i++) {
			if (!temp.contains(list.get(i))) {
				temp.add(list.get(i));
			}
		}
		return temp;
	}

}
