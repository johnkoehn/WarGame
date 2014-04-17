package Importer;
//random comment

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Wargame.Unit;

public class UnitImporter {

	private static ArrayList<Unit> unitList = new ArrayList<Unit>();
	public static final String DEFUALT_ADDRESS = "./UnitStatV2.csv";
	
	public static ArrayList<Unit> Importer() throws IOException {
		ArrayList<String> list = CSVImporter.Import(DEFUALT_ADDRESS);

		// skips header of import
		for (int i = 1; i < list.size(); i++) {
			parsHelper(list.get(i));
			// System.out.println(unitList.get(i));
		}
		return unitList;
	}
	
	private static void parsHelper(String s) throws IOException {
		Scanner v = new Scanner(s);
		v.useDelimiter(",");

		Unit temp = new Unit(v.next(), // given Name
				v.nextInt(), // given UnitID
				v.nextInt(), // given Type
				v.nextInt(), // given SubType
				v.nextInt(), // given Health
				v.nextInt(), // given MovePoints
				v.nextInt(), // given Fuel
				v.nextInt(), // given Ammo
				v.nextInt(), // given Attack
				v.nextInt(), // given Range
				v.nextInt(), // given WeaknessID
				v.nextDouble()); // given WeaknessMult
		unitList.add(temp);
		v.close();
	}
}
