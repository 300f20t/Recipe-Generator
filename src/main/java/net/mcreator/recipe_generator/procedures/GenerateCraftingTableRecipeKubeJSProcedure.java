package net.mcreator.recipe_generator.procedures;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.util.List;
import java.util.ArrayList;

public class GenerateCraftingTableRecipeKubeJSProcedure {
	public static void execute() {
		List<Object> KubeJSItemShapeArray = new ArrayList<>();
		double i = 0;
		String preGeneratedRecipe = "";
		String recipeType = "";
		String recipeTypeFutures = "";
		String KubeJSRecipeShape = "";
		KubeJSItemShapeArray.add("A:");
		KubeJSItemShapeArray.add("B:");
		KubeJSItemShapeArray.add("C:");
		KubeJSItemShapeArray.add("D:");
		KubeJSItemShapeArray.add("E:");
		KubeJSItemShapeArray.add("F:");
		KubeJSItemShapeArray.add("G:");
		KubeJSItemShapeArray.add("H:");
		KubeJSItemShapeArray.add("I:");
		preGeneratedRecipe = "\n" + ((KubeJSItemShapeArray.get(0) instanceof String _s ? _s : "") + "" + RecipeGeneratorModVariables.item_in_slot_0 + ",") + "\n"
				+ ((KubeJSItemShapeArray.get(1) instanceof String _s ? _s : "") + "" + RecipeGeneratorModVariables.item_in_slot_1 + ",") + "\n"
				+ ((KubeJSItemShapeArray.get(2) instanceof String _s ? _s : "") + "" + RecipeGeneratorModVariables.item_in_slot_2 + ",") + "\n"
				+ ((KubeJSItemShapeArray.get(3) instanceof String _s ? _s : "") + "" + RecipeGeneratorModVariables.item_in_slot_3 + ",") + "\n"
				+ ((KubeJSItemShapeArray.get(4) instanceof String _s ? _s : "") + "" + RecipeGeneratorModVariables.item_in_slot_4 + ",") + "\n"
				+ ((KubeJSItemShapeArray.get(5) instanceof String _s ? _s : "") + "" + RecipeGeneratorModVariables.item_in_slot_5 + ",") + "\n"
				+ ((KubeJSItemShapeArray.get(6) instanceof String _s ? _s : "") + "" + RecipeGeneratorModVariables.item_in_slot_6 + ",") + "\n"
				+ ((KubeJSItemShapeArray.get(7) instanceof String _s ? _s : "") + "" + RecipeGeneratorModVariables.item_in_slot_7 + ",") + "\n"
				+ ((KubeJSItemShapeArray.get(8) instanceof String _s ? _s : "") + "" + RecipeGeneratorModVariables.item_in_slot_8 + ",") + "\n";
		i = 0;
		while (i <= 8) {
			if (preGeneratedRecipe.contains((KubeJSItemShapeArray.get((int) i) instanceof String _s ? _s : "") + "'minecraft:air',")) {
				preGeneratedRecipe = preGeneratedRecipe.replace((KubeJSItemShapeArray.get((int) i) instanceof String _s ? _s : "") + "'minecraft:air',", " ");
			}
			i = i + 1;
		}
		KubeJSRecipeShape = "'" + (!(RecipeGeneratorModVariables.item_in_slot_0).equals("'minecraft:air'") ? "A" : " ") + (!(RecipeGeneratorModVariables.item_in_slot_1).equals("'minecraft:air'") ? "B" : " ")
				+ (!(RecipeGeneratorModVariables.item_in_slot_2).equals("'minecraft:air'") ? "C" : " ") + "'," + "\n" + "'" + (!(RecipeGeneratorModVariables.item_in_slot_3).equals("'minecraft:air'") ? "D" : " ")
				+ (!(RecipeGeneratorModVariables.item_in_slot_4).equals("'minecraft:air'") ? "E" : " ") + (!(RecipeGeneratorModVariables.item_in_slot_5).equals("'minecraft:air'") ? "F" : " ") + "'," + "\n" + "'"
				+ (!(RecipeGeneratorModVariables.item_in_slot_6).equals("'minecraft:air'") ? "G" : " ") + (!(RecipeGeneratorModVariables.item_in_slot_7).equals("'minecraft:air'") ? "H" : " ")
				+ (!(RecipeGeneratorModVariables.item_in_slot_8).equals("'minecraft:air'") ? "I" : " ") + "'";
		RecipeGeneratorModVariables.Generated_recipe = "ServerEvents.recipes(event => {" + "event.shaped(" + "\n" + " Item.of(" + RecipeGeneratorModVariables.item_in_slot_9 + ", "
				+ new java.text.DecimalFormat("##").format(RecipeGeneratorModVariables.item_in_slot_9_count) + ")," + "\n" + " [" + "\n" + KubeJSRecipeShape + "\n" + " ]," + "\n" + " {" + preGeneratedRecipe + "})})";
	}
}
