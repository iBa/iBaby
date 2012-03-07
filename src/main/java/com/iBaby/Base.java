package com.iBaby;

import java.util.HashMap;
import java.util.Map.Entry;

public class Base {
	public HashMap<String, Command> registers = new HashMap<String, Command>();
	/**
	 * Adds an command to an root command
	 * @param sub The sub command
	 * @param handler The command handler
	 */
	public void addCommand(String sub, Command handler) {
		registers.put(sub, handler);
	}
	public Command get(String sub) {
		System.out.println(sub);
		for(Entry<String, Command> e : registers.entrySet()) {
			if(e.getKey().equalsIgnoreCase(sub)) {
				return e.getValue();
			}
		}
		return null;
	}
	public String getName() {
		return null;
	}
}
