package de.teamlapen.vampirism.entity;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import de.teamlapen.vampirism.ModItems;
import de.teamlapen.vampirism.ModPotion;
import de.teamlapen.vampirism.VampirismMod;
import de.teamlapen.vampirism.entity.player.VampirePlayer;
import de.teamlapen.vampirism.util.BALANCE;
import de.teamlapen.vampirism.util.REFERENCE;
import de.teamlapen.vampirism.villages.VillageVampire;
import de.teamlapen.vampirism.villages.VillageVampireData;

public class EntityVampire extends DefaultVampire {
	// TODO Sounds

	public EntityVampire(World par1World) {
		super(par1World);
		// Avoids Vampire Hunters
		this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityVampireHunter.class, BALANCE.MOBPROP.VAMPIRE_DISTANCE_HUNTER, 1.0, 1.2));
		// Low priority tasks
		this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 0.6, false));
		this.tasks.addTask(6, new EntityAIWander(this, 0.7));
		this.tasks.addTask(9, new EntityAILookIdle(this));

		// Search for players
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.addAttackingTargetTasks(2);

	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(BALANCE.MOBPROP.VAMPIRE_MAX_HEALTH);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(BALANCE.MOBPROP.VAMPIRE_ATTACK_DAMAGE);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(BALANCE.MOBPROP.VAMPIRE_MOVEMENT_SPEED);
	}

	@Override
	public void onDeath(DamageSource s) {
		if (s.getEntity() != null && s.getEntity() instanceof EntityPlayer) {
			this.dropItem(ModItems.vampireFang, 1);
		}
	}

}
