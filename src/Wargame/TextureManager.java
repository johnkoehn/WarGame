package Wargame;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2i;

/**
 * This is class contains all the textures to prevent reloading multiple textures
 * @author Axel
 *
 */
public class TextureManager {
	/**
	 * Arraylist containing all the textures
	 */
	private ArrayList<Texture> textures;
	
	TextureManager()
	{
		textures = new ArrayList<Texture>();
	}
	
	/**
	 * Method to add a new texture to the array list
	 * @param textureName
	 */
	public void addTexture(String textureName)
	{
		Texture tempTexture = new Texture();
		try 
		{
			tempTexture.loadFromFile(Paths.get(textureName));
		} catch (IOException e) {
			System.out.println("INVALIE TEXTURE NAME! " + textureName);
			tempTexture = null;
		}
		
		textures.add(tempTexture);
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
			
		}
		return null;
	}

}





