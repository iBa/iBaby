package com.iBaby.abilities;

import org.bukkit.Material;
import org.bukkit.craftbukkit.inventory.CraftItemStack;

import net.minecraft.server.ItemStack;

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
	/**
	 * Matches the itemstack
	 * @param itemstack
	 * @return Either the ability or null
	 */
	public static Ability matches(ItemStack itemstack) {
		Ability handle = null;
		/* LEATHER */
		if(new CraftItemStack(itemstack).getType() == Material.LEATHER_CHESTPLATE) {
				handle = new ArmorAbility(9);
		}
		else if(new CraftItemStack(itemstack).getType() == Material.LEATHER_BOOTS) {
				handle = new ArmorAbility(7);
		}
		else if(new CraftItemStack(itemstack).getType() == Material.LEATHER_HELMET) {
				handle = new ArmorAbility(6);
		}
		else if(new CraftItemStack(itemstack).getType() == Material.LEATHER_LEGGINGS) {
				handle = new ArmorAbility(8);
		}
		/* GOLD */
		else if(new CraftItemStack(itemstack).getType() == Material.GOLD_CHESTPLATE) {
				handle = new ArmorAbility(12);
		}
		else if(new CraftItemStack(itemstack).getType() == Material.GOLD_BOOTS) {
				handle = new ArmorAbility(10);
		}
		else if(new CraftItemStack(itemstack).getType() == Material.GOLD_HELMET) {
				handle = new ArmorAbility(8);
		}
		else if(new CraftItemStack(itemstack).getType() == Material.GOLD_LEGGINGS) {
				handle = new ArmorAbility(11);
		}
		/* CHAIN */
		else if(new CraftItemStack(itemstack).getType() == Material.CHAINMAIL_CHESTPLATE) {
				handle = new ArmorAbility(26);
		}
		else if(new CraftItemStack(itemstack).getType() == Material.CHAINMAIL_BOOTS) {
				handle = new ArmorAbility(21);
		}
		else if(new CraftItemStack(itemstack).getType() == Material.CHAINMAIL_HELMET) {
				handle = new ArmorAbility(18);
		}
		else if(new CraftItemStack(itemstack).getType() == Material.CHAINMAIL_LEGGINGS) {
				handle = new ArmorAbility(25);
		}
		/* Iron @NERF */
		else if(new CraftItemStack(itemstack).getType() == Material.IRON_CHESTPLATE) {
				handle = new ArmorAbility(17);
		}
		else if(new CraftItemStack(itemstack).getType() == Material.IRON_BOOTS) {
				handle = new ArmorAbility(14);
		}
		else if(new CraftItemStack(itemstack).getType() == Material.IRON_HELMET) {
				handle = new ArmorAbility(11);
		}
		else if(new CraftItemStack(itemstack).getType() == Material.IRON_LEGGINGS) {
				handle = new ArmorAbility(15);
		}
		/* Diamond @NERF2 */
		else if(new CraftItemStack(itemstack).getType() == Material.DIAMOND_CHESTPLATE) {
				handle = new ArmorAbility(26);
		}
		else if(new CraftItemStack(itemstack).getType() == Material.DIAMOND_BOOTS) {
				handle = new ArmorAbility(21);
		}
		else if(new CraftItemStack(itemstack).getType() == Material.DIAMOND_HELMET) {
				handle = new ArmorAbility(18);
		}
		else if(new CraftItemStack(itemstack).getType() == Material.DIAMOND_LEGGINGS) {
				handle = new ArmorAbility(23);
		}
		return handle;
	}
}
