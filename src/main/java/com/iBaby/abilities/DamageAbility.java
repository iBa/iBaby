package com.iBaby.abilities;

import net.minecraft.server.ItemStack;

import org.bukkit.Material;
import org.bukkit.craftbukkit.inventory.CraftItemStack;

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
	
	/**
	 * Matches the itemstack
	 * @param itemstack
	 * @return Either the ability or null
	 */
	public static Ability matches(ItemStack itemstack) {
		Ability handle = null;
		CraftItemStack stack = new CraftItemStack(itemstack);
		/* WOOD */
		if(stack.getType() == Material.WOOD_SWORD) {
			handle = new DamageAbility(2);
		}
		else if(stack.getType() == Material.WOOD_AXE) {
			handle = new DamageAbility(1);
		}
		else if(stack.getType() == Material.WOOD_PICKAXE) {
			handle = new DamageAbility(1);
		}
		/* GOLD */
		else if(stack.getType() == Material.GOLD_SWORD) {
			handle = new DamageAbility(2);
		}
		else if(stack.getType() == Material.GOLD_AXE) {
			handle = new DamageAbility(1);
		}
		else if(stack.getType() == Material.GOLD_PICKAXE) {
			handle = new DamageAbility(1);
		}
		/* STONE */
		else if(stack.getType() == Material.STONE_SWORD) {
			handle = new DamageAbility(2);
		}
		else if(stack.getType() == Material.STONE_AXE) {
			handle = new DamageAbility(1);
		}
		else if(stack.getType() == Material.STONE_PICKAXE) {
			handle = new DamageAbility(1);
		}
		else if(stack.getType() == Material.STONE_SPADE) {
			handle = new DamageAbility(1);
		}
		/* IRON */
		else if(stack.getType() == Material.IRON_SWORD) {
			handle = new DamageAbility(3);
		}
		else if(stack.getType() == Material.IRON_AXE) {
			handle = new DamageAbility(2);
		}
		else if(stack.getType() == Material.IRON_PICKAXE) {
			handle = new DamageAbility(2);
		}
		else if(stack.getType() == Material.IRON_SPADE) {
			handle = new DamageAbility(1);
		}
		/* DIAMOND */
		else if(stack.getType() == Material.DIAMOND_SWORD) {
			handle = new DamageAbility(4);
		}
		else if(stack.getType() == Material.DIAMOND_AXE) {
			handle = new DamageAbility(3);
		}
		else if(stack.getType() == Material.DIAMOND_PICKAXE) {
			handle = new DamageAbility(2);
		}
		else if(stack.getType() == Material.DIAMOND_SPADE) {
			handle = new DamageAbility(2);
		}
		return handle;
	}
}
