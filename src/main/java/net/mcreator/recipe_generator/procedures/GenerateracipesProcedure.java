package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Checkbox;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.util.HashMap;

public class GenerateracipesProcedure {
	public static void execute(LevelAccessor world, HashMap guistate) {
		if (guistate == null)
			return;
		String preGeneratedRecipe = "";
		String recipeType = "";
		String recipeTypeFutures = "";
		if ((RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod).equals("CraftTweaker")) {
			preGeneratedRecipe = RecipeGeneratorModVariables.item_in_slot_9 + " * " + new java.text.DecimalFormat("##").format(RecipeGeneratorModVariables.item_in_slot_9_count) + ", [" + "\n" + "[" + RecipeGeneratorModVariables.item_in_slot_0 + ", "
					+ RecipeGeneratorModVariables.item_in_slot_1 + ", " + RecipeGeneratorModVariables.item_in_slot_2 + "], " + "\n" + "[" + RecipeGeneratorModVariables.item_in_slot_3 + ", " + RecipeGeneratorModVariables.item_in_slot_4 + ", "
					+ RecipeGeneratorModVariables.item_in_slot_5 + "], " + "\n" + "[" + RecipeGeneratorModVariables.item_in_slot_6 + ", " + RecipeGeneratorModVariables.item_in_slot_7 + ", " + RecipeGeneratorModVariables.item_in_slot_8 + "]]);";
			if (guistate.containsKey("checkbox:Is_mirrored") && ((Checkbox) guistate.get("checkbox:Is_mirrored")).selected()) {
				recipeType = "craftingTable.addShapedMirrored(\"";
				recipeTypeFutures = "MirrorAxis." + RecipeGeneratorModVariables.Mirror_axis + ", ";
			} else if (guistate.containsKey("checkbox:Is_shapeless") && ((Checkbox) guistate.get("checkbox:Is_shapeless")).selected()) {
				recipeType = "craftingTable.addShapeless(\"";
				recipeTypeFutures = preGeneratedRecipe.replace("<item:minecraft:air>, ", "");
			} else {
				recipeType = "craftingTable.addShaped(\"";
				recipeTypeFutures = preGeneratedRecipe;
			}
			RecipeGeneratorModVariables.Generated_recipe = recipeType + ""
					+ ((guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : "").isEmpty()
							? RecipeNameCreatorProcedure.execute(guistate)
							: (guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : ""))
					+ "\", " + recipeTypeFutures;
		} else if ((RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod).equals("KubeJS")) {
			preGeneratedRecipe = "\n" + (" A: " + RecipeGeneratorModVariables.item_in_slot_0 + ",") + "\n" + (" B: " + RecipeGeneratorModVariables.item_in_slot_1 + ",") + "\n" + (" C: " + RecipeGeneratorModVariables.item_in_slot_2 + ",") + "\n"
					+ (" D: " + RecipeGeneratorModVariables.item_in_slot_3 + ",") + "\n" + (" E: " + RecipeGeneratorModVariables.item_in_slot_4 + ",") + "\n" + (" F: " + RecipeGeneratorModVariables.item_in_slot_5 + ",") + "\n"
					+ (" G: " + RecipeGeneratorModVariables.item_in_slot_6 + ",") + "\n" + (" H: " + RecipeGeneratorModVariables.item_in_slot_7 + ",") + "\n" + (" I: " + RecipeGeneratorModVariables.item_in_slot_8) + "\n";
			RecipeGeneratorModVariables.Generated_recipe = "ServerEvents.recipes(event => {" + "event.shaped(" + "\n" + " Item.of(" + RecipeGeneratorModVariables.item_in_slot_9 + ", "
					+ new java.text.DecimalFormat("##").format(RecipeGeneratorModVariables.item_in_slot_9_count) + ")," + "\n" + " [" + "\n" + " 'ABC'," + "\n" + " 'DEF'," + "\n" + " 'GHI'" + "\n" + " ]," + "\n" + " {" + preGeneratedRecipe + "})})";
		}
		if (!world.isClientSide() && world.getServer() != null)
			world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(RecipeGeneratorModVariables.Generated_recipe), false);
	}
}
