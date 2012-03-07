package com.iBaby.commands;

import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.iBaby.Command;

public class HelpCommand extends Command {
	public HelpCommand() {
		requiresPlayer = false;
	}
	/**
	 * Displays a list of all commands
	 */
	public void handle(CommandSender sender) {
		sender.sendMessage(ChatColor.YELLOW + "Help of /"+getRoot().getName());
		for(Entry<String, Command> e : getRoot().registers.entrySet()) {
			String args = "";
			for(Param param : e.getValue().getParamList()) {
				String brace = (param.isNeeded() ? "[" : "(") + (param.isNeeded() ? "]" : ")");
				args +=  brace.charAt(0) + param.getName() + brace.charAt(1);
			}
			sender.sendMessage(ChatColor.YELLOW + "/" + getRoot().getName() + " " + ChatColor.GRAY + e.getKey() + " " + args + " " + e.getValue().getDescription());
		}
	}
	
	public String getDescription() {
		return "Displays the help";
	}
}
