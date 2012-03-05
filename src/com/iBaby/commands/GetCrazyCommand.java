package com.iBaby.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.iBaby.Command;
import com.iBaby.iBaby;

public class GetCrazyCommand  extends Command {
		public void handle(CommandSender sender, String[] args) {
			Player p = (Player) sender;
			if(iBaby.getIronBabys(p.getName()).size() > 0) {
				(iBaby.getIronBabys(p.getName()).get(0)).setCrazy(true);
				sender.sendMessage("You are now nearly invincible!");
			}else{
				sender.sendMessage("You dont have an iron babysitter!");
			}
		}
}
