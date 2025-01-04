package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.BuiltInRegistries;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class ItemsFormatProcedure {
	public static String execute(ItemStack inputItem) {
		String formatedItemString = "";
		String inputItemString = "";
		inputItemString = BuiltInRegistries.ITEM.getKey(inputItem.getItem()).toString();
		if ((RecipeGeneratorModVariables.selectedMethod).equals("CraftTweaker")) {
			formatedItemString = "<item:" + inputItemString + ">";
		} else if ((RecipeGeneratorModVariables.selectedMethod).equals("KubeJS")) {
			formatedItemString = "'" + inputItemString + "'";
		}
		return formatedItemString;
	}
}
