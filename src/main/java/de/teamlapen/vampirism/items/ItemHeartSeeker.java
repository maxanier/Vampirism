package de.teamlapen.vampirism.items;

import de.teamlapen.vampirism.api.items.IItemWithTier;
import de.teamlapen.vampirism.config.Balance;
import de.teamlapen.vampirism.util.Helper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class ItemHeartSeeker extends VampirismVampireSword implements IItemWithTier {

    public static final String regName = "heart_seeker";
    private final static float[] DAMAGE_TIER = {5.0F, 6.0F, 8.0F};
    private final static float[] SPEED_TIER = {0.4f, 0.5f, 0.6f};
    private final TIER tier;

    public ItemHeartSeeker(TIER tier) {
        super(regName + "_" + tier, ItemTier.IRON, 0.0f, 0.0f, new Properties());
        this.tier = tier;
    }


    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        addTierInformation(tooltip);
    }

    @Override
    public TIER getTier() {
        return tier;
    }


    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (target.getHealth() <= 0.0f && Helper.isVampire(attacker)) {
            float trained = getTrained(stack, attacker);
            int exp = target instanceof EntityPlayer ? 10 : (attacker instanceof EntityPlayer ? (Helper.getExperiencePoints(target, (EntityPlayer) attacker)) : 5);
            trained += exp / 5f * (1.0f - trained) / 15f;
            setTrained(stack, attacker, trained);
        }
        float charged = getCharged(stack);
        charged -= Balance.general.HEART_SEEKER_USAGE_FACTOR * (getTier().ordinal() + 2) / 2f;
        setCharged(stack, charged);
        attacker.setHeldItem(EnumHand.MAIN_HAND, stack);
        return super.hitEntity(stack, target, attacker);
    }

    @Override
    protected float getBaseAttackDamage(ItemStack stack) {
        return DAMAGE_TIER[getTier().ordinal()];
    }

    @Override
    protected float getBaseAttackSpeed(ItemStack stack) {
        return SPEED_TIER[getTier().ordinal()];
    }

    @Override
    protected float getChargingFactor(ItemStack stack) {
        return (float) Balance.general.HEART_SEEKER_CHARGING_FACTOR * 2f / (getTier().ordinal() + 2);
    }

}
