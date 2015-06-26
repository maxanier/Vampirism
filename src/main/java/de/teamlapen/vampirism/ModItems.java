package de.teamlapen.vampirism;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import cpw.mods.fml.common.registry.GameRegistry;
import de.teamlapen.vampirism.item.ItemBloodBottle;
import de.teamlapen.vampirism.item.ItemCoffin;
import de.teamlapen.vampirism.item.ItemGemOfBinding;
import de.teamlapen.vampirism.item.ItemHumanHeart;
import de.teamlapen.vampirism.item.ItemLeechSword;
import de.teamlapen.vampirism.item.ItemMinionNameTag;
import de.teamlapen.vampirism.item.ItemPitchfork;
import de.teamlapen.vampirism.item.ItemPureBlood;
import de.teamlapen.vampirism.item.ItemSunscreen;
import de.teamlapen.vampirism.item.ItemTorch;
import de.teamlapen.vampirism.item.ItemVampireArmor;
import de.teamlapen.vampirism.item.ItemVampireFang;

public class ModItems {

	public static void init() {
		GameRegistry.registerItem(leechSword, ItemLeechSword.name);
		GameRegistry.registerItem(bloodBottle, ItemBloodBottle.name);
		GameRegistry.registerItem(vampireFang, ItemVampireFang.NAME);
		GameRegistry.registerItem(pitchfork, ItemPitchfork.name);
		//GameRegistry.registerItem(torch, ItemTorch.name);
		GameRegistry.registerItem(humanHeart, ItemHumanHeart.name);
		GameRegistry.registerItem(pureBlood,ItemPureBlood.name);
		GameRegistry.registerItem(sunscreen, ItemSunscreen.name);
		GameRegistry.registerItem(coffin, ItemCoffin.name);
		GameRegistry.registerItem(vampireHelmet, vampireHelmet.getRegisterItemName());
		GameRegistry.registerItem(vampireChestplate, vampireChestplate.getRegisterItemName());
		GameRegistry.registerItem(vampireLeggings, vampireLeggings.getRegisterItemName());
		GameRegistry.registerItem(vampireBoots, vampireBoots.getRegisterItemName());
		GameRegistry.registerItem(gemOfBinding, ItemGemOfBinding.name);
		GameRegistry.registerItem(minionNameTag, ItemMinionNameTag.name);
	}
	
	public static void registerRecipes(){
		GameRegistry.addRecipe(new ItemStack(bloodBottle, 1, 0), "   ", "XYX", " X ", 'X', Blocks.glass, 'Y', Items.rotten_flesh);
		GameRegistry.addRecipe(new ItemStack(leechSword, 1), "XYX", "XYX", " Z ", 'X', vampireFang, 'Y', Items.iron_ingot, 'Z', Items.stick);
		GameRegistry.addRecipe(new ItemStack(sunscreen,1),"XYX","YZY","XYX",'X',ModBlocks.vampireFlower,'Y',Items.gold_nugget,'Z',humanHeart);
		GameRegistry.addRecipe(new ItemStack(coffin,1),"XXX","Y Y","XXX",'X',Blocks.planks,'Y',Blocks.wool);
		GameRegistry.addRecipe(new ItemStack(vampireHelmet,1),"XXX","YYY","YZY",'X',ModBlocks.vampireFlower,'Y',Items.iron_ingot,'Z',new ItemStack(bloodBottle,1,ItemBloodBottle.MAX_BLOOD));
		GameRegistry.addRecipe(new ItemStack(vampireBoots,1),"YZY","Y Y",'Y',Items.iron_ingot,'Z',new ItemStack(bloodBottle,1,ItemBloodBottle.MAX_BLOOD));
		GameRegistry.addRecipe(new ItemStack(vampireLeggings,1),"YYY","YZY","Y Y",'Y',Items.iron_ingot,'Z',new ItemStack(bloodBottle,1,ItemBloodBottle.MAX_BLOOD));
		GameRegistry.addRecipe(new ItemStack(vampireChestplate,1),"YZY","YYY","YYY",'Y',Items.iron_ingot,'Z',new ItemStack(bloodBottle,1,ItemBloodBottle.MAX_BLOOD));
		GameRegistry.addShapelessRecipe(new ItemStack(Items.glass_bottle),new ItemStack(bloodBottle,1,0));
		GameRegistry.addRecipe(new ItemStack(gemOfBinding,1)," X ","YZY"," V ",'X',ModItems.humanHeart,'Y',new ItemStack(bloodBottle,1,ItemBloodBottle.MAX_BLOOD),'Z',Items.diamond,'V',ModBlocks.vampireFlower);
		GameRegistry.addShapelessRecipe(new ItemStack(minionNameTag), Items.paper,ModBlocks.vampireFlower);
	}
	public static ItemSword leechSword = new ItemLeechSword();
	public static ItemBloodBottle bloodBottle = new ItemBloodBottle();

	public static ItemVampireFang vampireFang = new ItemVampireFang();
	public static ItemPitchfork pitchfork=new ItemPitchfork();
	public static ItemTorch torch = new ItemTorch();
	public static ItemHumanHeart humanHeart=new ItemHumanHeart();
	public static ItemPureBlood pureBlood=new ItemPureBlood();
	public static ItemSunscreen sunscreen=new ItemSunscreen();
	public static ItemCoffin coffin = new ItemCoffin();
	public static ItemVampireArmor vampireHelmet =new ItemVampireArmor(0);
	public static ItemVampireArmor vampireChestplate=new ItemVampireArmor(1);
	public static ItemVampireArmor vampireLeggings = new ItemVampireArmor(2);
	public static ItemVampireArmor vampireBoots = new ItemVampireArmor(3);
	public static ItemGemOfBinding gemOfBinding = new ItemGemOfBinding();
	public static ItemMinionNameTag minionNameTag = new ItemMinionNameTag();
}
