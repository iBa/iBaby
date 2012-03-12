package com.iBaby;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.craftbukkit.inventory.CraftItemStack;

import net.minecraft.server.ItemStack;

import com.iBaby.abilities.Ability;
import com.iBaby.abilities.ThrowAbility;

/**
 * Class which manages all special abilities of iBabys
 * @author steffengy
 *
 */
public class iBabyAbilities {
	private ArrayList<Ability> abilities = new ArrayList<Ability>();
	
	/**
	 * Adds an ability
	 * @param ability instanceof Ability
	 */
	public void addAbility(Ability ability) {
		abilities.add(ability);
	}
	
	/**
	 * Removes an ability
	 * @param ability instanceof Ability
	 */
	public void removeAbility(Ability ability) {
		abilities.remove(ability);
	}
	
	/**
	 * Gets the additional maximum health of an entity
	 * @return int
	 */
	public int getAdditionalHealth() {
		int basic = 0;
		for(Ability ability : abilities) {
			basic += ability.getAdditionalHealth();
		}
		return basic;
	}
	
	/**
	 * Gets the additional height, the golem will throw entitys 
	 * @return Float, see above
	 */
	public float getAdditionalThrowHeight() {
		float basic = 0;
		for(Ability ability : abilities) {
			basic += ability.getAdditionalThrowHeight();
		}
		return basic;
	}
	
	/**
	 * Gets the additional movement speed, the golem will do on melee attacks
	 * @return float, see above
	 */
	public float getAdditionalMeleeMovementSpeed() {
		float basic = 0;
		for(Ability ability : abilities) {
			basic += ability.getAdditionalMeleeMovementSpeed();
		}
		return basic;
	}
	/**
	 * Gets the additional movement speed, the golem will have while moving towards a target
	 * @return float, see above
	 */
	public float getAdditionalMoveTowardsTargetSpeed() {
		float basic = 0;
		for(Ability ability : abilities) {
			basic += ability.getAdditionalMoveTowardsTargetSpeed();
		}
		return basic;
	}
	/**
	 * Gets the additional movement speed, the golem will have while following its baby (baby = the player (owner))
	 * @return float, see above
	 */
	public float getAdditionalFollowBabySpeed() {
		float basic = 0;
		for(Ability ability : abilities) {
			basic += ability.getAdditionalFollowBabySpeed();
		}
		return basic;
	}

	/**
	 * Match abilities - Items
	 * @param itemstack
	 */
	public void updateAbilities(ItemStack itemstack, boolean add) {
		Ability handle = null;
		/*
		 * @ABILITY
		 * Feather : Throw
		 */
		if(new CraftItemStack(itemstack).getType() == Material.FEATHER) {
				handle = new ThrowAbility();
		}
		if(handle != null) {
			if(add)
				this.addAbility(handle);
			else
				this.removeAbility(handle);
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Ability> get() {
		return new ArrayList<Ability>((ArrayList<Ability>) this.abilities.clone());
	}
}
