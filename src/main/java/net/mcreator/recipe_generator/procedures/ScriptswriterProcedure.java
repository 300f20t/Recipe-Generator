package net.mcreator.recipe_generator.procedures;

import net.minecraft.client.gui.components.EditBox;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.util.HashMap;
import java.util.Calendar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ScriptswriterProcedure {
	public static void execute(HashMap guistate) {
		if (guistate == null)
			return;

		File generated = new File("");
		String localDir = "";
		String fileExtention = "";
		String fileName = "";

		if ((RecipeGeneratorModVariables.selectedMethod).equals("CraftTweaker")) {
			localDir = "/scripts";
			fileExtention = ".zs";
		} else if ((RecipeGeneratorModVariables.selectedMethod).equals("KubeJS")) {
			localDir = "/kubejs/server_scripts";
			fileExtention = ".js";
		}

		fileName = guistate.containsKey("text:file_name") ? ((EditBox) guistate.get("text:file_name")).getValue() : "";
		if ((fileName).isEmpty()) {
			fileName = "generated_" + new java.text.SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(Calendar.getInstance().getTime());
		}

		generated = new File(System.getProperty("user.dir") + localDir + "/" + fileName + fileExtention);

		File parentDir = generated.getParentFile();
		if (!parentDir.exists()) {
			parentDir.mkdirs();
		}

		String fileContent = RecipeGeneratorModVariables.Generated_recipe;

		try (FileWriter writer = new FileWriter(generated)) {
			writer.write(fileContent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
