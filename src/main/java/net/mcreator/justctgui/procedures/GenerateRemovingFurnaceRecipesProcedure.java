package net.mcreator.justctgui.procedures;

import net.mcreator.justctgui.network.JustCtguiModVariables;

public class GenerateRemovingFurnaceRecipesProcedure {
	public static void execute() {
		JustCtguiModVariables.Generated_recipe = "import crafttweaker.api.recipe.FurnaceRecipeManager; " + "furnace.remove(" + JustCtguiModVariables.item_in_slot_0_crafting_table + ");";
	}
}
