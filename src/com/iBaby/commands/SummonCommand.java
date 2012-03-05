package com.iBaby.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.iBaby.Command;
import com.iBaby.Configuration;
import com.iBaby.iBaby;

public class SummonCommand extends Command {
	public void handle(CommandSender sender, String[] args) {
			Player p = (Player) sender;
			if(iBaby.getIronBabys(p.getName()).size() < Configuration.max) {
				if(args.length >= 1 && Integer.parseInt(args[0]) > 0) {
					int c = 0;
					for(c = 0; c < Integer.parseInt(args[0]); c++) {
						iBaby.spawnIronBaby(p.getLocation(), p);
					}
					sender.sendMessage("Now you have " + c + " iron babysitter!");
				}else{
					iBaby.spawnIronBaby(p.getLocation(), p);
					sender.sendMessage("Now you have an iron babysitter!");
				}
			}else{
				sender.sendMessage("You have already an iron babysitter!");
			}
	}
}
