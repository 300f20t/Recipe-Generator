package net.mcreator.recipe_generator.configuration;

import net.neoforged.neoforge.common.ModConfigSpec;

public class RecipeGeneratorConfigConfiguration {
	public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
	public static final ModConfigSpec SPEC;
	public static final ModConfigSpec.ConfigValue<Double> FILEAUTOGENERATEDCOUNT;
	public static final ModConfigSpec.ConfigValue<Double> RECIPEAUTOGENERATEDCOUNT;
	static {
		BUILDER.push("AutoNameGeneration");
		FILEAUTOGENERATEDCOUNT = BUILDER.define("FileAutoGeneratedCount", (double) 0);
		RECIPEAUTOGENERATEDCOUNT = BUILDER.define("RecipeAutoGeneratedCount", (double) 0);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}
