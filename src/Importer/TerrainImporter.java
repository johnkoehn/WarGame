package Importer;

import java.util.ArrayList;
import java.util.Scanner;

import Wargame.Terrain;

public class TerrainImporter {

	private ArrayList<Terrain> terList = new ArrayList<Terrain>();

	public TerrainImporter(String address) {

//		String address = "/Users/Owner/Documents/Libraries/School/Spring 13/CPRE 186/UnitStatV1.csv";

		CSVImporter reader = new CSVImporter(address);
		ArrayList<String> list = reader.getCSVList();
		int size = list.size() - 5;

		for (int i = 0; i < size; i++) {
			parsHelper(list.get(i));
			System.out.println(terList.get(i));
		}
	}
	
	public TerrainImporter() {

//		String address = "/Users/Owner/Documents/Libraries/School/Spring 13/CPRE 186/UnitStatV1.csv";
		String address = "./UnitStatV1.csv";

		CSVImporter reader = new CSVImporter(address);
		ArrayList<String> list = reader.getCSVList();
		int size = list.size() - 5;

		for (int i = 0; i < size; i++) {
			parsHelper(list.get(i));
			System.out.println(terList.get(i));
		}
	}

	private void parsHelper(String s) {
		Scanner v = new Scanner(s);
		v.useDelimiter(",");

		Terrain temp = new Terrain(v.nextInt(), v.nextInt(), v.nextInt(), v.nextInt(), v.nextInt());
		terList.add(temp);
		v.close();
		// return temp;
	}

	public ArrayList<Terrain> getList() {
		return terList;
	}
}
