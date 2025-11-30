package net.mcreator.recipe_generator.procedures;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class GenerateCraftingTableShapedRecipeKubeJSProcedure {
	public static void execute() {
		double i = 0;
		String preGeneratedRecipe = "";
		String recipeType = "";
		String recipeTypeFutures = "";
		String KubeJSRecipeShape = "";
		{
			RecipeGeneratorModVariables.GUILabelsList.add("A:");
		}
		{
			RecipeGeneratorModVariables.GUILabelsList.add("B:");
		}
		{
			RecipeGeneratorModVariables.GUILabelsList.add("C:");
		}
		{
			RecipeGeneratorModVariables.GUILabelsList.add("D:");
		}
		{
			RecipeGeneratorModVariables.GUILabelsList.add("E:");
		}
		{
			RecipeGeneratorModVariables.GUILabelsList.add("F:");
		}
		{
			RecipeGeneratorModVariables.GUILabelsList.add("G:");
		}
		{
			RecipeGeneratorModVariables.GUILabelsList.add("H:");
		}
		{
			RecipeGeneratorModVariables.GUILabelsList.add("I:");
		}
		preGeneratedRecipe = "\n" + ((RecipeGeneratorModVariables.GUILabelsList.get(0) instanceof String _str9 ? _str9 : "") + "" + RecipeGeneratorModVariables.item_in_slot_0 + ",") + "\n"
				+ ((RecipeGeneratorModVariables.GUILabelsList.get(1) instanceof String _str10 ? _str10 : "") + "" + RecipeGeneratorModVariables.item_in_slot_1 + ",") + "\n"
				+ ((RecipeGeneratorModVariables.GUILabelsList.get(2) instanceof String _str11 ? _str11 : "") + "" + RecipeGeneratorModVariables.item_in_slot_2 + ",") + "\n"
				+ ((RecipeGeneratorModVariables.GUILabelsList.get(3) instanceof String _str12 ? _str12 : "") + "" + RecipeGeneratorModVariables.item_in_slot_3 + ",") + "\n"
				+ ((RecipeGeneratorModVariables.GUILabelsList.get(4) instanceof String _str13 ? _str13 : "") + "" + RecipeGeneratorModVariables.item_in_slot_4 + ",") + "\n"
				+ ((RecipeGeneratorModVariables.GUILabelsList.get(5) instanceof String _str14 ? _str14 : "") + "" + RecipeGeneratorModVariables.item_in_slot_5 + ",") + "\n"
				+ ((RecipeGeneratorModVariables.GUILabelsList.get(6) instanceof String _str15 ? _str15 : "") + "" + RecipeGeneratorModVariables.item_in_slot_6 + ",") + "\n"
				+ ((RecipeGeneratorModVariables.GUILabelsList.get(7) instanceof String _str16 ? _str16 : "") + "" + RecipeGeneratorModVariables.item_in_slot_7 + ",") + "\n"
				+ ((RecipeGeneratorModVariables.GUILabelsList.get(8) instanceof String _str17 ? _str17 : "") + "" + RecipeGeneratorModVariables.item_in_slot_8 + ",") + "\n";
		i = 0;
		while (i <= 8) {
			if (preGeneratedRecipe.contains((RecipeGeneratorModVariables.GUILabelsList.get((int) i) instanceof String _str18 ? _str18 : "") + "'minecraft:air',")) {
				preGeneratedRecipe = preGeneratedRecipe.replace((RecipeGeneratorModVariables.GUILabelsList.get((int) i) instanceof String _str19 ? _str19 : "") + "'minecraft:air',", " ");
			}
			i = i + 1;
		}
		KubeJSRecipeShape = "'" + (!(RecipeGeneratorModVariables.item_in_slot_0).equals("'minecraft:air'") ? "A" : " ") + (!(RecipeGeneratorModVariables.item_in_slot_1).equals("'minecraft:air'") ? "B" : " ")
				+ (!(RecipeGeneratorModVariables.item_in_slot_2).equals("'minecraft:air'") ? "C" : " ") + "'," + "\n" + "'" + (!(RecipeGeneratorModVariables.item_in_slot_3).equals("'minecraft:air'") ? "D" : " ")
				+ (!(RecipeGeneratorModVariables.item_in_slot_4).equals("'minecraft:air'") ? "E" : " ") + (!(RecipeGeneratorModVariables.item_in_slot_5).equals("'minecraft:air'") ? "F" : " ") + "'," + "\n" + "'"
				+ (!(RecipeGeneratorModVariables.item_in_slot_6).equals("'minecraft:air'") ? "G" : " ") + (!(RecipeGeneratorModVariables.item_in_slot_7).equals("'minecraft:air'") ? "H" : " ")
				+ (!(RecipeGeneratorModVariables.item_in_slot_8).equals("'minecraft:air'") ? "I" : " ") + "'";
		RecipeGeneratorModVariables.Generated_recipe = "ServerEvents.recipes(event => {" + "event.shaped(" + "\n" + "  Item.of(" + RecipeGeneratorModVariables.item_in_slot_9 + ", "
				+ new java.text.DecimalFormat("##").format(RecipeGeneratorModVariables.item_in_slot_9_count) + ")," + "\n" + "  [" + "\n" + KubeJSRecipeShape + "\n" + "  ]," + "\n" + "  {" + preGeneratedRecipe + "})})";
	}
}