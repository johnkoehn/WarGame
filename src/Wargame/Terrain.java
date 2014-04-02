package Wargame;

public class Terrain {
private int defense;
private int id;
private int subId;
private int color;
private boolean occupied;

public Terrain(int defense, int id, int subId, int color)
{this.defense = defense;
this.id = id;
this.subId = subId;
this.color = color;
this.occupied = false;
}
//access defense field
public int getDefense()
{return defense;}
//access ID field
public int getId()
{return id;}
//access subID field
public int getSubId()
{return subId;}
//access color field
public int getColor()
{return color;}
//returns whether or not the tile is occupied
public boolean isOccupied()
{return occupied;}
//make the tile occupied
public void makeOccupied()
{occupied = true;}
public void makeUnOccupied()
{occupied = false;}
}
