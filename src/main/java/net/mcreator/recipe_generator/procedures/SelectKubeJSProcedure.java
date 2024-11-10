package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class SelectKubeJSProcedure {
	public static void execute(LevelAccessor world) {
		RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod = "KJS";
		RecipeGeneratorModVariables.WorldVariables.get(world).syncData(world);
	}
}
