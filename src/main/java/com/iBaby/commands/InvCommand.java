package com.iBaby.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.iBaby.Command;
import com.iBaby.iBaby;
import com.iBaby.reflection.EntityIronBaby;

public class InvCommand extends Command {
	/**
	 * Mange the inventory of the iBaby
	 */
	public void handle(CommandSender sender) {
		EntityIronBaby target = null;
		if((target = iBaby.getSelectedBaby(((Player) sender).getName())) != null) {
			CraftPlayer p = ((CraftPlayer)sender);
			p.getHandle().openContainer(target.getIInventory());
		}else{
			sender.sendMessage(ChatColor.YELLOW + "Please select an iBaby by attacking it!");
		}
	}
	
	public String getDescription()
	{
		return "Shows the inventory of a selected iBaby";
	}
}
