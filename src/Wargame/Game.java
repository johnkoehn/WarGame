package Wargame;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.ContextActivationException;
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
	private Camera camera;
	private Map map;
	private TextureManager tManager;
	private RandomUnitGenerator generator;
	private int selectID;
	private ActiveUnit currentUnit;
	private UnitManager uManager;
	private UnitDisplay unitWindow;
	private final int MAP_SIZE = 300;
	private final int cameraSpeed = MAP_SIZE / 2;
	

	// private Reticule reticule;

	/***
	 * @param fwinWidth
	 * @param fwinHeight
	 * @param title
	 *            Give the windows width, height and title
	 * @throws IOException
	 */
	Game(int fwinWidth, int fwinHeight, String title) throws IOException {
		winWidth = fwinWidth;
		winHeight = fwinHeight;

		backColor = new Color(112, 48, 160); // purple

		// initialize the window
		VideoMode mode = new VideoMode(winWidth, winHeight);
		window = new RenderWindow(mode, title);

		// initialize the map
		// map = new Map(32, 32, "map.txt");
		map = new Map(32, 32, MAP_SIZE, MAP_SIZE);

		generator = new RandomUnitGenerator(map);
		uManager = generator.getUnits();

		camera = new Camera(0, 0, winWidth, winHeight);
		selectID = 0;
		setViewToActor();

		// RandomMapGenerator.makeMap(50, 50);
		// reticule = new Reticule(MouseMonitor.getMousePosition(window));
	}

	int getWidth() {
		return winWidth;
	}

	int getHeight() {
		return winHeight;
	}

	/**
	 * This function will keep looping until we quit the game
	 * @throws FileNotFoundException 
	 * @throws ContextActivationException 
	 */
	void runGame() throws FileNotFoundException, ContextActivationException {
		unitWindow = new UnitDisplay(window);
		window.setFramerateLimit(60);
		
		while (window.isOpen()) {
			
			// check for new window events that occurred since the last loop
			// iteration
			event = window.pollEvent();
			
			
			if (event != null) {
				// if a close requested event occurs, close the window
				if (event.type == Type.CLOSED) {
					window.close();
				}

				// check for input
				checkInput();

				// clear the window and prep for the new frame
				window.clear(backColor);

				// set the view
				window.setView(camera.getView());

				// draw the map
				displayMap();

				// draw the Actors
				uManager.draw(window);

				window.display();
				unitWindow.drawUnitInfo(currentUnit);
				unitWindow.displayWindow();
				

				// checkMousePosition();
				// getMousePosition();

				if (Mouse.isButtonPressed(Mouse.Button.LEFT)) {
					selectClickedUnit(getMousePosition());
				}

			}
		}
	}

	@SuppressWarnings("unused")
	private void checkMousePosition() {
		if (Mouse.isButtonPressed(Mouse.Button.LEFT)) {
			Point a = MouseMonitor.getMousePosition(window);
//			System.out.println(a.getX() + " " + a.getY());
		}
	}

	private Point getMousePosition() {
		Point p = MouseMonitor.getMousePosition(window);
//		System.out.println("---" + p.getXTile() + " " + p.getYTile() + "---");
		return p;
	}

	private void selectClickedUnit(Point p) {

		// System.out.println("***" + p.getXTile() + " " + p.getYTile() +
		// "***");
		for (int i = 0; i < uManager.getLength(); i++) {

			if (uManager.getUnit(i).getPoint().getXTile() == p.getXTile()
					&& uManager.getUnit(i).getPoint().getYTile() == p
							.getYTile()) {
				selectID = i;
				currentUnit = uManager.getUnit(selectID);
//				System.out.println("TRUE");
				return;
			}
		}
//		System.out.println("FALSE");
	}

	private void checkInput() throws FileNotFoundException {
		if (event.type == Type.KEY_PRESSED) {

			// camera moving commands
			if (Keyboard.isKeyPressed(Key.W)) {
				// actor.updateY((float) -1);
				camera.update(0, -1 * cameraSpeed);
				// System.out.println(camera.getX() + " " + camera.getY());
			}
			if (Keyboard.isKeyPressed(Key.S)) {
				// actor.updateY((float) 1);
				camera.update(0, cameraSpeed);
				// System.out.println(camera.getX() + " " + camera.getY());
			}
			if (Keyboard.isKeyPressed(Key.A)) {
				// actor.updateX((float) -1);
				camera.update(-1 * cameraSpeed, 0);
				// System.out.println(camera.getX() + " " + camera.getY());
			}
			if (Keyboard.isKeyPressed(Key.D)) {
				// actor.updateX((float) 1);
				camera.update(cameraSpeed, 0);
				// System.out.println(camera.getX() + " " + camera.getY());
			}

			// zooming commands
			if (Keyboard.isKeyPressed(Key.X)) {
				camera.zoom((float) 2);
			}
			if (Keyboard.isKeyPressed(Key.Z)) {
				camera.zoom((float) .5);
			}

			// setting view commands
			if (Keyboard.isKeyPressed(Key.C)) {
				setViewToActor();
			}
			if (Keyboard.isKeyPressed(Key.SPACE)) {
				selectID++;
				setViewToActor();
			}

			// moving active unit commands
			if (Keyboard.isKeyPressed(Key.UP)) {
				currentUnit.moveUp();
			}
			if (Keyboard.isKeyPressed(Key.DOWN)) {
				currentUnit.moveDown();
			}
			if (Keyboard.isKeyPressed(Key.LEFT)) {
				currentUnit.moveLeft();
			}
			if (Keyboard.isKeyPressed(Key.RIGHT)) {
				currentUnit.moveRight();
			}
			
			// refresh map
			if (Keyboard.isKeyPressed(Key.P)) {
				map.newMap();
				displayMap();
			}
		}
	}

	/**
	 * Method centers the camera view on a unit based on the selectID
	 */
	public void setViewToActor() {
		// check if the selectID is over bounds
		if (selectID >= uManager.getLength()) {
			selectID = 0;
		}

		// now get the active unit and center the camera on it
		currentUnit = uManager.getUnit(selectID);
		float xCenter = currentUnit.getWidth() / 2;
		float yCenter = currentUnit.getHeight() / 2;

		float xPos = currentUnit.getX() + xCenter;
		float yPos = currentUnit.getY() + yCenter;

		camera = new Camera(xPos, yPos, winWidth, winHeight);
	}

	/**
	 * Function displays the map
	 */
	public void displayMap() {
		map.draw(window);
	}

	public static void main(String args[]) throws IOException, ContextActivationException {
		Game game = new Game(800, 600, "WarGame!");
		game.runGame();
	}

}
