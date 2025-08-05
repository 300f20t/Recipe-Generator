package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class ReturnSelectedGeneratingMethodProcedure {
	public static String execute(LevelAccessor world) {
		return RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod;
	}
}