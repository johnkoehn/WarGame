package Wargame;

import org.jsfml.graphics.CircleShape;

public class Unit {
	//units are circles right now
	CircleShape circle;
	int radius;
	float xPos;
	float yPos;
	
	Unit(int fradius, float fxPos, float fyPos)
	{
		radius = fradius;
		xPos = fxPos;
		yPos = fyPos;
		
		circle = new CircleShape(radius);
		circle.setPosition(xPos, yPos);
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
		circle.setPosition(xPos, yPos);
	}
	
	void updateY(float deltaY)
	{
		yPos += deltaY;
		circle.setPosition(xPos, yPos);
	}
	
	CircleShape getCircle()
	{
		return circle;
	}

}
