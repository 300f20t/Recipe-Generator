package com.recipe_generator;

import com.recipe_generator.Constants.GenerationMethod;
import com.recipe_generator.platform.Services;

public class CommonClass {

    public static boolean isUIHidden = true;
    public static GenerationMethod generationMethod = GenerationMethod.CRAFTTWEAKER;

    public static void init() {

        if (Services.PLATFORM.isModLoaded("recipe_generator")) {

            Constants.LOG.info("Recipe Generator initialized, no way...");
        }
    }
}
