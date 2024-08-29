package net.mcreator.recipe_generator.procedures;

import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.client.gui.components.EditBox;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.util.HashMap;

import java.io.File;

public class FileNameCreatorProcedure {
	public static String execute(HashMap guistate) {
		if (guistate == null)
			return "";
		File oldGenerated = new File("");
		String fileName = "";
		if ((guistate.containsKey("text:file_name") ? ((EditBox) guistate.get("text:file_name")).getValue() : "").equals("")) {
			oldGenerated = new File((FMLPaths.GAMEDIR.get().toString() + "/scripts"), File.separator + ("generated " + RecipeGeneratorModVariables.filesWithGeneratedNameCount + ".zs"));
			while (oldGenerated.exists()) {
				RecipeGeneratorModVariables.filesWithGeneratedNameCount = RecipeGeneratorModVariables.filesWithGeneratedNameCount + 1;
				oldGenerated = new File((FMLPaths.GAMEDIR.get().toString() + "/scripts"), File.separator + ("generated " + RecipeGeneratorModVariables.filesWithGeneratedNameCount + ".zs"));
			}
			fileName = "generated " + new java.text.DecimalFormat("").format(RecipeGeneratorModVariables.filesWithGeneratedNameCount);
		} else {
			fileName = guistate.containsKey("text:file_name") ? ((EditBox) guistate.get("text:file_name")).getValue() : "";
		}
		return fileName;
	}
}
