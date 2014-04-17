package Wargame;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;

/**
 * This class contains the information for 
 * each tile in the map, therefor, it contains 
 * if it is occupied by a unit, the color
 * and the rectangleShape that gets rendered
 * @author Axel
 *
 */
public class Tile 
{
	private RectangleShape rectangle;
	private boolean isOccupied;
	
	/**
	 * Point p contains the dimensions of the tile
	 * color is the color of the tile 
	 * @param color
	 * @param p
	 */
	public Tile(Color color, Point p, Point position)
	{
		isOccupied = false;
		rectangle = new RectangleShape(p.getVector2f());
		rectangle.setFillColor(color);
		rectangle.setOrigin(position.getVector2f());
	}
	
	public void setOccupied()
	{
		isOccupied = true;
	}
	
	public boolean isThisOccupied()
	{
		return isOccupied;
	}
	
	public void setNotOccupied()
	{
		isOccupied = false;
	}
	
	public RectangleShape getRectangle()
	{
		return rectangle;
	}
}
