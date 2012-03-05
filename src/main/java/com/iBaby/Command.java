package com.iBaby;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * A raw command
 * @author steffengy
 *
 */
public class Command {
	protected HashMap<String, String> arguments = new HashMap<String, String>();
	private HashMap<Integer, String> neededParams = new HashMap<Integer, String>();
	private HashMap<Integer, String> optionalParams = new HashMap<Integer, String>();
	protected ArrayList<String> permissions = new ArrayList<String>();
	protected boolean requiresPlayer = true;
	private int i = 0;
	
	/**
	 * Handle a command
	 * @param args The arguments 2 give to the command
	 */
	public void handle(CommandSender sender) {}
	
	/**
	 * Called before the command gets actually handeled
	 * @param sender The CommandSender (excecutor)
	 * @param args His params
	 * @return
	 */
	public boolean preHandle(CommandSender sender, String[] args) {
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
		if(args.length < neededParams.size() || (neededParams.size() + optionalParams.size()) < args.length ) {
			sender.sendMessage(ChatColor.RED + "Your arguments are invalid!");
			return false;
		}
		// Match params
		int arg = 0;
		int c = 0;
		for(int i = 0; arg < args.length; i++) {
			if(neededParams.containsKey(i)) {
				//Its a needed one so just add it
				arguments.put(neededParams.get(i), args[arg]);
				arg++;
			}else{
				if(optionalParams.containsKey(i) && args.length >= (neededParams.size() + 1 + c)) {
					arguments.put(optionalParams.get(i), args[i]);
					c++;
					arg++;
				}
			}
		}
		return true;
	}
	
	/**
	 * Adds a needed param
	 * @param name The name, with which this entry shall got later
	 */
	public void addParam(String name) {
		neededParams.put(i, name);
		i++;
	}
	/**
	 * Adds a optional param
	 * @param name The name, with which this entry shall got later
	 */
	public void addOptionalParam(String name) {
		optionalParams.put(i, name);
		i++;
	}
	
	/**
	 * Lazy method for getting params
	 * @param name The name of the param
	 * @return value of param as String
	 */
	public String param(String name) {
		if(arguments.containsKey(name))
			return arguments.get(name);
		return null;
	}
	
}
