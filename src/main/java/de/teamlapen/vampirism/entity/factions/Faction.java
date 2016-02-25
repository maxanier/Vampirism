package de.teamlapen.vampirism.entity.factions;

import de.teamlapen.vampirism.api.entity.factions.IFaction;
import de.teamlapen.vampirism.api.entity.factions.IFactionEntity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.util.EnumChatFormatting;

/**
 * Represents a entity faction (e.g. Vampires)
 */
public class Faction<T extends IFactionEntity> implements IFaction<T> {
    private static int nextId = 0;
    protected final String name;
    protected final Class<T> entityInterface;
    private final int color;
    protected String unlocalizedName;
    /**
     * Id used for hashing
     */
    private int id;
    private EnumChatFormatting chatColor;

    Faction(String name, Class<T> entityInterface, int color) {
        this.name = name;
        this.entityInterface = entityInterface;
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Faction) && this.id == (((Faction) obj).id);
    }

    @Override
    public EnumChatFormatting getChatColor() {
        return chatColor == null ? EnumChatFormatting.WHITE : chatColor;
    }

    public Faction<T> setChatColor(EnumChatFormatting chatColor) {
        this.chatColor = chatColor;
        return this;
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public Class<T> getEntityInterface() {
        return entityInterface;
    }

    @Override
    public String getUnlocalizedName() {
        return unlocalizedName == null ? name : unlocalizedName;
    }

    public Faction<T> setUnlocalizedName(String unlocalizedName) {
        id = nextId++;
        this.unlocalizedName = unlocalizedName;
        return this;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean isEntityOfFaction(EntityCreature creature) {
        return entityInterface.isInstance(creature);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return "Faction{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    int getId() {
        return id;
    }
}
