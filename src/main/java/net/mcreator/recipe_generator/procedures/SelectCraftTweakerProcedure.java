package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class SelectCraftTweakerProcedure {
	public static void execute(LevelAccessor world) {
		RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod = "CT";
		RecipeGeneratorModVariables.WorldVariables.get(world).syncData(world);
	}
}
