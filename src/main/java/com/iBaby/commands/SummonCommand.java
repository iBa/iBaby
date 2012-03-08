package com.iBaby.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import com.iBaby.Command;
import com.iBaby.Configuration;
import com.iBaby.iBaby;

public class SummonCommand extends Command {
	public SummonCommand() {
		permissions.add("ibaby.summon");
		addOptionalParam("amount");
	}
	/**
	 * Spawns a new golem
	 */
	public void handle(CommandSender sender) {
			Player p = (Player) sender;
			if(iBaby.getIronBabys(p.getName()).size() < Configuration.max) {
				if(!param("amount").isEmpty()) {
					int c = 0;
					if(Configuration.enablePrice) {
						double amount = Configuration.price * Integer.parseInt(param("amount"));
						if(iBaby.economy.has(p.getName(), amount)) {
							iBaby.economy.withdrawPlayer(p.getName(), amount);
							sender.sendMessage(ChatColor.GREEN + " You paid " + amount);
						}else{
							sender.sendMessage(ChatColor.RED + " You don't have " + amount + " money");
							return;
						}
					}
					for(c = 0; c < Integer.parseInt(param("amount")); c++) {
						iBaby.spawnIronBaby(p.getLocation(), p);
					}
					sender.sendMessage("Now you have " + c + " iron babysitters!");
				}else{
					if(Configuration.enablePrice) {
						double amount = Configuration.price;
						if(iBaby.economy.has(p.getName(), amount)) {
							iBaby.economy.withdrawPlayer(p.getName(), amount);
							sender.sendMessage(ChatColor.GREEN + " You paid " + amount);
						}else{
							sender.sendMessage(ChatColor.RED + " You don't have " + amount + " money");
							return;
						}
					}
					iBaby.spawnIronBaby(p.getLocation(), p);
					sender.sendMessage("Now you have an iron babysitter!");
				}
			}else{
				sender.sendMessage("You have already " + Configuration.max + " iron babysitters!");
			}
	}
	
	public String getDescription() {
		return "Summons one or more iBabys";
	}
}
