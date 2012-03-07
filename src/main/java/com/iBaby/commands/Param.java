package com.iBaby.commands;

/**
 * Defining a param
 * @author steffengy
 *
 */
public class Param {
	private String name;
	private boolean needed;
	
	/**
	 * Defining a parameter of a function
	 * @param index The index
	 * @param name The name
	 * @param needed If this is needed
	 */
	public Param(String name, boolean needed) {
		this.name = name;
		this.needed = needed;
	}
	
	/**
	 * Returns the name of this
	 * @return String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns if this is needed
	 * @return Boolean
	 */
	public boolean isNeeded() {
		return this.needed;
	}
}
