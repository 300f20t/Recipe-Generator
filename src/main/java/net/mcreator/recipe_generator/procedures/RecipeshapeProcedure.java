package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.client.gui.components.Checkbox;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.util.HashMap;

public class RecipeshapeProcedure {
	public static void execute(LevelAccessor world, HashMap guistate) {
		if (guistate == null)
			return;
		if (guistate.containsKey("checkbox:Is_mirrored") && ((Checkbox) guistate.get("checkbox:Is_mirrored")).selected()) {
			RecipeGeneratorModVariables.WorldVariables.get(world).Is_mirrored = true;
			RecipeGeneratorModVariables.WorldVariables.get(world).syncData(world);
		} else {
			RecipeGeneratorModVariables.WorldVariables.get(world).Is_mirrored = false;
			RecipeGeneratorModVariables.WorldVariables.get(world).syncData(world);
		}
		if (guistate.containsKey("checkbox:Is_shapeless") && ((Checkbox) guistate.get("checkbox:Is_shapeless")).selected()) {
			RecipeGeneratorModVariables.WorldVariables.get(world).Is_shapeless = true;
			RecipeGeneratorModVariables.WorldVariables.get(world).syncData(world);
		} else {
			RecipeGeneratorModVariables.WorldVariables.get(world).Is_shapeless = false;
			RecipeGeneratorModVariables.WorldVariables.get(world).syncData(world);
		}
	}
}
