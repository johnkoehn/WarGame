package Wargame;

import java.io.IOException;

public class Unit extends Actor {

	private int name;
	private int unitID;
	private int type;
	private int subtype;

	private int attack;
	private int range;
	private String sprite;

	private int currentHealth;
	private int totalHealth;

	private int currentMovePoints;
	private int totalMovePoints;

	private int currentFuel;
	private int totalFuel;

	private int totalAmmo;
	private int currentAmmo;

	private int weaknessID;
	private double weaknessMult;

	// public Unit(int nextInt, int nextInt2, int nextInt3, int nextInt4,
	// int nextInt5, int nextInt6, int nextInt7, int nextInt8,
	// int nextInt9, int nextInt10, int nextInt11, double nextDouble,
	// int nextInt12, int nextInt13) {
	// // TODO Auto-generated constructor stub
	// }

	public Unit(String givenName, int givenUnitID, int givenType, int givenSubType,
			int givenHealth, int givenMovePoints, int givenFuel, int givenAmmo,
			int givenAttack, int givenRange, int givenWeaknessID,
			double givenWeaknessMult, float fxPos, float fyPos,
			String textureFile) throws IOException {
		super(fxPos, fyPos, textureFile);

		unitID = givenUnitID;
		type = givenType;
		subtype = givenSubType;

		currentHealth = givenHealth;
		totalHealth = givenHealth;

		currentMovePoints = givenMovePoints;
		totalMovePoints = givenMovePoints;

		currentFuel = givenFuel;
		totalFuel = givenFuel;

		currentAmmo = givenAmmo;
		totalAmmo = givenAmmo;

		attack = givenAttack;
		range = givenRange;

		weaknessID = givenWeaknessID;
		weaknessMult = givenWeaknessMult;

	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public int getTotalHealth() {
		return totalHealth;
	}

	public int getCurrentMovePoints() {
		return currentMovePoints;
	}

	public int getTotalMovePoints() {
		return totalMovePoints;
	}

	public int getCurrentFuel() {
		return currentFuel;
	}

	public int getTotalFuel() {
		return totalFuel;
	}

	public int getCurrentAmmo() {
		return currentAmmo;
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

	public void deductHealth(int loss) {
		if (loss > 0) {
			currentHealth = Math.max(0, currentHealth - loss);
		}
	}

	public void restoreHealth(int gain) {
		if (gain > 0) {
			currentHealth = Math.min(totalHealth, currentHealth + gain);
		}
	}

	public void fullHealth() {
		currentHealth = totalHealth;
	}

	public void deductMovePoints(int loss) {
		if (loss > 0) {
			currentMovePoints = Math.max(0, currentMovePoints - loss);
		}
	}

	public void resetMovePoints() {
		currentMovePoints = totalMovePoints;
	}

	public void deductFuel(int loss) {
		if (loss > 0) {
			currentFuel = Math.max(0, currentFuel - loss);
		}
	}

	public void restoreFuel(int gain) {
		if (gain > 0) {
			currentFuel = Math.min(totalFuel, currentFuel + gain);
		}
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
