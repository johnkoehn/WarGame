package Wargame;

import java.io.IOException;
import java.util.Random;

import org.jsfml.graphics.RenderWindow;

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
		uManager = new UnitManager();
		random = new Random();
		width = map.mapWidthTiles();
		height = map.mapHeightTiles();
		tileDeminsions = new Point(map.getTileWidth(), map.getTileHeight());
		
		createUnits();
	}
	
	private void createUnits() throws IOException
	{
		for(int i = 0; i < 50; i++)
		{
			//get ID of random unit to create
			int ID = 1;
			
			//create a new unit 
			Unit tempUnit = uLibrary.getUnit(ID);
			
			if (tempUnit == null)
			{
				System.out.println(i + " O crap! " + ID);
			}
			else
			{
				System.out.println("GOOD");
			}
			
			//now, get a random location for this cute little unit
			int xPos = random.nextInt(width);
			int yPos = random.nextInt(height);
			
			//update xPos and yPos to pixel coordinates
			xPos = (int) ((xPos * tileDeminsions.getX()) + (tileDeminsions.getX()));
			yPos = (int) ((yPos * tileDeminsions.getY()) + (tileDeminsions.getY()));
			
			//add this to the unit manager now
			uManager.addUnit(1, tempUnit, xPos, yPos, "./unit_1.png");
			
		}
		
	}
	
	public void draw(RenderWindow window)
	{
		uManager.draw(window);
	}
	
	public UnitManager getUnits()
	{
		return uManager;
	}
}







