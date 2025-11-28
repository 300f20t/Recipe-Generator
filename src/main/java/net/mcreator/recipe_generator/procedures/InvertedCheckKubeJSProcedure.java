package net.mcreator.recipe_generator.procedures;

import net.minecraftforge.fml.ModList;

public class InvertedCheckKubeJSProcedure {
	public static boolean execute() {
		if (ModList.get().isLoaded("kubejs")) {
			return true;
		}
		return false;
	}
}