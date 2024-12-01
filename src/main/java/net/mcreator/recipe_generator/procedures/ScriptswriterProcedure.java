package net.mcreator.recipe_generator.procedures;

import net.neoforged.fml.loading.FMLPaths;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.util.HashMap;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.io.BufferedWriter;

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
			localDir = ".zs";
		} else if ((RecipeGeneratorModVariables.selectedMethod).equals("KubeJS")) {
			localDir = "/server_scripts";
			localDir = ".js";
		}
		generated = new File((FMLPaths.GAMEDIR.get().toString() + "" + localDir), File.separator + (FileNameCreatorProcedure.execute(guistate) + "" + fileExtention));
		if (generated.exists()) {
			try {
				generated.getParentFile().mkdirs();
				generated.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		try {
			FileWriter generatedwriter = new FileWriter(generated, false);
			BufferedWriter generatedbw = new BufferedWriter(generatedwriter);
			generatedbw.write(RecipeGeneratorModVariables.Generated_recipe);
			generatedbw.newLine();
			generatedbw.close();
			generatedwriter.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
