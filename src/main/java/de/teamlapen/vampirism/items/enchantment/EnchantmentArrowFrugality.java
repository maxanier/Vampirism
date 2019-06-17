package de.teamlapen.vampirism.items.enchantment;

import de.teamlapen.vampirism.api.VReference;
import de.teamlapen.vampirism.items.ItemTechCrossbow;
import de.teamlapen.vampirism.util.REFERENCE;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

/**
 * Makes tech crossbows use less arrows
 */
public class EnchantmentArrowFrugality extends Enchantment {
    public EnchantmentArrowFrugality(Rarity rarityIn) {
        super(rarityIn, VReference.CROSSBOW_ENCHANTMENT, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setRegistryName(REFERENCE.MODID, "crossbowfrugality");

    }

    @Override
    protected String getDefaultTranslationKey() {
        return "vampirism.arrow_frugality";
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return !stack.isEmpty() && stack.getItem() instanceof ItemTechCrossbow;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return 50;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 10 + enchantmentLevel * 5;
    }
}
