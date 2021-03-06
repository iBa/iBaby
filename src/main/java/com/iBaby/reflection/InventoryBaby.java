package com.iBaby.reflection;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.craftbukkit.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.InventoryHolder;

import com.iBaby.iBabyAbilities;

import net.minecraft.server.EntityHuman;
import net.minecraft.server.IInventory;
import net.minecraft.server.ItemStack;
import net.minecraft.server.NBTTagCompound;
import net.minecraft.server.NBTTagList;

/**
 * Inventory for iBabys ; providing special abilities belonging to each different item
 * @author steffengy
 *
 */
public class InventoryBaby implements IInventory {

    private ItemStack[] items = new ItemStack[27]; // CraftBukkit
    public boolean a = false;
    public float f;
    public float g;
    public int h;
    private int ticks;
    private iBabyAbilities abilities;

    // CraftBukkit start
    public List<HumanEntity> transaction = new ArrayList<HumanEntity>();

    public ItemStack[] getContents() {
        return this.items;
    }

    public void onOpen(CraftHumanEntity who) {
        transaction.add(who);
    }

    public void onClose(CraftHumanEntity who) {
        transaction.remove(who);
    }

    public List<HumanEntity> getViewers() {
        return transaction;
    }
    // CraftBukkit end

    public InventoryBaby(iBabyAbilities abilities) {
    	this.abilities = abilities;
    }

    public int getSize() {
        return 27;
    }

    public ItemStack getItem(int i) {
        return this.items[i];
    }

    public ItemStack splitStack(int i, int j) {
        if (this.items[i] != null) {
            ItemStack itemstack;

            if (this.items[i].count <= j) {
                itemstack = this.items[i];
                setItem(i, null);
                return itemstack;
            } else {
                itemstack = this.items[i].a(j);
                if (this.items[i].count == 0) {
                    setItem(i, null);
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    public ItemStack splitWithoutUpdate(int i) {
        if (this.items[i] != null) {
            ItemStack itemstack = this.items[i];

            setItem(i, null);
            return itemstack;
        } else {
            return null;
        }
    }

    public void setItem(int i, ItemStack itemstack) {
        this.items[i] = itemstack;
        if (itemstack != null && itemstack.count > this.getMaxStackSize()) {
            itemstack.count = this.getMaxStackSize();
        }
        //iBaby start -  validate stack
        this.abilities.updateAbilities(itemstack, itemstack.count > 0 && itemstack != null ? true : false);
        //iBaby - end
        this.update();
    }

    public String getName() {
        return "container.chest";
    }

    public void a(NBTTagCompound nbttagcompound) {
        NBTTagList nbttaglist = nbttagcompound.getList("Items");

        this.items = new ItemStack[this.getSize()];

        for (int i = 0; i < nbttaglist.size(); ++i) {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.get(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.items.length) {
                setItem(j, ItemStack.a(nbttagcompound1));
            }
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.items.length; ++i) {
            if (this.items[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();

                nbttagcompound1.setByte("Slot", (byte) i);
                this.items[i].save(nbttagcompound1);
                nbttaglist.add(nbttagcompound1);
            }
        }

        nbttagcompound.set("Items", nbttaglist);
    }

    public int getMaxStackSize() {
        return 1; //iBaby - max stack size of 1
    }

    public void h() {
        this.a = false;
    }


    public void b(int i, int j) {
        if (i == 1) {
            this.h = j;
        }
    }

	public boolean a(EntityHuman arg0) {  return true; }

	public void f() { }

	public void g() { }

	public InventoryHolder getOwner() {
		return null;
	}

	public void update() { }

	@Override
	public void setMaxStackSize(int arg0) { /* Ignore input */ }
}
