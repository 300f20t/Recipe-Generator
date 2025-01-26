package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class NonemirroraxisProcedure {
	public static void execute(LevelAccessor world) {
		RecipeGeneratorModVariables.WorldVariables.get(world).Mirror_axis = "NONE";
		RecipeGeneratorModVariables.WorldVariables.get(world).syncData(world);
	}
}
