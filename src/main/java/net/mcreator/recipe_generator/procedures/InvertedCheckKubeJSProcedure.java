package net.mcreator.recipe_generator.procedures;

public class InvertedCheckKubeJSProcedure {
	public static boolean execute() {
		if (net.fabricmc.loader.api.FabricLoader.getInstance().isModLoaded("kubejs")) {
			return true;
		}
		return false;
	}
}
