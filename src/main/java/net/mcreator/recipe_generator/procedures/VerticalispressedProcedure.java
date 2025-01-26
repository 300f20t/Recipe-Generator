package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class VerticalispressedProcedure {
	public static boolean execute(LevelAccessor world) {
		if (("VERTICAL").equals(RecipeGeneratorModVariables.WorldVariables.get(world).Mirror_axis)) {
			return true;
		}
		return false;
	}
}
