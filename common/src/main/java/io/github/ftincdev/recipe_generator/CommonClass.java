package io.github.ftincdev.recipe_generator;

import io.github.ftincdev.recipe_generator.Constants.GenerationMethod;

import io.github.ftincdev.recipe_generator.platform.Services;

public class CommonClass {

    public static boolean isUIHidden = true;
    public static GenerationMethod generationMethod = GenerationMethod.CRAFTTWEAKER;

    public static void init() {

        if (Services.PLATFORM.isModLoaded("recipe_generator")) {

            Constants.LOG.info("Recipe Generator initialized, no way...");
        }
    }
}
