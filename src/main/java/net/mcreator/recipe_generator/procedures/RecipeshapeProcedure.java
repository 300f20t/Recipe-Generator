package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;
import net.mcreator.recipe_generator.init.RecipeGeneratorModMenus;

public class RecipeshapeProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof Player _entity0 && _entity0.containerMenu instanceof RecipeGeneratorModMenus.MenuAccessor _menu0) ? _menu0.getMenuState(1, "Is_mirrored", false) : false) {
			RecipeGeneratorModVariables.Is_mirrored = true;
		} else {
			RecipeGeneratorModVariables.Is_mirrored = false;
		}
		if ((entity instanceof Player _entity1 && _entity1.containerMenu instanceof RecipeGeneratorModMenus.MenuAccessor _menu1) ? _menu1.getMenuState(1, "Is_shapeless", false) : false) {
			RecipeGeneratorModVariables.Is_shapeless = true;
		} else {
			RecipeGeneratorModVariables.Is_shapeless = false;
		}
	}
}