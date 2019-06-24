package de.teamlapen.vampirism.items;

import de.teamlapen.vampirism.api.VReference;
import de.teamlapen.vampirism.api.items.IItemWithTier;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemHunterAxe extends VampirismHunterWeapon implements IItemWithTier {
    private static final String regName = "hunter_axe";

    private final TIER tier;


    public ItemHunterAxe(TIER tier) {
        super(regName + "_" + tier.getName(), ItemTier.IRON, 0.37F, new Properties());
        this.tier = tier;
        this.setTranslation_key(regName);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        addTierInformation(tooltip);
        tooltip.add(new TextComponentTranslation("text.vampirism.deals_more_damage_to", Math.round((getVampireMult() - 1) * 100)).appendSibling(VReference.VAMPIRE_FACTION.getNamePlural()));
    }


    @Override
    public float getDamageMultiplierForFaction(@Nonnull ItemStack stack) {
        return getVampireMult();
    }

    /**
     * @return A itemstack with the correct knockback enchantment applied
     */
    public ItemStack getEnchantedStack() {
        ItemStack stack = new ItemStack(this);
        Map<Enchantment, Integer> map = new HashMap<>();
        map.put(Enchantments.KNOCKBACK, getKnockback());
        EnchantmentHelper.setEnchantments(map, stack);
        return stack;
    }

    @Override
    public int getMinLevel(@Nonnull ItemStack stack) {
        return getMinLevel();
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return false;
    }

    @Override
    public TIER getVampirismTier() {
        return tier;
    }

    private int getKnockback() {
        switch (tier) {
            case ULTIMATE:
                return 4;
            case ENHANCED:
                return 3;
            default:
                return 2;
        }
    }

    private int getMinLevel() {
        switch (tier) {
            case ULTIMATE:
                return 8;
            case ENHANCED:
                return 6;
            default:
                return 4;
        }
    }

    private float getVampireMult() {
        switch (tier) {
            case ULTIMATE:
                return 1.4F;
            case ENHANCED:
                return 1.3F;
            default:
                return 1.1F;
        }
    }


}
