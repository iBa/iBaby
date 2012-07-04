package com.iBaby;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.iBaby.commands.Param;
import com.iBaby.commands.ParamMap;

/**
 * A raw command
 * @author steffengy
 *
 */
public class Command {
	protected HashMap<String, String> arguments = new HashMap<String, String>();
	private ParamMap<Integer, Param> Params = new ParamMap<Integer, Param>();
	protected ArrayList<String> permissions = new ArrayList<String>();
	protected boolean requiresPlayer = true;
	private int i = 0;
	private Base base;
	
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
		if(args.length < Params.countNeeded() || (Params.size()) < args.length ) {
			sender.sendMessage(ChatColor.RED + "Your arguments are invalid!");
			return false;
		}
		// Match params
		int arg = 0;
		int c = 0;
		for(int i = 0; arg < args.length; i++) {
			if(Params.containsKey(i) && Params.get(i).isNeeded()) {
				//Its a needed one so just add it
				arguments.put(Params.get(i).getName(), args[arg]);
				arg++;
			}else{
				if(Params.containsKey(i) && args.length >= (Params.countNeeded() + 1 + c)) {
					arguments.put(Params.get(i).getName(), args[arg]);
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
		Params.put(i, new Param(name, true));
		i++;
	}
	/**
	 * Adds a optional param
	 * @param name The name, with which this entry shall got later
	 */
	public void addOptionalParam(String name) {
		Params.put(i, new Param(name, false));
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
		return "";
	}
	/**
	 * Set what root command was used to call this
	 * @param root The root command
	 */
	public void setRoot(Base root) {
		this.base = root;
	}
	/**
	 * Gets what root command was used to call this
	 * @param root The root command
	 */
	public Base getRoot() {
		return base;
	}
	
	/**
	 * Returns the description of the Command
	 * @return String
	 */
	public String getDescription() {
		return "";
	}
	@SuppressWarnings("unchecked")
	public HashMap<String, String> getArgumentMap() {
		return (HashMap<String, String>) arguments.clone();
	}
	
	/**
	 * Gets a list of all params
	 * @return List<Param>
	 */
	public ArrayList<Param> getParamList() {
		ArrayList<Param> ret = new ArrayList<Param>();
		for(Entry<Integer, Param> a : Params.entrySet()) {
			ret.add(a.getValue());
		}
		return ret;
	}
}
