package com.recipe_generator;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;

@EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
public class ClientCommandRegistrar {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterClientCommandsEvent event) {
        RecipeGeneratorCommand.addCommand(event);
    }
}
