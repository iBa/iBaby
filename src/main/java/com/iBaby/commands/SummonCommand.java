package com.iBaby.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
				if(param("amount") != null) {
					int c = 0;
					for(c = 0; c < Integer.parseInt(param("amount")); c++) {
						iBaby.spawnIronBaby(p.getLocation(), p);
					}
					sender.sendMessage("Now you have " + c + " iron babysitters!");
				}else{
					iBaby.spawnIronBaby(p.getLocation(), p);
					sender.sendMessage("Now you have an iron babysitter!");
				}
			}else{
				sender.sendMessage("You have already " + Configuration.max + " iron babysitters!");
			}
	}
}
