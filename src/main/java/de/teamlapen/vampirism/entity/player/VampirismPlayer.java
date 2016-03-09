package de.teamlapen.vampirism.entity.player;

import de.teamlapen.lib.HelperLib;
import de.teamlapen.lib.lib.entity.IPlayerEventListener;
import de.teamlapen.lib.lib.network.ISyncable;
import de.teamlapen.vampirism.VampirismMod;
import de.teamlapen.vampirism.api.VampirismAPI;
import de.teamlapen.vampirism.api.entity.minions.IMinionLord;
import de.teamlapen.vampirism.api.entity.player.IFactionPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.UUID;

/**
 * Basic class for all of Vampirism's players.
 * Implements basic methods for level or minion handling
 */
public abstract class VampirismPlayer<T extends IFactionPlayer> implements IFactionPlayer<T>, ISyncable.ISyncableExtendedProperties, IPlayerEventListener, IMinionLord {


    private static final String TAG = "VampirismPlayer";
    protected final EntityPlayer player;

    public VampirismPlayer(EntityPlayer player) {
        this.player = player;
    }

    public void copyFrom(EntityPlayer old) {
        VampirismPlayer p = copyFromPlayer(old);
    }

    @Override
    public long getLastComebackCall() {
        return 0;
    }

    @Override
    public int getLevel() {
        return VampirismAPI.getFactionPlayerHandler(player).getCurrentLevel(getFaction());
    }

    @Override
    public int getMaxMinionCount() {
        return 0;
    }

    @Override
    public EntityLivingBase getMinionTarget() {
        return null;
    }

    /**
     * Only use this if you are dealing with minions.
     * Otherwise use {@link VampirismPlayer#getRepresentingPlayer()}
     *
     * @return
     */
    @Override
    public EntityLivingBase getRepresentingEntity() {
        return player;
    }

    @Override
    public EntityPlayer getRepresentingPlayer() {
        return player;
    }

    @Override
    public double getTheDistanceSquared(Entity e) {
        if (e == null) return Double.MAX_VALUE;
        return player.getDistanceSqToEntity(e);
    }

    @Override
    public int getTheEntityID() {
        return player.getEntityId();
    }

    @Override
    public UUID getThePersistentID() {
        return player.getUniqueID();
    }

    @Override
    public void init(Entity entity, World world) {

    }

    @Override
    public boolean isRemote() {
        if (player.worldObj == null) {
            VampirismMod.log.e(TAG, new Throwable("World not loaded").fillInStackTrace(), "Trying to check if remote, but world is not set yet");
            return false;
        }
        return player.worldObj.isRemote;
    }

    @Override
    public boolean isTheEntityAlive() {
        return player.isEntityAlive();
    }

    @Override
    public final void loadNBTData(NBTTagCompound nbt) {
        NBTTagCompound properties = nbt.getCompoundTag(getPropertyKey());
        if (properties == null) {
            VampirismMod.log.i(TAG, "VampirismPlayer(%s) data for %s cannot be loaded. It probably does not exist", this.getClass(), player);
            return;
        }
        loadData(properties);
    }

    @Override
    public final void loadUpdateFromNBT(NBTTagCompound nbt) {
        loadUpdate(nbt);
    }

    @Override
    public void onPlayerClone(EntityPlayer original) {
        copyFrom(original);
    }

    @Override
    public final void saveNBTData(NBTTagCompound nbt) {
        NBTTagCompound properties = new NBTTagCompound();
        saveData(properties);
        nbt.setTag(getPropertyKey(), properties);
    }

    /**
     * Sync all data
     *
     * @param all Whether all tracking players should receive this packet or only the representing player
     */
    public void sync(boolean all) {
        HelperLib.sync(this, player, all);
    }

    @Override
    public final void writeFullUpdateToNBT(NBTTagCompound nbt) {
        writeFullUpdate(nbt);
    }

    /**
     * Copy all relevant values from the given player and return a instance of the old players VampirismPlayer, so {@link VampirismPlayer} can copy it's values as well
     *
     * @param old
     * @return
     */
    protected abstract VampirismPlayer copyFromPlayer(EntityPlayer old);

    /**
     * Max level this player type can reach
     *
     * @return
     */
    protected abstract int getMaxLevel();

    protected abstract void loadData(NBTTagCompound nbt);

    /**
     * Can be overridden to load data from updates in subclasses
     *
     * @param nbt
     */
    protected void loadUpdate(NBTTagCompound nbt) {
    }

    protected abstract void saveData(NBTTagCompound nbt);

    /**
     * Sync the property using the given data
     *
     * @param data
     * @param all  Whether all tracking players should receive this packet or only the representing player
     */
    protected void sync(NBTTagCompound data, boolean all) {
        HelperLib.sync(this, data, player, all);
    }

    /**
     * Can be overridden to put data into updates in subclasses
     *
     * @param nbt
     */
    protected void writeFullUpdate(NBTTagCompound nbt) {
    }
}
