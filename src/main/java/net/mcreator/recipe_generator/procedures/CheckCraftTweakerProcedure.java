package net.mcreator.recipe_generator.procedures;

import net.minecraftforge.fml.ModList;

public class CheckCraftTweakerProcedure {
	public static boolean execute() {
		if (ModList.get().isLoaded("crafttweaker")) {
			return false;
		}
		return true;
	}
}
