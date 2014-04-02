package Wargame;

import java.io.FileNotFoundException;

import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;;

public class Map {
	//TIME TO MAKE MANY RECTANGLES FOR MAP
	private ArrayList<RectangleShape> tile = new ArrayList<RectangleShape>();
	private int tileWidth;
	private int tileHeight;
	private Color color1;
	
	//stuff for later
	private String mapFile;
	
	Map(int ftileWidth, int ftileHeight, String fmapFile)
	{
		tileWidth = ftileWidth;
		tileHeight = ftileHeight;
		mapFile = fmapFile;
		color1 = new Color(0, 255, 0);
	}
	
	/**
	 * Dummy verison for testing
	 */
	void loadMap()
	{
		Vector2f tileSizes = new Vector2f((float) tileWidth, (float) tileHeight);
		
		//fill up ZIE arraylist with rectangleshapes 
		for (int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				//create a new rectangle shape
				RectangleShape temp = new RectangleShape(tileSizes);
				
				//calculate its origins
				float x = i * tileWidth;
				float y = i * tileHeight;
				
				//set its origins
				temp.setOrigin(x, y);
				
				//set its color
				temp.setFillColor(color1);
				
				//add it to the arraylist
				tile.add(temp);
				
			}
		}
	}

	
	
}
