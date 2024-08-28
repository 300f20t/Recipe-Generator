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
		String fileName = "";
		File generated = new File("");
		FileNameCreatorProcedure.execute(guistate);
		generated = new File((FMLPaths.GAMEDIR.get().toString() + "/scripts"), File.separator + (FileNameCreatorProcedure.execute(guistate) + ".zs"));
		if (!generated.exists()) {
			try {
				generated.getParentFile().mkdirs();
				generated.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			try {
				FileWriter generatedwriter = new FileWriter(generated, false);
				BufferedWriter generatedbw = new BufferedWriter(generatedwriter);
				{
					generatedbw.write("import crafttweaker.api.recipe.MirrorAxis;");
					generatedbw.newLine();
				}
				{
					generatedbw.write("import crafttweaker.api.recipe.FurnaceRecipeManager;");
					generatedbw.newLine();
				}
				{
					generatedbw.write("import crafttweaker.api.recipe.BlastFurnaceRecipeManager;");
					generatedbw.newLine();
				}
				generatedbw.close();
				generatedwriter.close();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		try {
			FileWriter generatedwriter = new FileWriter(generated, false);
			BufferedWriter generatedbw = new BufferedWriter(generatedwriter);
			{
				generatedbw.write(RecipeGeneratorModVariables.Generated_recipe);
				generatedbw.newLine();
			}
			generatedbw.close();
			generatedwriter.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
