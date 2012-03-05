package com.iBaby;

import com.iBaby.commands.GetCrazyCommand;
import com.iBaby.commands.HelpCommand;
import com.iBaby.commands.SummonCommand;
import com.iBaby.commands.tptoCommand;

public class BabySitBase extends Base {
	public BabySitBase() {
		addCommand("help", new HelpCommand());
		addCommand("summon", new SummonCommand());
		addCommand("getcrazy", new GetCrazyCommand());
		addCommand("tpto", new tptoCommand());
	}
}
