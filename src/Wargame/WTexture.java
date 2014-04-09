package Wargame;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2i;

/**
 * Class contains texture information, 
 * such as the texture ID, it width and height
 * @author Axel
 *
 */
public class WTexture {
	private Texture texture;
	private int width;
	private int height;
	private int ID;
	
	WTexture(String textureFile, int ID)
	{
		this.ID = ID;
		
		//attempt to load the texture 
		try 
		{
			texture.loadFromFile(Paths.get(textureFile));
		} catch (IOException e) {
			System.out.println("INVALIE TEXTURE NAME! " + textureFile);
			texture = null;
			width = 0;
			height = 0;
			return;
		}
		
		//get the texture width and height
		Vector2i vec = texture.getSize();
		width = vec.x;
		height = vec.y;		
	}
	
	/**
	 * Returns the ID
	 * @return
	 */
	public int getID()
	{
		return ID;
	}
	
	/**
	 * returns the texture
	 * @return
	 */
	public Texture getTexture()
	{
		return texture;
	}
	
	public int getHeight() { return height; }
	public int getWidth() { return width; }

}






