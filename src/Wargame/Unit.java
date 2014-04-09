package Wargame;

import java.io.IOException;

public class Unit {

	private int name;
	private int unitID;
	private int type;
	private int subtype;

	private int attack;
	private int range;
	private String sprite;

	private int totalHealth;
	
	private int totalMovePoints;

	private int totalFuel;

	private int totalAmmo;

	private int weaknessID;
	private double weaknessMult;

	public Unit(String givenName, int givenUnitID, int givenType, int givenSubType,
			int givenHealth, int givenMovePoints, int givenFuel, int givenAmmo,
			int givenAttack, int givenRange, int givenWeaknessID,
			double givenWeaknessMult) throws IOException {

		unitID = givenUnitID;
		type = givenType;
		subtype = givenSubType;

		totalHealth = givenHealth;

		totalMovePoints = givenMovePoints;

		totalFuel = givenFuel;

		totalAmmo = givenAmmo;

		attack = givenAttack;
		range = givenRange;

		weaknessID = givenWeaknessID;
		weaknessMult = givenWeaknessMult;

	}

	public int getTotalHealth() {
		return totalHealth;
	}

	public int getTotalMovePoints() {
		return totalMovePoints;
	}

	public int getTotalFuel() {
		return totalFuel;
	}

	public int getTotalAmmo() {
		return totalAmmo;
	}

	public int getAttack() {
		return attack;
	}

	public int getRange() {
		return range;
	}

	public String getSprite() {
		return sprite;
	}

	public int getUnitID() {
		return unitID;
	}

	public int getType() {
		return type;
	}

	public int getSubType() {
		return subtype;
	}

	public int getWeaknessID() {
		return weaknessID;
	}

	public double getWeaknessMult() {
		return weaknessMult;
	}

	public double isWeak(int IDtoCheck) {
		if (IDtoCheck == weaknessID) {
			return weaknessMult;
		} else {
			return 1;
		}
	}

	public String toString() {
		return "Unit ID: " + unitID + " Attack: " + attack;
	}
}
