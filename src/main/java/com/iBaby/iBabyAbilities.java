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
import com.iBaby.abilities.DamageAbility;
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
	 * Gets the additional damage of an entity
	 * @return int
	 */
	public int getAdditionalDamage() {
		int basic = 0;
		for(Ability ability : abilities) {
			basic += ability.getAdditionalDamage();
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
		Ability tmp = null;
		/*
		 * @ABILITY
		 * Feather : ThrowAbility
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
		 * Armor: ArmorAbility
		 * Calculation of Damage Value: Durabilty / 9 
		 * or if cheap (@NERF) material Durability / 14
		 * or if too OP (@NERF2) material Durability / 20
		 */
		else if((tmp = ArmorAbility.matches(itemstack)) != null) {
			handle = tmp;
		}
		/**
		 * @ABILITY
		 * Weapons: DamageAbility
		 * Calculation of Damage Value: hearts NO FLOATS
		 */
		else if((tmp = DamageAbility.matches(itemstack)) != null) {
			handle = tmp;
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
