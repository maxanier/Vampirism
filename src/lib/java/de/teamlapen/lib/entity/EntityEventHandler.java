package de.teamlapen.lib.entity;

import de.teamlapen.lib.HelperRegistry;
import de.teamlapen.lib.VampLib;
import de.teamlapen.lib.lib.entity.IPlayerEventListener;
import de.teamlapen.lib.lib.network.ISyncable;
import de.teamlapen.lib.network.RequestPlayerUpdatePacket;
import de.teamlapen.lib.network.UpdateEntityPacket;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Library's entity event handler to do stuff
 */
public class EntityEventHandler {

    private final Capability[] listeners;

    public EntityEventHandler(Capability[] listeners) {
        this.listeners = listeners;
    }

    @SubscribeEvent
    public void onChangedDimension(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent event) {
        for (Capability listener : listeners) {
            ((IPlayerEventListener) event.getPlayer().getCapability(listener, null)).onChangedDimension(event.getFrom(), event.getTo());
        }
    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if (event.getEntity().getEntityWorld().isRemote) {
            if ((event.getEntity() instanceof EntityPlayerSP && HelperRegistry.getSyncablePlayerCaps().size() > 0)) {
                VampLib.dispatcher.sendToServer(new RequestPlayerUpdatePacket());
            }
        }

        if (event.getEntity() instanceof EntityPlayer) {
            for (Capability listener : listeners) {

                (event.getEntity().getCapability(listener, null)).ifPresent(cap -> {
                    ((IPlayerEventListener) cap).onJoinWorld();
                });
            }
        }

    }

    @SubscribeEvent
    public void onLivingAttack(LivingAttackEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            for (Capability listener : listeners) {
                boolean cancel = (boolean) event.getEntity().getCapability(listener, null).map(cap -> ((IPlayerEventListener) cap).onEntityAttacked(event.getSource(), event.getAmount())).orElse(false);
                if (cancel) {
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            for (Capability listener : listeners) {
                (event.getEntity().getCapability(listener, null)).ifPresent(cap -> ((IPlayerEventListener) cap).onDeath(event.getSource()));
            }
        }
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            for (Capability listener : listeners) {
                event.getEntity().getCapability(listener, null).ifPresent(cap -> ((IPlayerEventListener) cap).onUpdate());
            }
        }
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
        for (Capability listener : listeners) {
            (event.getEntity().getCapability(listener, null)).ifPresent(cap -> ((IPlayerEventListener) cap).onPlayerClone(event.getOriginal(), event.isWasDeath()));
        }

    }

    @SubscribeEvent
    public void onPlayerLoggedIn(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event) {
        for (Capability listener : listeners) {
            (event.getPlayer().getCapability(listener, null)).ifPresent(cap -> ((IPlayerEventListener) cap).onPlayerLoggedIn());
        }
    }

    @SubscribeEvent
    public void onPlayerLoggedOut(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent event) {
        for (Capability listener : listeners) {
            (event.getPlayer().getCapability(listener, null)).ifPresent(cap -> ((IPlayerEventListener) cap).onPlayerLoggedOut());
        }
    }

    @SubscribeEvent
    public void onPlayerUpdate(TickEvent.PlayerTickEvent event) {
        for (Capability listener : listeners) {
            (event.player.getCapability(listener, null)).ifPresent(cap -> ((IPlayerEventListener) cap).onUpdatePlayer(event.phase));
        }
    }

    @SubscribeEvent
    public void onStartTracking(PlayerEvent.StartTracking event) {
        if ((event.getTarget() instanceof EntityCreature && HelperRegistry.getSyncableEntityCaps().size() > 0) || event.getTarget() instanceof ISyncable || (event.getTarget() instanceof EntityPlayer && HelperRegistry.getSyncablePlayerCaps().size() > 0)) {
            UpdateEntityPacket packet = UpdateEntityPacket.createJoinWorldPacket(event.getTarget());
            VampLib.dispatcher.sendTo(packet, (EntityPlayerMP) event.getEntityPlayer());
        }
    }
}
