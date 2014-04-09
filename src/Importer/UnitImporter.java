package Importer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Wargame.Unit;

public class UnitImporter {

	private ArrayList<Unit> unitList = new ArrayList<Unit>();
	private final String DEFUALT_ADDRESS = "./UnitStatV1.csv";

	public UnitImporter(String address) throws IOException {

		CSVImporter reader = new CSVImporter(address);
		ArrayList<String> list = reader.getCSVList();

		// skips header of import
		for (int i = 1; i < list.size(); i++) {
			parsHelper(list.get(i));
			// System.out.println(unitList.get(i));
		}
	}

	public UnitImporter() throws IOException {

		CSVImporter reader = new CSVImporter(DEFUALT_ADDRESS);
		ArrayList<String> list = reader.getCSVList();

		for (int i = 0; i < list.size(); i++) {
			parsHelper(list.get(i));
			// System.out.println(unitList.get(i));
		}
	}

	private void parsHelper(String s) throws IOException {
		Scanner v = new Scanner(s);
		v.useDelimiter(",");

		Unit temp = new Unit(v.nextInt(), v.nextInt(), v.nextInt(),
				v.nextInt(), v.nextInt(), v.nextInt(), v.nextInt(),
				v.nextInt(), v.nextInt(), v.nextInt(), v.nextDouble(),
				v.nextFloat(), v.nextFloat(), v.next());
		unitList.add(temp);
		v.close();
	}

	public ArrayList<Unit> getList() {
		return unitList;
	}
}
