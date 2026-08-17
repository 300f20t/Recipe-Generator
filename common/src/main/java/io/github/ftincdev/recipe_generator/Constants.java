package io.github.ftincdev.recipe_generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {

	public enum GenerationMethod {
        CRAFTTWEAKER("scripts", ".zs"),
        KUBEJS("kubejs/server_scripts", ".js");

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

	public static final String MOD_ID = "recipe_generator";
	public static final String MOD_NAME = "Recipe Generator";
	public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
}
