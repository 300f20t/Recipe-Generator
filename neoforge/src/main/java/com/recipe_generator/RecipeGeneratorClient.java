package com.recipe_generator;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class RecipeGeneratorClient {

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

    public static boolean isUIHidden = true;
    public static GenerationMethod generationMethod = GenerationMethod.CRAFTTWEAKER;

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
        });
    }
}
