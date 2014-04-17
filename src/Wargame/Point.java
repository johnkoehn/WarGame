package Wargame;

import org.jsfml.system.Vector2f;

public class Point {
	private float xpos;
	private float ypos;

	// construct a point using x and y values
	public Point(float xpos, float ypos) {
		this.xpos = xpos;
		this.ypos = ypos;
	}

	// return x value
	public float getX() {
		return xpos;
	}

	// return y value
	public float getY() {
		return ypos;
	}

	public void setX(float nX) {
		xpos = nX;
	}

	public void setY(float nY) {
		ypos = nY;
	}

	public void changeX(float delta) {
		xpos += delta;
	}

	public void changeY(float delta) {
		ypos += delta;
	}
	
	public int getXTile() {
		return (int) (xpos / 32);
//		return 7;
	}
	
	public int getYTile() {
		return (int) (ypos / 32);
//		return 7;
	}
	
	public Vector2f getVector2f()
	{
		Vector2f temp = new Vector2f(xpos, ypos);
		return temp;
	}

}
