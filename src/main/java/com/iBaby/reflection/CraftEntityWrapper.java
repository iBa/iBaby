package com.iBaby.reflection;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftLivingEntity;
import org.bukkit.entity.EntityType;

import net.minecraft.server.EntityLiving;

public class CraftEntityWrapper extends CraftLivingEntity {
	private EntityLiving handle;
	public CraftEntityWrapper(CraftServer server, EntityLiving entity) {
		super(server, entity);
		this.handle = entity;
	}
	
	public EntityLiving getHandle() {
		return handle;
	}
	
	@Override
	public EntityType getType() {
		return EntityType.UNKNOWN;
	}
	
}
