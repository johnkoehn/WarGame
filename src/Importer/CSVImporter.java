package Importer;

//random comment
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVImporter {

	private static ArrayList<String> list = new ArrayList<String>();

	public static ArrayList<String> Import(String address)
			throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(address));
		while (scanner.hasNext()) {
			list.add(scanner.next());
		}

		scanner.close();

		return list;
	}
}
