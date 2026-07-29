package com.recipe_generator;

import com.recipe_generator.client.platform.FabricPlatformHelper;

import net.fabricmc.api.ModInitializer;

public class RecipeGenerator implements ModInitializer {
    
    @Override
    public void onInitialize() {

        Constants.LOG.info("Recipe Generator initialized for " + new FabricPlatformHelper().getPlatformName());
        CommonClass.init();
    }
}
