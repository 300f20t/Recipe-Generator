package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;
import net.mcreator.recipe_generator.init.RecipeGeneratorModMenus;

public class CraftingTableAddShapedMirroredCraftTweakerProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		String recipeType = "";
		String recipeTypeFutures = "";
		recipeType = "craftingTable.addShapedMirrored(\"";
		recipeTypeFutures = "MirrorAxis." + RecipeGeneratorModVariables.Mirror_axis + ", ";
		RecipeGeneratorModVariables.Generated_recipe = "import crafttweaker.api.recipe.MirrorAxis;" + recipeType
				+ (((entity instanceof Player _entity0 && _entity0.containerMenu instanceof RecipeGeneratorModMenus.MenuAccessor _menu0) ? _menu0.getMenuState(0, "recipe_name", "") : "").isEmpty()
						? RecipeNameCreatorProcedure.execute(entity)
						: ((entity instanceof Player _entity1 && _entity1.containerMenu instanceof RecipeGeneratorModMenus.MenuAccessor _menu1) ? _menu1.getMenuState(0, "recipe_name", "") : ""))
				+ "\", " + recipeTypeFutures + entity.getData(RecipeGeneratorModVariables.PLAYER_VARIABLES).preGeneratedRecipe;
	}
}