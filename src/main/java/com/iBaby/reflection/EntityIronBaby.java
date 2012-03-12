package com.iBaby.reflection;

import net.minecraft.server.EntityHuman;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EntityMonster;
import net.minecraft.server.EntityPlayer;
import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.craftbukkit.inventory.CraftInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import com.iBaby.iBabyAbilities;


import net.minecraft.server.Block;
import net.minecraft.server.DamageSource;
import net.minecraft.server.Entity;
import net.minecraft.server.EntityGolem;
import net.minecraft.server.IInventory;
import net.minecraft.server.Item;
import net.minecraft.server.MathHelper;
import net.minecraft.server.NBTTagCompound;
import net.minecraft.server.PathfinderGoalFloat;
import net.minecraft.server.PathfinderGoalHurtByTarget;
import net.minecraft.server.PathfinderGoalLookAtPlayer;
import net.minecraft.server.PathfinderGoalMeleeAttack;
import net.minecraft.server.PathfinderGoalMoveTowardsRestriction;
import net.minecraft.server.PathfinderGoalMoveTowardsTarget;
import net.minecraft.server.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.PathfinderGoalRandomLookaround;
import net.minecraft.server.PathfinderGoalRandomStroll;
import net.minecraft.server.Village;
import net.minecraft.server.World;

public class EntityIronBaby extends EntityGolem implements InventoryHolder {
	private String owner;
	// Imported from EntityIronGolem.java
	private int b = 0;
    private int c;
    private int g;
	//END
	public int defaultCD = 8;
	public int crazy = 0;
	private String customName = "";
	public iBabyAbilities abilities;
	private InventoryBaby inventory;
	//MOB AI implementation
	//TODO Implement Ability stuff somehow
	private PathfinderGoalMeleeAttack meleeAttackGoal = new PathfinderGoalMeleeAttack(this, 0.42F, true);
	private PathfinderGoalMoveTowardsTarget moveTowardsTarget = new PathfinderGoalMoveTowardsTarget(this, 0.37F, 32.0F);
	private PathfinderFollowBaby followBabyGoal = new PathfinderFollowBaby(this, 0.3F, 5.0F, 4.0F);
	
	public int p = -1;
    
	public EntityIronBaby(World world, String owner) {
		super(world);
		this.owner = owner;
		this.abilities = new iBabyAbilities();
		System.out.println(this.abilities);
		this.inventory = new InventoryBaby(this.abilities);
		// Imported from EntityIronGolem.java
		this.texture = "/mob/villager_golem.png";
        this.b(1.4F, 2.9F);
        this.ak().a(true);
        this.fireProof = true;
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
		this.goalSelector.a(1, meleeAttackGoal);
	    this.goalSelector.a(2, moveTowardsTarget);
	    //this.goalSelector.a(3, new PathfinderGoalMoveThroughVillage(this, 0.16F, true));
	    this.goalSelector.a(3, followBabyGoal);
	    this.goalSelector.a(4, new PathfinderGoalMoveTowardsRestriction(this, 0.16F));
	    //this.goalSelector.a(5, new PathfinderGoalOfferFlower(this));
	    this.goalSelector.a(6, new PathfinderGoalRandomStroll(this, f));
	    this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 6.0F));
	    this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
	    //this.targetSelector.a(1, new PathfinderGoalDefendVillage(this));
	    this.targetSelector.a(1, new PathfinderGoalBabysit(this));
	    this.targetSelector.a(2, new PathfinderGoalHurtByTarget(this, false));
	    this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget(this, EntityMonster.class, 16.0F, 0, false, true));
	}
	
	public EntityIronBaby(World world) {
		this(world, null);
	}
	
	/**
	 * Returns the "localized" name of this
	 */
	@Override
	public String getLocalizedName() {
		return "iBaby " +  customName + " (" + owner + " ) ";
	}
	/**
	 * Returns the owner
	 * @return String
	 */
	public String getOwner() {
		return owner;
	}
	
	/**
	 * Gets the custom name of this
	 * @return String
	 */
	public String getName() {
		return customName;
	}
	
	/**
	 * Sets the custom name of this
	 * @param name String
	 */
	public void setName(String name) {
		customName = name;
	}
	
	public void setCrazy(boolean crazy) {
    	setCrazy(15);
    }
	
    public void setCrazy(int ticks) {
    	crazy = ticks;
    }
    
    @Override
    public CraftEntity getBukkitEntity() {
    	return new CraftEntityWrapper((CraftServer) Bukkit.getServer(), this);
    }
	
	/**
	 * Called when the entity trys to target someone.
	 */
    @Override
    public void b(EntityLiving entityliving) {
    	if(entityliving instanceof EntityPlayer) {
    		//Name check + abortion if owner
    		if(((EntityPlayer)entityliving).getBukkitEntity().getName().equals(owner)) {
    			return;
    		}
    	}
    	//Hack
		try {
	    	Field priv;
			priv = EntityLiving.class.getDeclaredField("l");
			priv.setAccessible(true);
			priv.set(this, entityliving);
		}catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * @return org.bukkit.Location Gets the bukkit location of this entity
     */
    public Location getBukkitLocation() {
    	return new Location(world.getWorld(), this.locX, this.locY, this.locZ);
    }
    
    /* IMPORTED FROM EntityIronGolem.java */
    public void e() {
        super.e();
        if (this.c > 0) {
            --this.c;
        }

        if (this.g > 0) {
            --this.g;
        }

        if (this.motX * this.motX + this.motZ * this.motZ > 2.500000277905201E-7D && this.random.nextInt(5) == 0) {
            int i = MathHelper.floor(this.locX);
            int j = MathHelper.floor(this.locY - 0.20000000298023224D - (double) this.height);
            int k = MathHelper.floor(this.locZ);
            int l = this.world.getTypeId(i, j, k);

            if (l > 0) {
                this.world.a("tilecrack_" + l, this.locX + ((double) this.random.nextFloat() - 0.5D) * (double) this.width, this.boundingBox.b + 0.1D, this.locZ + ((double) this.random.nextFloat() - 0.5D) * (double) this.width, 4.0D * ((double) this.random.nextFloat() - 0.5D), 0.5D, ((double) this.random.nextFloat() - 0.5D) * 4.0D);
            }
        }
        super.e();
    }
    //Attack crepers + ghasts :P
    public boolean a(Class oclass) {
        return this.n_() && EntityHuman.class.isAssignableFrom(oclass) ? false : true;//super.a(oclass);
    }
    
    protected void b() {
        super.b();
        this.datawatcher.a(16, Byte.valueOf((byte) 0));
    }
    
    public boolean c_() {
        return true;
    }

    protected void g() {
    	//Stripped out because we ignore villages
        super.g();
    }

    public int getMaxHealth() {
        return 102 + (this.abilities != null ? this.abilities.getAdditionalHealth() : 0); // 2 more health :P
    }

    protected int b_(int i) {
        return i;
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.inventory.b(nbttagcompound);
        nbttagcompound.setBoolean("PlayerCreated", this.n_());
        nbttagcompound.setString("Owner", this.owner);
        nbttagcompound.setString("cName", this.customName);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.inventory.a(nbttagcompound);
        this.b(nbttagcompound.getBoolean("PlayerCreated"));
        this.owner = nbttagcompound.getString("Owner");
        this.customName = nbttagcompound.getString("cName");
    }

    public boolean a(Entity entity) {
    	//CUSTOM
    	if(this.crazy > 0) {
    		this.c = 0;
    		this.crazy--;
    	}else{
            this.c = defaultCD;
    	}
        
        this.world.broadcastEntityEffect(this, (byte) 4);
        boolean flag = entity.damageEntity(DamageSource.mobAttack(this), 7 + this.random.nextInt(15));

        if (flag) {
            entity.motY += 0.521D + this.abilities.getAdditionalThrowHeight(); //custom
        }

        this.world.makeSound(this, "mob.irongolem.throw", 1.0F, 1.0F);
        return flag;
    } 

    public Village l_() {
        return null; //We ignore villages
    }

    public void a(boolean flag) {
        this.g = flag ? 400 : 0;
        this.world.broadcastEntityEffect(this, (byte) 11);
    }

    protected String i() {
        return "none";
    }

    protected String j() {
        return "mob.irongolem.hit";
    }

    protected String k() {
        return "mob.irongolem.death";
    }

    protected void a(int i, int j, int k, int l) {
        this.world.makeSound(this, "mob.irongolem.walk", 1.0F, 1.0F);
    }

    protected void dropDeathLoot(boolean flag, int i) {
        int j = this.random.nextInt(3);

        int k;

        for (k = 0; k < j; ++k) {
            this.b(Block.RED_ROSE.id, 1);
        }

        k = 3 + this.random.nextInt(3);

        for (int l = 0; l < k; ++l) {
            this.b(Item.IRON_INGOT.id, 1);
        }
    }

    public int m_() {
        return this.g;
    }

    public boolean n_() {
        return (this.datawatcher.getByte(16) & 1) != 0;
    }

    public void b(boolean flag) {
        byte b0 = this.datawatcher.getByte(16);

        if (flag) {
            this.datawatcher.watch(16, Byte.valueOf((byte) (b0 | 1)));
        } else {
            this.datawatcher.watch(16, Byte.valueOf((byte) (b0 & -2)));
        }
    }
    
    //No fall damage
    protected void a(float f) {}

    //Not on ladder
    public boolean t() {
        return false;
    }

	public int getSize() {
		return 4;
	}

	public Inventory getInventory() {
		return new CraftInventory(this.inventory);
	}

	public IInventory getIInventory() {
		return this.inventory; 
	}
   
    /* END Imported Stuff */
    
}
