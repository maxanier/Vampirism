package de.teamlapen.vampirism.entity.action.vampire;

import de.teamlapen.vampirism.api.entity.EntityClassType;
import de.teamlapen.vampirism.api.entity.actions.EntityActionTier;
import de.teamlapen.vampirism.api.entity.actions.IEntityActionUser;
import de.teamlapen.vampirism.api.entity.actions.IInstantAction;
import de.teamlapen.vampirism.config.VampirismConfig;
import de.teamlapen.vampirism.entity.DarkBloodProjectileEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;

public class DarkProjectileEntityAction<T extends CreatureEntity & IEntityActionUser> extends VampireEntityAction<T> implements IInstantAction<T> {

    public DarkProjectileEntityAction(EntityActionTier tier, EntityClassType... param) {
        super(tier, param);
    }

    @Override
    public boolean activate(T entity) {
        LivingEntity shooter = entity.getRepresentingEntity();

        Vec3d vec3dd = new Vec3d(entity.getAttackTarget().getPosX() - entity.getPosX(), entity.getAttackTarget().getPosY() - entity.getPosY(), entity.getAttackTarget().getPosZ() - entity.getPosZ());
        vec3dd.normalize();

        DarkBloodProjectileEntity projectile = new DarkBloodProjectileEntity(shooter.getEntityWorld(), shooter.getPosX() + vec3dd.x * 1.0f, shooter.getPosY() + shooter.getEyeHeight() * 0.9f, shooter.getPosZ() + vec3dd.z * 1.0f, vec3dd.x, vec3dd.y, vec3dd.z);
        projectile.shootingEntity = shooter;
        projectile.setDamage(VampirismConfig.BALANCE.eaDarkProjectileDamage.get().floatValue(), VampirismConfig.BALANCE.eaDarkProjectileIndirectDamage.get().floatValue());

        shooter.getEntityWorld().addEntity(projectile);
        return true;
    }

    @Override
    public int getCooldown(int level) {
        return VampirismConfig.BALANCE.eaDarkProjectileCooldown.get() * 20;
    }

    @Override
    public int getWeight(CreatureEntity entity) {
        double distanceToTarget = new Vec3d(entity.getPosX(), entity.getPosY(), entity.getPosZ()).subtract(entity.getAttackTarget().getPosX(), entity.getAttackTarget().getPosY(), entity.getAttackTarget().getPosZ()).length();
        if (distanceToTarget > 20) {
            return 3;
        } else if (distanceToTarget > 12) {
            return 2;
        } else {
            return 1;
        }
    }
}