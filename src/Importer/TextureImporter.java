package Importer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Wargame.WTexture;

public class TextureImporter {

	private ArrayList<WTexture> texList = new ArrayList<WTexture>();
	public final String DEFUALT_ADDRESS = "./UnitStatV1.csv";

	public TextureImporter(String address) throws IOException {

		CSVImporter reader = new CSVImporter(address);
		ArrayList<String> list = reader.getCSVList();

		for (int i = 0; i < list.size(); i++) {
			parsHelper(list.get(i));
			// System.out.println(unitList.get(i));
		}
	}

	public TextureImporter() throws IOException {

		CSVImporter reader = new CSVImporter(DEFUALT_ADDRESS);
		ArrayList<String> list = reader.getCSVList();

		for (int i = 0; i < list.size(); i++) {
			parsHelper(list.get(i));
			// System.out.println(unitList.get(i));
		}
	}

	private void parsHelper(String s) throws IOException {
		Scanner v = new Scanner(s);
		
		int ID = v.nextInt();

		WTexture temp = new WTexture(v.next(), ID);
		texList.add(temp);
		v.close();
	}

	public ArrayList<WTexture> getList() {
		return texList;
	}
}
