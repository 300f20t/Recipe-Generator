package com.recipe_generator.client;

import net.fabricmc.api.ClientModInitializer;

public class RecipeGeneratorClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        RecipeGeneratorCommand.addCommand();
    }
}
