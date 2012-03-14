package com.iBaby.abilities;

/**
 * Will let the golem behave like a Thornmail, +6 life
 * @author steffengy
 *
 */
public class ArmorAbility extends Ability {
	private int health = 0;
	
	public ArmorAbility(int h) {
		this.health = h;
	}
	public int getAdditionalHealth() {
		return health;
	}
}
