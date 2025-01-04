package net.mcreator.recipe_generator.procedures;

public class CheckKubeJSProcedure {
	public static boolean execute() {
		if (net.fabricmc.loader.api.FabricLoader.getInstance().isModLoaded("kubejs")) {
			return false;
		}
		return true;
	}
}
