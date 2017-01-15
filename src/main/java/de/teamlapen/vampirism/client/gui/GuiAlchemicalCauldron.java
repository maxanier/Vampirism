package de.teamlapen.vampirism.client.gui;

import de.teamlapen.lib.lib.inventory.InventorySlot;
import de.teamlapen.vampirism.inventory.AlchemicalCauldronContainer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

/**
 * 1.10
 *
 * @author maxanier
 */
public class GuiAlchemicalCauldron extends GuiContainer {
    private static final ResourceLocation BACKGROUND = new ResourceLocation("vampirism:textures/gui/alchemicalCauldron.png");

    private final IInventory cauldron;

    public GuiAlchemicalCauldron(InventoryPlayer inventoryPlayer, InventorySlot.IInventorySlotInventory tile) {
        super(new AlchemicalCauldronContainer(inventoryPlayer, tile));
        cauldron = tile;
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(BACKGROUND);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        int k = this.getBurnLeftScaled(13);
        if (k > 0) this.drawTexturedModalRect(i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);

        int l = this.getCookProgressScaled(24);
        this.drawTexturedModalRect(i + 79, j + 34, 176, 14, l + 1, 16);
        l = getCookProgressScaled(30);
        this.drawTexturedModalRect(i + 142, j + 28 + 30 - l, 176, 60 - l, 12, l);
    }

    private int getBurnLeftScaled(int pixels) {
        int totalBurnTime = this.cauldron.getField(0);

        if (totalBurnTime == 0) {
            totalBurnTime = 200;
        }

        return this.cauldron.getField(1) * pixels / totalBurnTime;
    }

    private int getCookProgressScaled(int pixels) {
        int cookTime = this.cauldron.getField(3);
        int totalCookTime = this.cauldron.getField(2);
        return totalCookTime != 0 && cookTime != 0 ? cookTime * pixels / totalCookTime : 0;
    }
}
