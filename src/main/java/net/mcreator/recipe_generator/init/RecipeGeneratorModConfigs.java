package net.mcreator.recipe_generator.init;

import net.neoforged.fml.event.lifecycle.FMLConstructModEvent;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.bus.api.SubscribeEvent;

import net.mcreator.recipe_generator.RecipeGeneratorMod;

@EventBusSubscriber(modid = RecipeGeneratorMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class RecipeGeneratorModConfigs {
	@SubscribeEvent
	public static void register(FMLConstructModEvent event) {
		event.enqueueWork(() -> {
			ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RecipeGeneratorConfigConfiguration.SPEC, "RecipeGeneratorConfig.toml");
		});
	}
}
