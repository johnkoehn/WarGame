package Wargame;
import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.Mouse;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.Event.Type;

public class Game {
	private String variable;
	private int winWidth;
	private int winHeight;
	private Font arial = new Font();
	private RenderWindow window;
	private RenderWindow subWindow1;
	private Text text1;
	private Text text2;
	private Event event;
	private Color backColor;
	private Camera camera;
	private Map map;
	private TextureManager tManager;
	private RandomUnitGenerator generator;
	private int selectID;
	private UnitManager uManager;
	private ActiveUnit selectedUnit;
	
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
		//initialize the windows
		VideoMode mode = new VideoMode(winWidth, winHeight);
		VideoMode mode2 = new VideoMode(600, 200);
		window = new RenderWindow(mode, title);
		subWindow1 = new RenderWindow(mode2, "Info");
		//initialize the map
		map = new Map(32, 32, "map.txt");
		
<<<<<<< HEAD
		camera = new Camera(0, 0, winWidth, winHeight);
=======
		//initialize the Actor
		actor = new Actor(0, 0, "sprite1.png");
		
		//initialize text
		arial = new Font();
		try {
			arial.loadFromFile(Paths.get("arial.ttf"));
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		text1 = new Text("You",arial,15);
		text2 = new Text("Are",arial,15);
		
		setViewToActor();
>>>>>>> origin/Josh-Fix
		generator = new RandomUnitGenerator(map);
		uManager = generator.getUnits();
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
					subWindow1.close();
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
<<<<<<< HEAD
				uManager.draw(window);
=======
<<<<<<< HEAD
				window.draw(actor.getActor());
				text1.setPosition(100,100);
				text2.setPosition(300,100);
				subWindow1.draw(text1);
				subWindow1.draw(text2);
				text1.setColor(new Color(255,255,255));
				text2.setColor(new Color(255,255,255));
=======
				generator.draw(window);
>>>>>>> origin/Josh-Fix
				
>>>>>>> origin/master
				window.display();
				subWindow1.display();
				text1.setString("an");
				text2.setString("idiot");
				subWindow1.clear();
				subWindow1.draw(text1);
				subWindow1.draw(text2);
				
				
				checkMousePosition();
			
			}
		}
	}
	
	private void checkMousePosition() {
		if (Mouse.isButtonPressed(Mouse.Button.LEFT)) {
			Point a = MouseMonitor.getMousePosition();
			System.out.println(a.getX() + " " + a.getY());
		}
	}
	
	private void getClickedUnit(){
		
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
			if(Keyboard.isKeyPressed(Key.SPACE)){
				selectID++;
				setViewToActor();
			}
		}
	}
	
	/**
	 * Method centers the camera view on a unit based on the selectID
	 */
	public void setViewToActor()
	{
		//check if the selectID is over bounds
		if (selectID >= uManager.getLength())
		{
			selectID = 0;
		}
		
		//now get the active unit and center the camera on it
		selectedUnit = uManager.getUnit(selectID);
		float xCenter = temp.getWidth() / 2;
		float yCenter = temp.getHeight() / 2;
		
		float xPos = temp.getX() + xCenter;
		float yPos = temp.getY() + yCenter;
		
		camera = new Camera(xPos, yPos, winWidth, winHeight);
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
