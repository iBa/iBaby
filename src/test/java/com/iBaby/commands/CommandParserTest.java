package com.iBaby.commands;

import static org.junit.Assert.*;

import org.bukkit.command.CommandSender;
import org.junit.Test;

import com.iBaby.Command;

/**
 * Test for the new command parser
 * @author steffengy
 *
 */
public class CommandParserTest extends Command {
	
	public CommandParserTest() {
		addParam("a"); //0
		addParam("b"); //1
		addOptionalParam("c"); // 2
		addParam("d"); // 2 or 3
		addParam("e"); // 3 or 4
		addOptionalParam("f"); //4 or 5
		addOptionalParam("g"); //5 or 6
		addOptionalParam("h"); //6 or 7
		addParam("i"); // 7 or 8
		requiresPlayer = false;
	}
    @Test
    public void testFullNeeded() {
    	this.preHandle(getFakeCommandSender(), new String[] { "aValue", "bValue", "dValue", "eValue", "iValue" });
    	assertTrue(param("a").equals("aValue"));
    	assertTrue(param("b").equals("bValue"));
    	assertTrue(param("d").equals("dValue"));
    	assertTrue(param("e").equals("eValue"));
    	assertTrue(param("i").equals("iValue"));
    }
	@Test
    public void testFull() {
    	this.preHandle(getFakeCommandSender(), new String[] { "aValue", "bValue", "cValue", "dValue", "eValue", "fValue", "gValue", "hValue", "iValue" });
    	assertTrue(param("a").equals("aValue"));
    	assertTrue(param("b").equals("bValue"));
    	assertTrue(param("d").equals("dValue"));
    	assertTrue(param("e").equals("eValue"));
    	assertTrue(param("c").equals("cValue"));
    	assertTrue(param("f").equals("fValue"));
    	assertTrue(param("g").equals("gValue"));
    	assertTrue(param("h").equals("hValue"));
    	assertTrue(param("i").equals("iValue"));
    }
    
    @Test
    public void testFunnyMixture() {
    	this.preHandle(getFakeCommandSender(), new String[] { "aValue", "bValue", "cValue", "dValue", "eValue", "fValue", "gValue", "iValue" });
    	assertTrue(param("a").equals("aValue"));
    	assertTrue(param("b").equals("bValue"));
    	assertTrue(param("d").equals("dValue"));
    	assertTrue(param("e").equals("eValue"));
    	assertTrue(param("c").equals("cValue"));
    	assertTrue(param("f").equals("fValue"));
    	assertTrue(param("g").equals("gValue"));
    	assertTrue(param("i").equals("iValue"));
    }
    
    @Test
    public void testNotEnoughArguments() {
    	boolean b = this.preHandle(getFakeCommandSender(), new String[] { "aValue", "bValue", "cValue" });
    	assertTrue(!b);
    }
    
    @Test
    public void testToMuchArguments() {
    	boolean b = this.preHandle(getFakeCommandSender(), new String[] { "aValue", "bValue", "cValue", "aValue", "bValue", "cValue", "dValue", "eValue", "fValue", "gValue", "iValue" });
    	assertTrue(!b);
    }
    
    private CommandSender getFakeCommandSender() {
		return new FakeCommandSender();
	}
    
}
