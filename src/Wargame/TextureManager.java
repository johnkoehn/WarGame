package Wargame;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2i;

import Importer.TextureImporter;

/**
 * This is class contains all the textures to prevent reloading multiple textures
 * @author Axel
 *
 */
public class TextureManager {
	/**
	 * Arraylist containing all the textures
	 */
	private ArrayList<WTexture> textures;
	
	public TextureManager() throws IOException
	{
		TextureImporter texImp = new TextureImporter();
		textures = texImp.getList();
	}
	
	/**
	 * Method to add a new texture to the array list
	 * @param textureName
	 */
	public void addTexture(String textureName, int ID)
	{
		WTexture temp = new WTexture(textureName, ID);
		textures.add(temp);
	}
	
	/**
	 * Method finds and returns the texture based on the texture ID given
	 * @param texID
	 * @return Texture
	 */
	public Texture getTexture(int texID)
	{
		//find zie texture!
		for (int i = 0; i < textures.size(); i++)
		{
			if (texID == (textures.get(i)).getID())
			{
				return (textures.get(i)).getTexture();
			}
		}
		
		//texture not found :/
		System.out.println("Texture with ID " + texID + " not found.");
		return null;
	}
	

}





