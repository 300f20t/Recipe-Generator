package com.recipe_generator.client;

import net.fabricmc.api.ClientModInitializer;

public class RecipeGeneratorClient implements ClientModInitializer {

	public static boolean isUIHidden = true;

	@Override
	public void onInitializeClient() {
		RecipeGeneratorCommand.addCommand();
	}
}
