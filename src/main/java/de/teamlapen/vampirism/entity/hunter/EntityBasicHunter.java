package de.teamlapen.vampirism.entity.hunter;

import de.teamlapen.lib.lib.util.UtilLib;
import de.teamlapen.vampirism.api.VReference;
import de.teamlapen.vampirism.api.VampirismAPI;
import de.teamlapen.vampirism.api.difficulty.Difficulty;
import de.teamlapen.vampirism.api.entity.EntityClassType;
import de.teamlapen.vampirism.api.entity.actions.EntityActionTier;
import de.teamlapen.vampirism.api.entity.actions.IEntityActionUser;
import de.teamlapen.vampirism.api.entity.hunter.IBasicHunter;
import de.teamlapen.vampirism.api.world.IVampirismVillage;
import de.teamlapen.vampirism.config.Balance;
import de.teamlapen.vampirism.core.ModEntities;
import de.teamlapen.vampirism.core.ModItems;
import de.teamlapen.vampirism.entity.action.EntityActionHandler;
import de.teamlapen.vampirism.entity.ai.*;
import de.teamlapen.vampirism.entity.vampire.EntityVampireBase;
import de.teamlapen.vampirism.inventory.HunterBasicContainer;
import de.teamlapen.vampirism.items.VampirismItemCrossbow;
import de.teamlapen.vampirism.player.hunter.HunterLevelingConf;
import de.teamlapen.vampirism.player.hunter.HunterPlayer;
import de.teamlapen.vampirism.world.loot.LootHandler;
import de.teamlapen.vampirism.world.villages.VampirismVillageHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


/**
 * Exists in {@link EntityBasicHunter#MAX_LEVEL}+1 different levels
 */
public class EntityBasicHunter extends EntityHunterBase implements IBasicHunter, HunterAILookAtTrainee.ITrainer, EntityAIAttackRangedCrossbow.IAttackWithCrossbow, IEntityActionUser {
    private static final DataParameter<Integer> LEVEL = EntityDataManager.createKey(EntityBasicHunter.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> SWINGING_ARMS = EntityDataManager.createKey(EntityBasicHunter.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> WATCHED_ID = EntityDataManager.createKey(EntityBasicHunter.class, DataSerializers.VARINT);
    private final int MAX_LEVEL = 3;
    private final int MOVE_TO_RESTRICT_PRIO = 3;
    private final MeleeAttackGoal attackMelee;
    private final EntityAIAttackRangedCrossbow attackRange;
    /**
     * available actions for AI task & task
     */
    private final EntityActionHandler<?> entityActionHandler;
    private final EntityClassType entityclass;
    private final EntityActionTier entitytier;

    /**
     * Player currently being trained otherwise null
     */
    private @Nullable
    PlayerEntity trainee;

    private @Nullable
    IVampirismVillage cachedVillage;

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        IVampirismVillage v = getCurrentFriendlyVillage();
        if (v != null) {
            v.addOrRenewAggressor(source.getTrueSource());
        }
        return super.attackEntityFrom(source, amount);
    }

    /**
     * Stores the x axis angle between when targeting an enemy with the crossbow
     */
    private float targetAngle = 0;

    /**
     * If this is non-null we are currently attacking a village center
     */
    @Nullable
    private AxisAlignedBB village_attack_area;
    /**
     * If this is non-null we are currently defending a village center
     */
    @Nullable
    private AxisAlignedBB village_defense_area;

    public EntityBasicHunter(World world) {
        super(ModEntities.vampire_hunter, world, true);
        saveHome = true;
        ((GroundPathNavigator) this.getNavigator()).setEnterDoors(true);

        this.setSize(0.6F, 1.95F);


        this.setDontDropEquipment();

        this.attackMelee = new MeleeAttackGoal(this, 1.0, false);
        this.attackRange = new EntityAIAttackRangedCrossbow(this, this, 0.6, 60, 20);
        this.updateCombatTask();
        entitytier = EntityActionTier.Medium;
        entityclass = EntityClassType.getRandomClass(this.getRNG());
        IEntityActionUser.applyAttributes(this);
        this.entityActionHandler = new EntityActionHandler<>(this);
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        boolean flag = super.attackEntityAsMob(entity);
        if (flag && this.getHeldItemMainhand().isEmpty()) {
            this.swingArm(Hand.MAIN_HAND);  //Swing stake if nothing else is held
        }
        return flag;
    }

    @Override
    public @Nonnull
    ItemStack getArrowStackForAttack(LivingEntity target) {
        return new ItemStack(ModItems.crossbow_arrow_normal);
    }

    @Nullable
    @Override
    public IVampirismVillage getCurrentFriendlyVillage() {
        return cachedVillage != null ? cachedVillage.getControllingFaction() == VReference.HUNTER_FACTION ? cachedVillage : null : null;
    }

    @Override
    public int getLevel() {
        return getDataManager().get(LEVEL);
    }

    @Override
    public PlayerEntity getTrainee() {
        return trainee;
    }

    @Override
    public int getMaxLevel() {
        return MAX_LEVEL;
    }



    public float getTargetAngle() {
        return targetAngle;
    }

    @Override
    public void livingTick() {
        super.livingTick();
        if (trainee != null && !(trainee.openContainer instanceof HunterBasicContainer)) {
            this.trainee = null;
        }
        if (!world.isRemote) {
            LivingEntity target = getAttackTarget();
            int id = target == null ? 0 : target.getEntityId();
            this.updateWatchedId(id);
        } else {
            targetAngle = 0;
            if (isSwingingArms()) {
                int id = getWatchedId();
                if (id != 0) {
                    Entity target = world.getEntityByID(id);
                    if (target instanceof LivingEntity) {

                        double dx = target.posX - (this).posX;
                        double dy = target.posY - this.posY;
                        double dz = target.posZ - this.posZ;
                        float dist = MathHelper.sqrt(dx * dx + dz * dz);
                        targetAngle = (float) Math.atan(dy / dist);
                    }
                }
            }

        }
        if (entityActionHandler != null) {
            entityActionHandler.handle();
        }
    }


    @Override
    public boolean isCrossbowInMainhand() {
        return !this.getHeldItemMainhand().isEmpty() && this.getHeldItemMainhand().getItem() instanceof VampirismItemCrossbow;
    }

    @Override
    public boolean isLookingForHome() {
        return !hasHome();
    }

    public boolean isSwingingArms() {
        return this.getDataManager().get(SWINGING_ARMS);
    }

    public void setSwingingArms(boolean b) {
        this.getDataManager().set(SWINGING_ARMS, b);
    }

    @Override
    public void makeCampHunter(AxisAlignedBB box) {
        super.setHome(box);
        this.setMoveTowardsRestriction(MOVE_TO_RESTRICT_PRIO, true);
    }

    @Override
    public void makeNormalHunter() {
        super.setHome(null);
        this.disableMoveTowardsRestriction();
    }

    @Override
    public void makeVillageHunter(AxisAlignedBB box) {
        super.setHome(box);
        this.setMoveTowardsRestriction(MOVE_TO_RESTRICT_PRIO, true);

    }

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(DifficultyInstance difficulty, @Nullable ILivingEntityData livingdata, @Nullable CompoundNBT itemNbt) {
        livingdata = super.onInitialSpawn(difficulty, livingdata, itemNbt);

        if (this.getRNG().nextInt(4) == 0) {
            this.setLeftHanded(true);
            Item crossBow = getLevel() > 1 ? ModItems.enhanced_crossbow : ModItems.basic_crossbow;
            this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(crossBow));

        } else {
            this.setLeftHanded(false);
        }

        this.updateCombatTask();
        return livingdata;
    }

    @Override
    public void read(CompoundNBT tagCompund) {
        super.read(tagCompund);
        if (tagCompund.contains("level")) {
            setLevel(tagCompund.getInt("level"));
        }

        if (tagCompund.contains("crossbow") && tagCompund.getBoolean("crossbow")) {
            this.setLeftHanded(true);
            this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ModItems.basic_crossbow));
        } else {
            this.setLeftHanded(false);
            this.setItemStackToSlot(EquipmentSlotType.MAINHAND, ItemStack.EMPTY);
        }
        this.updateCombatTask();
        if (tagCompund.contains("village_attack_area")) {
            this.attackVillage(UtilLib.intToBB(tagCompund.getIntArray("village_attack_area")));
        } else if (tagCompund.contains("village_defense_area")) {
            this.defendVillage(UtilLib.intToBB(tagCompund.getIntArray("village_defense_area")));
        }

        if (entityActionHandler != null) {
            entityActionHandler.read(tagCompund);
        }
    }

    @Override
    public void setLevel(int level) {
        if (level >= 0) {
            getDataManager().set(LEVEL, level);
            this.updateEntityAttributes();
            if (level == 3) {
                this.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 1000000, 1));
            }
        }
    }

    @Override
    public void startTargeting() {
        this.setSwingingArms(true);
    }

    @Override
    public void stopTargeting() {
        this.setSwingingArms(false);
    }

    @Override
    public int suggestLevel(Difficulty d) {
        switch (this.rand.nextInt(5)) {
            case 0:
                return (int) (d.minPercLevel / 100F * MAX_LEVEL);
            case 1:
                return (int) (d.avgPercLevel / 100F * MAX_LEVEL);
            case 2:
                return (int) (d.maxPercLevel / 100F * MAX_LEVEL);
            default:
                return this.rand.nextInt(MAX_LEVEL + 1);
        }

    }

    public void updateCombatTask() {
        if (this.world != null && !this.world.isRemote) {
            this.tasks.removeTask(attackMelee);
            this.tasks.removeTask(attackRange);
            ItemStack stack = this.getHeldItemMainhand();
            if (!stack.isEmpty() && stack.getItem() instanceof VampirismItemCrossbow) {
                this.tasks.addTask(2, this.attackRange);
            } else {
                this.tasks.addTask(2, this.attackMelee);
            }
        }
    }

    @Override
    public void writeAdditional(CompoundNBT nbt) {
        super.writeAdditional(nbt);
        nbt.putInt("level", getLevel());
        nbt.putBoolean("crossbow", isCrossbowInMainhand());
        if (village_attack_area != null) {
            nbt.putIntArray("village_attack_area", UtilLib.bbToInt(village_attack_area));
        } else if (village_defense_area != null) {
            nbt.putIntArray("village_defense_area", UtilLib.bbToInt(village_defense_area));
        }
        nbt.putInt("entityclasstype", EntityClassType.getID(entityclass));
        if (entityActionHandler != null) {
            entityActionHandler.write(nbt);
        }
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.updateEntityAttributes();

    }

    @Override
    public boolean canDespawn() {
        return isLookingForHome() && super.canDespawn();
    }


    @Override
    protected void registerData() {
        super.registerData();
        this.getDataManager().register(LEVEL, -1);
        this.getDataManager().register(SWINGING_ARMS, false);
        this.getDataManager().register(WATCHED_ID, 0);
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) {
        return 6 + getLevel();
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootHandler.BASIC_HUNTER;
    }

    @Override
    public void attackVillage(AxisAlignedBB area) {
        this.village_attack_area = area;
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();

        this.tasks.addTask(1, new OpenDoorGoal(this, true));
        //Attack task is added in #updateCombatTasks which is e.g. called at end of constructor
        this.tasks.addTask(3, new HunterAILookAtTrainee(this));
        this.tasks.addTask(5, new EntityAIMoveThroughVillageCustom(this, 0.7F, false, 300));
        this.tasks.addTask(6, new RandomWalkingGoal(this, 0.7, 50));
        this.tasks.addTask(8, new LookAtGoal(this, PlayerEntity.class, 13F));
        this.tasks.addTask(8, new LookAtGoal(this, EntityVampireBase.class, 17F));
        this.tasks.addTask(8, new LookRandomlyGoal(this));

        this.targetTasks.addTask(1, new HurtByTargetGoal(this, false));
        this.targetTasks.addTask(2, new EntityAIAttackVillage<>(this));
        this.targetTasks.addTask(2, new EntityAIDefendVillage<>(this));//Should automatically be mutually exclusive with  attack village
        this.targetTasks.addTask(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 5, true, false, VampirismAPI.factionRegistry().getPredicate(getFaction(), true, false, false, false, null)));
        this.targetTasks.addTask(5, new NearestAttackableTargetGoal<CreatureEntity>(this, CreatureEntity.class, 5, true, false, VampirismAPI.factionRegistry().getPredicate(getFaction(), false, true, false, false, null)) {
            @Override
            protected double getTargetDistance() {
                return super.getTargetDistance() / 2;
            }
        });
        this.targetTasks.addTask(6, new NearestAttackableTargetGoal<>(this, ZombieEntity.class, true, true));
        //Also check the priority of tasks that are dynamically added. See top of class
    }

    @Override
    protected boolean processInteract(PlayerEntity player, Hand hand) {
        int hunterLevel = HunterPlayer.get(player).getLevel();
        if (this.isAlive() && !player.isSneaking()) {
            if (!world.isRemote) {
                if (HunterLevelingConf.instance().isLevelValidForBasicHunter(hunterLevel + 1)) {
                    if (trainee == null) {
                        //player.openGui(VampirismMod.instance, ModGuiHandler.ID_HUNTER_BASIC, this.world, (int) posX, (int) posY, (int) posZ);//TODO 1.14
                        trainee = player;
                    } else {
                        player.sendMessage(new TranslationTextComponent("text.vampirism.i_am_busy_right_now"));
                    }
                } else if (hunterLevel > 0) {
                    player.sendMessage(new TranslationTextComponent("text.vampirism.basic_hunter.cannot_train_you_any_further"));
                }
            }
            return true;
        }


        return super.processInteract(player, hand);
    }


    @Override
    public void defendVillage(AxisAlignedBB area) {
        this.village_defense_area = area;
    }

    @Nullable
    @Override
    public AxisAlignedBB getTargetVillageArea() {
        return village_attack_area == null ? village_defense_area : village_attack_area;
    }

    @Override
    public boolean isAttackingVillage() {
        return village_attack_area != null;
    }

    @Override
    public void stopVillageAttackDefense() {
        this.setCustomName(null);
        if (village_defense_area != null) {
            village_defense_area = null;
        } else if (village_attack_area != null) {
            village_attack_area = null;
        }
    }

    @Override
    protected void onRandomTick() {
        super.onRandomTick();
        this.cachedVillage = VampirismVillageHelper.getNearestVillage(this);
    }

    protected void updateEntityAttributes() {
        int l = Math.max(getLevel(), 0);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(Balance.mobProps.VAMPIRE_HUNTER_MAX_HEALTH + Balance.mobProps.VAMPIRE_HUNTER_MAX_HEALTH_PL * l);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(Balance.mobProps.VAMPIRE_HUNTER_ATTACK_DAMAGE + Balance.mobProps.VAMPIRE_HUNTER_ATTACK_DAMAGE_PL * l);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(Balance.mobProps.VAMPIRE_HUNTER_SPEED);
    }

    private int getWatchedId() {
        return getDataManager().get(WATCHED_ID);
    }

    private void updateWatchedId(int id) {
        getDataManager().set(WATCHED_ID, id);
    }


    @Override
    public EntityClassType getEntityClass() {
        return entityclass;
    }

    @Override
    public EntityActionTier getEntityTier() {
        return entitytier;
    }


}
