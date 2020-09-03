package de.teamlapen.vampirism.player.tasks.req;

import de.teamlapen.vampirism.api.entity.player.IFactionPlayer;
import de.teamlapen.vampirism.api.entity.player.task.TaskRequirement;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class EntityRequirement implements TaskRequirement.Requirement<EntityType<?>> {
    @Nonnull
    private final EntityType<?> entityType;
    private final int amount;
    @Nonnull
    private final ResourceLocation id;

    public EntityRequirement(@Nonnull ResourceLocation id, @Nonnull EntityType<?> entityType, int amount) {
        this.id = id;
        this.entityType = entityType;
        this.amount = amount;
    }

    @Nonnull
    @Override
    public TaskRequirement.Type getType() {
        return TaskRequirement.Type.ENTITY;
    }

    @Nonnull
    @Override
    public EntityType<?> getStat(IFactionPlayer<?> player) {
        return entityType;
    }

    @Override
    public int getAmount(IFactionPlayer<?> player) {
        return amount;
    }

    @Nonnull
    @Override
    public ResourceLocation getId() {
        return id;
    }

}
