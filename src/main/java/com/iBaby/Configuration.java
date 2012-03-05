package com.iBaby;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

/**
 * The class which shall handle the iBaby configuration
 * @author steffengy
 *
 */
public class Configuration {
	/**
	 * The max amount of iron babys per player
	 */
	public static int max = 1;
	/**
	 * Whether to enable support of vault
	 */
	public static boolean enablePrice = false;
	/**
	 * Price of one iron Baby
	 */
	public static double price = 12000;
	
	/**
	 * Loads the config entrys into the class
	 * @param config
	 */
	public static void init(File configF) {
		YamlConfiguration config = YamlConfiguration.loadConfiguration(configF);
		if(config.contains("max")) 
			max = config.getInt("max");
		if(config.contains("enablePrice")) 
			enablePrice = config.getBoolean("enablePrice");
		if(config.contains("price"))
			price = config.getDouble("price");
	}

	/**
	 * Resets the values to default
	 */
	public static void resetDefaults() {
		max = 1;
		enablePrice = false;
		price = 12000;
	}

	/**
	 * Creates the default config
	 * @param configFile
	 */
	public static void createDefaultOne(File configFile) {
		List<String> defaultConfig = new ArrayList<String>();
		defaultConfig.add("# Specifies how many iron babysitters a player can have");
		defaultConfig.add("max : 1");
		defaultConfig.add("# Specifies whether to enable economy support and price of iron baby");
		defaultConfig.add("enablePrice : false");
		defaultConfig.add("# Specifies how much a iron baby will cost");
		defaultConfig.add("price: 12000");
		try {
			FileOutputStream write = new FileOutputStream(configFile);
			for(String line : defaultConfig) {
				new PrintStream(write).println(line);
			}
			write.close();
		} catch (IOException e) {
			iBaby.log("Error while writing into config!");
		}
	}
}
