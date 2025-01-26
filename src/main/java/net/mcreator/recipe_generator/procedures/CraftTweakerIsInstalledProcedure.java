package net.mcreator.recipe_generator.procedures;

import net.neoforged.fml.ModList;

public class CraftTweakerIsInstalledProcedure {
	public static boolean execute() {
		if (ModList.get().isLoaded("crafttweaker")) {
			return false;
		}
		return true;
	}
}
