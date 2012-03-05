package com.iBaby;

import com.iBaby.commands.HelpCommand;
import com.iBaby.commands.SummonCommand;

public class BabySitBase extends Base {
	public BabySitBase() {
		addCommand("help", new HelpCommand());
		addCommand("summon", new SummonCommand());
	}
}
