package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class VerticalmirroraxisProcedure {
	public static void execute(LevelAccessor world) {
		RecipeGeneratorModVariables.WorldVariables.get(world).Mirror_axis = "VERTICAL";
		RecipeGeneratorModVariables.WorldVariables.get(world).syncData(world);
	}
}
