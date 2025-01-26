package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class DiagonalispressedProcedure {
	public static boolean execute(LevelAccessor world) {
		if (("DIAGONAL").equals(RecipeGeneratorModVariables.WorldVariables.get(world).Mirror_axis)) {
			return true;
		}
		return false;
	}
}
