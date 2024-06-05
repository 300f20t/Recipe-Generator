package net.mcreator.justctgui.procedures;

import net.minecraft.client.gui.components.EditBox;

import net.mcreator.justctgui.network.JustCtguiModVariables;

import java.util.HashMap;

public class GenerateFurnaceReciepsProcedure {
	public static void execute(HashMap guistate) {
		if (guistate == null)
			return;
		JustCtguiModVariables.Pre_generated_recipe = JustCtguiModVariables.item_in_slot_0_crafting_table + ", " + JustCtguiModVariables.item_in_slot_0_crafting_table;
		if ((guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : "").isEmpty()) {
			JustCtguiModVariables.Generated_recipe = "import crafttweaker.api.recipe.FurnaceRecipeManager;" + "furnace.addRecipe(\"" + "no_name" + new java.text.DecimalFormat("##.##").format(JustCtguiModVariables.generated_count) + "\", "
					+ JustCtguiModVariables.Pre_generated_recipe;
		} else {
			JustCtguiModVariables.Generated_recipe = "import crafttweaker.api.recipe.FurnaceRecipeManager;" + "furnace.addRecipe(\"" + (guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : "") + "\", "
					+ JustCtguiModVariables.Pre_generated_recipe;
		}
	}
}
