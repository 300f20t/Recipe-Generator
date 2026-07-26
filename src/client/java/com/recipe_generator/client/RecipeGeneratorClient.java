package com.recipe_generator.client;

import net.fabricmc.api.ClientModInitializer;

public class RecipeGeneratorClient implements ClientModInitializer {

    public enum GenerationMethod {
        CRAFTTWEAKER("scripts", ".zs"),
        KUBEJS("", "");

        private final String folder;
        private final String extension;

        GenerationMethod(String folder, String extension) {
            this.folder = folder;
            this.extension = extension;
        }

        public String getFolder() {
            return folder;
        }

        public String getExtension() {
            return extension;
        }
    }

    public static boolean isUIHidden = true;
    public static GenerationMethod genMethod = GenerationMethod.CRAFTTWEAKER;

    @Override
    public void onInitializeClient() {
        RecipeGeneratorCommand.addCommand();
    }
}
