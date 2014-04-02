package Wargame;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2i;


public class Actor {
	private Texture texture;
	private Vector2i textureSize;
	private Sprite actor;
	private float xPos;
	private float yPos;
	
	Actor(float fxPos, float fyPos, String textureFile) throws IOException
	{
		xPos = fxPos;
		yPos = fyPos;
		texture = new Texture();
		
		//load the texture
		texture.loadFromFile(Paths.get(textureFile));
		textureSize = texture.getSize();
		
		//set texture to smooth
		texture.setSmooth(true);
		
		//now create the sprite
		actor = new Sprite(texture);
		actor.setPosition(xPos, yPos);
	}
	
	float getX()
	{
		return xPos;
	}
	
	float getY()
	{
		return yPos;
	}
	
	void updateX(float deltaX)
	{
		xPos += deltaX;
		actor.move(deltaX, 0);
	}
	
	void updateY(float deltaY)
	{
		yPos += deltaY;
		actor.move(0, deltaY);
	}
	
	Sprite getActor()
	{
		return actor;
	}
	
	int getWidth()
	{
		Vector2i size = texture.getSize();
		return size.x;
	}
	
	int getHeight()
	{
		Vector2i size = texture.getSize();
		return size.y;
	}
}
