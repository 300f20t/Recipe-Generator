package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;
import net.mcreator.recipe_generator.init.RecipeGeneratorModMenus;

public class SmithingAddTransformRecipeCraftTweakerProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		String preGeneratedRecipe = "";
		preGeneratedRecipe = RecipeGeneratorModVariables.item_in_slot_3 + ", " + RecipeGeneratorModVariables.item_in_slot_0 + ", " + RecipeGeneratorModVariables.item_in_slot_1 + ", " + RecipeGeneratorModVariables.item_in_slot_2 + ");";
		RecipeGeneratorModVariables.Generated_recipe = "import crafttweaker.api.recipe.SmithingRecipeManager;" + "\n" + "import crafttweaker.api.item.IItemStack;" + "\n" + "smithing.addTransformRecipe(\""
				+ (((entity instanceof Player _entity0 && _entity0.containerMenu instanceof RecipeGeneratorModMenus.MenuAccessor _menu0) ? _menu0.getMenuState(0, "recipe_name", "") : "").isEmpty()
						? RecipeNameCreatorProcedure.execute(entity)
						: ((entity instanceof Player _entity1 && _entity1.containerMenu instanceof RecipeGeneratorModMenus.MenuAccessor _menu1) ? _menu1.getMenuState(0, "recipe_name", "") : ""))
				+ "\", " + preGeneratedRecipe;
	}
}