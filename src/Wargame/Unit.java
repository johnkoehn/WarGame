package Wargame;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2i;

public class Unit {
	Texture texture;
	Vector2i textureSize;
	Sprite unit;
	float xPos;
	float yPos;
	
	Unit(float fxPos, float fyPos, String textureFile) throws IOException
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
		unit = new Sprite(texture);
		unit.setPosition(xPos, yPos);
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
		unit.move(deltaX, 0);
	}
	
	void updateY(float deltaY)
	{
		yPos += deltaY;
		unit.move(0, deltaY);
	}
	
	Sprite getUnit()
	{
		return unit;
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
