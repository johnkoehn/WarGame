package Wargame;

import java.io.FileNotFoundException;

import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

import Importer.TerrainImporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;;

public class Map {
	//TIME TO MAKE MANY RECTANGLES FOR MAP
	private ArrayList<Tile> tiles = new ArrayList<Tile>();
	private Point tileDeminisons;
	private String mapFile;
	
	
	//map width and height are measured in the number of tiles that make up the width and height
	private int mapWidth; 
	private int mapHeight;
	
	//in the map file, value of 0 would use color 0, value 1 would use color 1
	private Color color0;
	private Color color1;
	private Color color2;
	private Color color3;
	
	//array contains all the values that make up the map
	private ArrayList<Integer> mapArray;
	
	// creates a library for the terrain
	private ArrayList<Terrain> terLib;
	
	public Map(int ftileWidth, int ftileHeight, int xMax, int yMax) throws FileNotFoundException
	{
		tileDeminisons = new Point(ftileWidth, ftileHeight);
		mapFile = null;
		mapHeight = yMax;
		mapWidth = xMax;
		
		color0 = new Color(112, 173, 71); //grass
		color1 = new Color(255, 197, 13); //desert 
		color2 = new Color(91, 155, 213); //water
		color3 = new Color(89, 89, 89); //mountain
		
		//creates map
		mapArray = RandomMapGenerator.makeMap(xMax, yMax);
		
		createMap();
		
		terLib = TerrainImporter.Importer();
	}
	
	public Map(int ftileWidth, int ftileHeight, String fmapFile) throws FileNotFoundException
	{
		tileDeminisons = new Point(ftileWidth, ftileHeight);
		mapFile = fmapFile;
		mapHeight = 0;
		mapWidth = 0;
		
		color0 = new Color(45, 255, 4); //grass
		color1 = new Color(255, 255, 0); //desert 
		color2 = new Color(56, 147, 192); //water
		color3 = new Color(0, 0, 0); //mountain
		
		//creates map
		mapArray = readMapFile();
		
		createMap();
		
		terLib = TerrainImporter.Importer();
	}
	
	/**
	 * Creates all the rectangles for the map
	 * @throws FileNotFoundException 
	 */
	public void createMap() throws FileNotFoundException
	{
		//fill up ZIE arraylist with rectangleshapes 
		for (int i= 0; i < mapWidth; i++)
		{
			for(int j = 0; j < mapHeight; j++)
			{				
				//calculate the tiles Origin
				float x = -(i * tileDeminisons.getX());
				float y = -(j * tileDeminisons.getY());
				
				Point position = new Point(x, y);
				
				Tile temp;
				//determine its color
				int colorID = mapArray.get(i*mapWidth + j);
				if (colorID == 0)
				{
					temp = new Tile(color0, tileDeminisons, position);
				}
				else if (colorID == 1)
				{
					temp = new Tile(color1, tileDeminisons, position);
				}
				else if (colorID == 2)
				{
					temp = new Tile(color2, tileDeminisons, position);
				}
				else if (colorID == 3)
				{
					temp = new Tile(color3, tileDeminisons, position);
				}
				else
				{
					temp = new Tile(color3, tileDeminisons, position);
				}

				//add it to the arraylist
				tiles.add(temp);
				
			}
		}
	}
	
	@SuppressWarnings("unused")
	private ArrayList<Integer> readMapFile() throws FileNotFoundException
	{
		//first calculate # of columns and rows of the file
		calculateDimensions();
		
		File file = new File(mapFile);
		Scanner scanner = new Scanner(file);
		
		ArrayList<Integer> tempMapArray = new ArrayList<Integer>();
		while(scanner.hasNextInt())
		{
			int newTile = scanner.nextInt();
			tempMapArray.add(newTile);
		}
		
		scanner.close();
		return tempMapArray;
	}
	
	private void calculateDimensions() throws FileNotFoundException
	{
		File file = new File(mapFile);
		Scanner scanner = new Scanner(file);
		
		String dummyString = "";
		while(scanner.hasNextLine())
		{
			dummyString = scanner.nextLine();
			mapHeight += 1;
		}
		
		//now use the dummyString to calculate the number of columns
		dummyString = dummyString.replaceAll("\\s+", "");
		mapWidth = dummyString.length(); 
		scanner.close();		
	}
	
	public Tile getTile(int index)
	{
		if(index < tiles.size())
		{
			System.out.println("Get tile index out of bounds!");
			return tiles.get(0);
		}
		return tiles.get(index);
	}
	
	/**
	 * Function returns the size of the arraylist
	 * @return
	 */
	public int getSize()
	{
		return tiles.size();
	}
	
	public void draw(RenderWindow window)
	{
		for (int i = 0; i < tiles.size(); i++)
		{
			window.draw(tiles.get(i).getRectangle());
		}
	}
	
	public int mapWidthTiles()
	{
		return mapWidth;
	}
	
	public int mapHeightTiles()
	{
		return mapHeight;
	}
	
	public int mapWidthPixels()
	{
		return (int) (mapWidth * tileDeminisons.getX());
	}
	
	public int mapHeightPixels()
	{
		return (int) (mapHeight * tileDeminisons.getY());
	}
	
	public ArrayList<Integer> getList()
	{
		return mapArray;
	}
	
	public int getTileWidth()
	{
		return (int) tileDeminisons.getX();
	}
	
	public int getTileHeight()
	{
		return (int) tileDeminisons.getY();
	}
	
}
