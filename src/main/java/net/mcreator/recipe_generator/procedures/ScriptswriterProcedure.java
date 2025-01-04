package net.mcreator.recipe_generator.procedures;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.util.HashMap;

import java.io.File;

public class ScriptswriterProcedure {
	public static void execute(HashMap guistate) {
		if (guistate == null)
			return;
		File generated = new File("");
		String localDir = "";
		String fileExtention = "";
		FileNameCreatorProcedure.execute(guistate);
		if ((RecipeGeneratorModVariables.selectedMethod).equals("CraftTweaker")) {
			localDir = "/scripts";
			fileExtention = ".zs";
		} else if ((RecipeGeneratorModVariables.selectedMethod).equals("KubeJS")) {
			localDir = "/kubejs/server_scripts";
			fileExtention = ".js";
		}
	}
}
