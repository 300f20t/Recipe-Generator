package net.mcreator.recipe_generator.procedures;

import net.neoforged.fml.loading.FMLPaths;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.util.HashMap;

import java.io.IOException;
import java.io.File;

public class ScriptswriterProcedure {
	public static void execute(HashMap guistate) {
		if (guistate == null)
			return;
		String fileName = "";
		FileNameCreatorProcedure.execute(guistate);
		RecipeGeneratorModVariables.generated = new File((FMLPaths.GAMEDIR.get().toString() + "/scripts"), File.separator + (FileNameCreatorProcedure.execute(guistate) + ".zs"));
		if (!RecipeGeneratorModVariables.generated.exists()) {
			try {
				RecipeGeneratorModVariables.generated.getParentFile().mkdirs();
				RecipeGeneratorModVariables.generated.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			{
				RecipeGeneratorModVariables.generatedbw.write("import crafttweaker.api.recipe.MirrorAxis;");
				RecipeGeneratorModVariables.generatedbw.newLine();
			}
			{
				RecipeGeneratorModVariables.generatedbw.write("import crafttweaker.api.recipe.FurnaceRecipeManager;");
				RecipeGeneratorModVariables.generatedbw.newLine();
			}
			{
				RecipeGeneratorModVariables.generatedbw.write("import crafttweaker.api.recipe.BlastFurnaceRecipeManager;");
				RecipeGeneratorModVariables.generatedbw.newLine();
			}
		}
		{
			RecipeGeneratorModVariables.generatedbw.write(RecipeGeneratorModVariables.Generated_recipe);
			RecipeGeneratorModVariables.generatedbw.newLine();
		}
	}
}
