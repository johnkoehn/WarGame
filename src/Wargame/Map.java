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
	private VertexArray m_vertices;
	private Texture m_tileset;
	private String tilesetFile;
	private String mapFileName;
	private ArrayList<int[]> tiles;
	
	//these values are measure in pixels
	private int tileHeight;
	private int tileWidth;
	
	//these values are the number of tiles that fit into the map
	private int mWidthTiles;
	private int mHeightTiles;
	
	Map(String ftilesetFile, String fmapFileName, int ftileHeight, int ftileWidth)
	{
		tilesetFile = ftilesetFile;
		mapFileName = fmapFileName;
		tileHeight = ftileHeight;
		tileWidth = ftileWidth;
	}
	
	/*public void readLevel() throws IOException
	{
		m_tileset.loadFromFile(Paths.get(tilesetFile));
		
		m_vertices.setPrimitiveType(PrimitiveType.valueOf("QUADS"));
		for (int i = 0; i < mWidthTiles; ++i)
		{
			for (int j = 0; j < mHeightTiles; ++j)
			{
				//check for going out of the vector tiles bounds
				if ((i + (j*mWidthTiles)) >= tiles.size())
				{
					System.out.println("ERROR, went out of bounds");
					return;
				}

				//get the current tile number
				int tileNumber = tiles.indexOf(i + (j * mWidthTiles));

				//find its position in the tileset texture
				int tu = tileNumber % (m_tileset.getSize().x / tileWidth);
				int tv = tileNumber / (m_tileset.getSize().x / tileWidth);

				//get a pointer to the current tile's quad
				Vertex quad = m_vertices.get((i + j * mWidthTiles) * 4);

				//define its 4 corners
				quad.position(new Vector2f(0, 0) ,new Vector2f(i * tileWidth, j * tileHeight));  
				quad.position = sf::Vector2f((i + 1) * tileSize.x, j * tileSize.y);
				quad.position = sf::Vector2f((i + 1) * tileSize.x, (j + 1) * tileSize.y);
				quad.position = sf::Vector2f(i * tileSize.x, (j + 1) * tileSize.y);

				//define its 4 texture coordinates
				quad[0].texCoords = sf::Vector2f(tu * tileSize.x, tv * tileSize.y);
				quad[1].texCoords = sf::Vector2f((tu + 1) * tileSize.x, tv * tileSize.y);
				quad[2].texCoords = sf::Vector2f((tu + 1) * tileSize.x, (tv + 1) * tileSize.y);
				quad[3].texCoords = sf::Vector2f(tu * tileSize.x, (tv + 1) * tileSize.y);
			}
		}
		File mFile = new File(mapFileName);
		Scanner scannerMap = new Scanner(mFile);
		
		scannerMap.close();
		
	} */
	
	
}
