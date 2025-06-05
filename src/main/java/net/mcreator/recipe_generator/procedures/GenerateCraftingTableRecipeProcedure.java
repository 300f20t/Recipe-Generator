package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
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
			String _setval = RecipeGeneratorModVariables.item_in_slot_9 + " * " + new java.text.DecimalFormat("##").format(RecipeGeneratorModVariables.item_in_slot_9_count) + ", [" + "\n" + "[" + RecipeGeneratorModVariables.item_in_slot_0 + ", "
					+ RecipeGeneratorModVariables.item_in_slot_1 + ", " + RecipeGeneratorModVariables.item_in_slot_2 + "], " + "\n" + "[" + RecipeGeneratorModVariables.item_in_slot_3 + ", " + RecipeGeneratorModVariables.item_in_slot_4 + ", "
					+ RecipeGeneratorModVariables.item_in_slot_5 + "], " + "\n" + "[" + RecipeGeneratorModVariables.item_in_slot_6 + ", " + RecipeGeneratorModVariables.item_in_slot_7 + ", " + RecipeGeneratorModVariables.item_in_slot_8 + "]]);";
			entity.getCapability(RecipeGeneratorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.preGeneratedRecipe = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if ((RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod).equals("CraftTweaker")) {
			if (guistate.containsKey("checkbox:Is_shapeless") && ((Checkbox) guistate.get("checkbox:Is_shapeless")).selected()) {
				CraftingTableAddShapelessCraftTweakerProcedure.execute(guistate);
			} else if (guistate.containsKey("checkbox:Is_mirrored") && ((Checkbox) guistate.get("checkbox:Is_mirrored")).selected()) {
				CraftingTableAddShapedMirroredCraftTweakerProcedure.execute(entity, guistate);
			} else {
				CraftingTableAddShapedCraftTweakerProcedure.execute(entity, guistate);
			}
		} else if ((RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod).equals("KubeJS")) {
			if (guistate.containsKey("checkbox:Is_shapeless") && ((Checkbox) guistate.get("checkbox:Is_shapeless")).selected()) {
				GenerateCraftingTableShapelessRecipeKubeJSProcedure.execute();
			} else {
				GenerateCraftingTableShapedRecipeKubeJSProcedure.execute();
			}
		}
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(RecipeGeneratorModVariables.Generated_recipe), false);
	}
}
