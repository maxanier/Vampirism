package de.teamlapen.vampirism.entity.goals;

import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IWorldReader;

import java.util.EnumSet;

/**
 * 1.14
 *
 * @author maxanier
 */
public abstract class MoveToPositionGoal<T extends CreatureEntity> extends Goal {


    protected final T entity;
    protected final IWorldReader world;
    private final double followSpeed;
    private final PathNavigator navigator;
    private final float minDist;
    private final float maxDist;
    private final boolean doTeleport;
    private final boolean look;
    private int timeToRecalcPath;
    private float oldWaterCost;

    public MoveToPositionGoal(T entity, double followSpeed, float minDist, float maxDist, boolean doTeleport, boolean look) {
        this.entity = entity;
        this.world = entity.getEntityWorld();
        this.followSpeed = followSpeed;
        this.minDist = minDist;
        this.navigator = entity.getNavigator();
        this.maxDist = maxDist;
        this.doTeleport = doTeleport;
        this.look = look;
        this.setMutexFlags(look ? EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Flag.JUMP) : EnumSet.of(Goal.Flag.MOVE, Flag.JUMP));
        if (!(entity.getNavigator() instanceof GroundPathNavigator) && !(entity.getNavigator() instanceof FlyingPathNavigator)) {
            throw new IllegalArgumentException("Unsupported mob type for MoveToPositionGoal");
        }
    }

    @Override
    public void resetTask() {
        this.navigator.clearPath();
        this.entity.setPathPriority(PathNodeType.WATER, this.oldWaterCost);
    }

    @Override
    public boolean shouldContinueExecuting() {
        return !this.navigator.noPath() && this.getTargetPosition().distanceSq(this.entity.getPosition()) > this.minDist * minDist;
    }

    @Override
    public boolean shouldExecute() {
        return this.getTargetPosition().distanceSq(entity.getPosition()) > this.minDist * minDist;
    }

    @Override
    public void startExecuting() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.entity.getPathPriority(PathNodeType.WATER);
        this.entity.setPathPriority(PathNodeType.WATER, 0.0F);
    }

    @Override
    public void tick() {
        Vec3i target = getTargetPosition();
        if (look) this.entity.getLookController().setLookPosition(getLookPosition());
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = 10;
            boolean flag = this.navigator.tryMoveToXYZ(target.getX(), target.getY(), target.getZ(), this.followSpeed);
            if (doTeleport && (!flag || this.entity.getRNG().nextInt(8) == 0)) {
                if (!(this.entity.getDistanceSq(target.getX(), target.getY(), target.getZ()) < maxDist * maxDist)) {
                    int i = target.getX() - 2;
                    int j = target.getY() - 2;
                    int k = target.getZ();

                    for (int l = 0; l <= 4; ++l) {
                        for (int i1 = 0; i1 <= 4; ++i1) {
                            if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && this.canTeleportToBlock(new BlockPos(i + l, k - 1, j + i1))) {
                                this.entity.setLocationAndAngles(((float) (i + l) + 0.5F), k, ((float) (j + i1) + 0.5F), this.entity.rotationYaw, this.entity.rotationPitch);
                                this.navigator.clearPath();
                                return;
                            }
                        }
                    }

                }

            }
        }

    }

    protected boolean canTeleportToBlock(BlockPos pos) {
        BlockState blockstate = this.world.getBlockState(pos);
        return blockstate.canEntitySpawn(this.world, pos, this.entity.getType()) && this.world.isAirBlock(pos.up()) && this.world.isAirBlock(pos.up(2));
    }

    protected abstract Vec3d getLookPosition();

    protected abstract Vec3i getTargetPosition();
}
