package Wargame;

import java.util.ArrayList;

public class CollisionManager {
	private ArrayList<Tile> tiles;
	private int mapWidth;
	private int mapHeight;
	
	public CollisionManager(Map map)
	{
		tiles = map.getTileList();
		mapWidth = map.mapWidthTiles();
		mapHeight = map.mapHeightTiles();
	}
	
	/**
	 * deltaX and deltaY are the units change tile coordinates
	 * @param unit
	 * @param deltaX
	 * @param deltaY
	 */
	public void moveUnit(ActiveUnit unit, int deltaX, int deltaY)
	{
		//check if the new tile coordinates have a unit occupied
		Point currentLocation = unit.getPoint();
		Point newLocation = new Point(currentLocation.getX(), currentLocation.getY());
		newLocation.changeX(deltaX * 32);
		newLocation.changeY(deltaY * 32);
		
		
		int index = (newLocation.getXTile() * mapHeight) + newLocation.getYTile();
		
		//check that index dosen't exceed array size
		if (index >= tiles.size() || index < 0)
		{
//			System.out.println("Error, index exceeds ArrayList tiles bounds -- value: " + index);
//			System.out.println("x: " + newLocation.getXTile() + " y: " + newLocation.getYTile());
			return;
		}
		
		//if the tile is not occupied and the unit has sufficient movement points move the unit
		if (!tiles.get(index).isThisOccupied() && (unit.getCurrentMovePoints() - 10) >= 0)
		{
			//move the unit
			unit.updatePoint(newLocation);
			
			//set the new position to being occupied
			tiles.get(index).setOccupied();
			
			//set the old location to not occupied
			index = (currentLocation.getXTile() * mapHeight) + currentLocation.getYTile();
//			System.out.println(index);
			tiles.get(index).setNotOccupied();
			
			//subtract movement
			unit.deductMovePoints(10);
		}
	}

}
