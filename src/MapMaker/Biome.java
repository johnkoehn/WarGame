package MapMaker;

import java.awt.Point;
import java.util.ArrayList;



public class Biome {

	private ArrayList<Point> node;
	private int ID;
	public final int AREA = 30;
	private Point seed;
	private int type;



	public Biome(int givenID) {
		ID = givenID;
		node = new ArrayList<Point>();
//		node.add(start);
	}
	
	public Biome(int givenID, Point start, int givenType) {
		ID = givenID;
		node = new ArrayList<Point>();
		node.add(start);
		seed = start;
		type = givenType;
	}
	
	public void addPoint(Point p){
		node.add(p);
	}

	public Point getSeed(){
		return seed;
	}
	
	public ArrayList<Point> getNodes() {
		return node;
	}

	public int getID() {
		return ID;
	}
	
	public int getType() {
		return type;
	}
	
	public int getCurrentSize(){
		return node.size();
	}
}
