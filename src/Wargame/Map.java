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
	private ArrayList<RectangleShape> tiles = new ArrayList<RectangleShape>();
	private int tileWidth;
	private int tileHeight;
	private String mapFile;
	
	
	//map width and height are measured in the number of tiles that make up the width and height
	private int mapWidth; 
	private int mapHeight;
	
	//in the map file, value of 0 would use color 0, value 1 would use color 1
	private Color color0;
	private Color color1;
	private Color color2;
	
	//array contains all the values that make up the map
	ArrayList<Integer> mapArray;
	
	//Terrain Library
	ArrayList<Terrain> terLib;
	
	public Map(int ftileWidth, int ftileHeight, String fmapFile) throws FileNotFoundException
	{
		tileWidth = ftileWidth;
		tileHeight = ftileHeight;
		mapFile = fmapFile;
		mapHeight = 0;
		mapWidth = 0;
		
		color0 = new Color(255, 255, 0); //desert 
		color1 = new Color(45, 255, 4); //grass
		color2 = new Color(56, 147, 192); //water
		createMap();
		
		//imports the terrain list
		TerrainImporter tImp = new TerrainImporter();
		terLib = tImp.getList();
	}
	
	/**
	 * Creates all the rectangles for the map
	 * @throws FileNotFoundException 
	 */
	public void createMap() throws FileNotFoundException
	{
		//prep for creating the map
		ArrayList<Integer> mapArray = readFile();
		Vector2f tileSizes = new Vector2f((float) tileWidth, (float) tileHeight);
		
		//fill up ZIE arraylist with rectangleshapes 
		for (int i= 0; i < mapHeight; i++)
		{
			for(int j = 0; j < mapWidth; j++)
			{
				//create a new rectangle shape
				RectangleShape temp = new RectangleShape(tileSizes);
				
				//calculate its origins
				float x = -(i * tileWidth);
				float y = -(j * tileHeight);
				
				//set its origins
				temp.setOrigin(x, y);
				
				//determine its color
				//System.out.println(mapArray.get(i*mapWidth + j));
				int colorID = mapArray.get(i*mapWidth + j);
				if (colorID == 0)
				{
					temp.setFillColor(color0);
				}
				else if (colorID == 1)
				{
					temp.setFillColor(color1);
				}
				else if (colorID == 2)
				{
					temp.setFillColor(color2);
				}
				else 
				{
					temp.setFillColor(color0);
				}

				
				//add it to the arraylist
				tiles.add(temp);
				
			}
		}
	}
	
	private ArrayList<Integer> readFile() throws FileNotFoundException
	{
		//first calculate # of columns and rows of the file
		calculateDimensions();
		
		File file = new File(mapFile);
		Scanner scanner = new Scanner(file);
		
		mapArray = new ArrayList<Integer>();
		while(scanner.hasNextInt())
		{
			int newTile = scanner.nextInt();
			mapArray.add(newTile);
		}
		
		scanner.close();
		return mapArray;
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
	
	public RectangleShape getRectangle(int index)
	{
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
			window.draw(tiles.get(i));
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
		return (mapWidth * tileWidth);
	}
	
	public int mapHeightPixels()
	{
		return (mapHeight * tileHeight);
	}
	
	public ArrayList<Integer> getList()
	{
		return mapArray;
	}

	
	
}
