package Importer;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Wargame.Terrain;

public class TerrainImporter {

	private static ArrayList<Terrain> terList = new ArrayList<Terrain>();
	public static final String DEFUALT_ADDRESS = "./TerrainStatV1.csv";
	
	public static ArrayList<Terrain> Importer() throws FileNotFoundException{
		ArrayList<String> list = CSVImporter.Import(DEFUALT_ADDRESS);

		// skips header of import
		for (int i = 1; i < list.size(); i++) {
			parsHelper(list.get(i));
			System.out.println(terList.get(i));
		}
		return terList;
	}

	private static void parsHelper(String s) {
		Scanner v = new Scanner(s);
		v.useDelimiter(",");

		terList.add(new Terrain(
				v.nextInt(), // id
				v.nextInt(), // subID
				v.nextInt(), // color
				v.nextInt(), // defense
				v.nextInt()));// moveCost
		v.close();
	}
}
