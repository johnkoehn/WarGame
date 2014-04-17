package Importer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Wargame.WTexture;

public class TextureImporter {

	private static ArrayList<WTexture> texList = new ArrayList<WTexture>();
	public static final String DEFUALT_ADDRESS = "./TerrainStatV1.csv";
	
	public static ArrayList<WTexture> Importer() throws IOException {
		ArrayList<String> list = CSVImporter.Import(DEFUALT_ADDRESS);

		for (int i = 0; i < list.size(); i++) {
			parsHelper(list.get(i));
			// System.out.println(unitList.get(i));
		}
		return texList;
	}

	private static void parsHelper(String s) throws IOException {
		Scanner v = new Scanner(s);
		
		int ID = v.nextInt();

		WTexture temp = new WTexture(v.next(), ID);
		texList.add(temp);
		v.close();
	}
}
