package com.iBaby.reflection;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.entity.CraftPlayer;

import net.minecraft.server.EntityLiving;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.PathfinderGoalTarget;

public class PathfinderGoalBabysit extends PathfinderGoalTarget {
	private EntityIronBaby entity;
	private EntityLiving lastTarget;
	
	 public PathfinderGoalBabysit(EntityIronBaby entity) {
	        super(entity, 16.0F, false, true);
	        this.entity = entity;
	        this.a(1);
	}
	@Override
	public boolean a() {
		if(Bukkit.getPlayer(this.entity.getOwner()) != null && this.entity.getOwner() != null) {
			EntityPlayer player = ((CraftPlayer) Bukkit.getPlayer(this.entity.getOwner())).getHandle();
			lastTarget = player.ao();
		}
		return this.a(this.lastTarget, false);
	}
	
	public void c() {
		 this.c.b(this.lastTarget);
	     super.c();
	}
}
