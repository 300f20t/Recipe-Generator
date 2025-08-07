package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;
import net.mcreator.recipe_generator.init.RecipeGeneratorModMenus;

public class AvaritaCraftingTableAddShapedCraftTweakerProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		String recipeType = "";
		String recipeTypeFutures = "";
		recipeType = "mods.avaritia.CraftingTable.addShaped(\"";
		RecipeGeneratorModVariables.Generated_recipe = recipeType + ""
				+ (((entity instanceof Player _entity0 && _entity0.containerMenu instanceof RecipeGeneratorModMenus.MenuAccessor _menu0) ? _menu0.getMenuState(0, "recipe_name", "") : "").isEmpty()
						? RecipeNameCreatorProcedure.execute(entity)
						: ((entity instanceof Player _entity1 && _entity1.containerMenu instanceof RecipeGeneratorModMenus.MenuAccessor _menu1) ? _menu1.getMenuState(0, "recipe_name", "") : ""))
				+ "\", " + "4, " + entity.getData(RecipeGeneratorModVariables.PLAYER_VARIABLES).preGeneratedRecipe;
	}
}