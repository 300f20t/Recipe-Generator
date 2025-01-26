
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.recipe_generator.init;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class RecipeGeneratorModCommands {
	public static void load() {
		CommandRegistrationCallback.EVENT.register((dispatcher, commandBuildContext, environment) -> {
			RguiCommandCommand.register(dispatcher, commandBuildContext, environment);
		});
	}
}
