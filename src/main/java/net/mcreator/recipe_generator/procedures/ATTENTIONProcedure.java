package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class ATTENTIONProcedure {
	public static boolean execute(LevelAccessor world) {
		if (RecipeGeneratorModVariables.WorldVariables.get(world).Is_mirrored) {
			if (RecipeGeneratorModVariables.WorldVariables.get(world).Is_shapeless) {
				return true;
			}
		}
		return false;
	}
}
