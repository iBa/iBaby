package com.iBaby;

import com.iBaby.commands.AbilityCommand;
import com.iBaby.commands.HelpCommand;
import com.iBaby.commands.InvCommand;
import com.iBaby.commands.NameCommand;
import com.iBaby.commands.SummonCommand;

public class BabySitBase extends Base {
	public BabySitBase() {
		addCommand("help", new HelpCommand());
		addCommand("", new HelpCommand()); //Map to HELP
		addCommand("summon", new SummonCommand());
		addCommand("name", new NameCommand());
		addCommand("inv", new InvCommand());
		addCommand("abilities", new AbilityCommand());
	}
	public String getName() {
		return "babysit";
	}
}
