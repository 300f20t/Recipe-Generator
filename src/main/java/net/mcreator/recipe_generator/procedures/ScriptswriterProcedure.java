package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.client.gui.components.EditBox;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.util.HashMap;
import java.util.Calendar;

public class ScriptswriterProcedure {
	public static void execute(LevelAccessor world, HashMap guistate) {
		if (guistate == null)
			return;
		String localDir = "";
		String fileExtention = "";
		String fileName = "";
		if ((RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod).equals("CraftTweaker")) {
			localDir = "/scripts";
			fileExtention = ".zs";
		} else if ((RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod).equals("KubeJS")) {
			localDir = "/kubejs/server_scripts";
			fileExtention = ".js";
		}
		fileName = guistate.containsKey("text:file_name") ? ((EditBox) guistate.get("text:file_name")).getValue() : "";
		if ((fileName).isEmpty()) {
			fileName = "generated " + new java.text.SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(Calendar.getInstance().getTime());
		}
	}
}
