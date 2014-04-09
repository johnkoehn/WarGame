package Wargame;

import org.jsfml.graphics.View;
import org.jsfml.system.Vector2f;

public class Camera {
	private View view;
	private float xPos;
	private float yPos;
	private float viewWidth;
	private float viewHeight;
	
	public Camera(float x, float y, float width, float height)
	{
		xPos = x;
		yPos = y;
		viewWidth = width;
		viewHeight = height;
		
		//now set the view
		view = new View();
		view.setCenter(xPos, yPos);
		view.setSize(width, height);
	}
	
	public void update(float deltaX, float deltaY)
	{
		view.move(deltaX, deltaY);
		xPos += deltaX;
		yPos += deltaY;
	}
	
	public void zoom(float factor)
	{
		view.zoom(factor);
	}
	
	public View getView()
	{
		return view;
	}
	
	public float getX() { return xPos; }
	public float getY() { return yPos; }

}
