package Wargame;
import java.io.IOException;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.Mouse;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.Event.Type;

public class Game {
	private int winWidth;
	private int winHeight;
	private RenderWindow window;
	private Event event;
	private Color backColor;
	private Actor actor;
	private Camera camera;
	private Map map;
	private TextureManager tManager;
	private RandomUnitGenerator generator;
	private int selectID;
	
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
		
		//initialize the map
		map = new Map(32, 32, "map.txt");
		
		//initialize the Actor
		actor = new Actor(0, 0, "sprite1.png");
		
		setViewToActor();
		generator = new RandomUnitGenerator(map);
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
				
				//draw the map
				displayMap();
				
				//draw the Actors
				generator.draw(window);
				
				window.display();
				
				checkMousePosition();
			}
		}
	}
	
	private void checkMousePosition()
	{if (Mouse.isButtonPressed(Mouse.Button.LEFT))
			{Point a = MouseMonitor.getMousePosition();
			System.out.println(a.getX() + " " + a.getY());
			}
	}
	
	@SuppressWarnings("unused")
	private Point getMousePosition() {
		return MouseMonitor.getMousePosition();
	}
	
	private void selectClickedUnit(Point p){
		UnitManager dummy = null;
		for(int i = 0; i < dummy.length(); i++){
			if(dummy.get(i).getPoint() == p){
				selectID = i;
			}
		}
	}
	
	
	private void checkInput()
	{
		if(event.type == Type.KEY_PRESSED)
		{
			
			if(Keyboard.isKeyPressed(Key.W))
			{
				//actor.updateY((float) -1);
				camera.update(0, -10);
				//System.out.println(camera.getX() + " " + camera.getY());
			}
			if (Keyboard.isKeyPressed(Key.S))
			{
				//actor.updateY((float) 1);
				camera.update(0,  10);
				//System.out.println(camera.getX() + " " + camera.getY());
			}
			if (Keyboard.isKeyPressed(Key.A))
			{
				//actor.updateX((float) -1);
				camera.update(-10, 0);
				//System.out.println(camera.getX() + " " + camera.getY());
			}
			if (Keyboard.isKeyPressed(Key.D))
			{
				//actor.updateX((float) 1);
				camera.update(10,  0);
				//System.out.println(camera.getX() + " " + camera.getY());
			}
			if (Keyboard.isKeyPressed(Key.X))
			{
				camera.zoom((float) 2);
			}
			if(Keyboard.isKeyPressed(Key.Z))
			{
				camera.zoom((float) .5);
			}
			if(Keyboard.isKeyPressed(Key.C)){
				setViewToActor();
			}
			if(Keyboard.isKeyPressed(Key.SPACEBAR)){
				selectID++;
				setViewToActor();
			}
		}
	}
	
	/**
	 * Method sets the play view using the camera class
	 */
	public void setViewToActor()
	{
		//set the view to the player's Actor
		//start by finding the center of the Actor
		float xCenter = actor.getWidth() / 2;
		float yCenter = actor.getHeight() / 2;
		
		//now find the (x,y) position of the center of the sprite
		float xPos = actor.getX() + xCenter;
		float yPos = actor.getY() + yCenter;
		
		camera = new Camera(xPos, yPos, (float) winWidth, (float) winHeight);
	}
	
	/**
	 * Function displays the map
	 */
	public void  displayMap()
	{
		map.draw(window);
	}
	
	public static void main(String args[]) throws IOException
	{
		Game game = new Game(800, 600, "WarGame!");
		game.runGame();
	}
	

	

}
