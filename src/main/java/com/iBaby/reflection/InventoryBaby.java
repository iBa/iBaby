package com.iBaby.reflection;

import net.minecraft.server.TileEntityChest;

public class InventoryBaby extends TileEntityChest {
	@Override
	public String getName() {
		return "container.ibaby";
	}
	@Override
	public int getMaxStackSize() {
        return 1;
    }
}
