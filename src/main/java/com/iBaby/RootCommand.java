package com.iBaby;


public enum RootCommand 
{
	BabySitBase(new BabySitBase(), "babysit");
	
	Base command;
	String name;
	private RootCommand(Base command, String name) {
		this.command = command;
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public Base getCommand() {
		return this.command;
	}
	public static RootCommand get(String root) {
		for(RootCommand s : RootCommand.values()) {
			if(s.getName().equalsIgnoreCase(root)) {
				return s;
			}
		}
		return null;
	}
	
}
