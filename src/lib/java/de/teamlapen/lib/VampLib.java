package de.teamlapen.lib;

import de.teamlapen.lib.entity.EntityEventHandler;
import de.teamlapen.lib.lib.network.AbstractPacketDispatcher;
import de.teamlapen.lib.lib.util.Logger;
import de.teamlapen.lib.network.LibraryPacketDispatcher;
import de.teamlapen.lib.proxy.ClientProxy;
import de.teamlapen.lib.proxy.CommonProxy;
import de.teamlapen.lib.proxy.IProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;


/**
 * If the package is moved as own mod (probably refactored with a different package name to avoid conflicts) this will be the mod main class.
 */
@Mod(value = LIBREFERENCE.MODID)
public class VampLib {
    public final static Logger log = new Logger(LIBREFERENCE.MODID, "de.teamlapen.lib");
    public static final AbstractPacketDispatcher dispatcher = new LibraryPacketDispatcher();
    public static boolean inDev = false;
    @SuppressWarnings("Convert2MethodRef")
    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new CommonProxy());

    public VampLib() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
    }

    private void checkDevEnv() {
        //TODO test
        String launchTarget = System.getenv().get("target");
        if (launchTarget != null && launchTarget.contains("dev")) {
            inDev = true;
            log.setDebug(true);
            if (FMLEnvironment.dist.isClient()) {
                log.displayModID();
            }
        }
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {

    }

    private void preInit(final FMLCommonSetupEvent event) {
        checkDevEnv();
        dispatcher.registerPackets();
    }

    private void processIMC(final InterModProcessEvent event) {
        HelperRegistry.finish();
        MinecraftForge.EVENT_BUS.register(new EntityEventHandler(HelperRegistry.getEventListenerCaps()));
    }
}
