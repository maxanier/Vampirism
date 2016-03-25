package de.teamlapen.vampirism.core;

import de.teamlapen.lib.lib.item.ItemMetaBlock;
import de.teamlapen.lib.lib.util.IInitListener;
import de.teamlapen.vampirism.blocks.*;
import de.teamlapen.vampirism.tileentity.TileAltarInfusion;
import de.teamlapen.vampirism.tileentity.TileCoffin;
import de.teamlapen.vampirism.tileentity.TileTent;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.event.FMLStateEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Handles all block registrations and reference.
 */
public class ModBlocks {
    public static BlockFluidBlood fluidBlood;
    public static BlockCastleBlock castleBlock;
    public static BlockCursedEarth cursedEarth;
    public static VampirismFlower vampirismFlower;
    public static BlockTent tent;
    public static BlockTentMain tentMain;
    public static BlockCoffin coffin;
    public static BlockAltarInfusion altarInfusion;
    public static BlockAltarPillar altarPillar;
    public static BlockAltarTip altarTip;
    public static BlockHunterTable hunterTable;
    public static BlockMedChair medChair;

    public static void onInitStep(IInitListener.Step step, FMLStateEvent event) {
        switch (step) {
            case PRE_INIT:
                registerBlocks();
                registerTiles();
                break;
        }

    }

    private static void registerTiles() {
        GameRegistry.registerTileEntity(TileTent.class, "VampirismTent");
        GameRegistry.registerTileEntity(TileCoffin.class, "VampirismCoffin");
        GameRegistry.registerTileEntity(TileAltarInfusion.class, "VampirismAltarInfusion");
    }

    private static void registerBlocks() {
        fluidBlood = registerBlock(new BlockFluidBlood());//TODO Maybe remove blood block later
        castleBlock = registerBlock(new BlockCastleBlock(), ItemMetaBlock.class);
        cursedEarth = registerBlock(new BlockCursedEarth());
        vampirismFlower = registerBlock(new VampirismFlower(), ItemMetaBlock.class);
        tent = registerBlock(new BlockTent(), null);
        tentMain = registerBlock(new BlockTentMain(), null);
        coffin = registerBlock(new BlockCoffin(), null);
        altarInfusion = registerBlock(new BlockAltarInfusion());
        altarPillar = registerBlock(new BlockAltarPillar());
        altarTip = registerBlock(new BlockAltarTip());
        hunterTable = registerBlock(new BlockHunterTable());
        medChair = registerBlock(new BlockMedChair());


    }

    private static <T extends Block> T registerBlock(T block) {
        return registerBlock(block, ItemBlock.class);
    }
    private static <T extends Block> T registerBlock(T block, Class<? extends ItemBlock> itemclass) {
        if (block.getRegistryName() == null) {
            throw new IllegalArgumentException("Missing registry name for " + block);
        }
        GameRegistry.registerBlock(block, itemclass);
        return block;
    }


}
