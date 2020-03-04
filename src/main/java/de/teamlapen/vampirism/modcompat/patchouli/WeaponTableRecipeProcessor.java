package de.teamlapen.vampirism.modcompat.patchouli;

import de.teamlapen.lib.lib.util.UtilLib;
import de.teamlapen.vampirism.api.entity.player.skills.ISkill;
import de.teamlapen.vampirism.api.items.IWeaponTableRecipe;
import de.teamlapen.vampirism.inventory.recipes.ShapedWeaponTableRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariableProvider;
import vazkii.patchouli.api.PatchouliAPI;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Loaded and instantiated by Patchouli to process weapon table recipe page
 */
@SuppressWarnings("unused")
public class WeaponTableRecipeProcessor implements IComponentProcessor {

    private IWeaponTableRecipe recipe;

    @Override
    public String process(String key) {
        if (recipe == null) {
            return null;
        } else if ("heading".equals(key)) {
            return UtilLib.translate(recipe.getRecipeOutput().getTranslationKey());
        } else if ("output".equals(key)) {
            return PatchouliAPI.instance.serializeItemStack(recipe.getRecipeOutput());
        } else if (key.startsWith("input")) {
            int index = Integer.parseInt(key.substring(5)) - 1;
            NonNullList<Ingredient> list = recipe.getIngredients();

            if (recipe instanceof ShapedWeaponTableRecipe) {
                int shapedX = index % 4;
                if (((ShapedWeaponTableRecipe) recipe).getWidth() < shapedX + 1)
                    return PatchouliAPI.instance.serializeIngredient(Ingredient.EMPTY);
                int shapedY = index / 4;
                int realIndex = index - (shapedY * (4 - ((ShapedWeaponTableRecipe) recipe).getWidth()));
                return PatchouliAPI.instance.serializeIngredient(list.size() > realIndex ? list.get(realIndex) : Ingredient.EMPTY);
            } else {
                return PatchouliAPI.instance.serializeIngredient(list.size() > index ? list.get(index) : Ingredient.EMPTY);
            }

        } else if ("lava".equals(key)) {
            return recipe.getRequiredLavaUnits() > 0 ? "yes" : null;
        } else if ("skills".equals(key)) {
            if (recipe.getRequiredSkills().length > 0) {
                return UtilLib.translate("gui.vampirism.hunter_weapon_table.skill", ("$(br)" + Arrays.stream(recipe.getRequiredSkills()).map(ISkill::getTranslationKey).map(n -> UtilLib.translate(n)).map(s -> "$(li) " + s).collect(Collectors.joining(", "))));
            }
            return null;
        } else if ("level".equals(key)) {
            if (recipe.getRequiredLevel() > 1) {
                return UtilLib.translate("gui.vampirism.hunter_weapon_table.level", recipe.getRequiredLevel());
            }
            return null;
        }
        return null;
    }

    @Override
    public void setup(IVariableProvider<String> iVariableProvider) {
        String recipeID = iVariableProvider.get("recipe");
        this.recipe = (IWeaponTableRecipe) Minecraft.getInstance().getConnection().getRecipeManager().getRecipe(new ResourceLocation(recipeID)).orElse(null);
    }
}
