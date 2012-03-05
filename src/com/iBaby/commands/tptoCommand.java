package com.iBaby.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.iBaby.Command;
import com.iBaby.iBaby;

public class tptoCommand extends Command {
		public void handle(CommandSender sender, String[] args) {
				Player p = (Player) sender;
				if(iBaby.getIronBabys(p.getName()).size() > 0) {
					p.teleport(iBaby.getIronBabys(p.getName()).get(0).getBukkitLocation());
					sender.sendMessage("Teleported!");
				}else{
					sender.sendMessage("You don't have a golem!");
				}
		}
}
