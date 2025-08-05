package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;
import net.mcreator.recipe_generator.init.RecipeGeneratorModMenus;

public class GenerateCraftingTableRecipeProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		{
			RecipeGeneratorModVariables.PlayerVariables _vars = entity.getData(RecipeGeneratorModVariables.PLAYER_VARIABLES);
			_vars.preGeneratedRecipe = RecipeGeneratorModVariables.item_in_slot_9 + " * " + new java.text.DecimalFormat("##").format(RecipeGeneratorModVariables.item_in_slot_9_count) + ", [" + "\n" + "[" + RecipeGeneratorModVariables.item_in_slot_0
					+ ", " + RecipeGeneratorModVariables.item_in_slot_1 + ", " + RecipeGeneratorModVariables.item_in_slot_2 + "], " + "\n" + "[" + RecipeGeneratorModVariables.item_in_slot_3 + ", " + RecipeGeneratorModVariables.item_in_slot_4 + ", "
					+ RecipeGeneratorModVariables.item_in_slot_5 + "], " + "\n" + "[" + RecipeGeneratorModVariables.item_in_slot_6 + ", " + RecipeGeneratorModVariables.item_in_slot_7 + ", " + RecipeGeneratorModVariables.item_in_slot_8 + "]]);";
			_vars.syncPlayerVariables(entity);
		}
		if ((RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod).equals("CraftTweaker")) {
			if ((entity instanceof Player _entity0 && _entity0.containerMenu instanceof RecipeGeneratorModMenus.MenuAccessor _menu0) ? _menu0.getMenuState(1, "Is_shapeless", false) : false) {
				CraftingTableAddShapelessCraftTweakerProcedure.execute(entity);
			} else if ((entity instanceof Player _entity1 && _entity1.containerMenu instanceof RecipeGeneratorModMenus.MenuAccessor _menu1) ? _menu1.getMenuState(1, "Is_mirrored", false) : false) {
				CraftingTableAddShapedMirroredCraftTweakerProcedure.execute(entity);
			} else {
				CraftingTableAddShapedCraftTweakerProcedure.execute(entity);
			}
		} else if ((RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod).equals("KubeJS")) {
			if ((entity instanceof Player _entity2 && _entity2.containerMenu instanceof RecipeGeneratorModMenus.MenuAccessor _menu2) ? _menu2.getMenuState(1, "Is_shapeless", false) : false) {
				GenerateCraftingTableShapelessRecipeKubeJSProcedure.execute();
			} else {
				GenerateCraftingTableShapedRecipeKubeJSProcedure.execute();
			}
		}
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(RecipeGeneratorModVariables.Generated_recipe), false);
	}
}