package Wargame;

public class World {
//shell class

/*Things that go here:
 * Methods that deal with interaction between actors
 * Methods that deal with interaction between actors and terrain
 */
	public World()
	{}
	/**
	 * Method to simulate unit attacking. For counter-attacks, simply call deduct health method again with the parameters reversed.
	 * @param attacker the attacking unit
	 * @param defender the defending unit
	 */
	public void dealDamage(Unit attacker, Unit defender)
	{defender.deductHealth(damageGiven(attacker,defender));}

	/*Helper method to calculate the damage value for an attack or public method to return predictive damage value for UI
	 * to implement terrain defense values, need a way to select a space on the map and access
	 * the unit + terrain objects
	 */
	
	public int damageGiven(Unit attacker, Unit defender)
	{return (1*attacker.getAttack()*(attacker.getCurrentHealth()/attacker.getTotalHealth()));
	//to do: defender's defense mod, terrain defense mod
	}
}
