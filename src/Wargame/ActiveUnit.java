package Wargame;

import java.io.IOException;

public class ActiveUnit extends Actor {

	private Unit u;
	
	private int currentHealth;
	private int currentMovePoints;
	private int currentFuel;
	private int currentAmmo;


	public ActiveUnit(Unit givenUnit, float fxPos, float fyPos, String textureFile) throws IOException {
		
		super(fxPos, fyPos, textureFile);

		u = givenUnit;
		currentHealth = u.getTotalHealth();
		currentFuel = u.getTotalFuel();
		currentAmmo = u.getTotalAmmo();
		currentMovePoints = u.getTotalMovePoints();
		

	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public int getTotalHealth() {
		return u.getTotalHealth();
	}

	public int getCurrentMovePoints() {
		return currentMovePoints;
	}

	public int getTotalMovePoints() {
		return u.getTotalMovePoints();
	}

	public int getCurrentFuel() {
		return currentFuel;
	}

	public int getTotalFuel() {
		return u.getTotalFuel();
	}

	public int getCurrentAmmo() {
		return currentAmmo;
	}

	public int getTotalAmmo() {
		return u.getTotalAmmo();
	}

	public int getAttack() {
		return u.getAttack();
	}

	public int getRange() {
		return u.getRange();
	}

	public int getUnitID() {
		return u.getUnitID();
	}

	public int getType() {
		return u.getType();
	}

	public int getSubType() {
		return u.getSubType();
	}

	public int getWeaknessID() {
		return u.getWeaknessID();
	}

	public double getWeaknessMult() {
		return u.getWeaknessMult();
	}

	public void deductHealth(int loss) {
		if (loss > 0) {
			currentHealth = Math.max(0, currentHealth - loss);
		}
	}

	public void restoreHealth(int gain) {
		if (gain > 0) {
			currentHealth = Math.min(u.getTotalHealth(), currentHealth + gain);
		}
	}

	public void fullHealth() {
		currentHealth = u.getTotalHealth();
	}

	public void deductMovePoints(int loss) {
		
		if (loss > 0) {
			currentMovePoints -= loss;
			if (currentMovePoints < 0)
			{
				currentMovePoints = 0;
			}
		}
	}

	public void resetMovePoints() {
		currentMovePoints = u.getTotalMovePoints();
	}

	public void deductFuel(int loss) {
		if (loss > 0) {
			currentFuel = Math.max(0, currentFuel - loss);
		}
	}

	public void restoreFuel(int gain) {
		if (gain > 0) {
			currentFuel = Math.min(u.getTotalFuel(), currentFuel + gain);
		}
	}

	public double isWeak(int IDtoCheck) {
		if (IDtoCheck == u.getWeaknessID()) {
			return u.getWeaknessMult();
		} else {
			return 1;
		}
	}

	public String toString() {
		return "Unit ID: " + u.getUnitID() + " Attack: " + u.getAttack();
	}
	
	public boolean isAlive(){
		return currentHealth > u.getTotalHealth();
	}

	public String getGivenName() {
		return u.getName();
	}
	
	/**
	 * Unit movement commands
	 */
	public boolean moveLeft()
	{
		if ((currentMovePoints - 10) >= 0)
		{
			updateX(-32);
			currentMovePoints -= 10;
			
			return true;
		}
		return false;
	}
	
	public boolean moveRight()
	{
		if ((currentMovePoints - 10) >= 0)
		{
			updateX(32);
			currentMovePoints -= 10;
			
			return true;
		}
		return false;
	}
	
	public boolean moveUp()
	{
		if ((currentMovePoints - 10) >= 0)
		{
			updateY(-32);
			currentMovePoints -= 10;
			
			return true;
		}
		return false;
	}
	
	public boolean moveDown()
	{
		if ((currentMovePoints - 10) >= 0)
		{
			updateY(32);
			currentMovePoints -= 10;
			
			return true;
		}
		return false;
	}
	
	
	
	
	
	
	
}
