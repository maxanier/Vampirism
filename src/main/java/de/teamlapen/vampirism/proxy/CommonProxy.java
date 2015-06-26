package de.teamlapen.vampirism.proxy;

import java.util.Iterator;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import de.teamlapen.vampirism.ModBiomes;
import de.teamlapen.vampirism.VampirismEventHandler;
import de.teamlapen.vampirism.VampirismMod;
import de.teamlapen.vampirism.entity.EntityBlindingBat;
import de.teamlapen.vampirism.entity.EntityDeadMob;
import de.teamlapen.vampirism.entity.EntityDracula;
import de.teamlapen.vampirism.entity.EntityGhost;
import de.teamlapen.vampirism.entity.EntityVampire;
import de.teamlapen.vampirism.entity.EntityVampireHunter;
import de.teamlapen.vampirism.entity.EntityVampireLord;
import de.teamlapen.vampirism.entity.VampireEntityEventHandler;
import de.teamlapen.vampirism.entity.minions.EntityRemoteVampireMinion;
import de.teamlapen.vampirism.entity.minions.EntitySaveableVampireMinion;
import de.teamlapen.vampirism.entity.player.VampirePlayer;
import de.teamlapen.vampirism.entity.player.VampirePlayerEventHandler;
import de.teamlapen.vampirism.util.BALANCE;
import de.teamlapen.vampirism.util.Logger;
import de.teamlapen.vampirism.util.REFERENCE;

public abstract class CommonProxy implements IProxy {

	private int modEntityId = 0;

	private int calculateColor(String n) {
		int hash = n.hashCode();
		while (hash > 0xFFFFFF) {
			hash = (int) (hash / 50F);
		}
		return hash;
	}

	@Override
	public void onServerTick(TickEvent.ServerTickEvent event) {
		WorldServer server = MinecraftServer.getServer().worldServerForDimension(0);

		if (server.areAllPlayersAsleep()) {
			Logger.i("ServerProxy", "All players are asleep");
			if (server.playerEntities.size() > 0) {// Should always be the case, but better check
				if (VampirePlayer.get(((EntityPlayer) server.playerEntities.get(0))).sleepingCoffin) {
					Logger.i("CommonProxy", "All players are sleeping in a coffin ->waking them up");
					// Set time to next night
					long i = server.getWorldTime() + 24000L;
					server.setWorldTime(i - i % 24000L - 11000L);

					wakeAllPlayers(server);
				} else {
					Logger.i("CommonProxy", "All players are sleeping in a bed");
				}
			}

		}
	}

	private void registerEntity(Class<? extends Entity> clazz, String name, boolean useGlobal) {

		Logger.d("EntityRegister", "Adding " + name + "(" + clazz.getSimpleName() + ")" + (useGlobal ? " with global id" : "with mod id"));
		if (useGlobal) {
			EntityRegistry.registerGlobalEntityID(clazz, name, EntityRegistry.findGlobalUniqueEntityId(), calculateColor(name), calculateColor(name + "2"));
		} else {
			name = name.replace("vampirism.", "");
			EntityRegistry.registerModEntity(clazz, name, modEntityId++, VampirismMod.instance, 80, 1, true);
		}

	}

	/**
	 * Registers the Entity and it's spawn
	 * 
	 * @param clazz
	 *            Class
	 * @param name
	 *            Name
	 * @param probe
	 *            WeightedProbe
	 * @param min
	 *            Min group size
	 * @param max
	 *            Max group size
	 * @param type
	 *            CreatureType
	 * @param biomes
	 *            Biomes
	 */
	private void registerEntity(Class<? extends EntityLiving> clazz, String name, int probe, int min, int max, EnumCreatureType type, BiomeGenBase... biomes) {
		this.registerEntity(clazz, name, true);
		Logger.d("EntityRegister", "Adding spawn with probe of " + probe);
		EntityRegistry.addSpawn(clazz, probe, min, max, type, biomes);
	}

	@Override
	public void registerEntitys() {
		// Create a array of all biomes except hell and end
		BiomeGenBase[] allBiomes = BiomeGenBase.getBiomeGenArray();
		allBiomes = allBiomes.clone();
		allBiomes[9] = null;
		allBiomes[8] = null;
		BiomeGenBase[] biomes = Iterators.toArray(Iterators.filter(Iterators.forArray(allBiomes), Predicates.notNull()), BiomeGenBase.class);

		registerEntity(EntityVampireHunter.class, REFERENCE.ENTITY.VAMPIRE_HUNTER_NAME, BALANCE.VAMPIRE_HUNTER_SPAWN_PROBE, 1, 2, EnumCreatureType.creature, biomes);
		registerEntity(EntityVampire.class, REFERENCE.ENTITY.VAMPIRE_NAME, BALANCE.VAMPIRE_SPAWN_PROBE, 1, 3, EnumCreatureType.monster, biomes);
		registerEntity(EntityVampireLord.class, REFERENCE.ENTITY.VAMPIRE_LORD_NAME, BALANCE.VAMPIRE_LORD_SPAWN_PROBE, 1, 1, EnumCreatureType.monster, ModBiomes.biomeVampireForest);
		registerEntity(EntitySaveableVampireMinion.class, REFERENCE.ENTITY.VAMPIRE_MINION_SAVEABLE_NAME, false);
		registerEntity(EntityRemoteVampireMinion.class, REFERENCE.ENTITY.VAMPIRE_MINION_REMOTE_NAME, false);
		registerEntity(EntityDeadMob.class, REFERENCE.ENTITY.DEAD_MOB_NAME, false);
		registerEntity(EntityDracula.class, REFERENCE.ENTITY.DRACULA_NAME, false);
		registerEntity(EntityGhost.class, REFERENCE.ENTITY.GHOST_NAME, 5, 1, 2, EnumCreatureType.monster, ModBiomes.biomeVampireForest);
		registerEntity(EntityBlindingBat.class, REFERENCE.ENTITY.BLINDING_BAT_NAME, false);

	}

	@Override
	public void registerSubscriptions() {
		Object playerHandler = new VampirePlayerEventHandler();
		MinecraftForge.EVENT_BUS.register(playerHandler);
		FMLCommonHandler.instance().bus().register(playerHandler);
		MinecraftForge.EVENT_BUS.register(new VampireEntityEventHandler());
		Object handler = new VampirismEventHandler();
		MinecraftForge.EVENT_BUS.register(handler);
		FMLCommonHandler.instance().bus().register(handler);
	}

	private void wakeAllPlayers(WorldServer server) {
		@SuppressWarnings("rawtypes")
		Iterator iterator = server.playerEntities.iterator();

		while (iterator.hasNext()) {
			EntityPlayerMP p = (EntityPlayerMP) iterator.next();
			VampirePlayer.get(p).wakeUpPlayer(true, false, false, true);

		}
	}
}
