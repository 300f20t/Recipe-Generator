package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.BuiltInRegistries;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class ItemsFormatProcedure {
	public static String execute(LevelAccessor world, ItemStack inputItem) {
		String formatedItemString = "";
		String inputItemString = "";
		inputItemString = BuiltInRegistries.ITEM.getKey(inputItem.getItem()).toString();
		if ((RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod).equals("CraftTweaker")) {
			formatedItemString = "<item:" + inputItemString + ">";
		} else if ((RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod).equals("KubeJS")) {
			formatedItemString = "'" + inputItemString + "'";
		}
		return formatedItemString;
	}
}