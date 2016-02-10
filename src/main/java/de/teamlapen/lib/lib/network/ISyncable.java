package de.teamlapen.lib.lib.network;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Provides an interface to sync entities
 */
public interface ISyncable  {
    /**
     * This method should load all included information. It might contain some or all synchable information.
     *
     * @param nbt
     */
    @SideOnly(Side.CLIENT)
     void loadUpdateFromNBT(NBTTagCompound nbt);

    /**
     * This method is called to get update informations which should be send to the client
     */
    void writeFullUpdateToNBT(NBTTagCompound nbt);

    /**
     * Send package containing all syncable data.
     * Should not be used too often, send smaller package manually if you need something updated frequently
     *
     * @param all For IExtendedProperties the package will only be send to the respective player if this is false
     */
    void sync(boolean all);

    interface ISyncableExtendedProperties extends IExtendedEntityProperties,ISyncable{
        /**
         * Returns the entity id of the representing entity
         *
         * @return
         */
        int getTheEntityID();

        /**
         * Return the key which can be used to retrieve this property from the entity using {@link net.minecraft.entity.Entity#getExtendedProperties(String)}
         * @return
         */
        String getPropertyKey();
    }
}
