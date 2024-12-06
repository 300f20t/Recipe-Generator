package net.mcreator.justctgui.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.components.EditBox;

import net.mcreator.justctgui.network.JustCtguiModVariables;

import java.util.HashMap;

public class FurnaceGenerateRecipesProcedure {
	public static void execute(LevelAccessor world, HashMap guistate) {
		if (guistate == null)
			return;
		JustCtguiModVariables.Pre_generated_recipe = JustCtguiModVariables.item_in_1_slot_of_furnace + ", " + JustCtguiModVariables.item_in_0_slot_of_furnace + ", "
				+ ((guistate.containsKey("text:XP") ? ((EditBox) guistate.get("text:XP")).getValue() : "").isEmpty() ? "1" : (guistate.containsKey("text:XP") ? ((EditBox) guistate.get("text:XP")).getValue() : "")) + ", "
				+ ((guistate.containsKey("text:burn_time") ? ((EditBox) guistate.get("text:burn_time")).getValue() : "").isEmpty() ? "200" : (guistate.containsKey("text:burn_time") ? ((EditBox) guistate.get("text:burn_time")).getValue() : ""))
				+ ");";
		JustCtguiModVariables.Generated_recipe = "import crafttweaker.api.recipe.FurnaceRecipeManager;" + "furnace.addRecipe(\""
				+ ((guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : "").isEmpty()
						? "no_name"
						: (guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : ""))
				+ "\", " + JustCtguiModVariables.Pre_generated_recipe;
		if (!world.isClientSide() && world.getServer() != null)
			world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(JustCtguiModVariables.Generated_recipe), false);
	}
}
