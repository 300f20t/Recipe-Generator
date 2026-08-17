package io.github.ftincdev.recipe_generator;

import io.github.ftincdev.recipe_generator.client.platform.NeoForgePlatformHelper;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class RecipeGenerator {

    public RecipeGenerator(IEventBus eventBus) {

        Constants.LOG.info("Recipe Generator initialized for " + new NeoForgePlatformHelper().getPlatformName());
        CommonClass.init();
    }
}
