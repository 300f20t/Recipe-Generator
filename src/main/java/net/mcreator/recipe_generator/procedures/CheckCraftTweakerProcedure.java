package net.mcreator.recipe_generator.procedures;

public class CheckCraftTweakerProcedure {
	public static boolean execute() {
		if (net.fabricmc.loader.api.FabricLoader.getInstance().isModLoaded("crafttweaker")) {
			return false;
		}
		return true;
	}
}
