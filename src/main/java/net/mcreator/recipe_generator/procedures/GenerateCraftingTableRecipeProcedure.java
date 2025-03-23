package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.components.Checkbox;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.util.HashMap;

public class GenerateCraftingTableRecipeProcedure {
	public static void execute(LevelAccessor world, Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		{
			RecipeGeneratorModVariables.PlayerVariables _vars = entity.getData(RecipeGeneratorModVariables.PLAYER_VARIABLES);
			_vars.preGeneratedRecipe = RecipeGeneratorModVariables.item_in_slot_9 + " * " + new java.text.DecimalFormat("##").format(RecipeGeneratorModVariables.item_in_slot_9_count) + ", [" + "\n" + "[" + RecipeGeneratorModVariables.item_in_slot_0
					+ ", " + RecipeGeneratorModVariables.item_in_slot_1 + ", " + RecipeGeneratorModVariables.item_in_slot_2 + "], " + "\n" + "[" + RecipeGeneratorModVariables.item_in_slot_3 + ", " + RecipeGeneratorModVariables.item_in_slot_4 + ", "
					+ RecipeGeneratorModVariables.item_in_slot_5 + "], " + "\n" + "[" + RecipeGeneratorModVariables.item_in_slot_6 + ", " + RecipeGeneratorModVariables.item_in_slot_7 + ", " + RecipeGeneratorModVariables.item_in_slot_8 + "]]);";
			_vars.syncPlayerVariables(entity);
		}
		if ((RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod).equals("CraftTweaker")) {
			if (guistate.containsKey("checkbox:Is_shapeless") && ((Checkbox) guistate.get("checkbox:Is_shapeless")).selected()) {
				GenerateCraftingTableShapelessRecipeCraftTweakerProcedure.execute(guistate);
			} else if (guistate.containsKey("checkbox:Is_mirrored") && ((Checkbox) guistate.get("checkbox:Is_mirrored")).selected()) {
				GenerateCraftingTableMirroredRecipeCraftTweakerProcedure.execute(entity, guistate);
			} else {
				GenerateCraftingTableShapedRecipeCraftTweakerProcedure.execute(entity, guistate);
			}
		} else if ((RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod).equals("KubeJS")) {
			GenerateCraftingTableRecipeKubeJSProcedure.execute();
		}
		if (!world.isClientSide() && world.getServer() != null)
			world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(RecipeGeneratorModVariables.Generated_recipe), false);
	}
}
