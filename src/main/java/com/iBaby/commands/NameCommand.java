package com.iBaby.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.iBaby.Command;
import com.iBaby.iBaby;
import com.iBaby.reflection.EntityIronBaby;

/**
 * The name command
 * @author steffengy
 *
 */
public class NameCommand extends Command {
	public NameCommand() {
		addParam("name");
	}
	
	/**
	 * Names a golem
	 */
	public void handle(CommandSender sender) {
		EntityIronBaby target = null;
		if((target = iBaby.getSelectedBaby(((Player) sender).getName())) != null) {
			if(param("name").length() < 11) {
				target.setName(param("name"));
				sender.sendMessage("Name of selected iBaby changed!");
			}else{
				sender.sendMessage(ChatColor.RED + "Your name can't be longer than 10 chars!");
			}
		}else{
			sender.sendMessage(ChatColor.YELLOW + "Please select an iBaby by attacking it!");
		}
	}
	
	public String getDescription()
	{
		return "Give a selected iBaby a name";
	}
}
