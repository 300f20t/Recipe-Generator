/*
 *	MCreator note:
 *
 *	If you lock base mod element files, you can edit this file and the proxy files
 *	and they won't get overwritten. If you change your mod package or modid, you
 *	need to apply these changes to this file MANUALLY.
 *
 *
 *	If you do not lock base mod element files in Workspace settings, this file
 *	will be REGENERATED on each build.
 *
 */
package net.mcreator.recipe_generator;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.mcreator.recipe_generator.init.RecipeGeneratorModProcedures;
import net.mcreator.recipe_generator.init.RecipeGeneratorModMenus;
import net.mcreator.recipe_generator.init.RecipeGeneratorModCommands;

import net.fabricmc.api.ModInitializer;

public class RecipeGeneratorMod implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "recipe_generator";

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing RecipeGeneratorMod");

		RecipeGeneratorModProcedures.load();
		RecipeGeneratorModCommands.load();

		RecipeGeneratorModMenus.load();

	}
}
