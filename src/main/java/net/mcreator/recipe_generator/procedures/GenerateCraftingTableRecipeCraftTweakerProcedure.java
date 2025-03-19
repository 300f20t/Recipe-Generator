package net.mcreator.recipe_generator.procedures;

import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Checkbox;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class GenerateCraftingTableRecipeCraftTweakerProcedure {
	public static void execute(HashMap guistate) {
		if (guistate == null)
			return;
		List<Object> KubeJSItemShapeArray = new ArrayList<>();
		double i = 0;
		String preGeneratedRecipe = "";
		String recipeType = "";
		String recipeTypeFutures = "";
		String KubeJSRecipeShape = "";
		preGeneratedRecipe = RecipeGeneratorModVariables.item_in_slot_9 + " * " + new java.text.DecimalFormat("##").format(RecipeGeneratorModVariables.item_in_slot_9_count) + ", [" + "\n" + "[" + RecipeGeneratorModVariables.item_in_slot_0 + ", "
				+ RecipeGeneratorModVariables.item_in_slot_1 + ", " + RecipeGeneratorModVariables.item_in_slot_2 + "], " + "\n" + "[" + RecipeGeneratorModVariables.item_in_slot_3 + ", " + RecipeGeneratorModVariables.item_in_slot_4 + ", "
				+ RecipeGeneratorModVariables.item_in_slot_5 + "], " + "\n" + "[" + RecipeGeneratorModVariables.item_in_slot_6 + ", " + RecipeGeneratorModVariables.item_in_slot_7 + ", " + RecipeGeneratorModVariables.item_in_slot_8 + "]]);";
		if (guistate.containsKey("checkbox:Is_mirrored") && ((Checkbox) guistate.get("checkbox:Is_mirrored")).selected()) {
			recipeType = "craftingTable.addShapedMirrored(\"";
			recipeTypeFutures = "MirrorAxis." + RecipeGeneratorModVariables.Mirror_axis + ", ";
			RecipeGeneratorModVariables.Generated_recipe = "import crafttweaker.api.recipe.MirrorAxis;" + recipeType
					+ ((guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : "").isEmpty()
							? RecipeNameCreatorProcedure.execute(guistate)
							: (guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : ""))
					+ "\", " + recipeTypeFutures + preGeneratedRecipe;
		} else if (guistate.containsKey("checkbox:Is_shapeless") && ((Checkbox) guistate.get("checkbox:Is_shapeless")).selected()) {
			recipeType = "craftingTable.addShapeless(\"";
			RecipeGeneratorModVariables.Generated_recipe = recipeType + ""
					+ ((guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : "").isEmpty()
							? RecipeNameCreatorProcedure.execute(guistate)
							: (guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : ""))
					+ "\", " + RecipeGeneratorModVariables.item_in_slot_9
					+ ((" * " + new java.text.DecimalFormat("##").format(RecipeGeneratorModVariables.item_in_slot_9_count) + ", " + "\n" + "["
							+ (RecipeGeneratorModVariables.item_in_slot_0.contains("<item:minecraft:air>, ") ? "" : RecipeGeneratorModVariables.item_in_slot_0 + ", ")
							+ (RecipeGeneratorModVariables.item_in_slot_1.contains("<item:minecraft:air>, ") ? "" : RecipeGeneratorModVariables.item_in_slot_1 + ", ")
							+ (RecipeGeneratorModVariables.item_in_slot_2.contains("<item:minecraft:air>, ") ? "" : RecipeGeneratorModVariables.item_in_slot_2 + ", ")
							+ (RecipeGeneratorModVariables.item_in_slot_3.contains("<item:minecraft:air>, ") ? "" : RecipeGeneratorModVariables.item_in_slot_3 + ", ")
							+ (RecipeGeneratorModVariables.item_in_slot_4.contains("<item:minecraft:air>, ") ? "" : RecipeGeneratorModVariables.item_in_slot_4 + ", ")
							+ (RecipeGeneratorModVariables.item_in_slot_5.contains("<item:minecraft:air>, ") ? "" : RecipeGeneratorModVariables.item_in_slot_5 + ", ")
							+ (RecipeGeneratorModVariables.item_in_slot_6.contains("<item:minecraft:air>, ") ? "" : RecipeGeneratorModVariables.item_in_slot_6 + ", ")
							+ (RecipeGeneratorModVariables.item_in_slot_7.contains("<item:minecraft:air>, ") ? "" : RecipeGeneratorModVariables.item_in_slot_7 + ", ")
							+ (RecipeGeneratorModVariables.item_in_slot_8.contains("<item:minecraft:air>, ") ? "" : RecipeGeneratorModVariables.item_in_slot_8 + ", ") + ");").replace("<item:minecraft:air>, ", "")).replace(">, );", ">]);");
		} else {
			recipeType = "craftingTable.addShaped(\"";
			recipeTypeFutures = preGeneratedRecipe;
			RecipeGeneratorModVariables.Generated_recipe = recipeType + ""
					+ ((guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : "").isEmpty()
							? RecipeNameCreatorProcedure.execute(guistate)
							: (guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : ""))
					+ "\", " + recipeTypeFutures;
		}
	}
}
