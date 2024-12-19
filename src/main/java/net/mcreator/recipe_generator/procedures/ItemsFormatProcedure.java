package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class ItemsFormatProcedure {
	public static String execute(LevelAccessor world, String inputItem) {
		if (inputItem == null)
			return "";
		String formatedItemString = "";
		String inputItemString = "";
		inputItemString = inputItem;
		if ((RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod).equals("CraftTweaker")) {
		} else if ((RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod).equals("KubeJS")) {
		}
		return formatedItemString;
	}
}
