package net.mcreator.recipe_generator.procedures;

import java.util.HashMap;

import java.io.File;

public class ScriptswriterProcedure {
	public static void execute(HashMap guistate) {
		if (guistate == null)
			return;
		File generated = new File("");
		FileNameCreatorProcedure.execute(guistate);
	}
}
