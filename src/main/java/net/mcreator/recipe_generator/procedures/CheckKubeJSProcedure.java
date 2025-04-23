package net.mcreator.recipe_generator.procedures;

import net.minecraftforge.fml.ModList;

public class CheckKubeJSProcedure {
	public static boolean execute() {
		if (ModList.get().isLoaded("kubejs")) {
			return false;
		}
		return true;
	}
}
