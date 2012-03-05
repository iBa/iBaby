package com.iBaby;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * A raw command
 * @author steffengy
 *
 */
public class Command {
	private static ArrayList<Object> arguments = new ArrayList<Object>();
	private static ArrayList<String> permissions = new ArrayList<String>();
	private static boolean requiresPlayer = true;
	/**
	 * Handle a command
	 * @param args The arguments 2 give to the command
	 */
	public void handle(CommandSender sender, String[] args) {}
	
	public boolean preHandle(CommandSender sender) {
		if(requiresPlayer) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "You need to be a player to execute tthis command!");
				return false;
			}
		}
		if(permissions.size() > 0) {
			boolean hasPerm = false;
			for(String perm : permissions) {
				if(sender.hasPermission(perm)) {
					hasPerm = true;
					break;
				}
			}
			if(!hasPerm) {
				sender.sendMessage(ChatColor.RED + "You don't have permission to do this!");
				return false;
			}
		}
		return true;
	}
	/** 
	 * Add arguments
	 * @param String argument
	 */
	public void addArgument(String[] arg) {
		arguments.add(arg);
	}
}
