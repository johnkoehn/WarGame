package Wargame;
import org.jsfml.*;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.Event.Type;

public class Game {
	private int winWidth;
	private int winHeight;
	private RenderWindow window;
	private Event event;
	private Color backColor;
	private Unit unit;
	
	/***
	 * @param fwinWidth
	 * @param fwinHeight
	 * @param title
	 * Give the windows width, height and title
	 */
	Game(int fwinWidth, int fwinHeight, String title)
	{
		winWidth = fwinWidth;
		winHeight = fwinHeight;
		
		backColor = new Color(0, 0, 255); //blue

		//initialize the window
		VideoMode mode = new VideoMode(winWidth, winHeight);
		window = new RenderWindow(mode, title);
		
		//initialize the unit
		unit = new Unit(30, 40, 50);
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
				
				//clear the window and prep for the new frame
				window.clear(backColor);
				
				//draw the units
				window.draw(unit.getCircle());
				
				window.display();
			}
		}
	}
	
	private void checkInput()
	{
		if(event.type == Type.KEY_PRESSED)
		{
			//if(event.asKeyEvent() == 'W')
		}
	}
	public static void main(String args[])
	{
		Game game = new Game(800, 600, "WarGame!");
		game.runGame();
	}
	
	
	

}
