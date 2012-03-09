package com.iBaby;

import java.util.List;

import net.minecraft.server.EntityFireball;

import org.bukkit.craftbukkit.CraftChunk;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.craftbukkit.entity.CraftFireball;
import org.bukkit.entity.Fireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import com.iBaby.reflection.CraftEntityWrapper;
import com.iBaby.reflection.EntityIronBaby;
/**
 * The listener of iBaby
 * @author steffengy
 * tasks:
 * - patch Ghasts
 */
public class iBabyListener implements Listener {
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event) {
		if(event.getEntity() instanceof Fireball) {
				if(handleFireball(event.getEntity())) {
					EntityFireball fb = ((CraftFireball)event.getEntity()).getHandle();
					fb.isIncendiary = false;
					fb.yield = 0;
					fb.locY = 10000; //ByeBye
				}
		}
	}
	// Don't let it look like that Players or the Golem can get damage by this
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Fireball) {
			EntityFireball fb = ((CraftFireball)event.getDamager()).getHandle();
			if(fb.yield == 0 && !fb.isIncendiary && ( event.getEntity() instanceof Player || validateIronBaby(event.getEntity()))) {
				event.setCancelled(true);
			}
		}
	}
	// Now surely block the explosion
	@EventHandler
	public void onEntityExplode(ExplosionPrimeEvent event) {
		if(event.getEntity() instanceof Fireball) {
			EntityFireball fb = ((CraftFireball)event.getEntity()).getHandle();
			if(fb.yield == 0 && !fb.isIncendiary && ( event.getEntity() instanceof Player || validateIronBaby(event.getEntity()))) {
				event.setCancelled(true);
			}
		}
	}
	private boolean validateIronBaby(Entity e) {
		if(e instanceof CraftEntityWrapper) {
			if(((CraftEntityWrapper)e).getHandle() instanceof EntityIronBaby) {
				return true;
			}
		}
		return false;
	}
	private boolean handleFireball(Entity entity2) {
		Fireball f = (Fireball) entity2;
		List<EntityIronBaby> babys = iBaby.getIronBabys(((CraftChunk)f.getLocation().getChunk()).getHandle());
		for(EntityIronBaby entity : babys) {
			//Now check if this and the "to-do" explosion WILL be in the same chunk
				return true;
			}
		return false;
	}
}
