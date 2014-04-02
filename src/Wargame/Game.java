package Wargame;
import java.io.IOException;

import org.jsfml.*;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.Event.Type;
import org.jsfml.window.event.KeyEvent;

public class Game {
	private int winWidth;
	private int winHeight;
	private RenderWindow window;
	private Event event;
	private Color backColor;
	private Unit unit;
	private Camera camera;
	
	/***
	 * @param fwinWidth
	 * @param fwinHeight
	 * @param title
	 * Give the windows width, height and title
	 * @throws IOException 
	 */
	Game(int fwinWidth, int fwinHeight, String title) throws IOException
	{
		winWidth = fwinWidth;
		winHeight = fwinHeight;
		
		backColor = new Color(0, 0, 255); //blue

		//initialize the window
		VideoMode mode = new VideoMode(winWidth, winHeight);
		window = new RenderWindow(mode, title);
		
		//initialize the unit
		unit = new Unit(50, 50, "sprite1.png");
		
		setViewToUnit();
	}
	
	int getWidth()
	{
		return winWidth;
	}
	
	int getHeight()
	{
		return winHeight;
	}
	
	/**
	 * This function will keep looping until we quit the game
	 */
	void runGame()
	{
		while (window.isOpen())
		{
			//check for new window events that occurred since the last loop iteration
			event = window.pollEvent();
			if (event != null)
			{
				//if a close requested event occurs, close the window
				if (event.type == Type.CLOSED)
				{
					window.close();
				}
				
				//check for input
				checkInput();
				
				//clear the window and prep for the new frame
				window.clear(backColor);
				
				//set the view 
				window.setView(camera.getView());
				//draw the units
				window.draw(unit.getUnit());
				
				window.display();
			}
		}
	}
	
	private void checkInput()
	{
		if(event.type == Type.KEY_PRESSED)
		{
			
			if(Keyboard.isKeyPressed(Key.W))
			{
				//unit.updateY((float) -1);
				camera.update(0, -1);
			}
			if (Keyboard.isKeyPressed(Key.S))
			{
				//unit.updateY((float) 1);
				camera.update(0,  1);
			}
			if (Keyboard.isKeyPressed(Key.A))
			{
				//unit.updateX((float) -1);
				camera.update(-1, 0);
			}
			if (Keyboard.isKeyPressed(Key.D))
			{
				//unit.updateX((float) 1);
				//camera.update(1,  0);
				camera.zoom((float) 1.001);
			}
		}
	}
	
	/**
	 * Method sets the play view using the camera class
	 */
	private void setViewToUnit()
	{
		//set the view to the player's unit
		//start by finding the center of the unit
		float xCenter = unit.getWidth() / 2;
		float yCenter = unit.getHeight() / 2;
		
		//now find the (x,y) position of the center of the sprite
		float xPos = unit.getX() + xCenter;
		float yPos = unit.getY() + yCenter;
		
		camera = new Camera(xPos, yPos, (float) winWidth, (float) winHeight);
	}
	
	public static void main(String args[]) throws IOException
	{
		Game game = new Game(800, 600, "WarGame!");
		game.runGame();
	}
	

	

}
