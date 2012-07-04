package com.iBaby;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;
import net.minecraft.server.Chunk;
import net.minecraft.server.Entity;
import net.minecraft.server.World;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import com.iBaby.reflection.EntityIronBaby;

/**
 * The IBaby Main Class
 * @author steffengy
 *
 */

public class iBaby extends JavaPlugin {
	/**
	 * Containing the File object, which belongs to config.yml
	 */
	private static File configFile = null;
	public static Economy economy = null;
	/**
	 * Containing which iBaby a player selected for a custom command
	 */
	public static HashMap<String, EntityIronBaby> select = new HashMap<String, EntityIronBaby>();
	
	public void onEnable() {
		//Register entity :)
		try{
			 Class[] args = new Class[3];
			 args[0] = Class.class;
			 args[1] = String.class;
			 args[2] = int.class;
			 Method a = net.minecraft.server.EntityTypes.class.getDeclaredMethod("a", args);
			 a.setAccessible(true);
			 a.invoke(null, EntityIronBaby.class, "IronBabysitter", 99);
		 }catch(Exception e) {
			 log("failed registering EntityIronBaby at 99");
			 e.printStackTrace();
		 }
		getServer().getPluginManager().registerEvents(new iBabyListener(), this);
		//Loading config
		if(!getDataFolder().exists())
			if(!getDataFolder().mkdir())
				log("Error while mkdir of iBaby");
		configFile = new File(getDataFolder(), "config.yml");
		if(configFile.exists()) {
			Configuration.init(configFile);
		}else{
			try {
				configFile.createNewFile();
			} catch (IOException e) {
				log("Error while creating file config.yml!");
			}
			Configuration.createDefaultOne(configFile);
		}
		// Try to fetch economy module of vault
		if(Configuration.enablePrice) {
			if(!setupEconomy()) {
				log("deactivation price support because economy failed to load. You have vault running?");
				Configuration.enablePrice = false;
			}
		}
		// Loaded successfully
		log(getDescription().getVersion() + " loaded!");
	}
	
	public void onDisable() {
		log(getDescription().getVersion() + " loaded!");
	}
	/**
	 * Write something into log
	 * @param s The String 2 write
	 */
	public static void log(String s) {
		Logger.getLogger("Minecraft").info("[iBaby] " + s);
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) { 	
		String sub = args.length > 0 ? args[0] : "";
		List<String> arggs = new ArrayList<String>(Arrays.asList(args));
		if(arggs.size() > 0) arggs.remove(0);
		String[] arguments = arggs.size() > 0 ? arggs.toArray(new String[0]) : new String[0];
		CommandHandler.handleCommand(sender,  cmd.getName(), sub, arguments);
		return true; 
	}
	/**
	 * Creates a iron Baby at a location with an owner
	 * @param location The location
	 * @param name The player
	 */
	public static EntityIronBaby spawnIronBaby(Location location, Player player) {
		EntityIronBaby ib = new EntityIronBaby(((CraftWorld)location.getWorld()).getHandle(), player.getName());
		ib.getBukkitEntity().teleport(location);
		((CraftWorld)location.getWorld()).getHandle().addEntity(ib);
		return ib;
	}
	
	private Boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
	
	/**
	 * Gets a list of all iron babys in world
	 * @param world The world
	 * @return List<EntityIronBaby>
	 */
	public static List<EntityIronBaby> getIronBabys(World world) {
		ArrayList<EntityIronBaby> entities = new ArrayList<EntityIronBaby>();
		for (Object entity: world.entityList) {
            if (entity instanceof net.minecraft.server.Entity) {
            	if(entity instanceof EntityIronBaby) {
            		entities.add((EntityIronBaby)entity);
            	}
            }
		}
        return entities;
	}
	/**
	 * Gets a list of all iron babys in world by player
	 * @param world The world
	 * @param player The playername Case sensitive
	 * @return List<EntityIronBaby>
	 */
	public static List<EntityIronBaby> getIronBabys(World world, String player) {
		ArrayList<EntityIronBaby> entities = new ArrayList<EntityIronBaby>();
		for(EntityIronBaby entity : getIronBabys(world)) {
			if(entity.getOwner().equals(player)) entities.add(entity);
		}
		return entities;
	}
	/**
	 * Gets a list of all iron babys
	 * @return List<EntityIronBaby>
	 */
	public static List<EntityIronBaby> getIronBabys() {
		ArrayList<EntityIronBaby> entities = new ArrayList<EntityIronBaby>();
		for(org.bukkit.World w : Bukkit.getWorlds()) {
			entities.addAll(getIronBabys(((CraftWorld)w).getHandle()));
		}
		return entities;
	}
	/**
	 * Gets a list of all iron babys by player
	 * @param p The playername Case sensitive1
	 * @return List<EntityIronBaby>
	 */
	public static List<EntityIronBaby> getIronBabys(String p) { 
		ArrayList<EntityIronBaby> entities = new ArrayList<EntityIronBaby>();
		for(org.bukkit.World w : Bukkit.getWorlds()) {
			entities.addAll(getIronBabys(((CraftWorld)w).getHandle(), p));
		}
		return entities;
	}
	/**
	 * Gets a list of all iron babys in chunk
	 * @param chunk The chunk
	 * @return
	 */
	public static ArrayList<EntityIronBaby> getIronBabys(Chunk chunk) {
		ArrayList<EntityIronBaby> entities = new ArrayList<EntityIronBaby>();
		for(List<Entity> entis : chunk.entitySlices) {
				for(Entity enty : entis) {
					System.out.println(enty.getClass().getName());
					if(enty instanceof EntityIronBaby) {
						entities.add((EntityIronBaby) enty);
					}
				}
		}
		return entities;
	}
	/**
	 * Gets a list of all iron babys in a chunk by player
	 * @param chunk
	 * @param player
	 * @return
	 */
	public static ArrayList<EntityIronBaby> getIronBabys(Chunk chunk, String player) {
		ArrayList<EntityIronBaby> entities = new ArrayList<EntityIronBaby>();
		for(List<Entity> entis : chunk.entitySlices) {
			if(entis instanceof EntityIronBaby) {
				if(((EntityIronBaby)entis).getOwner().equals(player)) 
					entities.add((EntityIronBaby) entis);
			}
		}
		return entities;
	}
	
	/**
	 * Returns the currently selected ibaby
	 * @param name The name of it
	 * @return
	 */
	public static EntityIronBaby getSelectedBaby(String name) {
		if(select.containsKey(name)) {
			return select.get(name);
		}
		return null;
	}
	
}
