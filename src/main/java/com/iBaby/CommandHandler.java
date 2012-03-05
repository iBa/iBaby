package com.iBaby;

import java.lang.reflect.Method;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Parses command to class
 * @author steffengy
 *
 */
public class CommandHandler {
	
	public static void handleCommand(CommandSender sender, String root, String sub, String[] args) {
		RootCommand r = RootCommand.get(root);
		if(r != null) {
			Command s  = r.getCommand().get(sub);
			if(s == null) {
				sender.sendMessage(ChatColor.RED + "Sub-Command not found!");
				return;
			}
			try {
				if(s.preHandle(sender, args)) {
					s.handle(sender);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else{
			sender.sendMessage(ChatColor.RED + "ROOTCommand not found!?!?!?");
		}
	}
}
