package com.iBaby;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.craftbukkit.inventory.CraftItemStack;

import net.minecraft.server.ItemStack;
import net.minecraft.server.PathfinderGoalMeleeAttack;
import net.minecraft.server.PathfinderGoalMoveTowardsTarget;
import net.minecraft.server.PathfinderGoalSelector;

import com.iBaby.abilities.Ability;
import com.iBaby.abilities.ArmorAbility;
import com.iBaby.abilities.SprintAbility;
import com.iBaby.abilities.ThrowAbility;
import com.iBaby.reflection.EntityIronBaby;
import com.iBaby.reflection.PathfinderFollowBaby;

/**
 * Class which manages all special abilities of iBabys
 * @author steffengy
 *
 */
public class iBabyAbilities {
	private ArrayList<Ability> abilities = new ArrayList<Ability>();
	private EntityIronBaby entity;
	
	/**
	 * Public Constructor
	 * @param baby The entity which represents an iBaby
	 */
	public iBabyAbilities(EntityIronBaby baby) {
		this.entity = baby;
	}
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
		Ability tmparmor = null;
		/*
		 * @ABILITY
		 * Feather : Throw
		 */
		if(new CraftItemStack(itemstack).getType() == Material.FEATHER) {
				handle = new ThrowAbility();
		}
		/**
		 * @ABILITY
		 * Water-Bucket:Sprint
		 */
		else if(new CraftItemStack(itemstack).getType() == Material.WATER_BUCKET) {
				handle = new SprintAbility();
		}
		/**
		 * @ABILITY
		 * Chestplate: Armor
		 * Calculation of Damage Value: Durabilty / 9 or if cheap (@CHEAP) material Durability / 14
		 */
		else if((tmparmor = ArmorAbility.matches(itemstack)) != null) {
			handle = tmparmor;
		}
		
		if(handle != null) {
			if(add)
				this.addAbility(handle);
			else
				this.removeAbility(handle);
		}
		updatePathFinders();
	}

	/**
	 * Update the path finders
	 */
	@SuppressWarnings("unchecked")
	private void updatePathFinders() {
		float additionalMelee = getAdditionalMeleeMovementSpeed();
		float additionalFollow = getAdditionalFollowBabySpeed();
		float additionalTowards = getAdditionalMoveTowardsTargetSpeed();
		//Reflection...
		PathfinderGoalSelector GS = entity.getGoalSelector();
		try{
			Field aField = GS.getClass().getDeclaredField("a");
			aField.setAccessible(true);
			@SuppressWarnings("rawtypes")
			ArrayList a = (ArrayList) aField.get(GS);
			a.set(1, new PathfinderGoalMeleeAttack(this.entity, 0.42F + additionalMelee, true));
			a.set(2, new PathfinderGoalMoveTowardsTarget(this.entity, 0.37F + additionalTowards, 32.0F));
			a.set(3, new PathfinderFollowBaby(this.entity, 0.3F + additionalFollow, 5.0F, 4.0F));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Ability> get() {
		return new ArrayList<Ability>((ArrayList<Ability>) this.abilities.clone());
	}
}
