package de.teamlapen.vampirism.api.entity;

import de.teamlapen.vampirism.api.entity.convertible.IConvertedCreature;
import de.teamlapen.vampirism.api.entity.convertible.IConvertingHandler;
import net.minecraft.entity.EntityCreature;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * Registration of blood values and converting handler for {@link EntityCreature}'s
 * TODO maybe rename to VampirismEntityRegistry or similar at some point
 */
public interface IBiteableRegistry {

    /**
     * Add a blood value for an creature
     *
     * @param entityId Entity id
     * @param value    blood value
     */
    void addBloodValue(String entityId, int value);

    /**
     * Add a set of entity id -> blood value pairs
     */
    void addBloodValues(Map<String, Integer> values);

    /**
     * Register a entity class which can be converted using Vampirism's default {@link IConvertingHandler} and thereby being turned into Vampirim's default {@link IConvertedCreature}
     * Requires a blood value to be registered for that creature
     *
     * @param clazz
     * @param overlay_loc Location of the overlay texture file
     */
    void addConvertible(Class<? extends EntityCreature> clazz, String overlay_loc);

    /**
     * Register a entity class which can be converted using a default {@link IConvertingHandler} and thereby being turned into Vampirim's default {@link IConvertedCreature}
     * Requires a blood value to be registered for that creature
     *
     * @param clazz
     * @param helper      Helper instance for the DefaultHandler to specify some values for the converted creature
     * @param overlay_loc Location of the overlay texture file
     */
    void addConvertible(Class<? extends EntityCreature> clazz, String overlay_loc, IConvertingHandler.IDefaultHelper helper);

    /**
     * Register a entity class which can be converted
     * Requires a blood value to be registered for that creature
     *
     * @param clazz
     * @param overlay_loc Location of the overlay texture file. Only required if Vampirism's default Converted Creature renderer is used, if you handle that stuff yourself, null is just fine.
     * @param handler     Handles the conversion
     */
    void addConvertible(Class<? extends EntityCreature> clazz, String overlay_loc, IConvertingHandler handler);

    /**
     * Registers a custom {@link IExtendedCreatureVampirism} for a entity class
     *
     * @param clazz       The entity class that should use the given constructor
     * @param constructor A 'constructor' that can be used to create the {@link IExtendedCreatureVampirism} object from the entity's object
     * @param <T>         The base class type
     */
    <T extends EntityCreature> void addCustomExtendedCreature(Class<? extends T> clazz, IExtendedCreatureConstructor<T> constructor);

    @Nullable
    IConvertedCreature convert(EntityCreature entity);

    /**
     * @return A map mapping the overlay resource location string to e convertible entity's class
     */
    @SideOnly(Side.CLIENT)
    Map<Class<? extends EntityCreature>, String> getConvertibleOverlay();

    /**
     * @return The custom constructor registered for the given entity's class. Can be null if none is registered
     */
    @Nullable
    <T extends EntityCreature> IExtendedCreatureConstructor<T> getCustomExtendedCreatureConstructor(T entity);

    BiteableEntry getEntry(EntityCreature creature);

    BiteableEntry getEntry(String entity_name);

    /**
     * Add blood values that override previously registered values. E.g. used for making the values configurable
     *
     * @param values
     */
    void overrideBloodValues(Map<String, Integer> values);

    /**
     * Creates a {@link IExtendedCreatureVampirism}
     *
     * @param <T>
     */
    interface IExtendedCreatureConstructor<T extends EntityCreature> {
        IExtendedCreatureVampirism create(T creature);
    }

    interface ICreateDefaultConvertingHandler {
        IConvertingHandler create(IConvertingHandler.IDefaultHelper helper);
    }
}