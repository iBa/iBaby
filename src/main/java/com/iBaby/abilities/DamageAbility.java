package com.iBaby.abilities;

/**
 * The additional damage a golem deals through weapons
 * @author steffengy
 *
 */
public class DamageAbility extends Ability {
	private int damage;
	
	public DamageAbility(int d) {
		this.damage = d;
	}
	
	@Override
	public int getAdditionalDamage() {
		return this.damage;
	}
}
