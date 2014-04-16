package Wargame;

import java.io.IOException;
import java.util.ArrayList;

import Importer.UnitImporter;

public class UnitLibrary {
	//Function:
	//1. Calls UnitImporter
	//2. Gets Unit list
	//3. Gets unit from the list with specified ID (getUnit(int ID))
	private ArrayList<Unit> list;
	public UnitLibrary() throws IOException
	{
		UnitImporter def = new UnitImporter();
		list = def.getList();
	}
	
//	public Unit getUnit(int ID)
//	{
//		for (int i=0;i<list.size();i++)
//		{
//			if (list.get(i).getUnitID() == ID)
//			{
//				System.out.println("T" + i);
//				return list.get(i);
//			}
//			else
//			{
//				System.out.println("S" + i);
//			}
//		}
//		return null;
//	}
	
	public Unit getUnit(int ID) {
		for(int i = 0; i < list.size(); i++) {
			System.out.println("R" + list.get(i).getUnitID());
			if(list.get(i).getUnitID() == ID) {
				return list.get(i);
			}
		}
		return null;
	}
}
