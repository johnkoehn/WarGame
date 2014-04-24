package Wargame;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2i;

public class Actor {
	private Texture texture;
	private Sprite actor;
	private Point pixelCoordinates;
	private Point tileCooridnates;

	public Actor(float fxPos, float fyPos, String textureFile) throws IOException {
		pixelCoordinates = new Point(fxPos, fyPos);
		
		//set xPos and yPos to tile coordinates (needed for unit collision)
		tileCooridnates = new Point(fxPos / 32, fyPos / 32); //just hardcoded xD

		texture = new Texture();

		// load the texture
		texture.loadFromFile(Paths.get(textureFile));

		// set texture to smooth
		texture.setSmooth(true);

		// now create the sprite
		actor = new Sprite(texture);
		actor.setPosition(pixelCoordinates.getX(), pixelCoordinates.getY());
	}

	/*public Actor(Point p, String textureFile) throws IOException {		
		texture = new Texture();

		// load the texture
		texture.loadFromFile(Paths.get(textureFile));

		// set texture to smooth
		texture.setSmooth(true);

		// now create the sprite
		actor = new Sprite(texture);
		actor.setPosition(p.getX(), p.getY());
	} */ 

	public Point getPoint() {
		return pixelCoordinates;
	}

	public float getX() {
		return pixelCoordinates.getX();
	}

	public float getY() {
		return pixelCoordinates.getY();
	}

	public void updateX(float deltaX) {
		pixelCoordinates.changeX(deltaX);
		actor.move(deltaX, 0);
		
		if (deltaX < 0)
		{
			tileCooridnates.changeX(-1);
		}
		else
		{
			tileCooridnates.changeX(1);
		}
	}

	public void updateY(float deltaY) {
		pixelCoordinates.changeY(deltaY);
		actor.move(0, deltaY);
		
		if(deltaY < 0)
		{
			tileCooridnates.changeY(-1);
			System.out.println(tileCooridnates.getY());
		}
		else 
		{
			tileCooridnates.changeY(1);
		}
	}

	public void updatePoint(Point newP) {
		pixelCoordinates = newP;
		// actor.move(, arg1);
	}

	public Sprite getActor() {
		return actor;
	}

	public int getWidth() {
		Vector2i size = texture.getSize();
		return size.x;
	}

	public int getHeight() {
		Vector2i size = texture.getSize();
		return size.y;
	}
	
	public Point getTileLocation()
	{
		return tileCooridnates;
	}
}
