package Importer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVImporter {

	private ArrayList<String> list = new ArrayList<String>();

	public CSVImporter(String address) {

		BufferedReader buffer = null;

		String tempLine;

		try {

			buffer = new BufferedReader(new FileReader(address));

			// gets rid of header line (prints to check)
//			System.out.println(buffer.readLine());
			buffer.readLine();

			do {
				// Reads line and transfers it to the temporary string
				tempLine = buffer.readLine();
				list.add(tempLine);

				// readIn++;
			} while (tempLine != null);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (buffer != null) {
				try {
					buffer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}// end try


	}
	
	public ArrayList<String> getCSVList() {
		return list;
	}
}
