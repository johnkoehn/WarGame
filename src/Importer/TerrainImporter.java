package Importer;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Wargame.Terrain;

public class TerrainImporter {

	private ArrayList<Terrain> terList = new ArrayList<Terrain>();
	private final String DEFUALT_ADDRESS = "./TerrainStatV1.csv";

	public TerrainImporter(String address) throws FileNotFoundException {

		CSVImporter reader = new CSVImporter(address);
		ArrayList<String> list = reader.getCSVList();

		// skips header of import
		for (int i = 1; i < list.size(); i++) {
			parsHelper(list.get(i));
			// System.out.println(terList.get(i));
		}
	}

	public TerrainImporter() throws FileNotFoundException {

		CSVImporter reader = new CSVImporter(DEFUALT_ADDRESS);
		ArrayList<String> list = reader.getCSVList();

		// skips header of import
		for (int i = 1; i < list.size(); i++) {
			parsHelper(list.get(i));
			System.out.println(terList.get(i));
		}
	}

	private void parsHelper(String s) {
		Scanner v = new Scanner(s);
		v.useDelimiter(",");

		terList.add(new Terrain(v.nextInt(), v.nextInt(), v.nextInt(), v
				.nextInt(), v.nextInt()));
		v.close();
	}

	public ArrayList<Terrain> getList() {
		return terList;
	}
}
