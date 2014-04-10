package Wargame;

import java.io.IOException;
import java.util.Random;

/**
 * Class generates a bunch of random units on the map
 * this pretty much a demo class
 * @author Axel
 *
 */
public class RandomUnitGenerator 
{
	/**
	 * Width is the width of the map in tiles
	 */
	private int width;
	
	/**
	 * Height of the map in tiles
	 */
	private int height;
	
	/**
	 * Contains the tile size
	 */
	private Point tileDeminsions;
	
	/**
	 * Manages all the units
	 */
	private UnitManager uManager;
	
	/**
	 * Used to generate random numbers
	 */
	private Random random;
	
	private UnitLibrary uLibrary;
	
	public RandomUnitGenerator(Map map) throws IOException
	{
		uLibrary = new UnitLibrary();
		random = new Random();
		width = map.mapWidthTiles();
		height = map.mapHeightTiles();
		tileDeminsions = new Point(map.getTileWidth(), map.getTileHeight());
	}
	
	private void createUnits()
	{
		for(int i = 0; i < 50; i++)
		{
			//get ID of random unit to create
			int ID = random.nextInt(1) + 1;
			
			//create a new unit 
			Unit tempUnit = uLibrary.getUnit(ID);
			
			//now, get a random location for this cute little unit
			int xPos = random.nextInt(width);
			int yPos = random.nextInt(height);
			
			//update xPos and yPos to pixel coordinates
			xPos = (int) ((xPos * tileDeminsions.getX()) + (tileDeminsions.getX() / 2));
			yPos = (int) ((yPos * tileDeminsions.getY()) + (tileDeminsions.getY() / 2));
			
		}
		
	}
}







