package de.teamlapen.vampirism.api.entity;

import de.teamlapen.vampirism.api.entity.convertible.IConvertedCreature;
import net.minecraft.entity.CreatureEntity;

import javax.annotation.Nullable;

/**
 * Interface for Vampirism's capability every {@link CreatureEntity} has attached
 */
public interface IExtendedCreatureVampirism extends IBiteableEntity {
    /**
     * @return If this entity can be converted to a vampire version
     */
    boolean canBecomeVampire();

    /**
     * If the entity never had any blood, this returns -1
     *
     * @return current blood level
     */
    int getBlood();

    /**
     * @param blood Value is checked
     */
    void setBlood(int blood);

    /**
     * @return the representing entity
     */
    CreatureEntity getEntity();

    /**
     * @return Max blood level
     */
    int getMaxBlood();

    /**
     * the bite attacker should get poisoned on bite
     */
    boolean hasPoisonousBlood();

    /**
     * Convert this creature into a vampire version if possible
     *
     * @return The converted creature
     */
    @Nullable
    IConvertedCreature makeVampire();

    /**
     * set if the bite attacker should get poisoned on bite
     */
    void setPoisonousBlood(boolean poisonous);

    /**
     * Called every tick
     */
    void tick();
}
