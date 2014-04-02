package Importer;

import java.util.ArrayList;
import java.util.Scanner;

import Wargame.Unit;

public class UnitImporter {

	private ArrayList<Unit> unitList = new ArrayList<Unit>();

	public UnitImporter(String address) {

//		String address = "/Users/Owner/Documents/Libraries/School/Spring 13/CPRE 186/UnitStatV1.csv";

		CSVImporter reader = new CSVImporter(address);
		ArrayList<String> list = reader.getCSVList();
		int size = list.size();

		for (int i = 0; i < size; i++) {
			parsHelper(list.get(i));
			System.out.println(unitList.get(i));
		}
	}
	
	public UnitImporter() {

//		String address = "/Users/Owner/Documents/Libraries/School/Spring 13/CPRE 186/UnitStatV1.csv";
		String address = "./UnitStatV1.csv";

		CSVImporter reader = new CSVImporter(address);
		ArrayList<String> list = reader.getCSVList();
		int size = list.size() - 5;

		for (int i = 0; i < size; i++) {
			parsHelper(list.get(i));
			System.out.println(unitList.get(i));
		}
	}

	private void parsHelper(String s) {
		Scanner v = new Scanner(s);
		v.useDelimiter(",");

		Unit temp = new Unit(v.nextInt(), v.nextInt(), v.nextInt(),
				v.nextInt(), v.nextInt(), v.nextInt(), v.nextInt(),
				v.nextInt(), v.nextInt(), v.next(), v.nextInt(), v.nextDouble());
		unitList.add(temp);
		v.close();
		// return temp;
	}

	public ArrayList<Unit> getList() {
		return unitList;
	}
}
