package com.recipe_generator.client;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;

import net.minecraft.network.chat.Component;

public class RecipeGeneratorCommand {
	public static void addCommand() {
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			dispatcher.register(ClientCommandManager.literal("rgui").executes(context -> {
				if (RecipeGeneratorClient.isUIHidden) {
					context.getSource().sendFeedback(Component.literal("The Recipe Generator GUI is shown"));
					RecipeGeneratorClient.isUIHidden = false;
				}
				else {
					context.getSource().sendFeedback(Component.literal("The Recipe Generator GUI is hidden"));
					RecipeGeneratorClient.isUIHidden = true;
				}
				return 1;
			}));
		});
	}
}
