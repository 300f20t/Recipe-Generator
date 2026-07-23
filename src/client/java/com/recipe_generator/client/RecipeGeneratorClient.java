package com.recipe_generator.client;

import net.fabricmc.api.ClientModInitializer;

public class RecipeGeneratorClient implements ClientModInitializer {

    public enum GenerationMethod {
        CRAFTTWEAKER(),
        KUBEJS()
    }

    public static boolean isUIHidden = true;
    public static GenerationMethod genMethod = GenerationMethod.CRAFTTWEAKER;

    @Override
    public void onInitializeClient() {
        RecipeGeneratorCommand.addCommand();
    }
}
